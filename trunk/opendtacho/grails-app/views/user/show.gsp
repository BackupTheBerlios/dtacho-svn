<head>
	<meta name="layout" content="main" />
	<title>Show User</title>
</head>

<body>

	<div class="nav">
		<span class="menuButton"><a class="home" href="${createLinkTo(dir:'')}">Home</a></span>
		<span class="menuButton"><g:link class="list" action="list">User List</g:link></span>
		<span class="menuButton"><g:link class="create" action="create">New User</g:link></span>
	</div>

	<div class="body">
		<h1><g:message code="user.show.header"/></h1>
		<g:if test="${flash.message}">
		<div class="message">${flash.message}</div>
		</g:if>
		<div class="dialog">
			<table>
			<tbody>

				<tr class="prop">
					<td valign="top" class="name">ID:</td>
					<td valign="top" class="value">${person.id}</td>
				</tr>

				<tr class="prop">
					<td valign="top" class="name"><g:message code="user.show.username"/></td>
					<td valign="top" class="value">${person.username?.encodeAsHTML()}</td>
				</tr>

				<tr class="prop">
					<td valign="top" class="name"><g:message code="user.show.fullname"/></td>
					<td valign="top" class="value">${person.userRealName?.encodeAsHTML()}</td>
				</tr>

				<tr class="prop">
					<td valign="top" class="name"><g:message code="user.show.enabled"/></td>
					<td valign="top" class="value">${person.enabled}</td>
				</tr>

				<tr class="prop">
					<td valign="top" class="name"><g:message code="user.show.description"/></td>
					<td valign="top" class="value">${person.description?.encodeAsHTML()}</td>
				</tr>

				<tr class="prop">
					<td valign="top" class="name"><g:message code="user.show.email"/></td>
					<td valign="top" class="value">${person.email?.encodeAsHTML()}</td>
				</tr>

				<tr class="prop">
					<td valign="top" class="name"><g:message code="user.show.showEmail"/></td>
					<td valign="top" class="value">${person.emailShow}</td>
				</tr>

				<tr class="prop">
					<td valign="top" class="name"><g:message code="user.show.authority"/></td>
					<td valign="top" class="value">
						<ul>
						<g:each in="${roleNames}" var='name'>
							<li>${name}</li>
						</g:each>
						</ul>
					</td>
				</tr>

			</tbody>
			</table>
		</div>

		<div class="buttons">
			<g:form>
				<input type="hidden" name="id" value="${person.id}" />
				<span class="button"><g:actionSubmit class="edit" value="Edit" /></span>
				<span class="button"><g:actionSubmit class="delete" onclick="return confirm('Are you sure?');" value="Delete" /></span>
			</g:form>
		</div>

	</div>
</body>
