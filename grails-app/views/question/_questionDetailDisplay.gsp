
<g:set var="userInstanceQuest" value="${questionInstance.user}" />

<table class="detailPost">
	<tr>
		<td class="voteCellUp">
		    <!-- Displays the correct up-arrow (if voted or not) and also hide the link if necessary -->
			<vv:voteUp postInstance="${questionInstance}" questionInstance="${questionInstance}" />
			<br/>
			<span id="votes${questionInstance.id}" class="votesNb">
				<ff:formatNumberScaled number="${fieldValue(bean: questionInstance, field: "votesNb")}" />
			</span>
		</td>
		<td></td>
		<td></td>
		<td>${fieldValue(bean: questionInstance, field: "content")}</td>
		<td></td>
	</tr>
	<tr>
		<td>
		    <vv:voteDown postInstance="${questionInstance}" questionInstance="${questionInstance}" />
		</td>
		<td></td>
		<td></td>
		<td>
			<g:render template="../tag/tagsDisplay" model="[questionInstance:questionInstance]"/>
			<g:render template="editDeleteCommentsDisplay" model="[postInstance:questionInstance, questionInstance:questionInstance]"/>
		</td>
		<td style="float: right; margin-right: 20px; margin-top: -20px;">
			<span style="float:right; font-size: 0.8em; color: #666666;">
				<strong>asked</strong>&nbsp;<prettytime:display date="${questionInstance.creationDate}"/>
			</span>
			<br/>
			<div id="avatar" style="float: left; margin-right: 10px;">
				<g:if test="${userInstanceQuest?.avatar}">
  					<img class="avatar" width="40" src="${createLink(controller:'User', action: 'retrieveAvatar', id:userInstanceQuest.ident())}" />
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

<h1>${questionInstance.answers.size()}&nbsp;<g:message code="answers.label" /></h1>
<g:if test="${questionInstance?.answers}">
	<g:each in="${questionInstance.answers}" var="answerInstance">
		<g:set var="userInstanceAnswer" value="${answerInstance.user}" />
		<table class="detailPost" style="border-bottom: 1px solid silver">
			<tr>
				<td class="voteCellUp">
				    <!-- Displays the correct up-arrow (if voted or not) and also hide the link if necessary -->
					<vv:voteUp postInstance="${answerInstance}" questionInstance="${questionInstance}" />
					<br/>
					<span id="votes${answerInstance.id}" class="votesNb">
						<ff:formatNumberScaled number="${fieldValue(bean: answerInstance, field: "votesNb")}" />
					</span>
				</td>
				<td></td>
				<td></td>
				<td>${fieldValue(bean: answerInstance, field: "content")}</td>
				<td></td>
			</tr>
			<tr>
				<td>
				    <vv:voteDown postInstance="${answerInstance}" questionInstance="${questionInstance}"/>
				</td>
				<td></td>
				<td></td>
				<td>
					<g:render template="editDeleteCommentsDisplay" model="[postInstance:answerInstance, questionInstance:questionInstance]"/>
				</td>
				<td style="float: right; margin-right: 20px; margin-top: -20px;">
					<span style="float:right; font-size: 0.8em; color: #666666;">
						<strong>asked</strong>&nbsp;<prettytime:display date="${answerInstance.creationDate}"/>
					</span>
					<br/>
					<div id="avatar" style="float: left; margin-right: 10px;">
						<g:if test="${userInstanceAnswer?.avatar}">
		  					<img class="avatar" width="40" src="${createLink(controller:'User', action: 'retrieveAvatar', id:userInstanceAnswer.ident())}" />
						</g:if>
					</div>
					<g:link controller="User" action="show" id="${answerInstance.user.id}">${answerInstance.user.username}</g:link>
					&nbsp;
					<span style="font-size: 0.9em; color: #222222;">
						<strong><ff:formatNumberScaled number="${answerInstance.user.reputation}"/></strong>
					</span>
				</td>
			</tr>
		</table>
	</g:each>
</g:if>

<!-- You must be logged to answer question -->
<sec:ifLoggedIn>
	<div style="margin-left: 40px; margin-top: 40px;">
		<g:form controller="answer" action="save">
			<g:hiddenField name="questionId" value="${questionInstance?.id}" />
			<label for="answerContent">
				<strong><g:message code="content.answer.label"/></strong>
			</label>
			<g:textArea name="answerContent"/>
			<g:submitButton name="sumbit" value="${message(code: 'post.answer.label')}"/>
		</g:form>
	</div>
</sec:ifLoggedIn>
	
	