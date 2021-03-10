<%-- 
    Document   : accountLookup
    Created on : Jun 25, 2020, 2:05:09 AM
    Author     : DefaultTheMighty
--%>

<%@page import="Business.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Account Display Page</title>
    </head>
    <body>  
    <center><h1>Account Lookup</h1> <br/>
        <% Customer c1 = (Customer)request.getSession().getAttribute("c1"); %>
        <%! Account a1 = new Account();%>
        <form>
            <h2>List of Your Accounts</h2><br>
            <table border="3">
            <%   
                for(int i=0 ; i < c1.accounts.size() ; i++) {
                    out.println("<tr>");
                    out.println("<td>" + c1.accounts.get(i) + "</td>");
                        a1.selectDB(c1.accounts.get(i));
                    out.println("<td>" + a1.getType() + "</td>");
                    out.println("<td>$" + a1.getBalance() + "</td>");
                    out.println("</tr>");
                }
             %>
            </table>
        </form>
    </center>
    </body>
</html>
