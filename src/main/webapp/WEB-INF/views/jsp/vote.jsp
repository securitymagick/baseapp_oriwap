<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="e" uri="https://www.owasp.org/index.php/OWASP_Java_Encoder_Project" %>
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
	<h1>Vote </h1>
	<jsp:include page='printMessage.jsp'></jsp:include>
	
	
	
	<c:if test="${not empty canVote}">
	
	<div class="well">
	<form:form id="fm1" modelAttribute="favoriteVote" method="post" action="vote">
	<div class="row">
		<div class="${siteLook.labelCols}">
			<p> Favorite ${siteLook.addWhat}: </p>
			
		</div>
		<div class="${siteLook.formCols}">
			<p id="voteTag"></p>
  			<p><form:input type="text" id="voteFor" name="voteFor" class="form-control" path="voteFor" value=""/></p> 
		</div>
		
	</div>
    <form:input type="hidden" name="username" path="username" value="${user}" />	
	<c:if test="${not empty validation.csrfProtect}">
		<form:input type="hidden" id="csrfToken" name="csrfToken" path="csrfToken" value="${validation.sessionCSRFStorage}" />
	</c:if> 
	<div class="row">
		<div class="${siteLook.labelCols}">
		</div>
		<div class="${siteLook.formCols}">
		<p> 
			<button class="btn btn-info btn-lg" type="submit" name="submit" class="form-control" value="Submit" >Vote</button> 
			<a class="btn btn-default btn-lg" id="change" href="javascript:Change()" class="form-control" role="button">Change</a>
		</p>
		</div>
	</div>	
	</form:form>
    </div>
    </c:if>
    
    <div class="well">
    <h2>Current Vote Count</h2>
	<c:forEach items="${items}" var="item">
	  
	  <hr class="featurette-divider">
      <div class="row featurette">
        <div class="col-md-4 col-md-offset-2">
          <h3 class="featurette-heading">${e:forHtml(item.voteFor)}</h3>
         </div>
           <div class="col-md-4 col-md-offset-2">
          <p class="lead">Votes: ${item.count}</p>
          </div>
          <hr class="featurette-divider">
        
      </div>
    </c:forEach>
    </div>
    <jsp:include page='menu.jsp'></jsp:include>
    <jsp:include page='footer.jsp'></jsp:include>
  
</div>
<script>
	function Change() {
		document.getElementById("voteFor").style.display = "block";
		document.getElementById("change").style.display = "none";
	}
	function setVote() {
		var url = new URL(window.location.href);
		
		var value= "";
		
		if (url.searchParams.get('voteFor')) {
			var token = url.searchParams.get("voteFor");
			sessionStorage.setItem("voteFor", token);
		}
		if (sessionStorage.getItem("voteFor")){
			document.getElementById("voteFor").value = sessionStorage.getItem("voteFor");
			document.getElementById("voteFor").style.display = "none";
			document.getElementById("voteTag").innerHTML = "You are supporting " + sessionStorage.getItem("voteFor");
		}
			
	}
	window.onload=setVote();
	</script>
 
<jsp:include page='javascript_includes.jsp'></jsp:include>
 
</body>
</html>