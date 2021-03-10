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
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/********************************************************************
 *	Java III - New Appointment Servlet
 * 	William Weldy - Spring 2020
 *      newAppointmentServlet.java
 ********************************************************************/
@WebServlet(urlPatterns = {"/newAppointment"})
public class newAppointmentServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        try (PrintWriter out = response.getWriter()) {
            RequestDispatcher rd;
            
            //Retrieving the patient attribute, and then looking up the patient from the appointments table
            Patient p1 = (Patient)request.getSession().getAttribute("p1");
            Appointment apt = new Appointment();
                apt.patientSelect(p1.getPatientID());
            
            HttpSession session = request.getSession();
            session.setAttribute("p1", p1);
            
            //This block will direct the patient user to the jsp page, but if they have an appointment it will just pull up the Patient_viewAppointment.jsp page
            if(apt.getApptDateTime().equals("(Unscheduled)")) { //No appointment for the user
                HttpSession ses2 = request.getSession();
                ses2.setAttribute("apt", apt);
                rd = request.getRequestDispatcher("newAppointment.jsp");
            }
            else { //The user already has one and gets redirected to the view/change appointments page
                rd = request.getRequestDispatcher("Patient_viewAppointment.jsp");
            }
            rd.forward(request,response);
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
    public String getServletInfo() { return "this servlet is for specifically determining whether to prompt the user to make a new appointment or redirect to the view/change appointments jsp page."; }
}
