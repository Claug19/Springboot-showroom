<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:param name="car_model"/>
    <xsl:template match="/">
        1. Car models
        <xsl:apply-templates select="//AvailableCars/child::*/model"/>

        2. Cars that have brand 'Dacia'
        <xsl:apply-templates select="//Car[carBrand = 'Dacia']/model" />

        3. Total number of features
        <xsl:apply-templates>
            <xsl:decimal-format  pattern-separator="\n" />
            <xsl:value-of select="count(//Car/features/feature)"/>
        </xsl:apply-templates>

        4. idRef of all discounts that expire in 2022
        <xsl:apply-templates select="//Discount[expirationDate=contains(.,'2022')]/@idRef" />

        5. Client firstNames that are not male
        <xsl:apply-templates select="//Client[not(contains(@gender,'male'))]/firstName" />

        6. Car models that have 2 features
        <xsl:apply-templates select="//Car[count(features/feature) = 2]/Model"/>

    </xsl:template>
</xsl:stylesheet>