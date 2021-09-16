<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
	  <!-- Required meta tags -->
    <meta charset="utf-8">

	
	<title>Menu</title>
	
</head>
<body>	  

    <section class="ftco-section">
    	<div class="container">
        <div class="row">
        
        <c:forEach var="item" items="${categories}">
        
        	<div class="col-md-6 mb-5 pb-3">
        		<h3 class="mb-5 heading-pricing ftco-animate">${item.name }</h3>
        		<c:forEach var="product" items="${item.products}">
        			
        			<c:url var="showProduct" value="/product">
        					<c:param name="id" value="${product.id }"></c:param>
        			</c:url>
        			
        			<div class="pricing-entry d-flex ftco-animate">
        			<div class="img" style="background-image: url(./product/thumbnail/${product.id}/${product.thumbnail});"><a href="${showProduct}"></a></div>
        			<div class="desc pl-3">	
	        			<div class="d-flex text align-items-center">
	        				<h3><span><a href="${showProduct}">${product.name}</a></span></h3>
	        				<span class="price">${product.price}</span>
	        			</div>
	        			<div class="d-block">
	        				<p>${product.shortDescription}</p>
	        			</div>
	        		</div>
        		</div>
        		
        		</c:forEach>
        	</div>
        </c:forEach>
        	
			
        	

        </div>
    	</div>
    </section>


</body>
</html>