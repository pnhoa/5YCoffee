<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<c:url var="customerURL" value="/admin/customers/list"/>
<!DOCTYPE html>
<html>
<head>
	<title>Product Detail</title>
	
	
</head>
<body >
	<p>Name: ${product.name }</p>
	<p>Category: ${product.category.name }</p>
</body>
</html>