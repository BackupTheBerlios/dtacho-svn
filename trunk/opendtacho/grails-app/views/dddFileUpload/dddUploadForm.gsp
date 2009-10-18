<%--
  Created by IntelliJ IDEA.
  User: meberspaecher
  Date: 14.08.2008
  Time: 15:39:33
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title><g:message code="ddd.title"/></title>
    <meta name="layout" content="main"/>
    <script type="text/javascript">
        function setDDDFileType(field) {
            var dddfile = document.dddinput['dddfile' + field].value;

            if (dddfile) {
                var dddfilename = dddfile.match(/[\/|\\]([^\\\/]+)$/);

                if (dddfilename == null) {
                    // no path
                    dddfilename = dddfile;
                }
                else {
                    // path before filename
                    dddfilename = dddfilename[1];
                }

                var dddfileprefix = dddfilename.substring(0, 2).toUpperCase();
                setRadioButtons(dddfileprefix, document.dddinput['dddfiletype' + field][0], document.dddinput['dddfiletype' + field][1], 'div' + field);
            }
            else {
                document.dddinput['dddfiletype' + field][0].checked = false;
                document.dddinput['dddfiletype' + field][1].checked = false;
                setWhiteBackground('div' + field);
            }
        }

        function setRadioButtons(dddfileprefix, drivercardradio, vehicleunitradio, divname) {
            if (dddfileprefix == "C_") {
                drivercardradio.checked = true;
                document.getElementsByName(divname)[0].style.backgroundColor = "#00ff00";
            }
            else if (dddfileprefix == "M_") {
                vehicleunitradio.checked = true;
                document.getElementsByName(divname)[0].style.backgroundColor = "#00ff00";
            }
            else {
                drivercardradio.checked = false;
                vehicleunitradio.checked = false;
                document.getElementsByName(divname)[0].style.backgroundColor = "#ff0000";
            }
        }

        function setGreenBackground(element) {
            document.getElementsByName(element)[0].style.backgroundColor = "#00ff00";
        }

        function setWhiteBackground(element) {
            document.getElementsByName(element)[0].style.backgroundColor = "#ffffff";
        }

        function setYellowBackground(element) {
            document.getElementsByName(element)[0].style.backgroundColor = "#ffff00";
        }

        var tablerow = 5;

        function addFileUpload() {
            if (tablerow < 15) {
                var table = document.getElementById("filetable");
                var row = table.insertRow(tablerow + 1);
                var cell = row.insertCell(0);

                cell.innerHTML = "\n<input type=\"file\" name=\"dddfile" + tablerow + "\" accept=\"text/*\" maxlength=\"2097152\" size=\"80\" onchange=\"javascript:setDDDFileType(" + tablerow + ")\" />\n";
                var cell = row.insertCell(1);

                cell.innerHTML = "\n<div name=\"div" + tablerow + "\" style=\"background-color:#ffffff;\">\n<input type=\"radio\" name=\"dddfiletype" + tablerow + "\" value=\"drivercard\" onclick=\"javascript:setYellowBackground('div" + tablerow + "')\" />Driver card\n<input type=\"radio\" name=\"dddfiletype" + tablerow + "\" value=\"vehicleunit\" onclick=\"javascript:setYellowBackground('div" + tablerow + "')\" />Vehicle unit\n</div>\n";
                tablerow++;

                if (tablerow == 15) {
                    var form = document.getElementById("formactionarea");
                    var fb = document.getElementById("addfilebutton");
                    form.removeChild(fb);
                }
            }
        }

        busyImage = new Image();
        busyImage.src = "/opendtacho/images/spinner.gif";

        function setBusy() {
            var uploadbutton = document.getElementById("formactionarea");
            var busyimg = document.createElement("img");
            var imgsrc = document.createAttribute("src");
            imgsrc.nodeValue = "/opendtacho/images/wait30.gif";
            busyimg.setAttributeNode(imgsrc);
            uploadbutton.appendChild(busyimg);
        }
    </script>
</head>
<body>
<div class="nav">
    <span class="menuButton"><a class="home" href="${createLinkTo()}"><g:message code="menu.home"/></a></span>
</div>
<div class="body">
    <h1><g:message code="ddd.header"/></h1>
    <g:if test="${flash.message}">
        <div class="message">${flash.message}</div>
    </g:if>

    <form name="dddinput" action="upload" enctype="multipart/form-data" method="post">
        <table id="filetable">
            <tr>
                <th align="left">
                    DDD files:
                </th>
                <th align="left">
                    Type of data:
                </th>
            </tr>
            <tr>
                <td nowrap>
                    <input type="file" name="dddfile0" accept="text/*" maxlength="2097152" size="80" onchange="javascript:setDDDFileType(0)"/>
                </td>
                <td nowrap>
                    <div name="div0" style="background-color:#ffffff;">
                        <input type="radio" name="dddfiletype0" value="drivercard" onclick="javascript:setYellowBackground('div0')"/>Driver card
                        <input type="radio" name="dddfiletype0" value="vehicleunit" onclick="javascript:setYellowBackground('div0')"/>Vehicle unit
                    </div>
                </td>
            </tr>
            <tr>
                <td>
                    <input type="file" name="dddfile1" accept="text/*" maxlength="2097152" size="80" onchange="javascript:setDDDFileType(1)"/>
                </td>
                <td>
                    <div name="div1" style="background-color:#ffffff;">
                        <input type="radio" name="dddfiletype1" value="drivercard" onclick="javascript:setYellowBackground('div1')"/>Driver card
                        <input type="radio" name="dddfiletype1" value="vehicleunit" onclick="javascript:setYellowBackground('div1')"/>Vehicle unit
                    </div>
                </td>
            </tr>
            <tr>
                <td>
                    <input type="file" name="dddfile2" accept="text/*" maxlength="2097152" size="80" onchange="javascript:setDDDFileType(2)"/>
                </td>
                <td>
                    <div name="div2" style="background-color:#ffffff;">
                        <input type="radio" name="dddfiletype2" value="drivercard" onclick="javascript:setYellowBackground('div2')"/>Driver card
                        <input type="radio" name="dddfiletype2" value="vehicleunit" onclick="javascript:setYellowBackground('div2')"/>Vehicle unit
                    </div>
                </td>
            </tr>
            <tr>
                <td>
                    <input type="file" name="dddfile3" accept="text/*" maxlength="2097152" size="80" onchange="javascript:setDDDFileType(3)"/>
                </td>
                <td>
                    <div name="div3" style="background-color:#ffffff;">
                        <input type="radio" name="dddfiletype3" value="drivercard" onclick="javascript:setYellowBackground('div3')"/>Driver card
                        <input type="radio" name="dddfiletype3" value="vehicleunit" onclick="javascript:setYellowBackground('div3')"/>Vehicle unit
                    </div>
                </td>
            </tr>
            <tr>
                <td>
                    <input type="file" name="dddfile4" accept="text/*" maxlength="2097152" size="80" onchange="javascript:setDDDFileType(4)"/>
                </td>
                <td>
                    <div name="div4" style="background-color:#ffffff;">
                        <input type="radio" name="dddfiletype4" value="drivercard" onclick="javascript:setYellowBackground('div4')"/>Driver card
                        <input type="radio" name="dddfiletype4" value="vehicleunit" onclick="javascript:setYellowBackground('div4')"/>Vehicle unit
                    </div>
                </td>
            </tr>
            <tr>
                <td id="formactionarea">
                    <div id="addfilebutton"><input type="button" value="+" onclick="javascript:addFileUpload()"/>&nbsp;Add file selector</div>
                    <br/>
                    <div class="buttons">
                        <span class="button">
                            <input class="upload" type="image" alt="<g:message code="ddd.button.upload"/>" onclick="javascript:setBusy()"/>
                        </span>
                    </div>
                </td>
                <td>
                </td>
            </tr>
        </table>
    </form>

</div>
</body>
</html>