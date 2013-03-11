<!DOCTYPE html>
<!--[if lt IE 7 ]> <html lang="en" class="no-js ie6"> <![endif]-->
<!--[if IE 7 ]>    <html lang="en" class="no-js ie7"> <![endif]-->
<!--[if IE 8 ]>    <html lang="en" class="no-js ie8"> <![endif]-->
<!--[if IE 9 ]>    <html lang="en" class="no-js ie9"> <![endif]-->
<!--[if (gt IE 9)|!(IE)]><!--> <html lang="en" class="no-js"><!--<![endif]-->
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
		<title><g:layoutTitle default="SofProject"/></title>
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<link rel="shortcut icon" href="${resource(dir: 'images', file: 'favicon.ico')}" type="image/x-icon">
		<link rel="stylesheet" href="${resource(dir: 'css', file: 'main.css')}" type="text/css">
		<link rel="stylesheet" href="${resource(dir: 'css', file: 'mobile.css')}" type="text/css">
		<g:layoutHead/>
		
		<r:layoutResources />
	</head>
	<body>
		<div id="header" role="banner">
			<div class="topBar">
	        	<div class="global-nav" data-section-term="top_nav">
	            	<div class="global-nav-inner">
	            		<div class="menuBar">
							<ul>
								<div style="margin-left: 5%">
									<sec:ifLoggedIn>
										<li style="margin-top: 4px; margin-right: -5px;"><g:message code="hello.label" /></li><li>
										<g:link controller='User' action="show" id="${sec.loggedInUserInfo(field: 'id')}">${sec.loggedInUserInfo(field: 'username')}</g:link></li>
										<li><g:link controller='logout'><g:message code="logout.label" /></g:link></li>
									</sec:ifLoggedIn>
									<sec:ifNotLoggedIn>
										<li><g:link controller='login' action='auth'><g:message code="login.label" /></g:link></li>
										<li><g:link controller='user' action="create"><g:message code="sign.up.label" /></g:link></li>
									</sec:ifNotLoggedIn> 
								</div>
								
								<div style="margin-left: 85%">
									<li><a class="home" href="${createLink(uri: '/')}"><img src="${resource(dir: 'images/skin', file: 'house.png')}" alt="Home"/></a></li>
									<li><a href="/SofProject/?lang=en"><g:img dir="images" file="uk_flag.png"/></a></li>
									<li><a href="/SofProject/?lang=fr"><g:img dir="images" file="french_flag.gif"/></a></li>
								</div>
							</ul>
						</div>
	             	</div>
        		</div>
        	</div>

			<div class="generalMenu">
				<table class="globalTableMenu"><tr>
					<td>
						<div class="logo"><a href="/SofProject"><img src="${resource(dir: 'images', file: 'logo.jpg')}" alt="Logo"/></a></div>
					</td>
					<td>
						<div class="menu">
							<table class="tableMenu"><tr>
								<td><g:link controller='Question' action='list'><g:message code="questions.label" /></g:link></td>
								<td><g:link controller='Tag' action='list'><g:message code="tags.label" /></g:link></td>
								<td><g:link controller='User' action='list'><g:message code="users.label" /></g:link></td>
								<td><g:link controller='Badge' action='list'><g:message code="badges.label" /></g:link></td>
								<td><g:link controller='Question' action='listUnanswered'><g:message code="unanswered.label" /></g:link></td>
								<td class="lastTd"><g:link controller='Question' action='create'><g:message code="ask.question.label" /></g:link></td>
							</tr></table>
						</div>
					</td>
				</tr></table>
			</div>
		</div>
	
		<g:layoutBody/>
		<div class="footer" role="contentinfo"></div>
		<div id="spinner" class="spinner" style="display:none;"><g:message code="spinner.alt" default="Loading&hellip;"/></div>
		
		<g:javascript library="application"/>
		
		<g:javascript src="scriptaculous.js" />
		
		<r:layoutResources />
	</body>
</html>
