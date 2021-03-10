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

package Business;
import java.sql.*;


/********************************************************************
 *	Java III - Procedure Class
 * 	William Weldy - Spring 2020
 *           Procedure.java
 ********************************************************************/
public class Procedure {
    final private String fileLocation = "jdbc:ucanaccess://C:/Users/DefaultTheMighty/Documents/NetBeansProjects/Java3_DentistOfficeApptWebApp/Java3_DentistOfficeApptWebApp/src/main/DentistOfficeMDB.mdb;COLUMNORDER=DISPLAY";
    private String sql;
    
    private String code;
    private String name;
    private String desc;
    private Double cost;
   
    
    //Constructors
    public Procedure() {
        code = "null";
        name = "N/A";
        desc = "N/A";
        cost = 0.0;
    }
    public Procedure(String procCode, String procName, String procDesc, Double procCost) {
        code = procCode;
        name = procName;
        desc = procDesc;
        cost = procCost;
    }
    
    //Setters and Getters
    public String getProcCode() {return code;}
    public void setProcCode(String procCode) {code = procCode;}
    
    public String getProcName() {return name;}
    public void setProcName(String procName) {name = procName;}
    
    public String getProcDesc() {return desc;}
    public void setProcDesc(String procDesc) {desc = procDesc;}
    
    public Double getProcCost() {return cost;}
    public void setProcCost(Double cost) {this.cost = cost;}
    
    
    /********************************************************************
     *	Java III - Procedure Display Properties
     * 	William Weldy - Spring 2020
     *  Display the properties of the Procedure Class.
     ********************************************************************/
    public void display() {
        System.out.println("**********************");
        
        System.out.println("* Procedure Code: " + code);
        System.out.println("Procedure Name: " + name);
        System.out.println("Procedure Description: " + desc);
        System.out.println("Procedure Cost: $" + cost);
        
        System.out.println("**********************\n");
    } public void display(String pc) { //Used to populate values before displaying without having to call selectDB()
        selectByCode(pc);
        display();
    }
    
    /********************************************************************
     *	Java III - Procedure Display Properties
     * 	William Weldy - Spring 2020
     *  SQL SELECT statement looking up the procedure name against the Procedures Table.
     ********************************************************************/
    public void selectByName(String procedureName) { sql = "SELECT * FROM Procedures WHERE procName = '" + procedureName + "'";
    try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver"); //Loading the Ucanaccess driver
            Connection connection = DriverManager.getConnection(fileLocation); //connecting the driver to the DB file
            
            Statement st = connection.createStatement(); //Creating statement object
            ResultSet rs = st.executeQuery(sql); //Executing the SQL statement

            while (rs.next()) { //All the DB information is retrieved & set to the appropriate values
                setProcCode(rs.getString(1));
                setProcName(rs.getString(2));
                setProcDesc(rs.getString(3));
                setProcCost(rs.getDouble(4));
            } 
            connection.close(); //Database connection closed
        } catch (Exception e) { System.out.println(e); } //If caught, an exception statement is printed
    }
    
    /********************************************************************
     *	Java III - Procedure Display Properties
     * 	William Weldy - Spring 2020
     *  SQL SELECT statement looking up the procedure code against the Procedures Table.
     ********************************************************************/
    public void selectByCode(String procedureCode) { sql = "SELECT * FROM Procedures WHERE procCode = '" + procedureCode + "'";
    try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver"); //Loading the Ucanaccess driver
            Connection connection = DriverManager.getConnection(fileLocation); //connecting the driver to the DB file
            
            Statement st = connection.createStatement(); //Creating statement object
            ResultSet rs = st.executeQuery(sql); //Executing the SQL statement

            while (rs.next()) { //All the DB information is retrieved & set to the appropriate values
                setProcCode(rs.getString(1));
                setProcName(rs.getString(2));
                setProcDesc(rs.getString(3));
                setProcCost(rs.getDouble(4));
            } 
            connection.close(); //Database connection closed
        } catch (Exception e) { System.out.println(e); } //If caught, an exception statement is printed
    }
}
