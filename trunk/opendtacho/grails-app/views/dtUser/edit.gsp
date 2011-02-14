<%@ page import="org.opendtacho.domain.DtPerson" %>
<head>
	<meta name="layout" content="main" />
	<title>Edit DtUser</title>
</head>

<body>

	<div class="nav">
		<span class="menuButton"><a class="home" href="${createLinkTo(dir:'')}">Home</a></span>
		<span class="menuButton"><g:link class="list" action="list">DtUser List</g:link></span>
		<span class="menuButton"><g:link class="create" action="create">New DtUser</g:link></span>
	</div>

	<div class="body">
		<h1>Edit DtUser</h1>
		<g:if test="${flash.message}">
		<div class="message">${flash.message}</div>
		</g:if>

        %{--person is a DtUser instance, sended from DtUserController--}%
		<g:hasErrors bean="${user}">
		<div class="errors">
			<g:renderErrors bean="${user}" as="list" />
		</div>
		</g:hasErrors>

		<div class="prop">
			<span class="name">ID:</span>
			<span class="value">${user.id}</span>
		</div>

		<g:form>
			<input type="hidden" name="id" value="${user.id}" />
			<input type="hidden" name="version" value="${user.version}" />
			<div class="dialog">
				<table>
				<tbody>

					<tr class="prop">
						<td valign="top" class="name"><label for="username">Login Name:</label></td>
						<td valign="top" class="value ${hasErrors(bean:user,field:'username','errors')}">
							<input type="text" id="username" name="username" value="${user.username?.encodeAsHTML()}"/>
						</td>
					</tr>

					<tr class="prop">
						<td valign="top" class="name"><label for="userRealName">Full Name:</label></td>
						<td valign="top" class="value ${hasErrors(bean:user,field:'userRealName','errors')}">
							<input type="text" id="userRealName" name="userRealName" value="${user.userRealName?.encodeAsHTML()}"/>
						</td>
					</tr>

					<tr class="prop">
						<td valign="top" class="name"><label for="password">Password:</label></td>
						<td valign="top" class="value ${hasErrors(bean:user,field:'password','errors')}">
							<input type="password" id="password" name="password" value="${user.password?.encodeAsHTML()}"/>
						</td>
					</tr>

					<tr class="prop">
						<td valign="top" class="name"><label for="enabled">Enabled:</label></td>
						<td valign="top" class="value ${hasErrors(bean:user,field:'enabled','errors')}">
							<g:checkBox name="enabled" value="${user.enabled}"/>
						</td>
					</tr>

					<tr class="prop">
						<td valign="top" class="name"><label for="description">Description:</label></td>
						<td valign="top" class="value ${hasErrors(bean:user,field:'description','errors')}">
							<input type="text" id="description" name="description" value="${user.description?.encodeAsHTML()}"/>
						</td>
					</tr>

					<tr class="prop">
						<td valign="top" class="name">
                            <label for="authorities">Roles:</label>
                        </td>
						<td valign="top" class="value ${hasErrors(bean:user,field:'authorities','errors')}">
							<ul>
                                <g:each var="entry" in="${roleMap}">
                                    <li>${entry.key.authority.encodeAsHTML()}
                                        <g:checkBox name="${entry.key.authority}" value="${entry.value}"/>
                                    </li>
                                </g:each>
							</ul>
						</td>
					</tr>


                    %{--show the related person--}%
                    <tr class="prop">
                        <td valign="top" class="name">
                            <label for="persons">Persons:</label>
                        </td>
                        <td valign="top" class="value ${hasErrors(bean:user,field:'person','errors')}">
                            <ul>
                                <g:each var="entry" in="${DtPerson.list()}">
                                    <li>
                                        ${entry.lastName}, ${entry.firstName}
                                        <g:radio value="${entry.id}" name="person"/> 
                                    </li>
                                </g:each>
                            </ul>
                        </td>
                    </tr>

				</tbody>
				</table>
			</div>

			<div class="buttons">
				<span class="button"><g:actionSubmit class="save" value="Update" /></span>
				<span class="button"><g:actionSubmit class="delete" onclick="return confirm('Are you sure?');" value="Delete" /></span>
			</div>

		</g:form>

	</div>
</body>
