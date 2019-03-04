<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>注册</title>
	<style>.error{color:red;}</style>
</head>
<body>

<div class="error">${error}</div>
<form action="${pageContext.request.contextPath}/register" method="post">
	用户名：<input type="text" name="username"><br/>
	密码：<input type="password" name="password"><br/>
	<input type="submit" value="注册">
</form>

</body>
</html>