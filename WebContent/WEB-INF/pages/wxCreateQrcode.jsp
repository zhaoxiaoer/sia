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
  <!-- 引入 WeUI -->
  <link rel="stylesheet" href="/sia/res/weui/css/example.css" />
  <link rel="stylesheet" href="/sia/res/weui/css/weui.css" />
</head>
<body>
  <div id="container" class="container">
    <div class="">
	    <div class="page__hd">
	      <h2 class="page__title">生成带参数的二维码</h2>
	    </div>
	    <div class="page__bd page_bd_spacing">
		    <mvc:form modelAttribute="qrcodeinfo" action="wxcreateqrcoderesult.mvc">
		      <table>
		        <tr>
		          <td><mvc:label path="qrtype" class="weui-cells__title">二维码类型</mvc:label></td>
		          <td><mvc:radiobuttons path="qrtype" items="${ qrtypes }" cssErrorClass="formFieldError" /></td>
		          <td><mvc:errors path="qrtype" /></td>
		        </tr>
		        <tr>
		          <td><mvc:label path="expiration">临时二维码过期时间（单位：秒）</mvc:label></td>
		          <td><mvc:input path="expiration" cssErrorClass="formFieldError" /></td>
		          <td><mvc:errors path="expiration" /></td>
		        </tr>
		        <tr>
		          <td><mvc:label path="scene">场景</mvc:label></td>
		          <td><mvc:input path="scene" cssErrorClass="formFieldError" /></td>
		          <td><mvc:errors path="scene" /></td>
		        </tr>
		        <tr>
		          <td colspan="2"><input type="submit" value="生成二维码" /></td>
		        </tr>
		      </table>
		    </mvc:form>
		</div>
    </div>
  </div>
</body>
</html>