<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<div id="navbar" class="navbar navbar-default          ace-save-state">
    <div class="navbar-container ace-save-state" id="navbar-container">
        <div class="navbar-header pull-left">
            <a href="/admin/" class="navbar-brand">
                <small>
                    <i class="fa fa-building"></i>
                    Trang quản trị
                </small>
            </a>
        </div>
        <div class="navbar-buttons navbar-header pull-right collapse navbar-collapse" role="navigation">
            <ul class="nav ace-nav">
                
                <security:authorize access="hasRole('EMPLOYEE')">
                	<li class="light-blue dropdown-modal">
	                    <a  href="${pageContext.request.contextPath}/admin/users/${sessionScope.user.id}" class="dropdown-toggle">
	                        Xin chào, <security:authentication property="principal.username"/>
	                    </a>
                	</li>
                	<li class="light-blue dropdown-modal">
                		<form:form action="${pageContext.request.contextPath}/admin/j_spring_security_logout" method="POST" hidden="true" name="logoutForm">
						
							<input type="submit" value="Logout" style="border: none; background: none;"/>
		
						</form:form>
	                    <a href="javascript: document.logoutForm.submit()">
	                        <i class="ace-icon fa fa-sign-out"></i>
	                        Logout
	                    </a>
               	   </li>
                </security:authorize>
                <c:if test="${sessionScope.user == null}">
                	<li class="light-blue dropdown-modal">
	                    <a href='<c:url value="/admin/login/showLoginForm"/>'>
	                        <i class="ace-icon fa fa-sign-in"></i>
	                        Login
	                    </a>
                	</li>
                </c:if>
                
                
            </ul>
        </div>
    </div>
</div>
