<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Wiki</title>
</head>
<body>
<a href ="/cs320stu13/EditPage?path=${entry.path}">Edit</a> |
<a href ="/cs320stu13/Revisions?path=${entry.path}">Revisions</a> |
<a href="/cs320stu13/${loginout}">${loginout}</a>
<br>
<p>${entry.content}</p>
<p><a href='/cs320stu13/wiki/index'>Return to index</a></p>
<p><i>This page was edited by <c:out value="${entry.name}"/> on <c:out value="${entry.date}"/></i></p>
</body>
</html>