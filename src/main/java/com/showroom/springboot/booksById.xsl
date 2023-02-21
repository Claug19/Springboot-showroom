<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:param name="carId" xml:space="preserve"/>
    <xsl:template match="/">
        <cars>
            <xsl:for-each select="Data/AvailableCars/Car[@carId=$carId]">
                <carBrand><xsl:value-of select="carBrand"/></carBrand>
                <model><xsl:value-of select="model"/></model>
            </xsl:for-each>
        </cars>
    </xsl:template>
</xsl:stylesheet>