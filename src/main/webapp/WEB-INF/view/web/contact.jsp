<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
	  <!-- Required meta tags -->
    <meta charset="utf-8">

	
	<title>Shop Single</title>
	<style>
		.error {color:red}
	</style>
	
</head>
<body>	 
	
	<section class="ftco-section contact-section">
      <div class="container mt-5">
        <div class="row block-9">
			<div class="col-md-4 contact-info ftco-animate">
				
				<div class="row">
					<div class="col-md-12 mb-4">
		              <h2 class="h4">Contact Information</h2>
		            </div>
		            <div class="col-md-12 mb-3">
		              <p><span>Address:</span> 02 Be Van Dan, Thanh Khe, Da Nang, Viet Nam</p>
		            </div>
		            <div class="col-md-12 mb-3">
		              <p><span>Phone:</span> <a href="tel://1234567920">0972 888 718</a></p>
		            </div>
		            <div class="col-md-12 mb-3">
		              <p><span>Email:</span> <a href="mailto:namycoffeeandfood@gmail.com">namycoffeeandfood@gmail.com</a></p>
		            </div>
		            <div class="col-md-12 mb-3">
		              <p><span>Website:</span> <a href="https://5-y-coffee.business.site/">https://5-y-coffee.business.site</a></p>
		            </div>
				</div>
			</div>
			
			<div class="col-md-1"></div>
			
          <div class="col-md-6 ftco-animate">
          		<c:if test="${not empty contact.messageNo}">
					<div class="alert alert-${contact.alert}">
 							${contact.messageNo}
					</div>
				</c:if>
          	<form:form action="/contact/send" method="POST" modelAttribute="contact" name="contactForm" class="contact-form">	
            	<div class="row">
            		<div class="col-md-6">
		                <div class="form-group">
		                  <form:input type="text" path="name" class="form-control" placeholder="Your Name" required="required"/>
		                  <form:errors path="name" cssClass="error" />
		                </div>
                	</div>
                <div class="col-md-6">
	                <div class="form-group">
	                  <form:input type="text" path="email" class="form-control" placeholder="Your Email" required="required"/>
	                  <form:errors path="email" cssClass="error" />
	                </div>
	                </div>
              </div>
              <div class="form-group">
                <form:input type="text" path="subject" class="form-control" placeholder="Subject" required="required"/>
                <form:errors path="subject" cssClass="error" />
              </div>
              <div class="form-group">
                <form:textarea path="message" cols="30" rows="7" class="form-control" placeholder="Message" required="required"/>
                <form:errors path="message" cssClass="error" />
              </div>
              <div class="form-group">
                <input type="submit" value="Send Message" class="btn btn-primary py-3 px-5">
              </div>
            </form:form>
          </div>
        </div>
      </div>
    </section> 

</body>
</html>