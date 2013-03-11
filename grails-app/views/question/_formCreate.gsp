<%@ page import="com.isima.sof.Question" %>


<div class="fieldcontain ${hasErrors(bean: questionInstance, field: 'title', 'error')} ">
	<label for="title">
		<g:message code="title.label" />
		
	</label>
	<g:textField name="title" value="${questionInstance?.title}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: questionInstance, field: 'content', 'error')} ">
	<label for="content">
		<g:message code="content.label" />
		
	</label>
	<g:textArea name="content" value="${questionInstance?.content}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: questionInstance, field: 'tags', 'error')} ">
	<label for="tags">
		<g:message code="tags.label" />
	</label>
	<g:select name="tags" from="${com.isima.sof.Tag.list()}" multiple="multiple" optionKey="id" optionValue="name" size="5" value="${questionInstance?.tags*.id}" class="many-to-many"/>
</div>

<div style="margin-left: 185px; margin-top: 15px;">
	<img src="${resource(dir: 'images/skin', file: 'information.png')}" alt="Infos"/>
	<g:message code="tip.ctrl.select.message" />
</div>

