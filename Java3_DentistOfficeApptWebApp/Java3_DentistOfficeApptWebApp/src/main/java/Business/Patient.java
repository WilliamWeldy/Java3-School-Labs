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
 *	Java III - Patient Class
 * 	William Weldy - Spring 2020
 *           Patient.java
 ********************************************************************/
public class Patient {
    final private String fileLocation = "jdbc:ucanaccess://C:/Users/DefaultTheMighty/Documents/NetBeansProjects/Java3_DentistOfficeApptWebApp/Java3_DentistOfficeApptWebApp/src/main/DentistOfficeMDB.mdb;COLUMNORDER=DISPLAY";
    private String sql;
    
    private String patID;
    private String passwd;
    private String firstName;
    private String lastName;
    private String addr;
    private String email;
    private String insCo;
    
    
    //Constructors
    public Patient() {
        patID = "";
        passwd = "";
        firstName = "";
        lastName = "";
        addr = "";
        email = "";
        insCo = "";
    }
    
    public Patient(String patientID, String patientPassword, String fname, String lname, String StreetAddress, String EmailAddress, String insuranceCompany) {
        patID = patientID;
        passwd = patientPassword;
        firstName = fname;
        lastName = lname;
        addr = StreetAddress;
        email = EmailAddress;
        insCo = insuranceCompany;
    }
    
    //Setters and Getters
    public String getPatientID() { return patID;}
    public void setPatientID(String patID) { this.patID = patID; }
    
    public String getPassword() {return passwd;}
    public void setPassword(String pw) { passwd = pw; }
    
    public String getFirstName() {return firstName;}
    public void setFirstName(String fn) { firstName = fn; }
    
    public String getLastName() {return lastName;}
    public void setLastName(String ln) { lastName = ln; }
    
    public String getAddress() { return addr; }
    public void setAddress(String address) { addr = address; }
    
    public String getEmail() { return email; }
    public void setEmail(String em) { email = em; }
    
    public String getInsCo() { return insCo; }
    public void setInsCo(String com) { insCo = com; }
    
    
    /********************************************************************
     *	Java III - Patient Display Properties
     * 	William Weldy - Spring 2020
     *  Display the properties of the Patient Class.
     ********************************************************************/
    public void display() {
        System.out.println("*************************");
        
        System.out.println("* Patient ID: " + patID);
        System.out.println("Patient Password: " + passwd);
        System.out.println("Patient First Name: " + firstName);
        System.out.println("Patient Last Name: " + lastName);
        System.out.println("Street Address: " + addr);
        System.out.println("E-Mail Address: " + email);
        System.out.println("Insurance Company: " + insCo);
        
        System.out.println("*************************\n");
    } public void display(String pid) { //Used to populate values before displaying without having to call selectDB()
        selectDB(pid);
        display();
    }
    
    /********************************************************************
     *	Java III - Procedure Display Properties
     * 	William Weldy - Spring 2020
     *  SQL SELECT statement looking up patients by their patient ID against the Patients Table.
     ********************************************************************/
    public void selectDB(String patientID) { sql = "SELECT * FROM Patients WHERE patId = '" + patientID + "'";
    try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver"); //Loading the Ucanaccess driver
            Connection connection = DriverManager.getConnection(fileLocation); //connecting the driver to the DB file
            
            Statement st = connection.createStatement(); //Creating statement object
            ResultSet rs = st.executeQuery(sql); //Executing the SQL statement

            while (rs.next()) { //All the DB information is retrieved & set to the appropriate values
                setPatientID(rs.getString(1));
                setPassword(rs.getString(2));
                setFirstName(rs.getString(3));
                setLastName(rs.getString(4));
                setAddress(rs.getString(5));
                setEmail(rs.getString(6));
                setInsCo(rs.getString(7));
            } 
            connection.close(); //Database connection closed
        } catch (Exception e) { System.out.println(e); } //If caught, an exception statement is printed
    }

    /********************************************************************
     *	Java III - Procedure Display Properties
     * 	William Weldy - Spring 2020
     *  SQL UPDATE statement that changes a row inside the Patients table.
     ********************************************************************/
    public void updateDB(String patientId, String patientPassword, String patientFirstName, String patientLastName, String streetAddress, String emailAddress, String insuranceComapny) {
        sql = "UPDATE Patients SET passwd = '" + patientPassword + "', firstName = '" + patientFirstName + "', lastName = '" + patientLastName + "', addr = '" + streetAddress + "', email = '" + emailAddress + "', insCo = '" + insuranceComapny + "' WHERE patId = '" + patientId + "'";
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver"); //Loading the Ucanaccess driver
            Connection connection = DriverManager.getConnection(fileLocation); //connecting the driver to the DB file
            
            Statement st = connection.createStatement(); //Creating a statement object
            st.executeUpdate(sql); //Execute the SQL statement

            connection.close(); //Database connection closed
        } catch (Exception e) { System.out.println(e); } //If caught, an exception statement is printed
    }
}
