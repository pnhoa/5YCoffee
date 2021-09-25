<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title>Contact Detail</title>
	
	
</head>
<body >
	<p>Date: ${contact.createdDate }</p>
	<p>Name: ${contact.name }</p>
	<p>Email: ${contact.email }</p>
	<p>Subject: ${contact.subject }</p>
	<p>Message: ${contact.message }</p>
</body>
</html>