<head>
	<meta name="layout" content="main" />
	<title>User Registration</title>
</head>

<body>

	<div class="nav">
		<span class="menuButton"><a class='home' href="${createLinkTo(dir:'')}">Home</a></span>
	</div>

	<div class="body">
		<h1><g:message code="register.header"/></h1>
		<g:if test="${flash.message}">
		<div class="message">${flash.message}</div>
		</g:if>
		<g:hasErrors bean="${person}">
		<div class="errors">
			<g:renderErrors bean="${person}" as="list" />
		</div>
		</g:hasErrors>

		<g:form action="save">
		<div class="dialog">
		<table>
		<tbody>

			<tr class='prop'>
				<td valign='top' class='name'><label for='username'><g:message code="register.username"/></label></td>
				<td valign='top' class='value ${hasErrors(bean:person,field:'username','errors')}'>
					<input type="text" name='username' value="${person?.username?.encodeAsHTML()}"/>
				</td>
			</tr>

			<tr class='prop'>
				<td valign='top' class='name'><label for='userRealName'><g:message code="register.fullname"/></label></td>
				<td valign='top' class='value ${hasErrors(bean:person,field:'userRealName','errors')}'>
					<input type="text" name='userRealName' value="${person?.userRealName?.encodeAsHTML()}"/>
				</td>
			</tr>

			<tr class='prop'>
				<td valign='top' class='name'><label for='passwd'><g:message code="register.password"/></label></td>
				<td valign='top' class='value ${hasErrors(bean:person,field:'passwd','errors')}'>
					<input type="password" name='passwd' value="${person?.passwd?.encodeAsHTML()}"/>
				</td>
			</tr>

			<tr class='prop'>
				<td valign='top' class='name'><label for='enabled'><g:message code="register.confirm"/></label></td>
				<td valign='top' class='value ${hasErrors(bean:person,field:'passwd','errors')}'>
					<input type="password" name='repasswd' value="${person?.passwd?.encodeAsHTML()}"/>
				</td>
			</tr>

			<tr class='prop'>
				<td valign='top' class='name'><label for='email'><g:message code="register.email"/></label></td>
				<td valign='top' class='value ${hasErrors(bean:person,field:'email','errors')}'>
					<input type="text" name='email' value="${person?.email?.encodeAsHTML()}"/>
				</td>
			</tr>

			<tr class='prop'>
				<td valign='bottom' class='name'><label for='code'><g:message code="register.code"/></label></td>
				<td valign='top' class='name'>
					<input type="text" name="captcha" size="8"/>
					<img src="${createLink(controller:'captcha', action:'index')}" align="absmiddle"/>
				</td>
			</tr>

		</tbody>
		</table>
		</div>

		<div class="buttons">
			<span class="formButton">
				<input class='save' type="submit" value="<g:message code="register.create"/>"></input>
			</span>
		</div>

		</g:form>
	</div>
</body>
