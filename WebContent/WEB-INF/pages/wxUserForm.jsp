<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="mvc" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=no">
<title>测试页</title>
<style type="text/css">
  .formFieldError { background-color: #FFC; }
</style>
</head>
<body>
  <h2>用户注册</h2>
  <mvc:form modelAttribute="user2" action="wXUserResult.mvc">
    <table>
      <tr>
        <td colspan="2"><mvc:input path="openId" type="hidden" /></td>
      </tr>
      <tr>
        <td><mvc:label path="name">姓名</mvc:label></td>
        <td><mvc:input path="name" cssErrorClass="formFieldError"/></td>
        <td><mvc:errors path="name" /></td>
      </tr>
      <tr>
        <td><mvc:label path="phone">电话</mvc:label></td>
        <td><mvc:input path="phone" cssErrorClass="formFieldError" /></td>
        <td><mvc:errors path="phone" /></td>
      </tr>
      <tr>
        <td colspan="2"><input type="submit" value="提交" /></td>
      </tr>
    </table>
  </mvc:form>
</body>
</html>