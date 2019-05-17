<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.securitymagick.domain.Permissions" %>
<%@ page import="com.securitymagick.web.cookie.CookieHandler" %>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Delete Public ${siteLook.title} Posts</title>
 
<spring:url value="/resources/core/css/hello.css" var="coreCss" />
<spring:url value="/resources/core/css/bootstrap.min.css" var="bootstrapCss" />
<link href="${bootstrapCss}" rel="stylesheet" />
<link href="${coreCss}" rel="stylesheet" />
</head>
 
<jsp:include page='navbar.jsp'></jsp:include>
 
  <div class="container">
	<br/><br/>
	<h1>${post.title}</h1>
	<jsp:include page='printMessage.jsp'></jsp:include>
		<% 
		Boolean showAdmin = false;
		CookieHandler p = new CookieHandler("permissions");
		if (p.checkForCookie(request)) {
			Cookie c = p.getCookie();
			Permissions userPermissions = new Permissions(c.getValue());
			if (userPermissions.getAdmin()) {
				showAdmin = true;
			}
		}
		if (showAdmin) {
	%>
	<img src="/${siteLook.url}/resources/core/images/${post.imageName}">
	<h3> Posted by ${post.author} <h3>
	
	<form:form id="fm1" modelAttribute="postToDelete" method="post" action="deletepost">
	<form:input type="hidden" name="id" path="id" value="${post.id}" />

	<div class="row">
		<div class="${siteLook.labelCols}">
		</div>
		<div class="${siteLook.formCols}">
		<p> 
			<button class="btn btn-info btn-lg" type="submit" class="form-control" value="Submit">Delete</button> 
		</p>
		</div>
	</div>	
	</form:form>
	<hr>
	<jsp:include page='menu.jsp'></jsp:include>
	<% } %>
  <jsp:include page='footer.jsp'></jsp:include>
</div>

<jsp:include page='javascript_includes.jsp'></jsp:include>
 
</body>
</html>