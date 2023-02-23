<?xml version="1.0" encoding="UTF-8"?>
<html xsl:version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <body style="font-family:Arial;font-size:12pt;background-color:#EEEEEE">
        <p>Discounts:</p>
        <table border="1">
            <tr bgcolor="#9acd32">
                <th>Expiration date</th>
                <th>Value</th>
            </tr>
            <xsl:for-each select="//Discount">
                <tr>
                    <td><xsl:value-of select="expirationDate"/></td>
                    <td><xsl:value-of select="value"/></td>
                </tr>
            </xsl:for-each>
        </table>
    </body>
</html>