/*****************************************
 * Class: CIST 2373 Java Programming III
 * Semester: SUMMER 2020
 * Instructor: Ron Enz
 * Lab #02
 *
 * @author William  G.  Weldy
 * @version 1.0
 *****************************************/

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/LoginServletDB"})
public class LoginServletDB extends HttpServlet {
    String pwd = "test"; //customer pwd DB
    String htmlid;
    String htmlpw;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
            htmlid = request.getParameter("CustomerID");
            htmlpw = request.getParameter("Password");
            selectDB();
        try (PrintWriter out = response.getWriter()) {
            if(htmlpw.equals(pwd)) {
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>LoginServlet</title>");            
                out.println("</head>");
                out.println("<body>");
                out.println("<h1>Valid Login!</h1>");
                out.println("</body>");
                out.println("</html>");
            } else {
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>LoginServlet</title>");            
                out.println("</head>");
                out.println("<body>");
                out.println("<h1>InValid Login.</h1>");
                out.println("</body>");
                out.println("</html>");
            }
        }
    }

    public void selectDB() { String sql = "SELECT CustPassword FROM Customers WHERE CustID = " + htmlid;
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver"); //Loading the Ucanaccess driver
            Connection c1 = DriverManager.getConnection("jdbc:ucanaccess://C:/Users/DefaultTheMighty/Documents/NetBeansProjects/Java3_Lab02/Java3_Lab02/src/main/ChattBankMDB.mdb"); //connecting driver to the DB
        
            Statement st = c1.createStatement(); //Creating statement object
            ResultSet rs = st.executeQuery(sql); //Executing the SQL statement

            rs.next(); //All the DB information is retrieved & set to the appropriate values
                pwd = rs.getString(1);

            c1.close();
        } catch (Exception e) { System.out.println(e); } //If caught, an exception statement is printed
    }
    
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
    public String getServletInfo() {
        return "Short description";
    }
}
