<%-- 
    Document   : viewnote
    Created on : Jan 23, 2020, 7:52:55 PM
    Author     : 810783
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Lab3 View</title>
    </head>
    <body>
        <form method="get">
            <p>
            <h1>Simple Note Keeper</h1>
            <h2>View Note</h2>
            <c:if test = "${note != null}">
                <h3>Title: ${note.title}</h3>
                <h3>Content: ${note.content}</h3><br>
            </c:if>
            <c:if test= "${note == null}">
                <h3>Sorry! There was an error!</h3>
            </c:if>
            </p>
            <input type="submit" value="Edit" name="link"/>
        </form>
    </body>
</html>
