<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title>List Orders</title>
	
	
</head>
<body >
	<div class="main-content">
		<div class="page-content">
			<h3>List of Orders</h3>
			<!-- add a box search -->
			<form:form action="search" method="GET">
				
				Search Orders: <input type="text" name="theSearchValue" placeholder="Order id"/>
				
				<input type="submit"  value="Search" class="add-button"/>
				
			</form:form>
			
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
			<form action="<c:url value='/admin/orders/list?page=${model.page}&limit=${model.limit}'/>" id="formSubmit" method="get">
					
			  <div class="row">
				<div class="col-xs-12">
					<div class="table-responsive">
					<table class="table table-bordered">
				
						<thead>
	     				 <tr>
							<th><a href="">ID</a></th>
							<th><a href="">Order Date</a></th>
							<th><a href="">Customer ID</a></th>
							<th><a href="">Total Price</a></th>
							<th><a href="">Address</a></th>
							<th><a href="">Note</a></th>
							<th>Action</th>
						</tr>
	   				 </thead>
					<tbody>
						<!-- loop over and print our orders -->
						<c:forEach var="tempOrder" items="${orders}">
							<tr>		
								<td> ${tempOrder.id }</td>
								<td> ${tempOrder.createdDate }</td>
								<td> ${tempOrder.customer.id }</td>
								<td> ${tempOrder.totalPrice } VND</td>
								<td> ${tempOrder.address }</td>
								<td> ${tempOrder.note }</td>
								<td>
									<a class="btn btn-sm btn-primary btn-edit" data-toggle="tooltip"
										title="View an order detail" href='/admin/orders/${tempOrder.id}'><i class="fa fa-eye " aria-hidden="true"></i>
									</a>
								</td>
							   
							</tr>
						
						</c:forEach>
						
						</tbody>
					</table>
						<nav aria-label="Page navigation">
			        		<ul class="pagination" id="pagination"></ul>
			    		</nav>	
						<input type="hidden" value="" id="page" name="page"/>
						<input type="hidden" value="" id="limit" name="limit"/>	
				</div>
			</div>
		</div>
							
			</form>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		var currentPage = ${model.page}
		var totalPage = ${model.totalPage}
		$(function () {
	        window.pagObj = $('#pagination').twbsPagination({
	            totalPages: totalPage,
	            visiblePages: 10,
	            startPage: currentPage,
	            onPageClick: function (event, page) {
	            	if (currentPage != page) {
	            		$('#limit').val(5);
						$('#page').val(page);
						$('#formSubmit').submit();
					}
	            }
	        });
	    });
		
			function warningBeforeDelete() {
				swal({
				  title: "Confirm Delete",
				  text: "Are you sure to delete?",
				  type: "warning",
				  showCancelButton: true,
				  confirmButtonClass: "btn-success",
				  cancelButtonClass: "btn-danger",
				  confirmButtonText: "Confirm",
				  cancelButtonText: "Cancel",
				}).then(function(isConfirm) {
				  if (isConfirm) {
						var ids = $('tbody input[type=checkbox]:checked').map(function () {
				            return $(this).val();
				        }).get();
						deleteNew(ids);
				  } else{
					  swal("Cancelled", "Delete Cancel", "error");
				  }
				  
				});
		} 
			
		    
		</script>
		
</body>
</html>