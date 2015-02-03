<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Edit Page</title>
</head>
<body>
<form action="EditPage" method="POST">
Your name: <input type="text" name="name" readonly="readonly" value ="${name}"/><br/>
Comment: <br/>
<textarea name="content" rows="5" cols="60">${entry.content}</textarea><br/>
<input type="hidden" name="index" value="${index}"/>
<input type="submit" name="save" value="Save" />
</form>
</body>
</html>