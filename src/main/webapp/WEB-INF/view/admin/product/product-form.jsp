<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title>Add Product</title>
	<link type="text/css"
			rel="stylesheet" 
			href="<c:url value='/template/admin/css/add-customer-style.css' />" />
	
</head>
<body >
	
	 <div class="main-content">
	 	<div class="page-content">
		<div class="row">
			<div class="col-xs-12"> 
				<form:form class="form-horizontal" role="form" id="formSubmit" action="save" modelAttribute="product" method="POST" enctype="multipart/form-data">
					<h2>Save Product</h2>
						<div class="form-group">
							<form:errors path="name" style="color:red;" />
							<label class="col-sm-3 control-label no-padding-right" for="form-field-1">Name:</label>
							<div class="col-sm-9">
								<form:input path="name" cssClass="col-xs-10 col-sm-5"/>
							</div>
						</div>
					
						<div class="form-group">
						  	<label for="shortDescription" class="col-sm-3 control-label no-padding-right">Short Description:</label>
						  	<div class="col-sm-9">
						  		<form:textarea path="shortDescription" rows="2" cols="10" cssClass="form-control" id="shortDescription"/>
						  	</div>
						</div>
							
						<div class="form-group">
						  	<label for="content" class="col-sm-3 control-label no-padding-right">Description</label>
						  	<div class="col-sm-9">
						  		<form:textarea path="description" name="description" rows="5" cols="10" cssClass="form-control" id="description"/>
						  	</div>
						</div>	
						
						<div class="form-group">
							<form:errors path="name" style="color:red;" />
							<label class="col-sm-3 control-label no-padding-right" for="form-field-1">Price:</label>
							<div class="col-sm-9">
							<form:input path="price" cssClass="col-xs-10 col-sm-5"/>
						</div>
						</div>
						
						<div class="form-group">
							  <form:errors path="categoryCode" style="color:red;" />
							  <label for="categoryCode" class="col-sm-3 control-label no-padding-right">Category:</label>
							  <div class="col-sm-9">
							  	 <form:select path="categoryCode" id="categoryCode">
							  	 	<c:if test="${not empty product.id}">
							  	 		<form:option selected="selected" value="${product.categoryCode}" label=""/>
							  	 		<form:options items="${categories}"/>
							  	 		
							  	 	</c:if>
							  	 	
							  	 	<c:if test="${ empty product.id}">
							  	 		<form:option value="" label="-- Choose category --"/>
							  	 		<form:options items="${categories}"/>
							  	 	</c:if>
							  	 	
							  	 </form:select>
							  </div>
						</div>
							
						<div class="form-group">
							<label class="col-sm-3 control-label no-padding-right" for="form-field-1">Picture</label>
							<div class="col-sm-9">
								<c:if test="${not empty product.thumbnail}">
									<img src="/product/thumbnail/${product.id}/${product.thumbnail}" id="output" width="128px"style="margin-left: 20px; margin-right: 20px;"/>
									<form:input type="file" class="col-xs-10 col-sm-5" path="fileData" accept="image/png, image/jpeg" onchange="loadFile(event)"/>
									
								</c:if>
								
								<c:if test="${empty product.thumbnail}">
									<form:input type="file" class="col-xs-10 col-sm-5" path="fileData" accept="image/png, image/jpeg" onchange="loadFile(event)"/>
									<img id="output" width="128px"style="margin-left: 20px;"/>
								</c:if>
							</div>
						</div>	
						<div class="clearfix form-actions">
							<div class="col-md-offset-3 col-md-9">
								<a class="btn btn-danger" href="javascript:history.back()" style="margin-right: 10px;">Go Back</a>
									<c:if test="${not empty product.id}">
										
										<form:input type="submit" path="" value="Update" class="btn btn-info"/>  
									
									</c:if>
									<c:if test="${empty product.id}">
										<form:input type="submit" path="" value="Save" class="btn btn-info"/>  
									</c:if>
		
									&nbsp; &nbsp; &nbsp;
									<button class="btn" type="reset">
										<i class="ace-icon fa fa-undo bigger-110"></i>
										Reset
									</button>
									
								</div>		
							</div>
					<form:hidden path="id"/>
			
				</form:form>
			</div>
		</div>
	</div>
	</div>
<script>

	var editor = '';
	$(document).ready(function(){
		editor = CKEDITOR.replace( 'description');
	});
	
  	var loadFile = function(event) {
    var output = document.getElementById('output');
    output.src = URL.createObjectURL(event.target.files[0]);
    output.onload = function() {
      URL.revokeObjectURL(output.src) // free memory
    }
  };
</script>
	
</body>
</html>