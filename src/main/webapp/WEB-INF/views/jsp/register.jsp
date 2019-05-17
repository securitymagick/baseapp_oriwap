<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>${siteLook.title} Registration</title>
 
<spring:url value="/resources/core/css/hello.css" var="coreCss" />
<spring:url value="/resources/core/css/bootstrap.min.css" var="bootstrapCss" />
<link href="${bootstrapCss}" rel="stylesheet" />
<link href="${coreCss}" rel="stylesheet" />
</head>
 
<jsp:include page='navbar.jsp'></jsp:include>
 

<div class="container">
	<div class="well">
	<h1>Provide the following to create an account</h1>
	<jsp:include page='printMessage.jsp'></jsp:include>
	<form:form id="fm1" modelAttribute="registerForm" method="post" action="register">
	
	<div class="row">
		<div class="${siteLook.labelCols}">
			<p> Username: </p>
		</div>
		<div class="${siteLook.formCols}">
			<c:choose> 
  				<c:when test="${not empty validation.usernameCheck}">			
					<p> <form:input type="text" path="username" name="username" class="form-control" onkeyup="testUsername(document.forms.fm1.username.value, 'userCheck', document.forms.fm1.submit)" value="" /> </p>
					<p id="userCheck"></p>
  				</c:when>
  				<c:otherwise>
  					<p> <form:input type="text" path="username" name="username" class="form-control" value="" /> </p>
  				</c:otherwise>
			</c:choose>  				
		</div>
	</div>
	
	<div class="row">
		<div class="${siteLook.labelCols}">
			<p> Password: </p>
		</div>
		<div class="${siteLook.formCols}">
			<c:choose> 
  				<c:when test="${not empty validation.passwordMeter}">
					<p> <form:input type="password" path="password" name="password" class="form-control" onkeyup="testPassword(document.forms.fm1.password.value, 'passwordMeter')" value="" /> </p>
					<p id="passwordMeter"></p>			
  				</c:when>
  				<c:otherwise>
   					<p> <form:input type="password" path="password" name="password" class="form-control" value="" /> </p>
  				</c:otherwise>
			</c:choose>		
		</div>
	</div>
	<div class="row">
		<div class="${siteLook.labelCols}">
			<p> Confirm Password: </p>
		</div>
		<div class="${siteLook.formCols}">
			<c:choose> 
  				<c:when test="${not empty validation.passwordMatch}">			
					<p> <form:input type="password" path="confirmPassword" name="confirmPassword" class="form-control" onkeyup="testPasswordMatch(document.forms.fm1.password.value, document.forms.fm1.confirmPassword.value, 'passwordMatch')" value="" /> </p>
					<p> <p id="passwordMatch"></p>	
  				</c:when>
  				<c:otherwise>
  					<p> <form:input type="password" path="confirmPassword" name="confirmPassword" class="form-control" value="" /> </p>
  				</c:otherwise>
			</c:choose>  		
		</div>
	</div>	
	<div class="row">
		<div class="${siteLook.labelCols}">
			<p> ${siteLook.favorite}: </p>
		</div>
		<div class="${siteLook.formCols}">
			<p> <form:input type="text" path="favorite" name="favorite" class="form-control" value="" /> </p>
		</div>
	</div>	
	<div class="row">
		<div class="${siteLook.labelCols}">
		</div>
		<div class="${siteLook.formCols}">
		<p> 
			<button class="btn btn-info btn-lg" type="submit" class="form-control" value="Submit" name="submit" >Register</button> 
		</p>
		</div>
	</div>
   </form:form>
   </div>
   <jsp:include page='menu.jsp'></jsp:include>
<jsp:include page='footer.jsp'></jsp:include>   
</div>
<jsp:include page='javascript_includes.jsp'></jsp:include>
</body>
</html>