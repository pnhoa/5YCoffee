<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>

<!DOCTYPE html>
<html>
<head>
	<title>Edit Employee</title>
	<link type="text/css"
			rel="stylesheet" 
			href="<c:url value='/template/admin/css/add-customer-style.css' />" />
	
</head>
<body >
	
	 <div class="main-content-inner">
		<div class="row">
			<div class="col-xs-12"> 
				
				
				<form:form class="form-horizontal" role="form" id="formSubmit" action="save" modelAttribute="user" method="POST" >
					<h2>Edit Employee</h2>
					  <!-- Check for registration error -->
						<c:if test="${registrationError != null}">
					
							<div class="alert alert-danger col-xs-offset-1 col-xs-10">
								${registrationError}
							</div>

						</c:if>
						  	   
						<div class="form-group">
							
							<label class="col-sm-3 control-label no-padding-right" for="form-field-1">Username:</label>
							<div class="col-sm-9">
							
								<c:if test="${not empty user.id}">
									<form:input path="userName" cssClass="col-xs-10 col-sm-5" placeholder="username (*)" readonly="true"/>
								</c:if>
								
								<c:if test="${empty user.id}">
									<form:input path="userName" cssClass="col-xs-10 col-sm-5" placeholder="username (*)"/>
								</c:if>
								<form:errors path="userName" style="color:red;" />
							</div>
							
						</div>
						<div class="form-group">
							
							<label class="col-sm-3 control-label no-padding-right" for="form-field-1">First Name:</label>
							<div class="col-sm-9">
								<form:input path="firstName" cssClass="col-xs-10 col-sm-5" placeholder="firstname (*)"/>
								<form:errors path="firstName" class="errorCss" />
							</div>
						</div>
						<div class="form-group">
							
							<label class="col-sm-3 control-label no-padding-right" for="form-field-1">Last Name:</label>
							<div class="col-sm-9">
								<form:input path="lastName" cssClass="col-xs-10 col-sm-5" placeholder="lasttname (*)"/>
								<form:errors path="lastName" style="color:red;" />
							</div>
						</div>
						<div class="form-group">
							
							<label class="col-sm-3 control-label no-padding-right" for="form-field-1">Birthday:</label>
							<div class="col-sm-9">
								<form:input path="birthdayString" placeholder="DD/MM/YYYY" cssClass="col-xs-10 col-sm-5"/>
								<form:errors path="birthdayString" style="color:red;" />
							</div>
						</div>
						<div class="form-group">
							
							<label class="col-sm-3 control-label no-padding-right" for="form-field-1">Address:</label>
							<div class="col-sm-9">
								<form:input path="address" cssClass="col-xs-10 col-sm-5" placeholder="address"/>
								<form:errors path="address" style="color:red;" />
							</div>
						</div>
						<div class="form-group">
							
							<label class="col-sm-3 control-label no-padding-right" for="form-field-1">Phone Number:</label>
							<div class="col-sm-9">
								<form:input path="phone" cssClass="col-xs-10 col-sm-5" placeholder="077123456"/>
								<form:errors path="phone" style="color:red;" />
							</div>
						</div>
						<div class="form-group">
							
							<label class="col-sm-3 control-label no-padding-right" for="form-field-1">Email:</label>
							<div class="col-sm-9">
								<c:if test="${not empty user.id}">
									<form:input path="email" cssClass="col-xs-10 col-sm-5" placeholder="email" readonly="true"/>
								</c:if>
								<c:if test="${ empty user.id}">
									<form:input path="email" cssClass="col-xs-10 col-sm-5" placeholder="email"/>
								</c:if>
								<form:errors path="email" style="color:red;" />
							</div>
						</div>
						
						
						
						
						<div class="form-group">
							  
							  <label for="roleCode" class="col-sm-3 control-label no-padding-right">Roles:</label>
							  <div class="col-sm-9">
							  	 <form:select path="roleCode" id="roleCode">
							  	 	<c:if test="${not empty user.id}">
							  	 		
							  	 		<form:option selected="selected" value="${user.roleCode}" label="${user.roleName}"/>
							  	 		
							  	 		<security:authorize access="hasRole('ADMIN')">
							  	 			<form:options items="${roles}"/>
							  	 		</security:authorize>
							  	 		
							  	 		
							  	 	</c:if>
							  	 	
							  	 	<c:if test="${ empty user.id}">
							  	 		<form:option value="" label="-- Roles --"/>
							  	 		<form:options items="${roles}"/>
							  	 	</c:if>
							  	 	
							  	 </form:select>
							  	 <form:errors path="roleCode" style="color:red;" />
							  </div>
							  
						</div>
						<c:if test="${ empty user.id}">
							<div class="form-group">
								
								<label class="col-sm-3 control-label no-padding-right" for="form-field-1">Password:</label>
								<div class="col-sm-9">
									<form:input path="password" placeholder="Password (*)" type="password" cssClass="col-xs-10 col-sm-5" required="required" />
									<form:errors path="password" style="color:red;" />
								</div>
							</div>
							<div class="form-group">
								
								<label class="col-sm-3 control-label no-padding-right" for="form-field-1">Repeat your password:</label>
								<div class="col-sm-9">
									<form:input path="matchingPassword" placeholder="Repeat Password (*)" type="password" cssClass="col-xs-10 col-sm-5" required="required" />
									<form:errors path="matchingPassword" style="color:red;" />
								</div>
							</div>
						</c:if>
						<c:if test="${not empty user.id}">
							<input type="checkbox" id="showChangePassword"> Change password
							<div id="showField">
								<div class="form-group">	
									<label class="col-sm-3 control-label no-padding-right" for="form-field-1">The old Password:</label>
									<div class="col-sm-9">
										<input name="theOldPassword" placeholder="Password (*)" type="password" class="col-xs-10 col-sm-5" id="theOldPassword"/>
										<form:errors path="theOldPassword" style="color:red;" />
									</div>
								</div>
								<div class="form-group">	
									<label class="col-sm-3 control-label no-padding-right" for="form-field-1">Password:</label>
									<div class="col-sm-9">
										<input name="password" placeholder="Password (*)" type="password" class="col-xs-10 col-sm-5" id="theNewPassword"/>
										<form:errors path="password" style="color:red;" />
									</div>
								</div>
								<div class="form-group">
									
									<label class="col-sm-3 control-label no-padding-right" for="form-field-1">Repeat your password:</label>
									<div class="col-sm-9">
										<input name="matchingPassword" placeholder="Repeat Password (*)" type="password" class="col-xs-10 col-sm-5" id="theMatchingPassword"/>
										<form:errors path="matchingPassword" style="color:red;" />
									</div>
								</div>
							</div>
						</c:if>
							
						
						<div class="clearfix form-actions">
							<div class="col-md-offset-3 col-md-9">
								<a class="btn btn-danger" href="javascript:history.back()" style="margin-right: 10px;">Go Back</a>
									<c:if test="${not empty user.id}">
										
										<form:input type="submit" path="" value="Update" class="btn btn-info"/>  
									
									</c:if>
									<c:if test="${empty user.id}">
										<form:input type="submit" path="" value="Save" class="btn btn-info"/>  
									</c:if>
		
									&nbsp; &nbsp; &nbsp;
									<button class="btn" type="reset">
										<i class="ace-icon fa fa-undo bigger-110"></i>
										Reset
									</button>
									
								</div>		
							</div>
					<form:hidden path="id"/>
					<input type="hidden" name="usernameAdmin" value="${sessionScope.user.userName}">
				</form:form>
			</div>
		</div>
	</div>
<script type="text/javascript">

	$(document).ready(function () {
		$('#showField').hide();
		
		$('#theOldPassword').attr('required', false);
		$('#theNewPassword').attr('required', false);
		$('#theMatchingPassword').attr('required', false);

		
		$('#showChangePassword').click(function () {
			if ($(this).is (":checked")){
				$('#showField').show();	
				$('#theOldPassword').attr('required', true);
				$('#theNewPassword').attr('required', true);
				$('#theMatchingPassword').attr('required', true);
			}
				
			else {
				$('#showField').hide();	
				$('#theOldPassword').attr('required', false);
				$('#theNewPassword').attr('required', false);
				$('#theMatchingPassword').attr('required', false);

			}
				
		});
		
	});

</script>	
	
</body>
</html>