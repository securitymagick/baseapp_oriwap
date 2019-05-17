 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
  <div class="row text-center">
    <c:forEach items="${menuItems}" var="menuItem">
    	<div class="${siteLook.menuCols} well">
		  <font color="${siteLook.fontColor}" size="${siteLook.glyphSize}px">
		    <i class='glyphicon ${menuItem.glyph}'></i>
		  </font>
		  <h2 class='text-center'>${menuItem.heading}</h2>
		  <p>${menuItem.phrase}</p>
		  <p>
			<a class="btn btn-default btn-lg" href="${menuItem.url}" role="button">View details</a>
		  </p>
	  </div>
	
    </c:forEach>
  </div>