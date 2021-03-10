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

@WebServlet(urlPatterns = {"/AccountLookupServlet"})
public class AccountLookupServlet extends HttpServlet {
    String htmlacct;
    String htmlid;
    String htmltype;
    String htmlbal;
    String acctNo;
    String cid;
    String type;
    String balance;
    Customer c1;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        htmlacct = request.getParameter("Account Number");
        htmlid = request.getParameter("Customer ID");
        htmltype = request.getParameter("Type");
        htmlbal = request.getParameter("Balance");
        
        try (PrintWriter out = response.getWriter()) {
            acctNo = request.getParameter("acctNo");
            Account a1 = new Account();
            a1.selectDB(htmlacct);
            
            HttpSession ses1;
            ses1 = request.getSession();
            ses1.setAttribute("a1", a1);
            
            RequestDispatcher rd = request.getRequestDispatcher("DisplayAccount.jsp");
        }
    }

    public void selectDB() { String sql = "SELECT Cid FROM Accounts WHERE AcctNo = " + htmlacct;
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver"); //Loading the Ucanaccess driver
            Connection c1 = DriverManager.getConnection("jdbc:ucanaccess://C:/Users/DefaultTheMighty/Documents/NetBeansProjects/Java3_Lab05/Java3_Lab05/src/main/ChattBankMDB.mdb"); //connecting driver to the DB
        
            Statement st = c1.createStatement(); //Creating statement object
            ResultSet rs = st.executeQuery(sql); //Executing the SQL statement

            while (rs.next()) { //All the DB information is retrieved & set to the appropriate values
                acctNo = rs.getString(1);
                cid = rs.getString(2);
                type = rs.getString(3);
                balance = rs.getString(4);
            }

            c1.close();
            System.out.println("acctNo: " + acctNo);
            System.out.println("Customer ID: " + cid);
            System.out.println("acctType: " + type);
            System.out.println("Balance: " + balance);
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
