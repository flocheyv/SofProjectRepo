
<g:set var="userInstance" value="${questionInstance.user}" />

<!-- If i != null, first border-bottom will be hide (presentation detail for tabs especially) 
     Use tabIndex if there is several list of questions (with tabs for example) -->
<table id="table${i}${tabIndex}">
	<tr>
		<td class="votesInfo">
			<ff:formatNumberScaled number="${fieldValue(bean: questionInstance, field: "votesNb")}" />
		</td>
		<td class="answersInfo">
			<ff:formatNumberScaled number="${questionInstance.answers.size()}" />
		</td>
		<td class="viewsInfo">
			<ff:formatNumberScaled number="${fieldValue(bean: questionInstance, field: "viewsNb")}" />
		</td>
		<td><g:link action="show" id="${questionInstance.id}" class="question">${fieldValue(bean: questionInstance, field: "title")}</g:link></td>
		<td></td>
	</tr>
	<tr>
		<td class="votesInfo"><g:message code="votes.label" /></td>
		<td class="answersInfo"><g:message code="answers.label" /></td>
		<td class="viewsInfo"><g:message code="views.label" /></td>
		<td>
			<div class="tags">
				<table class="tableMenu"><tr>
					<g:each in="${questionInstance?.tags}">
						<td>
							<g:link controller="Question" action="listByTag" id="${it.id}">${it.name}</g:link>
						</td>
					</g:each>
				</tr></table>
			</div>
		</td>
		<td style="float: right; margin-right: 10px; margin-top: -10px;">
			<span style="float:right; font-size: 0.8em; color: #666666;">
				<strong>asked</strong>&nbsp;<prettytime:display date="${questionInstance.creationDate}"/>
			</span>
			<br/>
			<div id="avatar">
				<g:if test="${userInstance?.avatar}">
  					<img class="avatar" width="40" src="${createLink(controller:'User', action: 'retrieveAvatar', id:userInstance.ident())}" />
				</g:if>
			</div>
			<g:link controller="User" action="show" id="${questionInstance.user.id}">${questionInstance.user.username}</g:link>
			&nbsp;
			<span style="font-size: 0.9em; color: #222222;">
				<strong><ff:formatNumberScaled number="${questionInstance.user.reputation}"/></strong>
			</span>
		</td>
	</tr>
</table>