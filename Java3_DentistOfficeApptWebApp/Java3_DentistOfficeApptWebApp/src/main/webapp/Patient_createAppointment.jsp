<%-- 
    Document   : Patient_createAppointment
    Created on : Jul 25, 2020, 9:23:05 AM
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
            This is the page for a patient to schedule a new appointment. The scriptlet tags get everything needed out of session, and look up the information needed from the Patients, Appointments, Dentists, and Procedures tables.
            Once everything is entered in, they will be directed to the schedulingAppointment.java Servlet to actually create the appointment.
        --%>
        <%
            Patient p1 = (Patient)request.getSession().getAttribute("p1");
            Appointment apt = (Appointment)request.getSession().getAttribute("apt");
            Procedure proc = (Procedure)request.getSession().getAttribute("proc");
            
            HttpSession ses1 = request.getSession();
                ses1.setAttribute("p1", p1);
            HttpSession ses2 = request.getSession();
                ses2.setAttribute("apt", apt);
            HttpSession ses3 = request.getSession();
                ses3.setAttribute("proc", proc);
        %>
        <h2>Please enter in when you would like to schedule your appointment</h2><br>
        <form action="http://localhost:8080/Java3_DentistOfficeApptWebApp/schedulingAppointment" method="post">
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
                </select><br>
                <label for="procedure">What would you like to do? </label>
                <select name="procedure">
                    <option value="Cleaning/Exam">Cleaning/Exam</option>
                    <option value="Xrays">Xrays</option>
                    <option value="Whitening">Whitening</option>
                    <option value="Cavity">Cavity</option>
                    <option value="Top Dentures">Top Dentures</option>
                    <option value="Bottom Dentures">Bottom Dentures</option>
                    <option value="Crown">Crown</option>
                    <option value="Root Canel">Root Canel</option>
                </select><br><br>
                <button type="button" name="backbtn" onclick="history.back()"><< Go back</button>
                <input type="Submit" value="Submit" /> 
        </form><br>
    </body>
</html>
