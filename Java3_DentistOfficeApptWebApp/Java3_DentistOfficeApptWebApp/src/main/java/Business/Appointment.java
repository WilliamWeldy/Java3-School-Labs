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
 *	Java III - Appointment Class
 * 	William Weldy - Spring 2020
 *           Appointment.java
 ********************************************************************/
public class Appointment {
    final private String fileLocation = "jdbc:ucanaccess://C:/Users/DefaultTheMighty/Documents/NetBeansProjects/Java3_DentistOfficeApptWebApp/Java3_DentistOfficeApptWebApp/src/main/DentistOfficeMDB.mdb;COLUMNORDER=DISPLAY";
    private String sql;

    private String apptDateTime;
    private String patID;
    private String dentID;
    private String procCode;
    

    //Constructors
    public Appointment() {
        apptDateTime = "(Unscheduled)";
        patID = "";
        dentID = "";
        procCode = "";
    }

    public Appointment (String appointmentDateAndTime, String patientID, String dentistID, String procedureCode) {
        apptDateTime = appointmentDateAndTime;
        patID = patientID;
        dentID = dentistID;
        procCode = procedureCode;
    }

    //Setters and Getters
    public String getApptDateTime() {return apptDateTime;}
    public void setApptDateTime(String adt) {apptDateTime = adt;}

    public String getPatientID() {return patID;}
    public void setPatientID(String pid) {patID = pid;}

    public String getDentistID() {return dentID;}
    public void setDentistID(String did) {dentID = did;}

    public String getProcCode() {return procCode;}
    public void setProcCode(String pc) {procCode = pc;}

    /********************************************************************
     *	Java III - Appointment Display Properties
     * 	William Weldy - Spring 2020
     *  Display the properties of the Appointment Class.
     ********************************************************************/
    public void display() {
        System.out.println("***************************");

        System.out.println("* Date and Time: " + apptDateTime);
        System.out.println("Patient ID: " + patID);
        System.out.println("Dentist ID: " + dentID);
        System.out.println("Procedure Code: " + procCode);

        System.out.println("***************************\n");
    } public void display(String adt) { //Used to populate values before displaying without having to call selectDB()
        selectDB(adt);
        display();
    }

    /********************************************************************
     *	Java III - Procedure Display Properties
     * 	William Weldy - Spring 2020
     *  SQL SELECT statement looking up an appointment's date and time against the Appointments Table.
     ********************************************************************/
    public void selectDB(String timeAndDate) { sql = "SELECT * FROM Appointments WHERE apptDateTime = '" + timeAndDate + "'";
    try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver"); //Loading the Ucanaccess driver
            Connection connection = DriverManager.getConnection(fileLocation); //connecting the driver to the DB file

            Statement st = connection.createStatement(); //Creating statement object
            ResultSet rs = st.executeQuery(sql); //Executing the SQL statement

            while (rs.next()) { //All the DB information is retrieved & set to the appropriate values
                setApptDateTime(rs.getString(1));
                setPatientID(rs.getString(2));
                setDentistID(rs.getString(3));
                setProcCode(rs.getString(4));
            }
            connection.close(); //Database connection closed
        } catch (Exception e) { System.out.println(e); } //If caught, an exception statement is printed
    }

    /********************************************************************
     *	Java III - Procedure Display Properties
     * 	William Weldy - Spring 2020
     *  SQL SELECT statement looking up an appointment's patient ID against the Appointments Table.
     ********************************************************************/
    public void patientSelect(String patientID) { sql = "SELECT * FROM Appointments WHERE patId = '" + patientID + "'";
    try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver"); //Loading the Ucanaccess driver
            Connection connection = DriverManager.getConnection(fileLocation); //connecting the driver to the DB file

            Statement st = connection.createStatement(); //Creating statement object
            ResultSet rs = st.executeQuery(sql); //Executing the SQL statement

            while (rs.next()) { //All the DB information is retrieved & set to the appropriate values
                setApptDateTime(rs.getString(1));
                setPatientID(rs.getString(2));
                setDentistID(rs.getString(3));
                setProcCode(rs.getString(4));
            }
            connection.close(); //Database connection closed
        } catch (Exception e) { System.out.println(e); } //If caught, an exception statement is print
    }
    
    /********************************************************************
     *	Java III - Procedure Display Properties
     * 	William Weldy - Spring 2020
     *  SQL SELECT statement looking up an appointment's dentist ID against the Appointments Table.
     ********************************************************************/
    public void dentistSelect(String dentistID) { sql = "SELECT * FROM Appointments WHERE dentId = '" + dentistID + "'";
    try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver"); //Loading the Ucanaccess driver
            Connection connection = DriverManager.getConnection(fileLocation); //connecting the driver to the DB file

            Statement st = connection.createStatement(); //Creating statement object
            ResultSet rs = st.executeQuery(sql); //Executing the SQL statement

            while (rs.next()) { //All the DB information is retrieved & set to the appropriate values
                setApptDateTime(rs.getString(1));
                setPatientID(rs.getString(2));
                setDentistID(rs.getString(3));
                setProcCode(rs.getString(4));
            }
            connection.close(); //Database connection closed
        } catch (Exception e) { System.out.println(e); } //If caught, an exception statement is print
    }
    
    
    /********************************************************************
     *	Java III - Procedure Display Properties
     * 	William Weldy - Spring 2020
     *  SQL INSERT statement that creates a new appointment row inside the Appointments table.
     ********************************************************************/
    public void insertDB(String timeAndDate, String patientID, String dentistID, String procCode) {
        sql = "INSERT INTO Appointments " +
                "VALUES ('" + timeAndDate + "', '" + patientID + "', '" + dentistID + "', '" + procCode + "' )";
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver"); //Loading the Ucanaccess driver
            Connection connection = DriverManager.getConnection(fileLocation); //connecting the driver to the DB file

            Statement st = connection.createStatement(); //Creating a statement object
            st.executeUpdate(sql); //Execute the SQL Statement

            connection.close(); //Database connection closed
        } catch (Exception e) { System.out.println(e); } //If caught, an exception statement is printed
    }

    
    /********************************************************************
     *	Java III - Procedure Display Properties
     * 	William Weldy - Spring 2020
     *  SQL UPDATE statement that changes an appointment row inside the Appointments table.
     ********************************************************************/
    public void updateDB(String timeAndDate, String patientID, String dentistID, String procCode) {
        sql = "UPDATE Appointments SET apptDateTime = '" + timeAndDate + "', dentId = '" + dentistID + "', procCode = '" + procCode + "' WHERE patId = '" + patientID + "'";
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver"); //Loading the Ucanaccess driver
            Connection connection = DriverManager.getConnection(fileLocation); //connecting the driver to the DB file

            Statement st = connection.createStatement(); //Creating a statement object
            st.executeUpdate(sql); //Execute the SQL statement

            connection.close(); //Database connection closed
        } catch (Exception e) { System.out.println(e); } //If caught, an exception statement is printed
    }
}
