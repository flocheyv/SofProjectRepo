<%@ page import="com.isima.sof.Question" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'question.label', default: 'Question')}" />
		<title><g:message code="default.edit.label" args="[entityName]" /></title>
	</head>
	<body>
		<div class="content">
		
			<h1><g:message code="default.edit.label" args="[entityName]" /></h1>
			
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			
			<g:hasErrors bean="${postInstance}">
			<ul class="errors" role="alert">
				<g:eachError bean="${postInstance}" var="error">
				<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
				</g:eachError>
			</ul>
			</g:hasErrors>
			<g:form method="post" controller="post" action="update">
				<g:hiddenField name="postId" value="${postInstance?.id}" />
				<g:hiddenField name="questionId" value="${questionId}" />  <!-- from post/edit action -->
				<fieldset class="form">
					<g:render template="../question/formEdit"/>
				</fieldset>
				<fieldset class="buttons">
					<g:submitButton class="save" name="submit" value="${message(code: 'update.label')}" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
