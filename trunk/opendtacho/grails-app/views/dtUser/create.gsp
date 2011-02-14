<%@ page import="org.opendtacho.domain.DtPerson" %>
<head>
	<meta name="layout" content="main" />
	<title>Create DtUser</title>
</head>

<body>

	<div class="nav">
		<span class="menuButton"><a class="home" href="${createLinkTo(dir:'')}">Home</a></span>
		<span class="menuButton"><g:link class="list" action="list">DtUser List</g:link></span>
	</div>

	<div class="body">
		<h1>Create DtUser</h1>
		<g:if test="${flash.message}">
		<div class="message">${flash.message}</div>
		</g:if>
		<g:hasErrors bean="${user}">
		<div class="errors">
			<g:renderErrors bean="${user}" as="list" />
		</div>
		</g:hasErrors>
		<g:form action="save">
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
							<g:checkBox name="enabled" value="${user.enabled}" ></g:checkBox>
						</td>
					</tr>

					<tr class="prop">
						<td valign="top" class="name"><label for="description">Description:</label></td>
						<td valign="top" class="value ${hasErrors(bean:user,field:'description','errors')}">
							<input type="text" id="description" name="description" value="${user.description?.encodeAsHTML()}"/>
						</td>
					</tr>

					<tr class="prop">
						<td valign="top" class="name" align="left">Assign Roles:</td>
					</tr>

					<g:each in="${authorityList}">
					<tr>
						<td valign="top" class="name" align="left">${it.authority.encodeAsHTML()}</td>
						<td align="left"><g:checkBox name="${it.authority}"/></td>
					</tr>
					</g:each>


                    <tr class="prop">
                        <td valign ="top" class="name" align="left">Persons:</td> 
                    </tr>



                    %{--added select box for related DtPerson--}%
                    <g:each in="${DtPerson.list()}">
                      <tr>
                        <td valign="top" class="name" align="left">
                          ${it.lastName}, ${it.firstName}
                        </td>
                        <td align="left">
                          <g:radio value="${it.id}" name="person"/>
                        </td>
                      </tr>
                    </g:each>

				</tbody>
				</table>
			</div>

			<div class="buttons">
				<span class="button"><input class="save" type="submit" value="Create" /></span>
			</div>

		</g:form>

	</div>
</body>
