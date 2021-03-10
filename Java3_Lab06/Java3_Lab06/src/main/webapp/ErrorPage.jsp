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
    <%! String custID; %>
    <% custID = request.getParameter("custID"); 
       Customer c1 = new Customer();
       c1.selectDB(custID);
         out.println("<h2>Error Logging in for user with ID " + custID + " </h2>"); %>
    </center>
</html>
