<%-- 
    Document   : ErrorPage
    Created on : Jun 25, 2020, 2:00:42 AM
    Author     : DefaultTheMighty
--%>

<%@page import="Business.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>InValid Login Error Page</title>
    </head>
    <center>
        <%
            Customer c1;
            c1 = (Customer)session.getAttribute("c1");
            String custID = c1.getCustId();
            c1.selectDB(custID);
            
            out.println("<h2>Error Logging in for user with ID " + custID + " </h2>"); %>
    </center>
</html>
