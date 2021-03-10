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
 *	Java III - Refresh Patient Attributes
 * 	William Weldy - Spring 2020
 *      refreshPatientAttributes.java
 ********************************************************************/
@WebServlet(urlPatterns = {"/refreshPatientAttributes"})
public class refreshPatientAttributes extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
            //Retrieving the parameters
            String fname = request.getParameter("fNameTxt");
            String lname = request.getParameter("lNameTxt");
            String pw = request.getParameter("pwTxt");
            String addr = request.getParameter("addrTxt");
            String email = request.getParameter("emTxt");
            String insCo = request.getParameter("insCoTxt");
            
        try (PrintWriter out = response.getWriter()) {
            //the patient attribute is retrieved and then their information is updated
            Patient p1 = (Patient)request.getSession().getAttribute("p1");
                p1.updateDB(p1.getPatientID(), pw, fname, lname, addr, email, insCo);
            
            HttpSession session = request.getSession();
            session.setAttribute("p1", p1);
            
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
    public String getServletInfo() { return "this servlet updates the database for the patient's attributes and tells the patient user their information has been updated."; }
}
