/*****************************************
 * Class: CIST 2373 Java Programming III
 * Semester: SUMMER 2020
 * Instructor: Ron Enz
 * Lab #05
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

@WebServlet(urlPatterns = {"/LoginServletDB"})
public class LoginServletDB extends HttpServlet {
    String cid = "test"; //customer  id DB
    String pwd = "test"; //customer pwd DB
    String htmlid;
    String htmlpw;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
            htmlid = request.getParameter("Customer ID");
            htmlpw = request.getParameter("Password");
        try (PrintWriter out = response.getWriter()) {
            Customer c1 = new Customer();
            selectDB();
            c1.selectDB(htmlid);
            
            HttpSession ses1 = request.getSession();
            ses1.setAttribute("c1", c1);
            RequestDispatcher rd;
                
            if(htmlpw.equals(pwd)) { //Successful Login!
                rd = request.getRequestDispatcher("accountLookup.jsp");
            } else { //Unsuccessful login
                rd = request.getRequestDispatcher("ErrorPage.jsp");
            }
            rd.forward(request,response);
        }
    }

    public void selectDB() { String sql = "SELECT CustPassword FROM Customers WHERE CustID = " + htmlid;
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver"); //Loading the Ucanaccess driver
            Connection connection = DriverManager.getConnection("jdbc:ucanaccess://C:/Users/DefaultTheMighty/Documents/NetBeansProjects/Java3_Lab05/Java3_Lab05/src/main/ChattBankMDB.mdb"); //connecting driver to the DB
            
            Statement st = connection.createStatement(); //Creating statement object
            ResultSet rs = st.executeQuery(sql); //Executing the SQL statement

            while (rs.next()) { //All the DB information is retrieved & set to the appropriate values
                pwd = rs.getString(1);
            }

            connection.close();
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
