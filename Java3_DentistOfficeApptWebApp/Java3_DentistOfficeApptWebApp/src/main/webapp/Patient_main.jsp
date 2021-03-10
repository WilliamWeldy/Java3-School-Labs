<%-- 
    Document   : Patient_main
    Created on : Jul 21, 2020, 7:53:39 PM
    Author     : William Weldy
--%>

<%@page import="Business.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Main Page</title>
    </head>
    <body>
        <%-- This is the main page for a patient user. --%>
        <% Patient p1 = (Patient)request.getSession().getAttribute("p1"); %>
        <h1>Welcome <%=p1.getFirstName()%>!! What would you like to do?</p></h1>
        <h3><br><ul>
                <li><a href="changePatientAttributes.jsp">View or change personal information</a></li>
                <li><a href="Patient_viewAppointment.jsp">Review or change your appointment</a></li>
                <li><a href="newAppointment">Schedule an appointment</a></li>
        </ul></h3>
        <br><center>
        <form action="login.jsp">
            <input type="submit" value="Log Out"/>
        </form></center>
    </body>
</html>
