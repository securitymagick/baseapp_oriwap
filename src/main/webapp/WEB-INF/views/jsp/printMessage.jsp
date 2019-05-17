<c:if test="${not empty message}">
	<div class="row">
		<div class="col-md-offset-3 col-md-6">
			<p class="text-danger">
			${message}
			</p>
		</div>
	</div>
</c:if>