<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
	  <!-- Required meta tags -->
    <meta charset="utf-8">

	
	<title>Đăng nhập</title>
	
</head>
<body>

	<div class ="container">
		
		<div class="row">
        <div class="col-md-6 order-md-2">
         <!--  <img src="<c:url value='/template/login/images/undraw_file_sync_ot38.svg' />" alt="Image" class="img-fluid" /> -->
        </div>
        <div class="col-md-6 contents">
          <div class="row justify-content-center">
            <div class="col-md-8">
              <div class="mb-4">
              <h3>Sign In to <strong>My Store</strong></h3>
              <p class="mb-4">Create the best product</p>
            </div>
            <!-- Login Form -->
					<form:form action="${pageContext.request.contextPath}/authenticateTheUser" method="POST">
            
            <!-- Place for messages: error, alert etc ... -->
					    
					        <div class="col-xs-15">
					            <div>
					            	<c:if test="${param.error != null}">
										
										<i class="alert alert-danger col-xs-offset-1 col-xs-">Invalid username/password</i>
									
									</c:if>
									
									<c:if test="${param.logout != null }">
										
										<i  class="alert alert-success col-xs-offset-1 col-xs-10"> You have been logged out</i>
										
									</c:if>
									
									<!--		            
									<div class="alert alert-danger col-xs-offset-1 col-xs-10">
										Invalid username and password.
									</div>
									-->
									
									<!--		            
									<div class="alert alert-success col-xs-offset-1 col-xs-10">
										You have been logged out.
									</div>
								    -->

					            </div>
					        </div>
					    
              <div class="form-group first">
                <label for="username">Username</label>
                <input  type="text" name="username"  class="form-control" id="username">

              </div>
              <div class="form-group last mb-4">
                <label for="password">Password</label>
                <input type="password" name="password"  class="form-control" id="password">
                
              </div>
              
              <div class="d-flex mb-5 align-items-center">
              <input type="checkbox" checked="checked" />
                <label class="control control--checkbox mb-0"><span class="caption">Remember me</span>
                  
                  
                </label>
                <span class="ml-auto"><a href="#" class="forgot-pass">Forgot Password</a></span> 
              </div>
			
               <button type="submit" class="btn text-white btn-block btn-primary">Login </button>
               <br>
               <a href="/register/showRegistrationForm">Register here!</a>

              <span class="d-block text-left my-4 text-muted"> or sign in with</span>
              
              <div class="social-login">
                <a href="/oauth2/authorization/facebook" class="facebook">
                  <span class="icon-facebook mr-3"></span> 
                </a>
                <a href="/oauth2/authorization/google" class="google">
                  <span class="icon-google mr-3"></span> 
                </a>
              </div>
            </form:form>
            </div>
          </div>
          
        </div>
        
	
		</div>
		
	</div>
	
</body>
</html>