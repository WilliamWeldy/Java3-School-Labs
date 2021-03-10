<%-- 
    Document   : Dentist_main
    Created on : Jul 21, 2020, 8:03:24 PM
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
        <%-- This is the main page for a dentist user. --%>
        <% Dentist d1 = (Dentist)request.getSession().getAttribute("d1"); %>
        <h1>Welcome back Dr. <%=d1.getLastName()%>! Please review your appointments, and have a nice day.</h1>
        <h3><br><ul>
                <li><a href="viewAppointments">View all upcoming appointments</a></li>
                <li><a href="changeDentistAttributes.jsp">View or change your personal information</a></li>
        </ul></h3>
        <br><center>
        <form action="login.jsp">
            <input type="submit" value="Log Out"/>
        </form></center>
    </body>
</html>
