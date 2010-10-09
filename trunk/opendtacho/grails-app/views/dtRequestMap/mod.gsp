<%@ page import="org.opendtacho.domain.*" contentType="text/html;charset=UTF-8" %>
%{-- Style sheet for tooltip of grails-ui plugin--}%
<link rel="stylesheet" type="text/css" href="${resource(dir:'css',file:'grails_ui.css')}"/>

<html>
  <head>
    <title><g:message code="results.title"/></title>
    <meta name="layout" content="main"/>
    <style type="text/css">
      .list table{
        border-collapse: separate;
        border-style: solid;
        table-layout: auto;
      }
      .list th, .list td{
        border-left:none;
      }
    </style>
  </head>

  <body>
    <div class="body">
      <h1><g:message code="authen.title"/></h1>
      %{--maps variable contains all DtRequestMap instances--}%
      <g:set var="maps" value="${DtRequestMap.list()}"/>
      
      %{--roles variable contains all DtRole instances--}%
      <g:set var="roles" value="${DtRole.list()}"/>

      %{--Driver Data--}%
      %{--dtactivitychange variable holds the DtRequestMap instance for DtActivityChange URL--}%
      <g:set var ="dtactivitychange" value='${DtRequestMap.findByUrl("/dtactivitychange/**")}'/>
      %{--dtdriver variable holds the DtRequestMap instance for DtDriver URL--}%
      <g:set var ="dtdriver" value='${DtRequestMap.findByUrl("/dtdriver/**")}'/>

      %{--File Upload--}%
      %{--xmlfileupload variable holds the DtRequestMap instance for XmlFileUpload URL--}%
      <g:set var ="xmlfileupload" value='${DtRequestMap.findByUrl("/xmlfileupload/**")}'/>
      %{--dddfileupload variable holds the DtRequestMap instance for DddFileUpload URL--}%
      <g:set var ="dddfileupload" value='${DtRequestMap.findByUrl("/dddfileupload/**")}'/>

      %{--User Management--}%
      %{--dtuser variable holds the DtRequestMap instance for DtUser URL--}%
      <g:set var ="dtuser" value='${DtRequestMap.findByUrl("/dtuser/**")}'/>
      %{--dtrole variable holds the DtRequestMap instance for DtRole URL--}%
      <g:set var ="dtrole" value='${DtRequestMap.findByUrl("/dtrole/**")}'/>
      %{--dtrequestmap variable holds the DtRequestMap instance for DtRequestMap URL--}%
      <g:set var ="dtrequestmap" value='${DtRequestMap.findByUrl("/dtrequestmap/**")}'/>

      %{--Master Data--}%
      %{--dtcompany variable holds the DtRequestMap instance for DtCompany URL--}%
      <g:set var ="dtcompany" value='${DtRequestMap.findByUrl("/dtcompany/**")}'/>
      %{--dtsubsidiary variable holds the DtRequestMap instance for DtSubsidiary URL--}%
      <g:set var ="dtsubsidiary" value='${DtRequestMap.findByUrl("/dtsubsidiary/**")}'/>
      %{--dtdepartment variable holds the DtRequestMap instance for DtDepartment URL--}%
      <g:set var ="dtdepartment" value='${DtRequestMap.findByUrl("/dtdepartment/**")}'/>
      %{--dtperson variable holds the DtRequestMap instance for DtPerson URL--}%
      <g:set var ="dtperson" value='${DtRequestMap.findByUrl("/dtperson/**")}'/>


      <g:form name="modifyForm" action="modResults">

        <div class="list">
          <table>
            <thead>
              <tr>
                <td></td>
                %{--Driver Data--}%
                <td><strong>Activity</strong></td>
                <td><strong>Driver</strong></td>

                %{--File Upload--}%
                <td><strong>XML</strong></td>
                <td><strong>DDD</strong></td>

                %{--User Management--}%
                <td><strong>User</strong></td>
                <td><strong>Role</strong></td>
                <td><strong>Map</strong></td>

                %{--Master Data--}%
                <td><strong>Company</strong></td>
                <td><strong>Subsidiary</strong></td>
                <td><strong>Department</strong></td>
                <td><strong>Person</strong></td>
              </tr>
            </thead>
            <tbody>
              %{--iterate every role--}%
              <g:each in ="${roles}" var="role">
                <tr>
                  <td>
                    ${role.getDescription()}
                  </td>

                  %{--Driver Data--}%
                  <td>
                    <g:if test ='${dtactivitychange.configAttribute.contains("${role.authority}")}'>
                      <input type ="checkbox" id="dtactivitychange_${role.authority}" name="dtactivitychange_${role.authority}" checked/>
                    </g:if>
                    <g:else>
                      <input type ="checkbox" id="dtactivitychange_${role.authority}" name="dtactivitychange_${role.authority}"/>
                    </g:else>
                  </td>
                  <td>
                    <g:if test ='${dtdriver.configAttribute.contains("${role.authority}")}'>
                      <input type ="checkbox" id="dtdriver_${role.authority}" name="dtdriver_${role.authority}" checked/>
                    </g:if>
                    <g:else>
                      <input type ="checkbox" id="dtdriver_${role.authority}" name="dtdriver_${role.authority}"/>
                    </g:else>
                  </td>

                  %{--File Upload--}%
                  <td>
                    <g:if test ='${xmlfileupload.configAttribute.contains("${role.authority}")}'>
                      <input type ="checkbox" id="xmlfileupload_${role.authority}" name="xmlfileupload_${role.authority}" checked/>
                    </g:if>
                    <g:else>
                      <input type ="checkbox" id="xmlfileupload_${role.authority}" name="xmlfileupload_${role.authority}"/>
                    </g:else>
                  </td>
                  <td>
                    <g:if test ='${dddfileupload.configAttribute.contains("${role.authority}")}'>
                      <input type ="checkbox" id="dddfileupload_${role.authority}" name="dddfileupload_${role.authority}" checked/>
                    </g:if>
                    <g:else>
                      <input type ="checkbox" id="dddfileupload_${role.authority}" name="dddfileupload_${role.authority}"/>
                    </g:else>
                  </td>

                  %{--User Management--}%
                  <td>
                    <g:if test ='${dtuser.configAttribute.contains("${role.authority}")}'>
                      <input type ="checkbox" id="dtuser_${role.authority}" name="dtuser_${role.authority}" checked/>
                    </g:if>
                    <g:else>
                      <input type ="checkbox" id="dtuser_${role.authority}" name="dtuser_${role.authority}"/>
                    </g:else>
                  </td>
                  <td>
                    <g:if test ='${dtrole.configAttribute.contains("${role.authority}")}'>
                      <input type ="checkbox" id="dtrole_${role.authority}" name="dtrole_${role.authority}" checked/>
                    </g:if>
                    <g:else>
                      <input type ="checkbox" id="dtrole_${role.authority}" name="dtrole_${role.authority}"/>
                    </g:else>
                  </td>
                  <td>
                    <g:if test ='${dtrequestmap.configAttribute.contains("${role.authority}")}'>
                   <input type ="checkbox" id="dtrequestmap_${role.authority}" name="dtrequestmap_${role.authority}" checked/>
                    </g:if>
                    <g:else>
                      <input type ="checkbox" id="dtrequestmap_${role.authority}" name="dtrequestmap_${role.authority}"/>
                    </g:else>
                  </td>

                  %{--Master Data--}%
                  <td>
                    <g:if test ='${dtcompany.configAttribute.contains("${role.authority}")}'>
                   <input type ="checkbox" id="dtcompany_${role.authority}" name="dtcompany_${role.authority}" checked/>
                    </g:if>
                    <g:else>
                      <input type ="checkbox" id="dtcompany_${role.authority}" name="dtcompany_${role.authority}"/>
                    </g:else>
                  </td>
                  <td>
                    <g:if test ='${dtsubsidiary.configAttribute.contains("${role.authority}")}'>
                   <input type ="checkbox" id="dtsubsidiary_${role.authority}" name="dtsubsidiary_${role.authority}" checked/>
                    </g:if>
                    <g:else>
                      <input type ="checkbox" id="dtsubsidiary_${role.authority}" name="dtsubsidiary_${role.authority}"/>
                    </g:else>
                  </td>
                  <td>
                    <g:if test ='${dtdepartment.configAttribute.contains("${role.authority}")}'>
                   <input type ="checkbox" id="dtdepartment_${role.authority}" name="dtdepartment_${role.authority}" checked/>
                    </g:if>
                    <g:else>
                      <input type ="checkbox" id="dtdepartment_${role.authority}" name="dtdepartment_${role.authority}"/>
                    </g:else>
                  </td>
                  <td>
                    <g:if test ='${dtperson.configAttribute.contains("${role.authority}")}'>
                   <input type ="checkbox" id="dtperson_${role.authority}" name="dtperson_${role.authority}" checked/>
                    </g:if>
                    <g:else>
                      <input type ="checkbox" id="dtperson_${role.authority}" name="dtperson_${role.authority}"/>
                    </g:else>
                  </td>
                </tr>
              </g:each>
            </tbody>
          </table>

        </div>

        <div class="buttons">
          <span class="button">
            <input class="update" type="button" value="<g:message code="authen.button"/>" onClick="document.modifyForm.action='modResults'; document.modifyForm.submit();"/> 
          </span>
        </div>
      </g:form>

    </div>
  </body>
</html>