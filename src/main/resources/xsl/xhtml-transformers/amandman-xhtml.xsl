<xsl:stylesheet
        xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
        xmlns:aman="http://www.parlament.gov.rs/schema/amandman"
        xmlns:elem="http://www.parlament.gov.rs/schema/elementi"
        version="2.0">

    <xsl:template match="/">
        <html>
            <head>
                <title>Amandman HTML</title>
            </head>
            <body style="font-family: Arial;">
                <xsl:apply-templates select="aman:amandmani/aman:body"/>
            </body>
        </html>
    </xsl:template>

    <xsl:template match="aman:amandmani/aman:body">
        <xsl:apply-templates/>
    </xsl:template>

    <xsl:template match="aman:pravni_osnov">
        <h3>Pravni osnov</h3>
        <xsl:apply-templates/>
    </xsl:template>

    <xsl:template match="aman:amandman/aman:head">
        <!-- Ne prikazuj vrijednosti iz aman:head-a -->
    </xsl:template>

    <xsl:template match="aman:amandman/aman:body">
        <xsl:apply-templates/>
    </xsl:template>

    <xsl:template match="aman:odredba">
        <h3>Odredba</h3>
        <xsl:apply-templates/>
    </xsl:template>

    <xsl:template match="aman:obrazlozenje">
        <h4>Obrazloženje</h4>
        <xsl:apply-templates/>
    </xsl:template>

    <!-- Elementi za obrazlozenje amandmana -->
    <xsl:template match="aman:razlog">
        <h5 style="font-weight: bold; text-align: center; font-size: 12pt">Razlog podnošenja amandmana</h5>
        <p><xsl:value-of select="current()"/></p>
    </xsl:template>

    <xsl:template match="aman:objasnjene_predlozenog_rjesenja">
        <h5 style="font-weight: bold; text-align: center; font-size: 12pt">Objašnjenje predloženog rješenja</h5>
        <p><xsl:value-of select="current()"/></p>
    </xsl:template>

    <xsl:template match="aman:cilj">
        <h5 style="font-weight: bold; text-align: center; font-size: 12pt">Cilj</h5>
        <p><xsl:value-of select="current()"/></p>
    </xsl:template>

    <xsl:template match="aman:uticaj_na_budzetska_sredstva">
        <h5 style="font-weight: bold; text-align: center; font-size: 12pt">Uticaj na budžetska sredstva
        </h5>
        <p><xsl:value-of select="current()"/></p>
    </xsl:template>

    <!-- Elementi koji se mogu naci u odredbi amandmana -->
    <xsl:template match="elem:clan">
        <h6 style="font-weight: bold; text-align: center; font-size: 12pt">
            <xsl:value-of select="@name"/>
        </h6>
        <h6 style="text-align: center; font-size: 12pt">
            <i>Član <xsl:number format="1." level="any" count="elem:clan"/></i>
        </h6>
        <xsl:apply-templates select="elem:stav"/>
    </xsl:template>

    <xsl:template match="elem:stav">
        <p style="font-size: 11pt; text-align: justify">
            <xsl:value-of select="current()"/>
        </p>
        <xsl:apply-templates select="elem:tacka"/>
    </xsl:template>

    <xsl:template match="elem:tacka">
        <ol>
            <li style="font-size: 11pt; text-align: justify">
                <xsl:value-of select="current()"/>

                <xsl:apply-templates select="elem:podtacka"/>
            </li>
        </ol>
    </xsl:template>

    <xsl:template match="elem:podtacka">
        <ol class="podtacka">
            <li style="font-size: 11pt; text-align: justify">
                <xsl:value-of select="current()"/>
            </li>
        </ol>
    </xsl:template>

</xsl:stylesheet>