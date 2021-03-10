<%-- 
    Document   : Patient_changeAppointment
    Created on : Jul 24, 2020, 4:27:38 PM
    Author     : William Weldy
--%>

<%@page import="Business.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Reschedule Appointment Page</title>
    </head>
    <body>
        <%-- 
            This is the page for a patient to change their appointment. The scriptlet tags get everything needed out of session, look up the information needed from the Patients, Appointments, and Procedures tables.
            Once everything is entered in, they will be directed to the updatedAppointment.java Servlet to actually update the information.
        --%>
        <%
            Patient p1 = (Patient)request.getSession().getAttribute("p1");
            Appointment apt = (Appointment)request.getSession().getAttribute("apt");
            Procedure proc = (Procedure)request.getSession().getAttribute("proc");
            
            apt.patientSelect(p1.getPatientID());
            proc.selectByCode(apt.getProcCode());
            
            HttpSession ses1 = request.getSession();
                ses1.setAttribute("p1", p1);
            HttpSession ses2 = request.getSession();
                ses2.setAttribute("apt", apt);
            HttpSession ses3 = request.getSession();
                ses3.setAttribute("proc", proc);
        %>
        <h2>Please enter in when you would like to reschedule</h2><br>
        <form action="http://localhost:8080/Java3_DentistOfficeApptWebApp/updatedAppointment" method="post">
        <label for="month">Choose a month </label>
                <select name="month">
                    <option value="Jan">January</option>
                    <option value="Feb">February</option>
                    <option value="Mar">March</option>
                    <option value="Apr">April</option>
                    <option value="May">May</option>
                    <option value="June">June</option>
                    <option value="July">July</option>
                    <option value="Aug">August</option>
                    <option value="Sept">September</option>
                    <option value="Oct">October</option>
                    <option value="Nov">November</option>
                    <option value="Dec">December</option>
                    <input type="text" name="dayOfMonth" value="1">
                </select><br>
                <label for="year">Choose a year </label>
                <select name="year">
                    <option value="2018">2018</option>
                    <option value="2019">2019</option>
                    <option value="2020">2020</option>
                    <option value="2021">2021</option>
                </select><br>
                <label for="timeOfDay">What time of day? </label>
                <select name="timeOfDay">
                    <option value="am">AM</option>
                    <option value="pm">PM</option>
                    <input type="text" name="hourOfDay" value="8">
                </select><br><br>
                <button type="button" name="backbtn" onclick="history.back()"><< Go back</button>
                <input type="Submit" value="Submit" /> 
        </form><br>
    </body>
</html>
