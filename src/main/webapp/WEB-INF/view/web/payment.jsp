<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
	  <!-- Required meta tags -->
    <meta charset="utf-8">

	
	<title>Payment</title>
	
</head>
<body>

	<section class="ftco-section">
      <div class="container">
        <div class="row">
        <form:form action="${pageContext.request.contextPath}/checkout/orders" method="post" class="billing-form ftco-bg-dark p-3 p-md-5" name="ordersForm">
          <div class="col-xl-8 ftco-animate">
				<input type="hidden" name="id" value="${sessionScope.customer.id}">
				<h3 class="mb-4 billing-heading">Billing Details</h3>
	          	<div class="row align-items-end">
	          		<div class="col-md-6">
	                <div class="form-group">
	                	<label for="firstname">First Name</label>
	                  <input type="text" class="form-control" name="firstName" value="${sessionScope.customer.firstName}" placeholder="" readonly>
	                </div>
	              </div>
	              <div class="col-md-6">
	                <div class="form-group">
	                	<label for="lastname">Last Name</label>
	                  <input type="text" class="form-control" name="lastName" value="${sessionScope.customer.lastName}" placeholder="" readonly>
	                </div>
                </div>
                <div class="w-100"></div>
		           
		            <div class="w-100"></div>
		            <div class="col-md-12">
		            	<div class="form-group">
	                	<label for="streetaddress">Address</label>
	                  <input type="text" class="form-control" name="address" value="${sessionScope.customer.address}" placeholder="House number and street name" required>
	                </div>
		            </div>
		            
		           
		            <div class="w-100"></div>
		            <div class="col-md-6">
	                <div class="form-group">
	                	<label for="phone">Phone</label>
	                  <input type="text" class="form-control" name="phone" value="${sessionScope.customer.phone}" placeholder="" readonly>
	                </div>
	              </div>
	              <div class="col-md-6">
	                <div class="form-group">
	                	<label for="emailaddress">Email Address</label>
	                  <input type="text" class="form-control" name="email" value="${sessionScope.customer.email}" placeholder="" readonly>
	                </div>
                </div>
                <div class="w-100"></div>
                <div class="col-md-12">
	                <div class="form-group">
	                	<label for="emailaddress">Note</label>
	                  <input type="text" class="form-control" name="note" value="" placeholder="Optional (other number phone)" >
	                </div>
                </div>
                <div class="w-100"></div>
               </div>
               </div> 
	         



	          <div class="row mt-5 pt-3 d-flex">
	          	<div class="col-md-6 d-flex">
	          		<div class="cart-detail cart-total ftco-bg-dark p-3 p-md-4">
	          			<h3 class="billing-heading mb-4">Cart Total</h3>
	          			<p class="d-flex">
		    						<span>Subtotal</span>
		    						<span>${sessionScope.myCart.totalPrice} VND</span>
		    					</p>
		    					<p class="d-flex">
		    						<span>Delivery</span>
		    						<span>0.00 VND</span>
		    					</p>
		    			
		    					<hr>
		    					<p class="d-flex total-price">
		    						<span>Total</span>
		    						<span>${sessionScope.myCart.totalPrice} VND</span>
		    					</p>
		    					<p><input type="submit" class="btn btn-primary py-3 px-4" value="Place an order"></p>
								</div>
	          	</div>
	          	
	          </div>
	           </form:form><!-- END -->
          </div> <!-- .col-md-8 -->
          
       </div>
      
    </section>

</body>
</html>