<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<nav class="navbar navbar-expand-lg navbar-dark ftco_navbar bg-dark ftco-navbar-light" id="ftco-navbar">
	    <div class="container">
	      <a class="navbar-brand" href="<c:url value='/'/>">5Y <small>coffee</small> </a>
	      <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#ftco-nav" aria-controls="ftco-nav" aria-expanded="false" aria-label="Toggle navigation">
	        <span class="oi oi-menu"></span> Menu
	      </button>
	      <div class="collapse navbar-collapse" id="ftco-nav">
	        <ul class="navbar-nav ml-auto">
	          <li class="nav-item active"><a href="<c:url value='/'/>" class="nav-link">Home</a></li>
	          <li class="nav-item"><a href="<c:url value='/menu'/>" class="nav-link">Menu</a></li>
	          <li class="nav-item"><a href="<c:url value='/services'/>" class="nav-link">Services</a></li>
	          <li class="nav-item"><a href="<c:url value='/'/>" class="nav-link">Blog</a></li>
	          <li class="nav-item "><a href="<c:url value='/'/>" class="nav-link">About</a></li>
	          <li class="nav-item dropdown">
              <a class="nav-link dropdown-toggle" href="room.html" id="dropdown04" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Shop</a>
              <div class="dropdown-menu" aria-labelledby="dropdown04">
              	<a class="dropdown-item" href="shop.html">Shop</a>
                <a class="dropdown-item" href="product-single.html">Single Product</a>
                <a class="dropdown-item" href="room.html">Cart</a>
                <a class="dropdown-item" href="checkout.html">Checkout</a>
              </div>
            </li>
	          <li class="nav-item"><a href="contact.html" class="nav-link">Contact</a></li>
	        <c:if test="${sessionScope.customer != null}">
	        	 <li class="nav-item dropdown">
		              <a class="nav-link dropdown-toggle" href="/customer/account/" id="dropdown04" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"><span class="icon-user" ></span> Account</a>
		              <div class="dropdown-menu" aria-labelledby="dropdown04">
		              	<a class="dropdown-item" href="/account">Customer Info</a>
		                <a class="dropdown-item" href="/orders/history">Order History</a>
		                <form:form action="${pageContext.request.contextPath}/logout" method="POST" hidden="true" name="logoutForm">
						
							<input type="submit" value="Logout" style="border: none; background: none;" class="nav-link"/>
		
						</form:form>
						<a class="dropdown-item" href="javascript: document.logoutForm.submit()" >Sign out</a>
		                
		              </div>
           		 </li>	
	         </c:if>
	      
	          
	          <c:if test="${sessionScope.customer == null}">
	          	 <li class="nav-item dropdown">
		              <a class="nav-link dropdown-toggle" href="/login/showLoginForm" id="dropdown04" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
		              
		              	<span class="icon-user" ></span> Hello, Sign in
		              	
		              </a>
		              <div class="dropdown-menu" aria-labelledby="dropdown04">
		              	<a class="dropdown-item" href="/login/showLoginForm">Sign in</a>
		                <a class="dropdown-item" href="/register/showRegistrationForm">Register</a>
		                <a class="dropdown-item" href="/oauth2/authorization/google"><span class="icon-google mr-3"></span> Sign in with Google</a>
		                <a class="dropdown-item" href="/oauth2/authorization/facebook"><span class="icon-facebook mr-3"></span>Sign in with Facebook</a>
		                
		              </div>
           		 </li>	
	          </c:if>
	          
	           <li class="nav-item cart"><a href="/checkout/cart" class="nav-link"><span class="icon icon-shopping_cart"></span><span class="bag d-flex justify-content-center align-items-center">
		           <small> 
		           		<c:if test="${sessionScope.customer == null}">0</c:if>
		           		<c:if test="${sessionScope.customer != null}">${sessionScope.cartItemNum}</c:if>
		           </small>
	           </span></a></li>
	         
	          	
	         
	        </ul>
	            
	      </div>
		  </div>
</nav>
    <!-- END nav -->