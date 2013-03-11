
<%@ page import="com.isima.sof.Question" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'question.label', default: 'Question')}" />
		<title>StackOverflow-like</title>
		
		<style type="text/css" media="screen">
			#avatar {
				float: left;
				margin-right: 5px;
			}
			
		</style>
		
		<link rel="stylesheet" href="http://code.jquery.com/ui/1.10.1/themes/base/jquery-ui.css" />
		<script src="http://code.jquery.com/jquery-1.9.1.js"></script>
		<script src="http://code.jquery.com/ui/1.10.1/jquery-ui.js"></script>
		<script>
			$(function() {
			  $( "#tabs" ).tabs({
				  // TODO: when pagination bug work, use this type of event in order to stay in the same
			      // tab when switch page
				  show: function(event, ui) {
					//$(" #ongletSelectId").val(...);
					// Cf jQuery documentation
					//var selected = $tabs.tabs('option', 'selected');
				  }
			  });
			});
		</script>
		
		<!-- We do not want border-top for the first question displayed (does not work in "script" tag) -->
		<g:javascript>
			var e0 = document.getElementById('table00');
			var e1 = document.getElementById('table01');
			var e2 = document.getElementById('table02');
			var e3 = document.getElementById('table03');
			if(e0 != null) { e0.className = 'firstElement'; }
			if(e1 != null) { e1.className = 'firstElement'; }
			if(e2 != null) { e2.className = 'firstElement'; }
			if(e3 != null) { e3.className = 'firstElement'; }
		</g:javascript>
	</head>
	<body>
		<div> <!-- Put a little bar under h1 title with class="content" -->
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			
			<!-- Tabs with jQuery -->
			<div id="tabs">
				<ul class="top-tabs">
				    <li><h1 class="titleTabs"><g:message code="top.questions.label" /></h1></li>
					<li><a href="#tab-dates"><g:message code="newest.label" /></a></li>
					<li><a href="#tab-views"><g:message code="views.label" /></a></li>
					<li><a href="#tab-votes"><g:message code="votes.label" /></a></li>
				</ul>
				<div id="tab-dates">
					<g:each in="${questionInstanceList}" status="i" var="questionInstance">
						<g:render template="questionDisplay" model="[questionInstance:questionInstance, i:i, tabIndex:0]"/>
					</g:each>
					<!-- There is a bug with the pagination because of the sort attribute in QuestionController
					     Without sort attribute, pagination works but otherwise we have always the first ten results -->
					<div class="pagination">
						<!--<*g:paginate  total="$*{questionInstanceTotal}" />-->
					</div>
				</div>
				
				<div id="tab-views">
					<g:each in="${questionInstanceListByViews}" status="i" var="questionInstance">
						<g:render template="questionDisplay" model="[questionInstance:questionInstance, i:i, tabIndex:1]"/>
					</g:each>
				</div>
				
				<div id="tab-votes">
					<g:each in="${questionInstanceListByVotes}" status="i" var="questionInstance">
						<g:render template="questionDisplay" model="[questionInstance:questionInstance, i:i, tabIndex:2]"/>
					</g:each>
				</div>
			</div>
		</div>
	</body>
</html>
