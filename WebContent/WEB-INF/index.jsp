<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>首页</title>
</head>
<body>

<a href="${pageContext.request.contextPath}/login">登录</a>
<a href="${pageContext.request.contextPath}/authenticated">已身份认证</a>
<a href="${pageContext.request.contextPath}/role">角色授权</a>
<a href="${pageContext.request.contextPath}/permission">权限授权</a>

</body>
</html>