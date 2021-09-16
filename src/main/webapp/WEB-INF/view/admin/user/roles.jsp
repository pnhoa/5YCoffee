<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title>Roles</title>
	
	
</head>
<body >
	<div class="main-content">
		<div class="page-content">
		<h3>Roles Management</h3>
			<div class="col-xs-12">
				<!-- check message -->
				<c:if test="${not empty user.message}">
					<div class="alert alert-${user.alert}">
								${user.message}
					</div>
				</c:if>
			</div>
			
			<form:form action="change-roles" method="post" modelAttribute="user" >
			
				<form:input type="text" path="email" placeholder="Enter email"  required="required"/>
				
				<form:select path="roleCode" id="roleCode">
					<form:options items="${roles}"/>
				</form:select>
				
				<input type="hidden" name="usernameAdmin" value="${sessionScope.user.userName}">
				
				<input type="submit" value="Change role">
			</form:form>
		</div>
	</div>
	
</body>
</html>