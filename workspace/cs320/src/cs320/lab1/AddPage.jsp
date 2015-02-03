<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add Page</title>
</head>
<body>
<form action='AddPage' method='POST'>
<table border ='1'>
<tr><td>Path:</td><td><input type='text' name='path' readonly='readonly' value="${path}"></td></tr>
<tr><td>Your Name:</td><td><input type='text' name='name' readonly='readonly' value ="${name}"/></td></tr>
<tr><td>Content:</td><td><textarea name='content' rows='5' cols='60'></textarea></td></tr>
</table>
<input type='submit' name='add' value='Add' />
</form>
</body>
</html>