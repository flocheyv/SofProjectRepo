<%@ page import="com.isima.sof.User" %>



<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'username', 'error')} required">
	<label for="username">
		<g:message code="username.label" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="username" required="" value="${userInstance?.username}"/>
</div>

<div class="fieldcontain">
	<label for="website"><g:message code="website.label" /></label>
	<g:textField name="website" value="${userInstance?.website}"/>
</div>

<div class="fieldcontain">
	<label for="location"><g:message code="location.label" /></label>
	<g:textField name="location" value="${userInstance?.location}"/>
</div>

<div class="fieldcontain">
	<label for="age"><g:message code="age.label" /></label>
	<g:textField name="age" value="${userInstance?.age}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'avatar', 'error')} ">
	<fieldset>
	    <label for="avatar">Avatar</label>
	    <input type="file" name="avatar" id="avatar" value="${userInstance?.avatar}" />
	    <div style="margin-left: 185px; margin-top: 15px;">
			<img src="${resource(dir: 'images/skin', file: 'information.png')}" alt="Infos"/>
			<g:message code="tip.avatar.message" />
		</div>
	</fieldset>
</div>