<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Revisions</title>
</head>
<body>

<c:forEach items ="${changes}" var="changes" varStatus="status">
<a href="/cs320stu13/Rev?path=${entry.path}&index=${status.index}">Revision ${status.index}</a><br>
</c:forEach>
</body>
</html>