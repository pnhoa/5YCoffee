<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
	  <!-- Required meta tags -->
    <meta charset="utf-8">

	
	<title>Orders History</title>
	
</head>
<body>	
	
	<section class="ftco-section ftco-cart">
			<div class="container">
				 <div class="row">
					<div class="col-xs-12">
						<div class="table-responsive">
						<table class="table table-bordered">
							<thead>
		     				 <tr>
								<th><a href="">ID</a></th>
								<th><a href="">Order Date</a></th>
								<th><a href="">Product</a></th>
								<th><a href="">Quantity</a></th>
								<th><a href="">Total Price</a></th>
								<th><a href="">Status</a></th>
							</tr>
		   				 </thead>
						<tbody>
							<!-- loop over and print our user -->
							<c:forEach var="tempOrderItem" items="${orderItems}">
								<tr>		
									<td> ${tempOrderItem.id }</td>
									<td> ${tempOrderItem.createdDate }</td>
									<td><a href="/product?id=${tempOrderItem.product.id }"> ${tempOrderItem.product.name }</a></td>
									<td> ${tempOrderItem.quantity }</td>
									<td> ${tempOrderItem.totalPrice }</td>
									<td> ${tempOrderItem.statusMsg }</td>
									
								</tr>
							
							</c:forEach>
							</tbody>
						</table>
							
					</div>
				</div>
			</div>
				
			</div>
			
		</section>

</body>
</html>