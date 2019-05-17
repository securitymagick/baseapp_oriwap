<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Add New ${siteLook.addWhat}</title>
 
<spring:url value="/resources/core/css/hello.css" var="coreCss" />
<spring:url value="/resources/core/css/bootstrap.min.css" var="bootstrapCss" />
<link href="${bootstrapCss}" rel="stylesheet" />
<link href="${coreCss}" rel="stylesheet" />
</head>
 

<jsp:include page='navbar.jsp'></jsp:include>

 

<div class="container">
	<jsp:include page='printMessage.jsp'></jsp:include>
	<h1>Add New ${siteLook.addWhat}</h1>

	<c:if test="${not empty part1}">
	<form id="fm1" enctype="multipart/form-data" method="post" action="addPost?uploadfile=yes">
	<div class="row">
		<div class="${siteLook.labelCols}">
			<p> Select Image file to upload: </p>
		</div>
		<div class="${siteLook.formCols}">
			<p> <input type="file" name="file" class="form-control"  value="" /> </p>
		</div>
	</div>
	<div class="row">
		<div class="${siteLook.labelCols}">
		</div>
		<div class="${siteLook.formCols}">
		<p> 
			<button class="btn btn-info btn-lg" type="submit" class="form-control" value="Submit" >Upload</button> 
		</p>
		</div>
	</div>
    </form>
	</c:if>
	<c:if test="${not empty part2}">
	<br/><img src="/${siteLook.url}/resources/core/images/${imageName}"/>
	<form:form id="fm2" modelAttribute="zooBabyForm" method="post" action="addPost?createpost=yes">
	<div class="row">
		<div class="${siteLook.labelCols}">
			<p> Enter Awesome Title: </p>
		</div>
		<div class="${siteLook.formCols}">
			<c:choose> 
  				<c:when test="${not empty validation.postTitleXss}">			
					<p> <form:input type="text" name="title" path="title"  class="form-control" onkeyup="testText(document.forms.fm2.title.value, 'checkTitle', document.forms.fm2.submit)" value="" /> </p>
					<p id="checkTitle"></p> 
  				</c:when>
  				<c:otherwise>
  					<p> <form:input type="text" name="title" path="title"  class="form-control" value="" /> </p>
  				</c:otherwise>
			</c:choose>  			
			    <form:input type="hidden" name="author" path="author" value="${user}" />
				 <form:input type="hidden" name="imageName" path="imageName" value="${imageName}" />
				<c:if test="${not empty validation.csrfProtect}">
					<form:input type="hidden" id="csrfToken" name="csrfToken" path="csrfToken" value="${validation.sessionCSRFStorage}" />
				</c:if> 
		</div>
	</div>
	<div class="row">
		<div class="${siteLook.labelCols}">
			<p> Enter Fun Description: </p>
		</div>
		<div class="${siteLook.formCols}">
			<c:choose> 
  				<c:when test="${not empty validation.postXss}">			
					<p> <form:textarea type="text" name="description" path="description" class="form-control" onkeyup="testText(document.forms.fm2.description.value, 'checkDescription', document.forms.fm2.submit)" value="" /> </p>
					<p id="checkDescription"></p> 
  				</c:when>
  				<c:otherwise>
  					<p> <form:textarea type="text" name="description" path="description" class="form-control" value="" /> </p>
  				</c:otherwise>
			</c:choose>  				
			
		</div>
	</div>	
	<div class="row">
		<div class="${siteLook.labelCols}">
		</div>
		<div class="${siteLook.formCols}">
		<p> 
			<button class="btn btn-info btn-lg" type="submit" name="submit" value="Submit" >Submit</button> 
		</p>
		</div>
	</div>
    </form:form>
	</c:if>
	<jsp:include page='menu.jsp'></jsp:include>
   <jsp:include page='footer.jsp'></jsp:include>
</div>
<jsp:include page='javascript_includes.jsp'></jsp:include>
 
</body>
</html>