/*****************************************
 * Class: CIST 2373 Java Programming III
 * Semester: SUMMER 2020
 * Instructor: Ron Enz
 ** The Dentist Office Appointment Web Application Project
 *
 * 
 * @author William  G.  Weldy
 * @version 1.0
 *****************************************/


import Business.*;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/********************************************************************
 *	Java III - Updated Appointment
 * 	William Weldy - Spring 2020
 *        updatedAppointment.java
 ********************************************************************/
@WebServlet(urlPatterns = {"/updatedAppointment"})
public class updatedAppointment extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
            
            //Retrieving the parameters
            String month = request.getParameter("month");
            String monthDay = request.getParameter("dayOfMonth");
                int maxMonthDay = 31; //used to check the last day of each month
            String year = request.getParameter("year");
            String timeOfDay = request.getParameter("timeOfDay");
            String hourOfDay = request.getParameter("hourOfDay");
            
            String date; //this will be used when the database is ready to be updated after all the information constructing the date is validated
            
        try (PrintWriter out = response.getWriter()) {
            //Retrieving the attributes and looking up the tables for information that will be needed soon
            Patient p1 = (Patient)request.getSession().getAttribute("p1");
                p1.selectDB(p1.getPatientID());
                
            Appointment apt = (Appointment)request.getSession().getAttribute("apt");
                apt.patientSelect(p1.getPatientID());
            
            //This switch case block sets the maxMonthDay integer to whichever is accurate for the calender month
            switch(month) {
                case "January": maxMonthDay = 31;
                    break;
                case "February":
                    if(year=="2020") {maxMonthDay = 29;}
                                else {maxMonthDay = 28;}
                    break;
                case "March": maxMonthDay = 31;
                    break;
                case "April": maxMonthDay = 30;
                    break;
                case "May": maxMonthDay = 31;
                    break;
                case "June": maxMonthDay = 30;
                    break;
                case "July": maxMonthDay = 31;
                    break;
                case "August": maxMonthDay = 31;
                    break;
                case "September": maxMonthDay = 30;
                    break;
                case "October": maxMonthDay = 31;
                    break;
                case "November": maxMonthDay = 30;
                    break;
                case "December": maxMonthDay = 31;
                    break;
                default:
                    break;
                    
                    //12 AM is Midnight, and 12 PM is Noon
            //these if-else statements are to check various variables to make sure everything is correct and nothing is unrealistic
            } if(Integer.parseInt(monthDay) > maxMonthDay) {
                monthDay = String.valueOf(maxMonthDay);
            }
            if(Integer.parseInt(hourOfDay) > 12) {
                int newHour = Integer.parseInt(hourOfDay) - 12;
                timeOfDay = "pm";
                    if(newHour > 4) hourOfDay="4";
                  else hourOfDay = String.valueOf(newHour);
            }
            else if(Integer.parseInt(hourOfDay)==12) {
                timeOfDay = "pm";
            }
            else if(Integer.parseInt(hourOfDay)==5) {
                hourOfDay = "4";
                timeOfDay = "pm";
            }
            else if(Integer.parseInt(hourOfDay) > 16) {
                hourOfDay = "4";
                timeOfDay = "pm";
            }
            else if(Integer.parseInt(hourOfDay) < 8) {
                hourOfDay = "8";
                timeOfDay = "am";
            }
            else if(Integer.parseInt(hourOfDay) >= 8 || Integer.parseInt(hourOfDay) < 12) {
                timeOfDay = "am";
            }
            
            //all the date information is finally put together and used to update the Appointments table
            date = month + " " + monthDay + ", " + year + ", " + hourOfDay + timeOfDay;
            apt.updateDB(date,p1.getPatientID(),apt.getDentistID(),apt.getProcCode());
            
            HttpSession ses1 = request.getSession();
            ses1.setAttribute("p1", p1);
            
            //and finally, the page is generated
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Information Updated</title>");            
            out.println("</head>");
            out.println("<body>");
            
                out.println("<h2>Your information has been updated!</h2> <a href=\"Patient_main.jsp\"> return to the main page.");
  
            out.println("</body>");
            out.println("</html>");
        } //End of try-block
    } //end of processRequest method

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() { return "this servlet will take all the information from Patient_createAppointment.jsp and run the code necessary to make changes to the Appointments table."; }
}
