<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Public ${siteLook.title} Posts</title>
 
<spring:url value="/resources/core/css/hello.css" var="coreCss" />
<spring:url value="/resources/core/css/bootstrap.min.css" var="bootstrapCss" />
<link href="${bootstrapCss}" rel="stylesheet" />
<link href="${coreCss}" rel="stylesheet" />
</head>
 
<jsp:include page='navbar.jsp'></jsp:include>
 
  <div class="container">
	<h1>Latest Posts</h1>
	<jsp:include page='printMessage.jsp'></jsp:include>
	<div class="well">
	<c:forEach items="${posts}" var="post">
	  <hr class="featurette-divider">

      <div class="row featurette">
        <div class="col-md-7">
          <h2 class="featurette-heading"><a href="post?id=${post.id}">${post.title} </a> <span class="text-muted">${post.awesomeWord}!</span></h2>
          <p class="lead">Posted by ${post.author}</p>
        </div>
        <div class="col-md-5">
		  <img style="max-height:175px; max-width:175px" class="featurette-image img-thumbnail center-block" src="/${siteLook.url}/resources/core/images/${post.imageName}"/>
       
        </div>
      </div>
    </c:forEach>
    </div>
    <jsp:include page='menu.jsp'></jsp:include>
    <jsp:include page='footer.jsp'></jsp:include>
  
</div>
 
<jsp:include page='javascript_includes.jsp'></jsp:include>
 
</body>
</html>