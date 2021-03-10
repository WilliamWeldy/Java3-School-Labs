<%-- 
    Document   : View/Change Dentist attributes
    Created on : Jul 16, 2020, 3:45:06 PM
    Author     : William Weldy
--%>

<%@page import="Business.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Dentist View/Change Page</title>
    </head>
    <body>
        <%-- This page shows the dentist their attributes in the system, and gives them the opportunity to change their attributes as well. --%>
        <%
            Dentist d1 = (Dentist)request.getSession().getAttribute("d1");
            d1.selectDB(d1.getDentistID());
        %>
    <center><h1>View/Change Your Information</h1></center>
    Click on the text box and type in any valid data value in order to change any one of your attributes. Then click Submit to commit those changes. <br><br>
        <form action="http://localhost:8080/Java3_DentistOfficeApptWebApp/refreshDentistAttributes" method="post">
        <table border="2">
            <tr>
                <td>           Your ID </td>
                <td><input type="text" name="idTxt" value="<%=d1.getDentistID()%>" readonly></td>
            </tr>
            <tr>
                <td>Last Name</td>
                <td><input type="text" name="lNameTxt" value="<%=d1.getLastName()%>"/></td>
            </tr>
            <tr>
                <td>First Name</td>
                <td><input type="text" name="fNameTxt" value="<%=d1.getFirstName()%>"/></td>
            </tr>
            <tr>
                <td>Login Password  </td>
                <td><input type="text" name="pwTxt" value="<%=d1.getPassword()%>"/></td>
            </tr>
            <tr>
                <td>E-Mail</td>
                <td><input type="text" name="emTxt" value="<%=d1.getEmail()%>"/></td>
            </tr>
            <tr>
                <td>Office</td>
                <td><input type="text" name="officeTxt" value="<%=d1.getOffice()%>"/></td>
            </tr>
            <tr>
                <td><input type="Submit" value="*Submit*" /></td>
                <td><button type="button" name="backbtn" onclick="history.back()"><< Go back</button></td>
            </tr>
        </table>
        </form>
    </body>
</html>
