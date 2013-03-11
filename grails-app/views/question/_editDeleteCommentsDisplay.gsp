<div class="editAndDelete">
	<!-- You must be logged to edit a question -->
	<sec:ifLoggedIn>
		<g:form controller="post" action="delete">
			<g:hiddenField name="postId" value="${postInstance?.id}" />
			<g:hiddenField name="questionId" value="${questionInstance?.id}" />
			<g:link class="link" controller="Post" action="edit" params="[postId: postInstance.id, questionId: questionInstance.id]"><g:message code="edit.label"/></g:link>
			<!--  Adminstrators only can delete a question-->
			<sec:ifAllGranted roles="ROLE_ADMIN">
				&nbsp;|&nbsp;
				<g:submitButton class="link" name="submit" value="${message(code: 'delete.label')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
			</sec:ifAllGranted>
		</g:form>
		<!-- Scriptaculous !! -->
		<g:javascript src="prototype.js"/>
		<g:link url="javascript:appear${postInstance?.id}();">
			<g:message code="comment.label"/>
		</g:link>
		<div id="commentLink${postInstance?.id}" class="commentLink${postInstance?.id}" style="display:none;">
			<g:form controller="comment" action="save">
				<g:hiddenField name="postId" value="${postInstance?.id}" />
				<g:hiddenField name="questionId" value="${questionInstance?.id}" /> <!-- For redirection question is mandatory -->
				<g:textField name="commentContent" size="100" />
				<g:submitButton class="link" name="sumbit" value="${message(code: 'post.comment.label')}"/>
			</g:form>
		</div>
	</sec:ifLoggedIn>
</div>
<div>
	<table style="margin-top: 15px; font-size: 0.9em; color: #666666; border-top: 1px dashed silver;">
		<g:each in="${postInstance?.comments}" var="commentInstance">
			<tr style="border-bottom: 1px dashed silver;"><td>
				${commentInstance.content}&nbsp;
				<span style="float: right;">
					<prettytime:display date="${commentInstance.creationDate}"/>
					&nbsp;<g:message code="by.label"/>&nbsp;
					<g:link controller="User" action="show" id="${postInstance.user.id}">${postInstance?.user.username}</g:link>
					&nbsp;${postInstance?.user.reputation}
				</span>
			</td></tr>
		</g:each>
	</table>
</div>