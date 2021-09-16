<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
	  <!-- Required meta tags -->
    <meta charset="utf-8">

	
	<title>Shop Single</title>
	
</head>
<body>	  

    <section class="ftco-section">
    	<div class="container">
    		<div class="row">
    			<div class="col-lg-6 mb-5 ftco-animate">
    				<a href="./product/thumbnail/${product.id}/${product.thumbnail}" class="image-popup"><img src="./product/thumbnail/${product.id}/${product.thumbnail}" class="img-fluid" alt="Colorlib Template"></a>
    			</div>
    			<div class="col-lg-6 product-details pl-md-5 ftco-animate">
    				<h3>${product.name}</h3>
    				<p class="description"><span>${product.description}</span></p>
    				<p class="price"><span>${product.price}</span></p>
    				<p></p>
						
						<div class="row mt-4">
							<div class="col-md-6">
								<div class="form-group d-flex">
		              <div class="select-wrap">
	                  <div class="icon"><span class="ion-ios-arrow-down"></span></div>
	                  <select name="" id="" class="form-control">
	                  	<option value="">Small</option>
	                    <option value="">Medium</option>
	                    <option value="">Large</option>
	                    <option value="">Extra Large</option>
	                  </select>
	                </div>
		            </div>
							</div>
							<div class="w-100"></div>
							<div class="input-group col-md-6 d-flex mb-3">
	             	<span class="input-group-btn mr-2">
	                	<button type="button" class="quantity-left-minus btn"  data-type="minus" data-field="">
	                   <i class="icon-minus"></i>
	                	</button>
	            		</span>
	             	<input type="text" id="quantity" name="quantity" class="form-control input-number" value="1" min="1" max="100">
	             	<span class="input-group-btn ml-2">
	                	<button type="button" class="quantity-right-plus btn" data-type="plus" data-field="">
	                     <i class="icon-plus"></i>
	                 </button>
	             	</span>
	          	</div>
          	</div>
          	<p><a href="cart.html" class="btn btn-primary py-3 px-5">Add to Cart</a></p>
    			</div>
    		</div>
    	</div>
    </section>

    <section class="ftco-section">
    	<div class="container">
    		<div class="row justify-content-center mb-5 pb-3">
          <div class="col-md-7 heading-section ftco-animate text-center">
          	<span class="subheading">Discover</span>
            <h2 class="mb-4">Related products</h2>
            <p>Far far away, behind the word mountains, far from the countries Vokalia and Consonantia, there live the blind texts.</p>
          </div>
        </div>
        <div class="row">
        	<c:forEach var="product" items="${products}" >
        		<c:url var="showProduct" value="/product">
        					<c:param name="id" value="${product.id }"></c:param>
        		</c:url>
        		
        		<div class="col-md-3">
        			<div class="menu-entry">
    					<a href="${showProduct}" class="img" style="background-image: url(./product/thumbnail/${product.id}/${product.thumbnail});"></a>
    					<div class="text text-center pt-4">
    						<h3><a href="${showProduct}">${product.name }</a></h3>
    						<p>${product.shortDescription}</p>
    						<p class="price"><span>${product.price}</span></p>
    						<p><a href="#" class="btn btn-primary btn-outline-primary">Add to Cart</a></p>
    					</div>
    				</div>
        		</div>
        	
        	</c:forEach>
        	
        	
        </div>
    	</div>
    </section>


</body>
</html>