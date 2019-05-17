<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.securitymagick.domain.Permissions" %>
<%@ page import="com.securitymagick.web.cookie.CookieHandler" %>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Public ${siteLook.title} Post</title>
 
<spring:url value="/resources/core/css/hello.css" var="coreCss" />
<spring:url value="/resources/core/css/bootstrap.min.css" var="bootstrapCss" />
<link href="${bootstrapCss}" rel="stylesheet" />
<link href="${coreCss}" rel="stylesheet" />
</head>
 
<jsp:include page='navbar.jsp'></jsp:include>
 
  <div class="container">
	<hr class="featurette-divider">	
      <div class="row featurette">
        <div class="col-md-7">
          <h2 class="featurette-heading"><a href="post?id=${post.id}">${post.title} </a> <span class="text-muted">Posted by ${post.author}</span></h2>
          <p class="lead">${post.description}</p>
        </div>
        <div class="col-md-5">
          <FIGURE>
		  <img class="featurette-image img-circle center-block" src="/${siteLook.url}/resources/core/images/${post.imageName}"/>
          <!--<img class="featurette-image img-responsive center-block" data-src="holder.js/500x500/auto" alt="Generic placeholder image">-->
          	<figcaption style="text-align: center;">${post.awesomeWord}!</figcaption>
 		</FIGURE>
        </div>
      </div>	

	<c:forEach items="${post.comments}" var="comment">
		<hr class="featurette-divider">
      <div class="row featurette">
        <div class="col-md-7">	
		<p class="lead">${comment}</p>		
        </div>
      </div>		
    </c:forEach>
		<% 
		Boolean showAddComment = false;
		CookieHandler p = new CookieHandler("permissions");
		if (p.checkForCookie(request)) {
			Cookie c = p.getCookie();
			Permissions userPermissions = new Permissions(c.getValue());
			if (userPermissions.getUser()) {
				showAddComment = true;
			}
		}
		if (showAddComment) {
	%>
	<hr class="featurette-divider">
	<form:form id="fm1" modelAttribute="postComment" method="post" action="post">
	<div class="row">
		<div class="col-md-4"><h2>Add a new Comment </h2></div>
		<div class="col-md-4">
			<p> Comment: </p>
		</div>
		<div class="col-md-4">
			<c:choose> 
  				<c:when test="${not empty validation.commentXss}">			
					<p> <form:input type="text" name="comment" class="form-control" path="comment" onkeyup="testText(document.forms.fm1.comment.value, 'commentCheck', document.forms.fm1.submit)" value="" /> </p>
					<p id="commentCheck"></p>
  				</c:when>
  				<c:otherwise>
  					<p> <form:input type="text" name="comment" class="form-control" path="comment" value="" /> </p>
				</c:otherwise>
			</c:choose>  				
		</div>
		
	</div>
	<form:input type="hidden" name="postid" path="postid" value="${post.id}" />
    <form:input type="hidden" name="username" path="username" value="${user}" />	
	<c:if test="${not empty validation.csrfProtect}">
		<form:input type="hidden" id="csrfToken" name="csrfToken" path="csrfToken" value="${validation.sessionCSRFStorage}" />
	</c:if> 
	<div class="row">
		<div class="col-md-4 col-md-offset-4">
		</div>
		<div class="col-md-4">
		<p> 
			<button class="btn btn-info btn-lg" type="submit" name="submit" class="form-control" value="Submit" >Add Comment</button> 
		</p>
		</div>
	</div>	
	</form:form>
	<% } %>
<hr class="featurette-divider">	
<jsp:include page='menu.jsp'></jsp:include>	
<jsp:include page='footer.jsp'></jsp:include>
</div>
 
<jsp:include page='javascript_includes.jsp'></jsp:include>
</body>
</html>