<%-- 
    Document   : New Appointment Page
    Created on : Jul 16, 2020, 3:45:47 PM
    Author     : William Weldy
--%>

<%@page import="Business.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>New Appointment</title>
    </head>
    <body>
        <%-- This page is where you're redirected from if the patient user does not have an appointment, and asks if they want to make one or go back to the main page. --%>
        <%! Procedure proc = new Procedure(); %>
        <%! Dentist dr = new Dentist(); %>
        <%
            Patient p1 = (Patient)request.getSession().getAttribute("p1");
                p1.selectDB(p1.getPatientID());
                
            Appointment apt = (Appointment)request.getSession().getAttribute("apt");
            
            apt.patientSelect(p1.getPatientID());
            proc.selectByCode(apt.getProcCode());
            dr.selectDB(apt.getDentistID());
            
            HttpSession ses1 = request.getSession();
                ses1.setAttribute("p1", p1);
            HttpSession ses2 = request.getSession();
                ses1.setAttribute("apt", apt);
            HttpSession ses3 = request.getSession();
                ses2.setAttribute("proc", proc);
        %>
        <form action="http://localhost:8080/Java3_DentistOfficeApptWebApp/Patient_createAppointment.jsp" method="post">
            <h3>You do not have any appointments scheduled at this time, would you like to schedule one?</h3><br><br>
                <button type="button" name="backbtn" onclick="history.back()"><< Go back</button>
                <input type="Submit" value="Schedule Appointment" /> 
        </form><br>
    </body>
</html>
