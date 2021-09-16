<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title><sitemesh:write property='title'/></title>
    
    <!-- Reference Bootstrap files -->
	<link rel="stylesheet"
		 href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
	
	<script	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

    <!-- Font Icon -->
    <link rel="stylesheet" href="<c:url value='/template/register/fonts/material-icon/css/material-design-iconic-font.min.css' />" />
    <link rel="stylesheet" href="<c:url value='/template/register/vendor/jquery-ui/jquery-ui.min.css' /> " />

    <!-- Main css -->
    <link rel="stylesheet" href="<c:url value='/template/register/css/style.css' />" />
    
    <sitemesh:write property='head'/>
</head>
<body>

    <div class="main">
    	
    	<sitemesh:write property='body'/>
    
     </div>

    <!-- JS -->
    <script src="<c:url value='/template/register/vendor/jquery/jquery.min.js' />" >
    <script src="<c:url value='/template/register/vendor/jquery-ui/jquery-ui.min.js' /> "></script>
    <script src="<c:url value='/template/register/vendor/jquery-validation/dist/jquery.validate.min.js' />" ></script>
    <script src="<c:url value='/template/register/vendor/jquery-validation/dist/additional-methods.min.js' /> "></script>
    <script src="<c:url value='/template/register/js/main.js' />" ></script>
</body>
</html>