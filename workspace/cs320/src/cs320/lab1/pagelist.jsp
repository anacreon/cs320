<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Page List</title>
</head>
<body>
<table border='1'>
<tr><th>Path</th><th>Last Edited By</th><th>Time of Last Edit</th></tr>
<c:forEach items ="${pageListEntries}" var="entry" varStatus="status">
<tr>
<td><a href='${entry.uripath}'>${entry.path}</a></td><td>${entry.name}</td><td>${entry.date}</td>
</tr>
</c:forEach>
</table>
</body>
</html>