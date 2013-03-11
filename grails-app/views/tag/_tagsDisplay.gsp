<div class="tags">
	<table class="tableMenu"><tr>
		<g:each in="${questionInstance?.tags}">
			<td>
				<g:link controller="Question" action="listByTag" id="${it.id}">${it.name}</g:link>
			</td>
		</g:each>
	</tr></table>
</div>