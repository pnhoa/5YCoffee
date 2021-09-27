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
	
	<div>
		
		<div id="loginbox" style="margin-top: 50px; margin-left:600px;"
			class="mainbox col-md-3 col-md-offset-2 col-sm-6 col-sm-offset-2">
			
			<div class="panel panel-info">

				<div class="panel-heading">
					<div class="panel-title">Sign In</div>
				</div>

				<div style="padding-top: 30px" class="panel-body">

					<!-- Login Form -->
					<form:form action="${pageContext.request.contextPath}/admin/authenticateTheUser" method="POST" class="form-horizontal">

					    <!-- Place for messages: error, alert etc ... -->
					    <div class="form-group">
					        <div class="col-xs-15">
					            <div>
					            	<c:if test="${param.error != null}">
										
										<i class="alert alert-danger col-xs-offset-1 col-xs-10">Sorry! You entered a invalid username/password</i>
									
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
					    </div>

						<!-- User name -->
						<div style="margin-bottom: 25px" class="input-group">
							<span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span> 
							
							<input type="text" name="username" placeholder="username" class="form-control">
						</div>

						<!-- Password -->
						<div style="margin-bottom: 25px" class="input-group">
							<span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span> 
							
							<input type="password" name="password" placeholder="password" class="form-control" >
							
							
						</div>

						<!-- Login/Submit Button -->
						<div style="margin-top: 10px" class="form-group">						
							<div class="col-sm-6 controls">
								<button type="submit" class="btn btn-success">Login</button>
							</div>
						</div>
						
					
					</form:form>

				</div>

			</div>

		</div>

	</div>

</body>
</html>