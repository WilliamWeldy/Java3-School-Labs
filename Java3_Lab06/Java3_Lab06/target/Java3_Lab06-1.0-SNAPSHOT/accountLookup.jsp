<%-- 
    Document   : accountLookup
    Created on : Jun 25, 2020, 2:05:09 AM
    Author     : DefaultTheMighty
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>  
    <center><h1>Account Lookup</h1> <br/>
        <form action="http://localhost:8080/Java3_Lab06/LoginServletDB" method="post">
            ACCount Number <input type="Text" name="Account Number" /> <br/>
            Customer ID <input type="Text" name="Customer ID" /> <br/> 
            Account Type <input type="Text" name="Type" /> <br/> 
            Balance <input type="Text" name="Balance" /> <br/> <br/>
            <input type="Submit" value="Lookup" />
            <input type="Reset" value="Clear" />
        </form>
    </center>
    </body>
</html>
