<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
	  <!-- Required meta tags -->
    <meta charset="utf-8">

	
	<title>My Cart</title>
	
</head>
<body>	
	<c:if test="${sessionScope.cartItemNum != 0}">
		<section class="ftco-section ftco-cart">
			<div class="container">
			<form:form action="${pageContext.request.contextPath}/checkout/updateCart" method="POST" name="cartForm" >
			<p><input type="submit" class="btn btn-primary py-3 px-5" value="Update product"></p>
				<div class="row">
					
    			<div class="col-md-12 ftco-animate">
    				<div class="cart-list">
	    				<table class="table">
						    <thead class="thead-primary">
						      <tr class="text-center">
						        <th>&nbsp;</th>
						        <th>&nbsp;</th>
						        <th>Product</th>
						        <th>Price</th>
						        <th>Quantity</th>
						        <th>Total</th>
						      </tr>
						    </thead>
						    <tbody>
						    	<c:forEach var="cartItem" items="${sessionScope.myCartItems}">
						    		<input type="hidden" name="id" value="${cartItem.product.id}">
						    		<tr class="text-center">
							        <td class="product-remove"><a href="${pageContext.request.contextPath}/checkout/remove/${cartItem.product.id}"><span class="icon-close" onclick="return confirm('Are you sure you want to Remove?');"></span></a></td>
							        
							        <td class="image-prod"><div class="img" style="background-image:url(/product/thumbnail/${cartItem.product.id}/${cartItem.product.thumbnail});"></div></td>
							        
							        <td class="product-name">
							        	<h3>${cartItem.product.name}</h3>
							        	<p>${cartItem.product.shortDescription}</p>
							        </td>
							        
							        <td class="price">${cartItem.product.price}</td>
							        
							        <td class="quantity">
							        	<div class="row mt-4">
											<div class="input-group col-md-6 d-flex mb-3">
								             	<span class="input-group-btn mr-2">
								                	<button type="button" class="quantity-left-minus"  data-type="minus" data-field="">
								                   <i class="icon-minus"></i>
								                	</button>
								            		</span>
								             	<input type="text" id="quantity" name="quantity" class="form-control input-number" value="${cartItem.quantity}" min="1" max="100">
								             	<span class="input-group-btn ml-2">
								                	<button type="button" class="quantity-right-plus" data-type="plus" data-field="">
								                     <i class="icon-plus"></i>
								                 </button>
								             	</span>
								          	</div>
				          				</div>
						          </td>
							        
							        <td class="total">${cartItem.totalPrice}</td>
						    	
						    	</c:forEach>
						    </tbody>
						  </table>
					  </div>
    			</div>
    		</div>
    		<br>
    		
    		</form:form>
    		<div class="row justify-content-end">
    			<div class="col col-lg-3 col-md-6 mt-5 cart-wrap ftco-animate">
    				<div class="cart-total mb-3">
    					<h3>Cart Totals</h3>
    					<p class="d-flex">
    						<span>Subtotal</span>
    						<span class="subtotal">${sessionScope.myCart.totalPrice}</span>
    					</p>
    					
    				</div>
    				<p class="text-center"><a href="${pageContext.request.contextPath}/checkout/payment" class="btn btn-primary py-3 px-4">Proceed to Checkout</a></p>
    			</div>
    			
    		</div>
    		
			</div>
		</section>
	</c:if>
	
	<c:if test="${sessionScope.cartItemNum == 0}">
		<section class="ftco-section ftco-cart">
			<div class="container">
				
				<br><br><br><br>	
				
				<p class="text-center">There are no products in your cart.</p> <br>
				
				<p class="text-center"><a href="/menu" class="btn btn-primary py-3 px-4">Continue Shopping</a></p>
				
			</div>
			
		</section>
	</c:if>


 
	
</body>
</html>