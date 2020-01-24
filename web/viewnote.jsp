<%-- 
    Document   : viewnote
    Created on : Jan 23, 2020, 7:52:55 PM
    Author     : 810783
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Lab3 SimpleNoteKeeper</title>
    </head>
    <body>
        <form method="get">
            <p>
            <h1>Simple Note Keeper</h1>
            <h2>View Note</h2>
            <c:if test = "${note != null}">
                <h3><b>Title: </b>${note.title}</h3>
                <h3><b>Content: </b>${note.content}</h3>
            </c:if>
            </p>
        </form>
    </body>
</html>
