<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="mvc" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=no">
<title>生成带参数的二维码</title>
<style type="text/css">
  .formFieldError { background-color: #FFC; }
</style>
</head>
<body>
  <h2>生成带参数的二维码</h2>
  <img alt="场景值为${ scene }的二维码" src="${ src }">
</body>
</html>