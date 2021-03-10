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


/********************************************************************
 *	Java III - View Appointments
 * 	William Weldy - Spring 2020
 *         viewAppointments.java
 ********************************************************************/
@WebServlet(urlPatterns = {"/viewAppointments"})
public class viewAppointments extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        try (PrintWriter out = response.getWriter()) {
            //Retrieving the dentist attribute and looking up the tables for information that will be needed to generate the jsp table of appointments
            Dentist d1 = (Dentist)request.getSession().getAttribute("d1");
                d1.selectDB(d1.getDentistID());
            Appointment apt = new Appointment();
            Procedure proc = new Procedure();
            Patient p1 = new Patient();
            
            //the page starts generating
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Your Appointments</title>");            
            out.println("</head>");
            out.println("<body>");
            
            out.println("<form>");
            out.println("<h3>Your list of appointments</h3><br>");
            out.println("<table border=\"2\">");
            
            d1.getAppointments(); //this method
            
                //this is the start of the table that will display all the appointments the dentist has
                out.println("<tr>");
                out.println("<td><center><b> Date and Time </b></center></td>");
                out.println("<td><center><b> Procedure </b></center></td>");
                out.println("<td><center><b> Patient's Name </b></center></td>");
                out.println("</tr>");
                if(d1.appointments.isEmpty()) { //if there's no one scheduled for this dentist, it generates only 1 row with hyphens to show that there's no one the dentist will be seeing right now
                    out.println("<td><b><center> - </center></b></td>");
                    out.println("<td><b><center> - </center></b></td>");
                    out.println("<td><b><center> - </center></b></td>");
                } else { //otherwise, the table will generate everyone the dentist will be seeing, including when the appointment is, the procedure, and the patient's full name
                    for(int i=0 ; i < d1.appointments.size() ; i++) {
                        out.println("<tr>");
                    
                        //the information for each appointment is being collected for the table
                        apt.dentistSelect(d1.getDentistID());
                        apt.selectDB(d1.appointments.get(i));
                        proc.selectByCode(apt.getProcCode());
                        p1.selectDB(apt.getPatientID());
                    
                        //all the information is displayed
                        out.println("<td>" + d1.appointments.get(i) + "</td>");
                        out.println("<td>" + proc.getProcName() + "</td>");
                        out.println("<td>" + p1.getFirstName() + " " + p1.getLastName() + "</td>");
                        out.println("</tr>");
                    }
                }
                out.println("</table><br>");
            out.println("</form>");
            
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
    public String getServletInfo() { return "This servlet is used to draw a table of appointments with patients for the dentists."; }
}
