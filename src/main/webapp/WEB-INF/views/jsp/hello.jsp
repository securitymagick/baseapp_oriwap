<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.securitymagick.domain.Permissions" %>
<%@ page import="com.securitymagick.web.cookie.CookieHandler" %>

<!DOCTYPE html>
<html lang="en">
<head>
<title>${siteLook.title}</title>
 
<spring:url value="/resources/core/css/hello.css" var="coreCss" />
<spring:url value="/resources/core/css/bootstrap.min.css" var="bootstrapCss" />
<link href="${bootstrapCss}" rel="stylesheet" />
<link href="${coreCss}" rel="stylesheet" />
</head>
 
<jsp:include page='navbar.jsp'></jsp:include>
 
  <div class="container">
	<p>
		<jsp:include page='printMessage.jsp'></jsp:include>
 
		<c:if test="${empty message}">
			Welcome!
		</c:if>
    </p>
    <p/>
    
	<jsp:include page='menu.jsp'></jsp:include>
 
 
  <jsp:include page='footer.jsp'></jsp:include>
</div>
 
<jsp:include page='javascript_includes.jsp'></jsp:include>
</body>
</html>