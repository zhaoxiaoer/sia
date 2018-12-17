<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="mvc" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
  .formFieldError { background-color: #FFC; }
</style>
</head>
<body>

<h2>User Registration</h2>
<mvc:form modelAttribute="user" action="result.mvc" enctype="multipart/form-data">
    <table>
      <tr>
        <td><mvc:label path="name">Name</mvc:label></td>
        <td><mvc:input path="name" cssErrorClass="formFieldError"/></td>
        <td><mvc:errors path="name" /></td>
      </tr>
      <tr>
        <td><mvc:label path="lastname">Last Name</mvc:label></td>
        <td><mvc:input path="lastname" cssErrorClass="formFieldError"/></td>
        <td><mvc:errors path="lastname" /></td>
      </tr>
      <tr>
        <td><mvc:label path="password">Password</mvc:label></td>
        <td><mvc:password path="password" cssErrorClass="formFieldError" /></td>
        <td><mvc:errors path="password" /></td>
      </tr>
      <tr>
        <td><mvc:label path="detail">Detail</mvc:label></td>
        <td><mvc:textarea path="detail" cssErrorClass="formFieldError" /></td>
        <td><mvc:errors path="detail" /></td>
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