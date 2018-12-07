<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/mytag/mytag.tld" prefix="ex" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>hello world</title>
</head>
<body>
  <p>${ message }</p>
  <p>
    <ex:Hello msg="111">aaaaaa</ex:Hello>
  </p>
</body>
</html>