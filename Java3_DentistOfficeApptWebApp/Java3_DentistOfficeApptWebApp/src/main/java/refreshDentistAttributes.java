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
 *	Java III - Refresh Dentist Attributes Servlet
 * 	William Weldy - Spring 2020
 *      refreshDentistAttributes.java
 ********************************************************************/
@WebServlet(urlPatterns = {"/refreshDentistAttributes"})
public class refreshDentistAttributes extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
            //Retrieving the parameters
            String fname = request.getParameter("fNameTxt");
            String lname = request.getParameter("lNameTxt");
            String pw = request.getParameter("pwTxt");
            String email = request.getParameter("emTxt");
            String office = request.getParameter("officeTxt");
            
        try (PrintWriter out = response.getWriter()) {
            //the dentist attribute is retrieved and then their information is updated
            Dentist d1 = (Dentist)request.getSession().getAttribute("d1");
                d1.updateDB(d1.getDentistID(), pw, fname, lname, email, office);
                
            HttpSession session = request.getSession();
            session.setAttribute("d1", d1);
            
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Information Updated</title>");            
            out.println("</head>");
            out.println("<body>");
                    
                out.println("<h2>Your information has been updated!</h2> <a href=\"Dentist_main.jsp\"> return to the main page.");
                        
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
    public String getServletInfo() { return "this servlet updates the database for the dentist's attributes and tells the dentist user their information has been updated."; }
}
