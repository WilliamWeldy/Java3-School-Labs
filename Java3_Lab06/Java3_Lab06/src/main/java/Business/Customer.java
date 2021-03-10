/*****************************************
 * Class: CIST 2373 Java Programming III
 * Semester: SUMMER 2020
 * Instructor: Ron Enz
 * Lab #06
 *
 * @author William  G.  Weldy
 * @version 1.0
 *****************************************/
package Business;

import java.sql.*;

public class Customer {
    private String custId;
    private String custPassword;
    private String custFirstName;
    private String custLastName;
    private String custAddress;
    private String custEmail;
    public AccountList aList = new AccountList();
    private String sql;
    
    public Customer() {
        custId = "";
        custPassword = "";
        custFirstName = "";
        custLastName = "";
        custAddress = "";
        custEmail = "";
    }
    public Customer(String ID, String Password, String FirstName, String LastName, String Address, String Email) {
        custId = ID;
        custPassword = Password;
        custFirstName = FirstName;
        custLastName = LastName;
        custAddress = Address;
        custEmail = Email;
    }
    
    public String getCustId() { return custId;}
    public void setCustId(String custId) { this.custId = custId; }
    
    public String getCustPassword() { return custPassword; }
    public void setCustPassword(String custPassword) { this.custPassword = custPassword; }
    
    public String getCustFirstName() { return custFirstName; }
    public void setCustFirstName(String custFirstName) { this.custFirstName = custFirstName; }
    
    public String getCustLastName() { return custLastName; }
    public void setCustLastName(String custLastName) { this.custLastName = custLastName; }
    
    public String getCustAddress() { return custAddress; }
    public void setCustAddress(String custAddress) { this.custAddress = custAddress; }
    
    public String getCustEmail() { return custEmail; }
    public void setCustEmail(String custEmail) { this.custEmail = custEmail; }
    
    public void display() {
        //Print stuff out to display
        System.out.println("Customer ID: " + custId);
        System.out.println("Password: " + custPassword);
        System.out.println("First Name: " + custFirstName);
        System.out.println("Last Name: " + custLastName);
        System.out.println("Address: " + custAddress);
        System.out.println("E-Mail: " + custEmail);
        aList.displayList();
    } public void display(String cid) { //Used to populate values before displaying without having to call selectDB()
        selectDB(cid);
        display();
    }
    
    public void selectDB(String custID) { sql = "SELECT CustPassword FROM Customers WHERE CustID = '" + custID + "'";
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver"); //Loading the Ucanaccess driver
            Connection connection = DriverManager.getConnection("jdbc:ucanaccess://C:/Users/DefaultTheMighty/Documents/NetBeansProjects/Java3_Lab06/Java3_Lab06/src/main/ChattBankMDB.mdb"); //connecting driver to the DB
            
            Statement st = connection.createStatement(); //Creating statement object
            ResultSet rs = st.executeQuery(sql); //Executing the SQL statement

            while (rs.next()) { //All the DB information is retrieved & set to the appropriate values
                setCustId(rs.getString(1));
                setCustPassword(rs.getString(2));
                setCustFirstName(rs.getString(3));
                setCustLastName(rs.getString(4));
                setCustAddress(rs.getString(5));
                setCustEmail(rs.getString(6));
            } 
            connection.close(); //Database connection closed
        } catch (Exception e) { System.out.println(e); } //If caught, an exception statement is printed
        getAccounts();
    }
    
    public void getAccounts() { sql = "Select acctNo from Accounts where Cid = " + custId + "";
    try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver"); //Loading the Ucanaccess driver
            Connection connection = DriverManager.getConnection("jdbc:ucanaccess://C:/Users/DefaultTheMighty/Documents/NetBeansProjects/Java3_Lab06/Java3_Lab06/src/main/ChattBankMDB.mdb"); //connecting driver to the DB
            
            Statement st = connection.createStatement(); //Creating statement object
            ResultSet rs = st.executeQuery(sql); //Executing the SQL statement

            String an;
            Account a1;
            while (rs.next()) { //All the DB information is retrieved & set to the appropriate values
                an = rs.getString(1);
                a1 = new Account();
                a1.selectDB(an);
                aList.addAccount(a1);
            } 
            connection.close(); //Database connection closed
        } catch (Exception e) { System.out.println(e); } //If caught, an exception statement is printed
    }
    
    public void insertDB(String cid, String pw, String firstName, String lastName, String Address, String Email) {
        sql = "INSERT INTO Customers " +
                "VALUES ('" + cid + "', '" + pw + "', '" + firstName + "', '" + lastName + "', '" + Address + "', '" + Email + "' " + ")";
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver"); //Loading the Ucanaccess driver
            Connection connection = DriverManager.getConnection("jdbc:ucanaccess://C:/Users/DefaultTheMighty/Documents/NetBeansProjects/Java3_Lab06/Java3_Lab06/src/main/ChattBankMDB.mdb"); //connecting driver to the DB
            
            Statement st = connection.createStatement(); //Creating a statement object
            st.executeUpdate(sql); //Execute the SQL Statement

            connection.close(); //Database connection closed
        } catch (Exception e) { System.out.println(e); } //If caught, an exception statement is printed
    }

    public void deleteDB() { sql = "DELETE FROM Customers WHERE CustID = " + getCustId();
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver"); //Loading the Ucanaccess driver
            Connection connection = DriverManager.getConnection("jdbc:ucanaccess://C:/Users/DefaultTheMighty/Documents/NetBeansProjects/Java3_Lab06/Java3_Lab06/src/main/ChattBankMDB.mdb"); //connecting driver to the DB
            
            Statement st = connection.createStatement(); //Creating statement object
            st.executeUpdate(sql); //Execute the SQL statement

            connection.close(); //Database connection closed
        } catch (Exception e) { System.out.println(e); } //If caught, an exception statement is printed
    }
}
