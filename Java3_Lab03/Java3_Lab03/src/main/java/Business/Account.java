/*****************************************
 * Class: CIST 2373 Java Programming III
 * Semester: SUMMER 2020
 * Instructor: Ron Enz
 * Lab #03
 *
 * @author William  G.  Weldy
 * @version 1.0
 *****************************************/
package Business;

import java.sql.*;

public class Account {
    private String acctNo;
    private String custId;
    private String type;
    private Double balance;
    private String sql;
    
    Account() {
        acctNo = "";
        custId = "";
        type = "";
        balance = 0.0;
    }
    Account(String account, String ID, String type, Double balance) {
        acctNo = account;
        custId = ID;
        this.type = type;
        this.balance = balance;
    }
    
    public String getAcctNo() { return acctNo; }
    public void setAcctNo(String acctNo) { this.acctNo = acctNo; }
    
    public String getCustId() { return custId; }
    public void setCustId(String custId) { this.custId = custId; }
    
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    
    public Double getBalance() { return balance; }
    public void setBalance(Double balance) { this.balance = balance; }
    
    public static void main(String[] args) {
        Account a1 = new Account();
        a1.selectDB(90000);
        a1.display();
    }
    
    public void display() {
        //Print stuff out to display
        System.out.println("Account Number: " + acctNo);
        System.out.println("Customer ID: " + custId);
        System.out.println("Type: " + type);
        System.out.println("Balance: " + balance);
    } public void display(int acctNo) { //Used to populate values before displaying without having to call selectDB()
        selectDB(acctNo);
        display();
    }
    
    public void selectDB(int acctNo) { sql = "SELECT * FROM Accounts WHERE AcctNo = " + acctNo;
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver"); //Loading the Ucanaccess driver
            Connection connection = DriverManager.getConnection("jdbc:ucanaccess://C:/Users/DefaultTheMighty/Documents/NetBeansProjects/Java3_Lab03a/Java3_Lab03/src/main/ChattBankMDB.mdb"); //connecting driver to the DB
            
            Statement st = connection.createStatement(); //Creating statement object
            ResultSet rs = st.executeQuery(sql); //Executing the SQL statement

            while (rs.next()) { //All the DB information is retrieved & set to the appropriate values
                setAcctNo(rs.getString(1));
                setCustId(rs.getString(2));
                setType(rs.getString(3));
                setBalance(rs.getDouble(4));
            } 
            connection.close(); //Database connection closed
        } catch (Exception e) { System.out.println(e); } //If caught, an exception statement is printed
    }
    
    public void insertDB(String aNo, String cid, String pw, String type, Double balance) {
        sql = "INSERT INTO Accounts " +
                "VALUES ('" + aNo + "', '" + cid + "', '" + type + "', " + balance + " )";
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver"); //Loading the Ucanaccess driver
            Connection connection = DriverManager.getConnection("jdbc:ucanaccess://C:/Users/DefaultTheMighty/Documents/NetBeansProjects/Java3_Lab03a/Java3_Lab03/src/main/ChattBankMDB.mdb"); //connecting driver to the DB
            
            Statement st = connection.createStatement(); //Creating a statement object
            st.executeUpdate(sql); //Execute the SQL Statement

            connection.close(); //Database connection closed
        } catch (Exception e) { System.out.println(e); } //If caught, an exception statement is printed
    }

    public void deleteDB() { sql = "DELETE FROM Accounts WHERE CustID = " + getAcctNo();
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver"); //Loading the Ucanaccess driver
            Connection connection = DriverManager.getConnection("jdbc:ucanaccess://C:/Users/DefaultTheMighty/Documents/NetBeansProjects/Java3_Lab03a/Java3_Lab03/src/main/ChattBankMDB.mdb"); //connecting driver to the DB
            
            Statement st = connection.createStatement(); //Creating statement object
            st.executeUpdate(sql); //Execute the SQL statement

            connection.close(); //Database connection closed
        } catch (Exception e) { System.out.println(e); } //If caught, an exception statement is printed
    }
}
