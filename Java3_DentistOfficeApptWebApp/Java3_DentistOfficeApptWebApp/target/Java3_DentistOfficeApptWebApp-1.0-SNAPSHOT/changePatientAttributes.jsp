<%-- 
    Document   : View/Change Paitent attributes
    Created on : Jul 16, 2020, 3:44:42 PM
    Author     : William Weldy
--%>

<%@page import="Business.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Patient View/Change Page</title>
    </head>
    <body>
        <%-- This page shows the patient their attributes in the system, and gives them the opportunity to change their attributes as well. --%>
        <%
            Patient p1 = (Patient)request.getSession().getAttribute("p1");
            p1.selectDB(p1.getPatientID());
        %>
    <center><h1>View/Change Your Information</h1></center>
    Click on the text box and type in any valid data value in order to change any one of your attributes. Then click Submit to commit those changes. <br><br>
        <form action="http://localhost:8080/Java3_DentistOfficeApptWebApp/refreshPatientAttributes" method="post">
        <table border="2">
            <tr>
                <td>First Name</td>
                <td><input type="text" name="fNameTxt" value="<%=p1.getFirstName()%>"/></td>
            </tr>
            <tr>
                <td>Last Name</td>
                <td><input type="text" name="lNameTxt" value="<%=p1.getLastName()%>"/></td>
            </tr>
            <tr>
                <td>Login Password</td>
                <td><input type="text" name="pwTxt" value="<%=p1.getPassword()%>"/></td>
            </tr>
            <tr>
                <td>Street Address</td>
                <td><input type="text" name="addrTxt" value="<%=p1.getAddress()%>"/></td>
            </tr>
            <tr>
                <td>E-Mail Address</td>
                <td><input type="text" name="emTxt" value="<%=p1.getEmail()%>"/></td>
            </tr>
            <tr>
                <td>Insurance Company  </td>
                <td><input type="text" name="insCoTxt" value="<%=p1.getInsCo()%>"/></td>
            </tr>
            <tr>
                <td><input type="Submit" value="*Submit*" /></td>
                <td><button type="button" name="backbtn" onclick="history.back()"><< Go back</button></td>
            </tr>
        </table>
        </form>
    </body>
</html>
