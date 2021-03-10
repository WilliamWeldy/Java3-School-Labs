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
import java.sql.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/********************************************************************
 *	 Java III - Login Servlet
 * 	William Weldy - Spring 2020
 *           LoginServlet.java
 ********************************************************************/
@WebServlet(urlPatterns = {"/Login"})
public class LoginServlet extends HttpServlet {

    final private String fileLocation = "jdbc:ucanaccess://C:/Users/DefaultTheMighty/Documents/NetBeansProjects/Java3_DentistOfficeApptWebApp/Java3_DentistOfficeApptWebApp/src/main/DentistOfficeMDB.mdb;COLUMNORDER=DISPLAY";
    
    String dbid;    //user's id to be retrieved from the Database.
    String dbpw;    //user's password to be retrieved from the Database.
    
    String htmlid;  //user's id from the JSP side.
    String htmlpw;  //user's password from the JSP side.

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
            htmlid = request.getParameter("UserID");
            htmlpw = request.getParameter("Password");
            
        try (PrintWriter out = response.getWriter()) {
            RequestDispatcher rd;
            
            //For patient login
            if(htmlid.contains("A") || htmlid.contains("a")) {
                Patient p1 = new Patient();
                comparePasswords(0); //this is a SELECT method, and the arg '0' tells the method to look up the patient table in an if-else condition
                p1.selectDB(htmlid);

                //This block is for a successful login as a Patient user
                if(htmlpw.equals(dbpw)) {
                    HttpSession session = request.getSession();
                    session.setAttribute("p1", p1);
                    rd = request.getRequestDispatcher("Patient_main.jsp");
                } else { //if the Patient user enters in the wrong password
                    rd = request.getRequestDispatcher("BadLogin.jsp");
                }
                rd.forward(request,response);
            }
            
            //For Dentist login
            else if(htmlid.contains("D") || htmlid.contains("d")) {
                Dentist d1 = new Dentist();
                comparePasswords(1); //this is a SELECT method, and the arg '1' tells the method to look up the dentist table in an if-else condition
                d1.selectDB(htmlid);
                
                //This block is for a successful login as a Dentist user
                if(htmlpw.equals(dbpw)) {
                    HttpSession ses1 = request.getSession();
                    ses1.setAttribute("d1", d1);
                    rd = request.getRequestDispatcher("Dentist_main.jsp");
                }
                else { //if the Dentist user enters in the wrong password
                    rd = request.getRequestDispatcher("BadLogin.jsp");
                }
                rd.forward(request,response);
            }
            
            //If the login is not recognized as a possible valid Patient or Dentist, they're forwarded to this page.
            else {
                rd = request.getRequestDispatcher("BadUser.jsp");
                rd.forward(request,response);
            }
            
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
    public String getServletInfo() { return "this is the servlet for patients and/or dentists to log in."; }
    
    
    /********************************************************************
     *	Java III - Procedure Display Properties
     * 	William Weldy - Spring 2020
     *  SQL SELECT statement looking up either the Dentist's table or Patient's table depending on the number passed to the method.
     ********************************************************************/
    private void comparePasswords(int WhichTableToLookUp) {
        try {
            
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver"); //Loading the Ucanaccess driver
            Connection connection = DriverManager.getConnection(fileLocation); //connecting the driver to the DB file
            
            Statement st = connection.createStatement(); //Creating statement object

            if(WhichTableToLookUp==0) { //This looks up the patient table
                ResultSet rs = st.executeQuery("SELECT * FROM Patients WHERE patId = '" + htmlid + "'");
                 while (rs.next()) {
                     dbid = rs.getString(1);
                     dbpw = rs.getString(2);
                 }
            }
            else if(WhichTableToLookUp==1) { //This looks up the dentist table instead
                ResultSet rs = st.executeQuery("SELECT * FROM Dentists WHERE id = '" + htmlid + "'");
                 while (rs.next()) { 
                     dbid = rs.getString(1);
                     dbpw = rs.getString(2); 
                 }
            }
            else {} //If the parameter isn't 0 (patient) or 1 (dentist), than do nothing and just close the connection

            connection.close();
        } catch (Exception e) { System.out.println(e); } //If caught, an exception statement is printed
    }
}
