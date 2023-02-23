<?xml version="1.0" encoding="UTF-8"?>
<html xsl:version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:param name="string" xml:space="preserve"/>
    <body style="font-family:Arial;font-size:12pt;background-color:#EEEEEE">
        <p>Cars models that start with: <xsl:value-of select="translate($string,'%20',' ')"/></p>
        <table border="1">
            <tr bgcolor="#9acd32">
                <th>Brand</th>
                <th>Model</th>
                <th>Type</th>
                <th>Consumption</th>
                <th>Year</th>
                <th>Price</th>
            </tr>
            <xsl:for-each select="//Car[model[starts-with(.,$string)]]">
                <tr>
                    <td><xsl:value-of select="carBrand"/></td>
                    <td><xsl:value-of select="model"/></td>
                    <td><xsl:value-of select="type"/></td>
                    <td><xsl:value-of select="consumption"/></td>
                    <td><xsl:value-of select="year"/></td>
                    <td><xsl:value-of select="price"/></td>
                </tr>
            </xsl:for-each>
        </table>
    </body>
</html>