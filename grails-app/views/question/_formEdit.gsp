<div class="fieldcontain ${hasErrors(bean: postInstance, field: 'content', 'error')} ">
	<label for="content">
		<g:message code="content.label" />
		
	</label>
	<g:textArea name="content" value="${postInstance?.content}"/>
</div>