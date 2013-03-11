
<%@ page import="com.isima.sof.User" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${userInstance.username}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<div class="content">
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			
			<table style="border: 0px; margin-top: 20px;">
				<tr>
					<td>
						<ol class="property-list user">
						<li class="fieldcontain">
							<span class="property-label"><g:message code="username.label" /></span>
							<strong>
								<span class="property-value"><g:fieldValue bean="${userInstance}" field="username"/></span>
							</strong>
						</li>
						
						<li class="fieldcontain">
							<span class="property-label"><g:message code="website.label" /></span>
							<span class="property-value">
								<a href="http://${userInstance?.website}"><g:fieldValue bean="${userInstance}" field="website"/></a>
							</span>
						</li>
						
						<li class="fieldcontain">
							<span class="property-label"><g:message code="location.label" /></span>
							<span class="property-value"><g:fieldValue bean="${userInstance}" field="location"/></span>
						</li>
						
						<li class="fieldcontain">
							<span class="property-label"><g:message code="reputation.label" /></span>
							<span class="property-value"><g:fieldValue bean="${userInstance}" field="reputation"/></span>
						</li>
						
						<li class="fieldcontain">
							<span class="property-label"><g:message code="age.label" /></span>
							<span class="property-value"><g:fieldValue bean="${userInstance}" field="age"/></span>
						</li>
						</ol>
					</td>
					<td>
						<g:if test="${userInstance?.badges}">
							<span style="color: #444444;"><g:message code="badges.label" /></span>
							<g:each in="${userInstance.badges}">
								<table class="tableBadge" style="width: 10%;">
									<tr class="badges">
										<td class="badge">
											<g:link controller="Badge" action="list">
												${it.name}
											</g:link>
										</td>
									</tr>
								</table>
							</g:each>
						</g:if>
					</td>
					<td>
						<div>
							<g:if test="${userInstance?.avatar}">
			  					<img class="avatar" width="100" src="${createLink(controller:'User', action: 'retrieveAvatar', id:userInstance.ident())}" />
							</g:if>
						</div>
					</td>
				</tr>
			</table>
			
			<!-- If the connected user is admin or wether it is his own profile -->
			<ss:ifUserModifyAuth value="${userInstance}">
				<g:form>
					<fieldset class="buttons">
						<g:hiddenField name="id" value="${userInstance?.id}" />
						<g:link class="edit" action="edit" id="${userInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					</fieldset>
				</g:form>
			</ss:ifUserModifyAuth>
			
			<h1 style="margin-top: 30px;"><g:message code="actions.sum.up" />&nbsp;<strong>${userInstance.username}</strong></h1>
			<table style="margin-top: 20px; border: 0px;">
				<tr>
					<td>
						<strong>${questionInstanceList.size()}&nbsp;<g:message code="questions" /></strong>
						<span style="float: right; margin-right: 6px;"><g:message code="votes" /></span>
						<table>
							<g:each var="question" in="${questionInstanceList}">
								<tr style="border: 1px dashed silver;"><td>
									<img src="${resource(dir: 'images', file: 'interrogation.png')}" alt="Question"/>
									<g:link controller="question" action="show" id="${question.id}">${question.title}</g:link>
									<span style="float: right;"><strong>+&nbsp;${question.votesNb}</strong></span>
								</td></tr>
							</g:each>
						</table>
					</td>
					<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					</td>
					<td>
						<strong><g:message code="answers.by.question" /></strong>
						<span style="float: right; margin-right: 6px;"><g:message code="votes" /></span>
						<table>
							<g:each var="question" in="${questionInstanceList}">
								<g:if test="${question.answers.size() > 0}">
									<tr style="border: 1px dashed silver;"><td>
										<img src="${resource(dir: 'images', file: 'interrogation.png')}" alt="Question"/>
										${question.title}
									</td></tr>
									<g:each var="answer" in="${question.answers}">
										<tr style="border: 1px dashed silver;"><td>
											<g:link controller="question" action="show" id="${question.id}">${answer.content}</g:link>
											<span style="float: right;"><strong>+&nbsp;${answer.votesNb}</strong></span>
										</td></tr>
									</g:each>
								</g:if>
							</g:each>
						</table>
					</td>
				</tr>
			</table>
				
		</div>
	</body>
</html>
