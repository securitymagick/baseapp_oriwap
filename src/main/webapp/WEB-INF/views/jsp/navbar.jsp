<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.securitymagick.domain.Permissions" %>
<%@ page import="com.securitymagick.web.cookie.CookieHandler" %>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<spring:url value="/resources/core/js/hello.js" var="coreJs" />
<spring:url value="/resources/core/js/bootstrap.min.js" var="bootstrapJs" />
 
<script src="${coreJs}"></script>
<script src="${bootstrapJs}"></script>

		

<nav class="navbar navbar-default"></nav> <!-- Dummy nav bar -->
<nav class="navbar navbar-inverse navbar-fixed-top">
  <div class="container">
	<div class="navbar-header">
		<a class="navbar-brand" href="/${siteLook.url}">
		
		<font color="${siteLook.fontColor}" size="${siteLook.logoSize}px">
		${siteLook.preLogoText}
		<c:if test="${not empty siteLook.logoGlyphOne}"><i class='glyphicon ${siteLook.logoGlyphOne}'></i></c:if>
		<c:if test="${not empty siteLook.logoGlyph2}"><i class='glyphicon ${siteLook.logoGlyph2}'></i></c:if>
		<c:if test="${not empty siteLook.logoGlyph3}"><i class='glyphicon ${siteLook.logoGlyph3}'></i></c:if>
		<c:if test="${not empty siteLook.logoGlyph4}"><i class='glyphicon ${siteLook.logoGlyph4}'></i></c:if>	
		
		<c:if test="${not empty siteLook.title}">
		-- ${siteLook.title}
		</c:if>
		
		</font>
		</a>

		<ul class="nav nav-pills pull-right">
		<c:forEach items="${navbarMenuItems}" var="navbarMenuItem">
			<c:if test="${navbarMenuItem.phrase eq ''}">
				<li><a href="${navbarMenuItem.url}">${navbarMenuItem.heading}</a></li>
			</c:if>
		</c:forEach>
		<% 
			Boolean showAlreadyLoggedIn = false;
			Boolean showAdmin = false;
			CookieHandler p = new CookieHandler("permissions");
			if (p.checkForCookie(request)) {
				Cookie c = p.getCookie();
				Permissions userPermissions = new Permissions(c.getValue());
				if (userPermissions.getUser()) {
					showAlreadyLoggedIn = true;
				}
				if (userPermissions.getAdmin()) {
					showAdmin = true;
				}
			}
			if (showAdmin) {
		%>
		<c:forEach items="${navbarMenuItems}" var="navbarMenuItem">
			<c:if test="${navbarMenuItem.phrase eq 'admin'}">
				<li><a href="${navbarMenuItem.url}">${navbarMenuItem.heading}</a></li>
			</c:if>
		</c:forEach>		
		<% } 
		   if (showAlreadyLoggedIn) {
		%>
		<c:forEach items="${navbarMenuItems}" var="navbarMenuItem">
			<c:if test="${navbarMenuItem.phrase eq 'logged in'}">
				<li><a href="${navbarMenuItem.url}">${navbarMenuItem.heading}</a></li>
			</c:if>
		</c:forEach>
		<% } %>	
		<c:if test="${not empty resetpasswordEmail}">
			<li><a href="#" data-toggle="modal" data-target="#myModal"> NEW EMAIL</a></li>
		</c:if>			
		</ul>
		
		
	</div>
  </div>
  <div id="line"></div>
</nav>
<div class="modal fade" id="myModal" role="dialog">
    <div class="modal-dialog">
    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          
          <h4 class="modal-title">Your New Reset Password Email</h4>
         </div>
        <div class="modal-body">
          <p id="resetPasswordTxt">${resetpasswordEmail}</p>
        </div>

        <div class="modal-footer">
          <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        </div>
      </div>
      
    </div>
</div>    