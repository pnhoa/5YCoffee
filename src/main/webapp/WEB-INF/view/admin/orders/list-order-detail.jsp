<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title>List Orders Detail</title>
	
	
</head>
<body >
	<div class="main-content">
		<div class="page-content">
			<h3>List of Orders Detail</h3>
		
			<br><br>
			
			<div class="col-xs-12">
				<c:if test="${not empty model.message}">
					<div class="alert alert-${model.alert}">
 							${model.message}
					</div>
				</c:if>
				<div >
					<div class="table-btn-controls">
						<div class="pull-right tableTools-container">
						
						</div>
					</div>
				</div>
			<form:form action="update"  id="formSubmit" method="post" >
			  <input type="hidden" name="orderId" value="${model.id}">
			  <div class="row">
				<div class="col-xs-12">
					<div class="table-responsive">
					<table class="table table-bordered">
				
						<thead>
	     				 <tr>
	     				
							<th><a href="">Order Date</a></th>
							<th><a href="">Product Name</a></th>
							<th><a href="">Picture</a></th>
							<th><a href="">Quantity</a></th>
							<th><a href="">Total Price</a></th>
							<th><a href="">Status</a></th>
						</tr>
	   				 </thead>
					<tbody>
						<!-- loop over and print our orders -->
						<c:forEach var="tempOrderDetail" items="${orderDetails}">
							<input type="hidden" name = "id" value="${tempOrderDetail.id}">
							<tr>		
								<td> ${tempOrderDetail.createdDate }</td>
								<td> ${tempOrderDetail.product.name }</td>
								<td> <a href="/product/thumbnail/${tempOrderDetail.product.id}/${tempOrderDetail.product.thumbnail}" style="cursor: zoom-in;"><img src="/product/thumbnail/${tempOrderDetail.product.id}/${tempOrderDetail.product.thumbnail}" width="128px"  height="128px" style="border-radius: 8px;" class="img-fluid" alt="picture"></a></td>
								<td> ${tempOrderDetail.quantity }</td>
								<td> ${tempOrderDetail.totalPrice } VND</td>
								<td>
									 <select name="status" id="status"> 
									 	<c:if test="${tempOrderDetail.status == 2}">
											<option value="${tempOrderDetail.status}" selected>${tempOrderDetail.statusMsg}</option>
										</c:if>
											
										<c:if test="${tempOrderDetail.status != 2}">
											<c:forEach var="status" items="${tempOrderDetail.statusMap }">
												<c:if test="${tempOrderDetail.status == status.key }">
													<option value="${status.key}" selected>${status.value}</option>
												</c:if>
												<c:if test="${tempOrderDetail.status != status.key }">
													<option value="${status.key}">${status.value}</option>
												</c:if>
												
																						
											</c:forEach>
												
										</c:if>
										
							  	 	</select>	
							  	 
								</td>
							</tr>
						
						</c:forEach>
						
						</tbody>
					</table>
						<a class="btn btn-success" href="javascript:history.back()" style="margin-right: 10px;">Go Back</a>
						
						<div class="pull-right ">
				
						<input type="submit" value="Update" class="dt-button buttons-html5 btn btn-gray btn-primary"/>
						</div>
				</div>
			</div>
		</div>
							
			</form:form>
			</div>
		</div>
	</div>
	
	
		
</body>
</html>