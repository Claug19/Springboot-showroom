<?xml version="1.0" encoding="UTF-8"?>
<html xsl:version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <body style="font-family:Arial;font-size:12pt;background-color:#EEEEEE">
        <p>Clients:</p>
        <table border="1">
            <tr bgcolor="#9acd32">
                <th>First Name</th>
                <th>Last Name</th>
                <th>Gender</th>
                <th>Preferred Car</th>
            </tr>
            <xsl:for-each select="//Client">
                <tr>
                    <td><xsl:value-of select="firstName"/></td>
                    <td><xsl:value-of select="lastName"/></td>
                    <td><xsl:value-of select="@gender"/></td>
                    <td><xsl:value-of select="preferredCar"/></td>
                </tr>
            </xsl:for-each>
        </table>
    </body>
</html>