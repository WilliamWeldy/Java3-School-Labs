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
import java.util.ArrayList;


/********************************************************************
 *	Java III - Dentist Class
 * 	William Weldy - Spring 2020
 *           Dentist.java
 ********************************************************************/
public class Dentist {
    final private String fileLocation = "jdbc:ucanaccess://C:/Users/DefaultTheMighty/Documents/NetBeansProjects/Java3_DentistOfficeApptWebApp/Java3_DentistOfficeApptWebApp/src/main/DentistOfficeMDB.mdb;COLUMNORDER=DISPLAY";
    private String sql;
    
    private String dentID;
    private String passwd;
    private String firstName;
    private String lastName;
    private String email;
    private String office;
    public ArrayList<String> appointments = new ArrayList<>();
    
    
    //Constructors
    public Dentist() {
        dentID = "";
        passwd = "";
        firstName = "";
        lastName = "";
        email = "";
        office = "";
    }
    
    public Dentist(String dentistID, String dentPassword, String fname, String lname, String dentEmail, String office) {
        dentID = dentistID;
        passwd = dentPassword;
        firstName = fname;
        lastName = lname;
        email = dentEmail;
        this.office = office;
    }
    
    //Setters and Getters
    public String getDentistID() {return dentID;}
    public void setDentistID(String did) {dentID = did;}
    
    public String getPassword() {return passwd;}
    public void setPassword(String pw) { passwd = pw; }
    
    public String getFirstName() {return firstName;}
    public void setFirstName(String fn) { firstName = fn; }
    
    public String getLastName() {return lastName;}
    public void setLastName(String ln) { lastName = ln; }
    
    public String getEmail() { return email; }
    public void setEmail(String em) { email = em; }
    
    public String getOffice() {return office;}
    public void setOffice(String o) {office = o;}
    
    
    /********************************************************************
     *	Java III - Dentist Display Properties
     * 	William Weldy - Spring 2020
     *  Display the properties of the Dentist Class.
     ********************************************************************/
    public void display() {
        System.out.println("************************");
        
        System.out.println("* Dentist ID: " + dentID);
        System.out.println("Dentist Password: " + passwd);
        System.out.println("Dentist First Name: " + firstName);
        System.out.println("Dentist Last Name: " + lastName);
        System.out.println("E-Mail Address: " + email);
        System.out.println("Dentist Office: " + office);
        
        System.out.println("************************\n");
    } public void display(String did) { //Used to populate values before displaying without having to call selectDB()
        selectDB(did);
        display();
    }
    
    /********************************************************************
     *	Java III - Procedure Display Properties
     * 	William Weldy - Spring 2020
     *  SQL SELECT statement looking up dentists by their dentist ID against the Dentists Table.
     ********************************************************************/
    public void selectDB(String dentistID) { sql = "SELECT * FROM Dentists WHERE id = '" + dentistID + "'";
    try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver"); //Loading the Ucanaccess driver
            Connection connection = DriverManager.getConnection(fileLocation); //connecting the driver to the DB file
            
            Statement st = connection.createStatement(); //Creating statement object
            ResultSet rs = st.executeQuery(sql); //Executing the SQL statement

            while (rs.next()) { //All the DB information is retrieved & set to the appropriate values
                setDentistID(rs.getString(1));
                setPassword(rs.getString(2));
                setFirstName(rs.getString(3));
                setLastName(rs.getString(4));
                setEmail(rs.getString(5));
                setOffice(rs.getString(6));
            } 
            connection.close(); //Database connection closed
        } catch (Exception e) { System.out.println(e); } //If caught, an exception statement is printed
    }
    
    /********************************************************************
     *	Java III - Procedure Display Properties
     * 	William Weldy - Spring 2020
     *  SQL SELECT statement that retrieves multiple appointments for the dentist object against the Appointments table.
     ********************************************************************/
    public void getAppointments() { sql = "SELECT apptDateTime FROM Appointments WHERE dentId = '" + dentID + "'";
    try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver"); //Loading the Ucanaccess driver
            Connection connection = DriverManager.getConnection(fileLocation); //connecting the driver to the DB file
            
            Statement st = connection.createStatement(); //Creating statement object
            ResultSet rs = st.executeQuery(sql); //Executing the SQL statement

            Appointment apt1;
            while (rs.next()) { //All the DB information is retrieved & set to the appropriate values
                apt1 = new Appointment();
                apt1.dentistSelect(rs.getString(1));
                appointments.add(rs.getString(1));
            } 
            connection.close(); //Database connection closed
        } catch (Exception e) { System.out.println(e); } //If caught, an exception statement is printed
    }

    /********************************************************************
     *	Java III - Procedure Display Properties
     * 	William Weldy - Spring 2020
     *  SQL UPDATE statement that changes a row inside the Dentists table.
     ********************************************************************/ 
    public void updateDB(String dentistID, String dentistPassword, String dentistFirstName, String dentistLastName, String emailAddress, String dentistOffice) {
        sql = "UPDATE Dentists SET passwd = '" + dentistPassword + "', firstName = '" + dentistFirstName + "', lastName = '" + dentistLastName + "', email = '" + emailAddress + "', office = '" + dentistOffice + "' WHERE id = '" + dentistID + "'";
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver"); //Loading the Ucanaccess driver
            Connection connection = DriverManager.getConnection(fileLocation); //connecting the driver to the DB file
            
            Statement st = connection.createStatement(); //Creating a statement object
            st.executeUpdate(sql); //Execute the SQL statement

            connection.close(); //Database connection closed
        } catch (Exception e) { System.out.println(e); } //If caught, an exception statement is printed
    }
}
