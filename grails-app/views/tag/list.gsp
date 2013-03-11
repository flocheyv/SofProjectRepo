
<%@ page import="com.isima.sof.Tag" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'tag.label', default: 'Tag')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<div class="content">
			<h1><g:message code="tags.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			
			<br/><span style="margin-left: 20px;"><g:message code="tag.detail" /></span><br/><br/>
			
			<table style="border: 0px solid white;">
			    <g:each in="${tagInstanceList}" status="i" var="tagInstance">
					<td>
						<div style="padding-top: 10px;">
							<table style="border: 0px solid white;">
								<tr class="tags">
									<!-- We pass the tag id in parameter in order to display questions having this tag -->
									<td>
										<g:link controller="Question" action="listByTag" id="${tagInstance.id}">${fieldValue(bean: tagInstance, field: "name")}</g:link>
									</td>
									<td class="desc">
										<span>
											&times; ${tagInstance.questions.size()}
										</span>
									</td>
								</tr>
								<tr>
									<td style="font-size: 12px; color:#888888;">${fieldValue(bean: tagInstance, field: "description")}</td>
								</tr>
							</table>
						</div>
					</td>
					<% if( (i+1)%4 == 0 ) { %>
						<tr></tr>
					<% } %>
				</g:each>
			</table>
		</div>
	</body>
</html>
