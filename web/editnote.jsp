<%-- 
    Document   : editnote
    Created on : Jan 23, 2020, 7:53:06 PM
    Author     : 810783
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Lab3 Edit</title>
    </head>
    <body>
        <form method="POST">
            <p>
            <h1>Simple Note Keeper</h1>
            <h2>Edit Note</h2>
            <c:if test = "${note != null}">
                <h3>Title: <input required type="text" value="${note.title}" name="newTitle"/>
                <h3>Content: </h3><textarea required rows="4" cols="50" name="newContent">${note.content}</textarea>
            </c:if>
            <c:if test= "${note == null}">
                <h3>Sorry! There was an error!</h3>
            </c:if>
            </p>
            <input type="submit" value="Save"/>
        </form>
    </body>
</html>
