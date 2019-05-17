<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>${siteLook.title} Update Account</title>
 
<spring:url value="/resources/core/css/hello.css" var="coreCss" />
<spring:url value="/resources/core/css/bootstrap.min.css" var="bootstrapCss" />
<link href="${bootstrapCss}" rel="stylesheet" />
<link href="${coreCss}" rel="stylesheet" />
</head>
 
<jsp:include page='navbar.jsp'></jsp:include>
<jsp:include page='badcsrf.jsp'></jsp:include>
 

<div class="container">
	
	<h1>Hello, ${user} </h1>
	<jsp:include page='printMessage.jsp'></jsp:include>
	
	<div class="row">
		<div class="${siteLook.labelCols} well">
			<h2>Update your ${siteLook.favorite}</h2>
			<form:form id="fm1" modelAttribute="updateZooBabyForm" method="post" action="updateAccount?updatefavorite=yes">
			<p> ${siteLook.favorite}: </p>
			<p> <form:input type="text" path="favorite" name="favorite" class="form-control" value="${favorite}" /> </p>
			<p> 
				<button class="btn btn-info btn-lg" type="submit" class="form-control" value="Submit" >Update</button> 
			</p>
			</form:form>
		</div>
   		<div class="${siteLook.labelCols} well">
		   <h2>Update your password</h2>
   		   <form:form id="fm2" modelAttribute="updatePasswordForm" method="post" action="updateAccount?updatepassword=yes">
			<p> Password: </p>
			<c:choose> 
  				<c:when test="${not empty validation.passwordMeter}">
					<p> <form:input type="password" path="password" name="password" class="form-control" onkeyup="testPassword(document.forms.fm2.password.value, 'passwordMeter')" value="***" /> </p>
					<p id="passwordMeter"></p>			
  				</c:when>
  				<c:otherwise>
   					<p> <form:input type="password" path="password" name="password" class="form-control" value="***" /> </p>
  				</c:otherwise>
			</c:choose>
			<p> Confirm Password: </p>
			<c:choose> 
  				<c:when test="${not empty validation.passwordMatch}">			
					<p> <form:input type="password" path="confirmPassword" name="confirmPassword" class="form-control" onkeyup="testPasswordMatch(document.forms.fm2.password.value, document.forms.fm2.confirmPassword.value, 'passwordMatch')" value="***" /> </p>
					<p> <p id="passwordMatch"></p>	
  				</c:when>
  				<c:otherwise>
  					<p> <form:input type="password" path="confirmPassword" name="confirmPassword" class="form-control" value="***" /> </p>
  				</c:otherwise>
			</c:choose>  							
			<p>
				<button class="btn btn-info btn-lg" type="submit" class="form-control" value="Submit" >Reset Password</button> 
			</p>
			</form:form>
		</div>
	</div>
   
  <jsp:include page='menu.jsp'></jsp:include> 
   <jsp:include page='footer.jsp'></jsp:include>
</div>
<jsp:include page='javascript_includes.jsp'></jsp:include>
</body>
</html>