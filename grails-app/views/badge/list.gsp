
<%@ page import="com.isima.sof.Badge" %>
<%@ page import="com.isima.sof.BadgeType" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'badge.label', default: 'Badge')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<div class="content">
			<h1><g:message code="badges.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			
			<br/><span style="margin-left: 20px;"><g:message code="badge.detail" /></span><br/><br/>
			
			
		    <g:each in="${badgeInstanceList}" status="i" var="badgeInstance">
				<table class="tableBadge">
					<tr class="badges">
						<td class="badge">
							${fieldValue(bean: badgeInstance, field: "name")}
						</td>
						<td class="desc">
							<span>
								&times; <strong>${badgeInstance.users.size()}</strong>
							</span>
						</td>
						<td>
							<span style="font-size: 13px;">
								<!-- We handle translation for badge description ! -->
								${g.message(code: badgeInstance.description.toString() )}&nbsp;
								(<strong>${fieldValue(bean: badgeInstance, field: "reputationNeeded")}</strong>&nbsp;<g:message code="reputation.points.needed" />)
							</span>
						</td>
						<td>
							<g:if test="${badgeInstance.type == BadgeType.BRONZE}">
								<td style="color: orange; float: right;">
									${badgeInstance.type}
								</td>
							</g:if>
							<g:else>
								<g:if test="${badgeInstance.type == BadgeType.SILVER}">
									<td style="color: silver; float: right;">
										${badgeInstance.type}
									</td>
								</g:if>
								<g:else>
									<td style="color: gold; float: right;">
										${badgeInstance.type}
									</td>
								</g:else>
							</g:else>
						</td>
					</tr>
				</table>
			</g:each>
		</div>
	</body>
</html>
