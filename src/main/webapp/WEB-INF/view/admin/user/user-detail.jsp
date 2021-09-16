<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title>User Detail</title>
	
	
</head>
<body >
	<p>${user.userName }</p>
	<c:if test="${sessionScope.user.id == user.id}">
		<form:form action="${pageContext.request.contextPath}/admin/users/edit" method="post" modelAttribute="user" >
		
			<input type="hidden" name="id" value="${user.id}" />
		
			<input type="submit" value="Edit profile">
	
		</form:form>
	</c:if>
	
</body>
</html>