<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
	  <!-- Required meta tags -->
    <meta charset="utf-8">

	
	<title>My Account</title>
	
</head>
<body>		
	<div class="ftco-section ftco-cart">
		<div class="container">
			<div class="row">
		        <form:form action="${pageContext.request.contextPath}/account/update" modelAttribute="customer" method="post" class="billing-form ftco-bg-dark p-3 p-md-5" name="ordersForm">
		          <div class="col-xl-8 ftco-animate">
						<form:input type="hidden" path="id" value="${customer.id}"/>
						<h2>Account Information</h2> 
			          	<div class="row align-items-end">
			          		
			          		<div class="col-md-12">
				                <div class="form-group">
			                		<label for="userName">Username</label>
				                 	<form:errors path="userName" cssClass="error" />
				                  	<form:input type="text" class="form-control" path="userName" value="${customer.userName}" placeholder="" readonly="true"/>
				                </div>
			             	 </div>
			             	 
			          		<div class="col-md-6">
				                <div class="form-group">
			                		<label for="firstName">First Name</label>
				                 	<form:errors path="firstName" cssClass="error" />
				                  	<form:input type="text" class="form-control" path="firstName" value="${customer.firstName}" placeholder="First name (*)" />
				                </div>
			             	 </div>
			              <div class="col-md-6">
			                <div class="form-group">
			                	<label for="lastname">Last Name</label>
			                	<form:errors path="lastName" cssClass="error" />
			                  	<form:input type="text" class="form-control" path="lastName" value="${customer.lastName}" placeholder="Last name (*)" />
			                </div>
		                </div>
		                	<div class="col-md-12">
				                <div class="form-group">
				                	<label for="email">Email</label>
				                	<form:errors path="email" cssClass="error" />
				                  	<form:input type="text" class="form-control" path="email" value="${sessionScope.customer.email}" placeholder="" readonly="true"/>
				                </div>
		                	</div>
				            
				            
				             <div class="col-md-12">
				            	<div class="form-group">
				                	<label for="address">Address</label>
				                	<form:errors path="address" cssClass="error" />
				                  	<form:input type="text" class="form-control" path="address" value="${customer.address}" placeholder="House number and street name" />
			                	</div>
				            </div>
				            
				           
				            <div class="w-100"></div>
				            <div class="col-md-6">
			                <div class="form-group">
			                	<label for="phone">Phone</label>
			                	<form:errors path="phone" cssClass="error" />
			                  	<form:input type="text" class="form-control" path="phone" value="${customer.phone}" placeholder="Number phone" required="required" />
			                </div>
			              </div>
			              
		                <div class="col-md-6">
			            	<div class="form-group">
			                	<label for="birthdayString">Birthday</label>
			                	<form:errors path="birthdayString" cssClass="error" />
			                  	<form:input type="text" class="form-control" path="birthdayString" value="${customer.birthdayString}" placeholder="dd/MM/yyyy" />
		                	</div>
				         </div>
		                <div class="w-100"></div>
		                <div class="col-md-6">
				            <div class="form-group">
				            	<input type="checkbox" id="showChangePassword" onclick="handleClick()"> Change password
				            </div>
				        </div>	
				        
				        <div class="w-100"></div>
				        
				        <div id="showField" class="row align-items-end" style="display:none">
				        
			        		<div class="col-md-8">
			            		<div class="form-group">
				                	<label for="theOldPassword">The old password</label>
				                	<form:errors path="theOldPassword" cssClass="error" />
				                  	<form:input type="password" class="form-control" path="theOldPassword" placeholder="The old password (*)" id="theOldPassword"/>
		                		</div>
				         	</div>
			        		
				         	<div class="col-md-8">
			            		<div class="form-group">
				                	<label for="theNewPassword">The new password</label>
				                	<form:errors path="password" cssClass="error" />
				                  	<input type="password" class="form-control" name="password" value="" placeholder="The new password (*)" id="theNewPassword">
		                		</div>
				         	</div>
				         	<div class="col-md-8">
			            		<div class="form-group">
				                	<label for="theOldPassword">Repeat your password</label>
				                	<form:errors path="matchingPassword" cssClass="error" />
				                  	<form:input type="password" class="form-control" path="matchingPassword" placeholder="Repeat password (*)" id="theMatchingPassword"/>
		                		</div>
				         	</div>
				         	
				        </div>
				        
		                <div class="w-100"></div>
		                
		                <p><input type="submit" class="btn btn-primary py-3 px-5" value="Update Info"></p>
		               </div>
		               </div> 
			          
			    </form:form><!-- END -->
          </div> <!-- .col-md-8 -->
			
		</div>
			
	</div>
	

<script type="text/javascript">


function handleClick(){
	$('#showField').show();
	
	$('#theOldPassword').attr('required', true);
	$('#theNewPassword').attr('required', true);
	$('#theMatchingPassword').attr('required', true);

	
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
}




</script>	

</body>
</html>