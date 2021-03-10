<%-- 
    Document   : The Login Page
    Created on : Jul 16, 2020, 3:38:00 PM
    Author     : William Weldy
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
    </head>
    <%-- This is the login page, once the user has hit submit, the Login servlet will determine whether they're a patient or dentist, who they are, and then if their credentials are all correct for the user. --%>
    <body>
        <center>
            <h1>Log into your account</h1>
            <form action="http://localhost:8080/Java3_DentistOfficeApptWebApp/Login" method="post">
                User ID      <input type="Text" name="UserID" /> <br/>
                Password    <input type="Text" name="Password" /> <br/> <br/>
                <input type="Submit" value="Login" />
                <input type="Reset" value="Clear" />
            </form>
        </center>
    </body>
</html>
