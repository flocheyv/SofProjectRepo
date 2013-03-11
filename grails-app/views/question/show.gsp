
<%@ page import="com.isima.sof.Question" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'question.label', default: 'Question')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
		
		<!-- For scriptaculous -->
		<g:javascript>
			function appear${questionInstance?.id}() {
				// Effect for question
				Effect.SlideDown($('commentLink${questionInstance?.id}'))
			}
			
			// Effect for each answers
			<g:each in="${questionInstance.answers}" var="answerInstance">
				function appear${answerInstance?.id}() {
					// Effect for question
					Effect.SlideDown($('commentLink${answerInstance?.id}'))
				}
			</g:each>
		</g:javascript>
		
	</head>
	<body>
		<div class="content">
			<h1>${questionInstance?.title}</h1>
			
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			
			<g:render template="questionDetailDisplay" model="[questionInstance:questionInstance]"/>
			
		</div>
	</body>
</html>
