<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<c:url var="productURL" value="/admin/products/list"/>
<c:url var="deleteURL" value="/admin/products/"/>
<!DOCTYPE html>
<html>
<head>
	<title>List Products</title>
	
	
</head>
<body >
	<div class="main-content">
		<div class="page-content">
			<h3>List of Products</h3>
			<!-- add a box search -->
			<form:form action="search" method="GET">
				
				Search Product: <input type="text" name="theSearchValue"/>
				
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
						<security:authorize access="hasRole('ADMIN')">
						
							<div class="dt-buttons btn-overlap btn-group">
								<c:url var="createProductURL" value="/admin/products/edit"></c:url>
												<a flag="info"
												   class="dt-button buttons-colvis btn btn-white btn-primary btn-bold" data-toggle="tooltip"
												   title='Add product' href='${createProductURL}'>
															<span>
																<i class="fa fa-plus-circle bigger-110 purple"></i>
															</span>
												</a>
								<button id="btnDelete" type="button"  onclick="warningBeforeDelete()"
										class="dt-button buttons-html5 btn btn-white btn-primary btn-bold" data-toggle="tooltip" title='Delete the products'>
												<span>
													<i class="fa fa-trash-o bigger-110 pink"></i>
												</span>
								</button>
							</div>
					
						</security:authorize>
						</div>
					</div>
				</div>
			<form action="<c:url value='/admin/products/list?page=${model.page}&limit=${model.limit}'/>" id="formSubmit" method="get">
					
			  <div class="row">
				<div class="col-xs-12">
					<div class="table-responsive">
					<table class="table table-bordered">
						<thead>
	     				 <tr>
	     				 <security:authorize access="hasRole('ADMIN')">
	     				 	<th><input type="checkbox" id="checkAll"></th>
	     				 </security:authorize>
							<th><a href="">Name</a></th>
							<th><a href="">Picture</a></th>
							<th><a href="">Price</a></th>
							<th><a href="">Short Description</a></th>
						<security:authorize access="hasRole('MANAGER')">
							<th>Action</th>
						 </security:authorize>
						</tr>
	   				 </thead>
					<tbody>
						<!-- loop over and print our product -->
						<c:forEach var="tempProduct" items="${products}">

							<!-- add delete link -->
							<c:url var="deleteLink" value="/products/delete">
								<c:param name="productId" value="${tempProduct.id}"></c:param>
							</c:url>
					 	
							<tr>		
								<security:authorize access="hasRole('ADMIN')">
								<td><input type="checkbox" id="checkbox_${tempProduct.id}"  value="${tempProduct.id}" class="chkCheckBoxId"></td>
								</security:authorize>
								<td> ${tempProduct.name }</td>
								<td> <a href="/product/thumbnail/${tempProduct.id}/${tempProduct.thumbnail}" style="cursor: zoom-in;"><img src="/product/thumbnail/${tempProduct.id}/${tempProduct.thumbnail}" width="128px"  height="128px" style="border-radius: 8px;" class="img-fluid" alt="picture"></a></td>
								<td> ${tempProduct.price }</td>
								<td> ${tempProduct.shortDescription }</td>
								<security:authorize access="hasRole('MANAGER')">
									<td>
									<a class="btn btn-sm btn-primary btn-edit" data-toggle="tooltip"
										title="View product detail" href='/admin/products/${tempProduct.id}'><i class="fa fa-eye " aria-hidden="true"></i>
									</a>
									<br><br>
									<c:url var="updateProductURL" value="/admin/products/edit">
											<c:param name="id" value="${tempProduct.id}"/>															
									</c:url>															
									<a class="btn btn-sm btn-primary btn-edit" data-toggle="tooltip"
									   title="Update Product" href='${updateProductURL}'><i class="fa fa-pencil-square-o" aria-hidden="true"></i>
									</a>
									
									</td>
							    </security:authorize>
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
			function deleteNew(data) {
				 $(function () {
					    var token = "${_csrf.token}";
					    var header = "X-CSRF-TOKEN";
					    $(document).ajaxSend(function(e, xhr, options) {
					        xhr.setRequestHeader(header, token);
					    });
					});
				 
		        $.ajax({
		        	
		            url: '${deleteURL}',
		            type: 'DELETE',
		            contentType: 'application/json',
		            data: JSON.stringify(data),
		            success: function (result) {
		                window.location.href = "/admin/products/list?page=1&limit=5&message=delete_success";
		            },
		            error: function (error) {
		            	window.location.href = "/admin/products/list?page=1&limit=5&message=error_system";
		            }
		        });
		    }
		</script>
		<script type="text/javascript">
			
		$(document).ready(function () {
			$('#checkAll').click(function () {
				if ($(this).is (":checked"))
					$('.chkCheckBoxId').prop ('checked', true);
				else
					$('.chkCheckBoxId').prop ('checked', false);

			});

		});
		
		</script>

</body>
</html>