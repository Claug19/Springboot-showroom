<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:template match="/">
        1. Car models
        <xsl:apply-templates select="//AvailableCars/child::*/model"/>

        2. Cars that have brand 'Dacia'
        <xsl:apply-templates select="//Car[carBrand = 'Dacia']/model" />

        3. idRef of all discounts that expire in 2022
        <xsl:apply-templates select="//Discount[expirationDate=contains(.,'2022')]/@idRef" />

        4. Client firstNames that are not male
        <xsl:apply-templates select="//Client[not(@gender = 'male')]/firstName" />

        5. Count number of filters
        <xsl:value-of select="count(/Data/Clients/Client/filter)"/>

        6. Cars that cost less than 11000
        <xsl:apply-templates select="//Car[11000 > price]/model" />

    </xsl:template>
</xsl:stylesheet>