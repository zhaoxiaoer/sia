<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<title>欢迎</title>
</head>
<body>
<p>欢迎${subject.principal}登录成功！<a href="${pageContext.request.contextPath}/logout">退出</a></p>
</body>
</html>