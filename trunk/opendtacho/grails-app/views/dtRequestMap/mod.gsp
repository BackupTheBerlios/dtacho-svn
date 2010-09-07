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

      %{--maps variable contains all DtRequestMap instances--}%
      <g:set var="maps" value="${DtRequestMap.list()}"/>
      
      %{--roles variable contains all DtRole instances--}%
      <g:set var="roles" value="${DtRole.list()}"/>


      %{--dtactivitychange variable holds the DtRequestMap instance for DtActivityChange URL--}%
      <g:set var ="dtactivitychange" value='${DtRequestMap.findByUrl("/dtactivitychange/**")}'/>

      %{--dtdriver variable holds the DtRequestMap instance for DtDriver URL--}%
      <g:set var ="dtdriver" value='${DtRequestMap.findByUrl("/dtdriver/**")}'/>

      %{--xmlfileupload variable holds the DtRequestMap instance for XmlFileUpload URL--}%
      <g:set var ="xmlfileupload" value='${DtRequestMap.findByUrl("/xmlfileupload/**")}'/>

      %{--dddfileupload variable holds the DtRequestMap instance for DddFileUpload URL--}%
      <g:set var ="dddfileupload" value='${DtRequestMap.findByUrl("/dddfileupload/**")}'/>

      %{--dtuser variable holds the DtRequestMap instance for DtUser URL--}%
      <g:set var ="dtuser" value='${DtRequestMap.findByUrl("/dtuser/**")}'/>

      %{--dtrole variable holds the DtRequestMap instance for DtRole URL--}%
      <g:set var ="dtrole" value='${DtRequestMap.findByUrl("/dtrole/**")}'/>

      %{--dtrequestmap variable holds the DtRequestMap instance for DtRequestMap URL--}%
      <g:set var ="dtrequestmap" value='${DtRequestMap.findByUrl("/dtrequestmap/**")}'/>

      <g:form name="modifyForm" action="modResults">

        <div class="list">
          <table>
                  <thead>
                    <tr>
                      <td></td>
                      <td><strong>activity list</strong></td>
                      <td><strong>driver list</strong></td>
                      <td><strong>xml upload</strong></td>
                      <td><strong>ddd upload</strong></td>
                      <td><strong>user list</strong></td>
                      <td><strong>role list</strong></td>
                      <td><strong>request map list</strong></td>
                    </tr>
                  </thead>
                  <tbody>

                    %{--iterate every role--}%
                    <g:each in ="${roles}" var="role">
                      <tr>
                        <td>
                          ${role.getDescription()}
                        </td>

                        <td>
                          <g:if test ='${dtactivitychange.configAttribute.contains("${role.authority}")}'>
                            <input type ="checkbox" id="activityTrue" name="activityTrue" checked/>
                          </g:if>
                          <g:else>
                            <input type ="checkbox" id="activityFalse" name="activityFalse"/>
                          </g:else>
                        </td>

                        <td>
                          <g:if test ='${dtdriver.configAttribute.contains("${role.authority}")}'>
                            <input type ="checkbox" id="driverTrue" name="driverTrue" checked/>
                          </g:if>
                          <g:else>
                            <input type ="checkbox" id="driverFalse" name="driverFalse"/>
                          </g:else>
                        </td>

                        <td>
                          <g:if test ='${xmlfileupload.configAttribute.contains("${role.authority}")}'>
                            <input type ="checkbox" id="xmlfileuploadTrue" name="xmlfileuploadTrue" checked/>
                          </g:if>
                          <g:else>
                            <input type ="checkbox" id="xmlfileuploadFalse" name="xmlfileuploadFalse"/>
                          </g:else>
                        </td>

                        <td>
                          <g:if test ='${dddfileupload.configAttribute.contains("${role.authority}")}'>
                            <input type ="checkbox" id="dddfileuploadTrue" name="dddfileuploadTrue" checked/>
                          </g:if>
                          <g:else>
                            <input type ="checkbox" id="dddfileuploadFalse" name="dddfileuploadFalse"/>
                          </g:else>
                        </td>

                        <td>
                          <g:if test ='${dtuser.configAttribute.contains("${role.authority}")}'>
                            <input type ="checkbox" id="dtuserTrue" name="dtuserTrue" checked/>
                          </g:if>
                          <g:else>
                            <input type ="checkbox" id="dtuserFalse" name="dtuserFalse"/>
                          </g:else>
                        </td>

                        <td>
                          <g:if test ='${dtrole.configAttribute.contains("${role.authority}")}'>
                            <input type ="checkbox" id="dtroleTrue" name="dtroleTrue" checked/>
                          </g:if>
                          <g:else>
                            <input type ="checkbox" id="dtroleFalse" name="dtroleFalse"/>
                          </g:else>
                        </td>

                        <td>
                          <g:if test ='${dtrequestmap.configAttribute.contains("${role.authority}")}'>
                            <input type ="checkbox" id="dtrequestmapTrue" name="dtrequestmapTrue" checked/>
                          </g:if>
                          <g:else>
                            <input type ="checkbox" id="dtrequestmapFalse" name="dtrequestmapFalse"/>
                          </g:else>
                        </td>
                      </tr>
                    </g:each>
                  </tbody>
                </table>
        </div>

        <div class="buttons">
          <span class="button">
            <input class="update" type="button" value="update" onClick="document.modifyForm.action='modResults'; document.modifyForm.submit();"/> 
          </span>
        </div>
      </g:form>

    </div>
  </body>
</html>