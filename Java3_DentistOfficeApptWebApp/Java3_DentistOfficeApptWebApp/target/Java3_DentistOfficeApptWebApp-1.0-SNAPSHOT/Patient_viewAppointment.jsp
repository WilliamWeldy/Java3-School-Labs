<%-- 
    Document   : Appointment Viewing Page
    Created on : Jul 16, 2020, 3:45:36 PM
    Author     : William Weldy
--%>

<%@page import="Business.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Appointment View Page</title>
    </head>
    <body>
        <%-- 
            This is the page for a patient to view their appointment. The scriptlet tags get everything needed out of session, look up the information needed from the Patients, Appointments, Dentists, and Procedures tables.
            The patient can either proceed to the Patient_changeAppointment.jsp to start changing their appointment's attributes or go back to their main page.
        --%>
        <%! Appointment apt = new Appointment(); %>
        <%! Procedure proc = new Procedure(); %>
        <%! Dentist dr = new Dentist(); %>
        <%
            Patient p1 = (Patient)request.getSession().getAttribute("p1");
                p1.selectDB(p1.getPatientID());
            
            apt.patientSelect(p1.getPatientID());
            proc.selectByCode(apt.getProcCode());
            dr.selectDB(apt.getDentistID());
            
            HttpSession ses1 = request.getSession();
                ses1.setAttribute("apt", apt);
            HttpSession ses2 = request.getSession();
                ses2.setAttribute("proc", proc);
        %>
        <form action="http://localhost:8080/Java3_DentistOfficeApptWebApp/Patient_changeAppointment.jsp" method="post">
            <h3>Your appointment is on <input type="text" name="timeTxt" value="<%=apt.getApptDateTime()%>" readonly> for <input type="text" name="procTxt" value="<%=proc.getProcName()%>" readonly> with <% out.print("Dr. " + dr.getLastName()); %>.</h3><br><br>
                <button type="button" name="backbtn" onclick="history.back()"><< Go back</button>
                <input type="Submit" value="Change Appointment" /> 
        </form><br>
    </body>
</html>
