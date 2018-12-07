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
<mvc:form modelAttribute="user" action="result.mvc">
    <table>
      <tr>
        <td><mvc:label path="name">Name</mvc:label></td>
        <td><mvc:input path="name" /></td>
      </tr>
      <tr>
        <td><mvc:label path="lastname">Last Name</mvc:label></td>
        <td><mvc:input path="lastname" /></td>
      </tr>
      <tr>
        <td><mvc:label path="password">Password</mvc:label></td>
        <td><mvc:password path="password" /></td>
      </tr>
      <tr>
        <td><mvc:label path="detail">Detail</mvc:label></td>
        <td><mvc:textarea path="detail" /></td>
      </tr>
      <tr>
        <td><mvc:label path="birthDate">BirthDate</mvc:label></td>
        <td><mvc:input path="birthDate" /></td>
      </tr>
      <tr>
        <td><mvc:label path="gender">Gender</mvc:label></td>
        <td><mvc:radiobuttons path="gender" items="${ genders }" /> </td>
      </tr>
      <tr>
        <td><mvc:label path="country">Country</mvc:label></td>
        <td><mvc:select path="country" items="${ countries }" /></td>
      </tr>
      <tr>
        <td><mvc:label path="nonSmoking">Non Smoking</mvc:label></td>
        <td><mvc:checkbox path="nonSmoking" /></td>
      </tr>
      <tr>
        <td colspan="2">
          <input type="submit" value="Submit" />
        </td>
      </tr>
    </table>
  </mvc:form>
</body>
</html>