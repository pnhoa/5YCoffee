<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<c:url var="deleteURL" value="/admin/users/"/>
<!DOCTYPE html>
<html>
<head>
	<title>List Employees</title>
	
	
</head>
<body >
	<div class="main-content">
		<div class="page-content">
			<h3>List of Employees</h3>
			<!-- add a box search -->
			<form:form action="search" method="GET">
				
				Search Employee: <input type="text" name="theSearchValue"/>
				
				<input type="submit"  value="Search" class="add-button"/>
				
			</form:form>
			
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
							<c:url var="createEmployeeURL" value="/admin/users/add"></c:url>
									<a flag="info"
									   class="dt-button buttons-colvis btn btn-white btn-primary btn-bold" data-toggle="tooltip"
									   title='Add an employee' href="${createEmployeeURL}">
												<span>
													<i class="fa fa-plus-circle bigger-110 purple"></i>
												</span>
									</a>
								<button id="btnDelete" type="button"  onclick="warningBeforeDelete()"
										class="dt-button buttons-html5 btn btn-white btn-primary btn-bold" data-toggle="tooltip" title='Delete the employees'>
												<span>
													<i class="fa fa-trash-o bigger-110 pink"></i>
												</span>
								</button>
							</div>
					
						</security:authorize>
						</div>
					</div>
				</div>
			<form action="<c:url value='/admin/users/list?page=${model.page}&limit=${model.limit}'/>" id="formSubmit" method="get">
					
			  <div class="row">
				<div class="col-xs-12">
					<div class="table-responsive">
					<table class="table table-bordered">
				
						<thead>
	     				 <tr>
	     				 <security:authorize access="hasRole('ADMIN')">
	     				 	<th><input type="checkbox" id="checkAll"></th>
	     				 </security:authorize>
							<th><a href="">Username</a></th>
							<th><a href="">First Name</a></th>
							<th><a href="">Last Name</a></th>
							<th><a href="">Email</a></th>
							<th><a href="">Phone</a></th>
							<th><a href="">Roles</a></th>
						 <security:authorize access="hasRole('MANAGER')">
							<th>Action</th>
						 </security:authorize>
						</tr>
	   				 </thead>
					<tbody>
						<!-- loop over and print our user -->
						<c:forEach var="tempUser" items="${users}">
							
							<!-- add delete link -->
							<c:url var="deleteLink" value="/users/delete">
								<c:param name="userId" value="${tempUser.id}"></c:param>
							</c:url>
					 	
							<tr>		
								<security:authorize access="hasRole('ADMIN')">
								<td><input type="checkbox" id="checkbox_${tempUser.id}"  value="${tempUser.id}" class="chkCheckBoxId"></td>
								</security:authorize>
								<td> ${tempUser.userName }</td>
								<td> ${tempUser.firstName }</td>
								<td> ${tempUser.lastName }</td>
								<td> ${tempUser.email }</td>
								<td> ${tempUser.phone }</td>
								<td> 
									<c:forEach var="role" items="${tempUser.roles}">
										${role.name} <br>
									</c:forEach>
								</td>
								<security:authorize access="hasRole('MANAGER')">
									<td>
									<a class="btn btn-sm btn-primary btn-edit" data-toggle="tooltip"
										title="View an employee detail" href='/admin/users/${tempUser.id}'><i class="fa fa-eye " aria-hidden="true"></i>
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
		            type: 'POST',
		            contentType: 'application/json',
		            data: JSON.stringify(data),
		            success: function (result) {
		                window.location.href = "/admin/users/list?page=1&limit=5&message=delete_success";
		            },
		            error: function (error) {
		            	window.location.href = "/admin/users/list?page=1&limit=5&message=error_system";
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