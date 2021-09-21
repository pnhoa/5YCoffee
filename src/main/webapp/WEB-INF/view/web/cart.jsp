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
						    	<c:forEach var="cartItem" items="${sessionScope.myCartItems}" varStatus="loop">
						    		<input type="hidden" name="id" value="${cartItem.product.id}">
						    		<tr class="text-center">
							        <td class="product-remove"><a href="${pageContext.request.contextPath}/checkout/remove/${cartItem.product.id}"><span class="icon-close" onclick="return confirm('Are you sure you want to Remove?');"></span></a></td>
							        
							        <td class="image-prod"><div class="img" style="background-image:url(/product/thumbnail/${cartItem.product.id}/${cartItem.product.thumbnail});"></div></td>
							        
							        <td class="product-name">
							        	<h3>${cartItem.product.name}</h3>
							        	<p>${cartItem.product.shortDescription}</p>
							        </td>
							        
							        <td class="price" id="price_${loop.index}">${cartItem.product.price}</td>
							        
							        <td class="quantity">
							        	<div class="row mt-4">
											<div class="input-group col-md-6 d-flex mb-3">
								             	<span class="input-group-btn mr-2">
								                	<button type="button" id = "quantity-left-minus_${loop.index}" class="quantity-left-minus btn"  data-type="minus" onclick="clickLeft(${loop.index},${sessionScope.cartItemNum})" data-field="">
								                   <i class="icon-minus"></i>
								                	</button>
								            		</span>
								             	<input type="text" id="quantity_${loop.index}" name="quantity" class="form-control input-number" value="${cartItem.quantity}" min="1" max="100">
								             	<span class="input-group-btn ml-2">
								                	<button type="button" id="quantity-right-plus_${loop.index}" class="quantity-right-plus btn" data-type="plus" onclick="clickRight(${loop.index},${sessionScope.cartItemNum})" data-field="">
								                     <i class="icon-plus"></i>
								                 </button>
								             	</span>
								          	</div>
				          				</div>
						          </td>
							        
							        <td class="total" id ="total_${loop.index}">${cartItem.totalPrice} VND</td>
						    	
						    	</c:forEach>
						    </tbody>
						  </table>
					  </div>
    			</div>
    		</div>
    		<br>
    		
    		
    		<div class="row justify-content-end">
    			<div class="col col-lg-3 col-md-6 mt-5 cart-wrap ftco-animate">
    				<div class="cart-total mb-3">
    					<h3>Cart Totals</h3>
    					<p class="d-flex">
    						<span>Subtotal</span>
    						<span class="subtotal">${sessionScope.myCart.totalPrice} VND</span>
    					</p>
    					
    				</div>
    				
    				<p class="text-center"><input type="submit" class="btn btn-primary py-3 px-5" value="Proceed to Checkout"></p>
    			</div>
    			
    		</div>
    		</form:form>
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


   <script>
		function clickRight(index, totalItem){
			var quantity = parseInt($('#quantity'+'_'+ index).val());
			
			$('#quantity'+'_'+ index).val(quantity + 1);
			
			var totalPrice = ((quantity+1) * parseFloat($('#price'+'_'+ index).text())).toFixed(2) ;
			
			$('#total'+'_'+ index).text(totalPrice);
			
			var subTotal = computeTotalPrice(totalItem).toFixed(2);

			$('.subtotal').text(subTotal);
		}

		function clickLeft(index, totalItem){
		    var quantity = parseInt($('#quantity'+'_'+ index).val());
		    
	            if(quantity>1){
	            	
	           	 	$('#quantity'+'_'+ index).val(quantity - 1);
	           	 	
	           		var totalPrice = ((quantity-1) * parseFloat($('#price'+'_'+ index).text())).toFixed(2) ;
	 			
	 				$('#total'+'_'+ index).text(totalPrice);
	
	            }
	            var subTotal = computeTotalPrice(totalItem).toFixed(2);

 				$('.subtotal').text(subTotal);
		}	
		
		function computeTotalPrice(totalItem){
			var i = 0;
			var subTotal = 0.00;
			for(i = 0 ; i < totalItem ; i++ ){
				subTotal += parseFloat($('#total'+'_'+ i).text());
			}
			return subTotal;
		}
			  
	</script>
	
</body>
</html>