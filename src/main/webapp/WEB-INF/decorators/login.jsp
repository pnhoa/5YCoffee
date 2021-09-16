<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
	  <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link href="https://fonts.googleapis.com/css?family=Roboto:300,400&display=swap" rel="stylesheet">
	
	<title><sitemesh:write property='title'/></title>
	

	
	
	<link rel="stylesheet" href="<c:url value='/template/login/fonts/icomoon/style.css' />" type ="text/css" media = "all" />

    <link rel="stylesheet" href="<c:url value='/template/login/css/owl.carousel.min.css' />" type ="text/css" media = "all"/>

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="<c:url value='/template/login/css/bootstrap.min.css' />" type ="text/css" media = "all"/>
    
    <!-- Style -->
    <link rel="stylesheet" href="<c:url value='/template/login/css/style.css' />" type ="text/css" media = "all"/>
	
	<sitemesh:write property='head'/>
</head>
<body>

  <div class="content">
  
		<sitemesh:write property='body'/>
		
</div>

	<script src="<c:url value='/template/login/js/jquery-3.3.1.min.js' />"></script>
    <script src="<c:url value='/template/login/js/popper.min.js' />"></script>
    <script src="<c:url value='/template/login/js/bootstrap.min.js' />"></script>
    <script src="<c:url value='/template/login/js/main.js' />"></script>
	
</body>
</html>