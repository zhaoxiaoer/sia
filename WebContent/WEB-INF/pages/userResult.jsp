<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="mvc" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
  <h2>User Registration</h2>
  <table>
    <tr>
      <td>Name</td>
      <td>${ u.name }</td>
    </tr>
    <tr>
      <td>Last name</td>
      <td>${ u.lastname }</td>
    </tr>
    <tr>
      <td>Password</td>
      <td>${ u.password }</td>
    </tr>
    <tr>
      <td>Detail</td>
      <td>${ u.detail }</td>
    </tr>
    <tr>
      <td>Birth Date</td>
      <td>${ u.birthDate }</td>
    </tr>
    <tr>
      <td>Gender</td>
      <td>${ u.gender }</td>
    </tr>
    <tr>
      <td>Country</td>
      <td>${ u.country }</td>
    </tr>
    <tr>
      <td>Non-Smoking</td>
      <td>${ u.nonSmoking }</td>
    </tr>
  </table>
</body>
</html>