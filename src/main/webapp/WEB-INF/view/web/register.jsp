<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
	  <!-- Required meta tags -->
    <meta charset="utf-8">

	
	<title>Đăng ký</title>
	<style>
		.error {color:red}
	</style>
	
</head>
<body>
	
	<section class="signup">
            <!-- <img src="images/signup-bg.jpg" alt=""> -->
            <div class="container">
                <div class="signup-content">
                
                         <p class="register-header">Register Form</p>
                
                 <!-- Registration Form -->
					<form:form action="${pageContext.request.contextPath}/register/processRegistrationForm"  method="POST"
						  	   modelAttribute="customer"
						  	   id="signup-form" class="signup-form">
						  	   <!-- Check for registration error -->
									<c:if test="${registrationError != null}">
								
										<div class="alert alert-danger col-xs-offset-1 col-xs-10">
											${registrationError}
										</div>
		
									</c:if>
						  	   
                    	<div class="form-group form-icon">
                                <label for="username">Username</label>
                                <form:errors path="userName" cssClass="error" />
                                <form:input path="userName" placeholder="username (*)" type="text" class="form-input" />
                         </div>
                        <div class="form-row">
                            <div class="form-group">
                                <label for="first_name">First name</label>
                                <form:errors path="firstName" cssClass="error" />
                                <form:input path="firstName" placeholder="firstname (*)" type="text" class="form-input" />
                            </div>
                            <div class="form-group">
                                <label for="last_name">Last name</label>
                                <form:errors path="lastName" cssClass="error" />
                                <form:input path="lastName" placeholder="lasttname (*)" type="text" class="form-input" />
                            </div>
                        </div>
                        <div class="form-row">
                            <div class="form-group form-icon">
                                <label for="birth_date">Birth date</label>
                                 <form:errors path="birthdayString" cssClass="error" />
                                <form:input path="birthdayString" placeholder="DD/MM/YYYY" type="text" class="form-input" />
                            </div>
                             <div class="form-group form-icon">
                                <label for="address">Address</label>
                                <form:errors path="address" cssClass="error" />
                                <form:input path="address" placeholder="address" type="text" class="form-input" />
                            </div>
                        </div>
                        <div class="form-row">
                        	<div class="form-group form-icon">
                                <label for="phone_number">Phone Number</label>
                                  <form:errors path="phone" cssClass="error" />
                                <form:input path="phone" placeholder="Phone Number (*)" type="text" class="form-input" />
                            </div>
                            <div class="form-group form-icon">
                                <label for="email">Email</label>
                                 <form:errors path="email" cssClass="error" />
                                <form:input path="email" placeholder="Email (*)" type="text" class="form-input" />
                            </div>
                        </div>
                        <div class="form-row">
                            <div class="form-group">
                                <label for="password">Password</label>
                                <form:errors path="password" cssClass="error" />
                                <form:input path="password" placeholder="Password (*)" type="password" class="form-input" />
                            </div>
                            <div class="form-group">
                                <label for="re_password">Repeat your password</label>
                                 <form:errors path="matchingPassword" cssClass="error" />
                                <form:input path="matchingPassword" placeholder="Repeat Password (*)" type="password" class="form-input" />
                            </div>
                        </div>
                        
                        <div class="form-group">
                        	<button type="submit" class="btn btn-primary" class="form-submit">Register</button>
                        </div>
                        <a class="btn btn-danger" href="javascript:history.back()">Go Back</a>
                    </form:form>
                </div>
            </div>
        </section>

</body>
</html>