
<%@ page import="com.isima.sof.User" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'user.label', default: 'User')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<div class="content" >
			<h1><g:message code="users.label" /></h1>
				<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			
			<table style="border: 0px solid white;">
			    <g:each in="${userInstanceList}" status="i" var="userInstance">
					<td>
						<div id="avatar" style="float: left; margin-right: 10px;">
							<g:if test="${userInstance?.avatar}">
			  					<img class="avatar" width="40" src="${createLink(controller:'User', action: 'retrieveAvatar', id:userInstance.ident())}" />
							</g:if>
						</div>
						<g:link controller="User" action="show" id="${userInstance.id}">${userInstance.username}</g:link>
						&nbsp;
						<span style="font-size: 0.9em; color: #222222;">
							<strong><ff:formatNumberScaled number="${userInstance.reputation}"/></strong>
						</span>
					</td>
					<% if( (i+1)%4 == 0 ) { %>
						<tr></tr>
					<% } %>
				</g:each>
			</table>
			<div class="pagination">
				<g:paginate total="${userInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
