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

        <xsl:template match="aman:pravni_osnov">
            <p>Pravni osnov</p>
        </xsl:template>

        <xsl:template match="aman:amandman">
            <h1>Amandman pronadjen</h1>
            <xsl:apply-templates/>
        </xsl:template>

        <xsl:template match="aman:odredba">
            <p>
                Odredba...
            </p>
            <xsl:apply-templates/>
        </xsl:template>

        <xsl:template match="aman:obrazlozenje">
            <h4>Obrazlozenje</h4>
            <xsl:apply-templates/>
        </xsl:template>

        <!-- Elementi za obrazlozenje amandmana -->
        <xsl:template match="aman:razlog">
            <h6 style="font-weight: bold; text-align: center; font-size: 12pt">
                Razlog podnosenja amandmana
            </h6>
            <p>
                <xsl:value-of select="current()"/>
            </p>
        </xsl:template>

        <xsl:template match="aman:objasnjene_predlozenog_rjesenja">
            <h6 style="font-weight: bold; text-align: center; font-size: 12pt">
                Objasnjenje predlozenog rjesenja
            </h6>
            <p>
                <xsl:value-of select="current()"/>
            </p>
        </xsl:template>

        <xsl:template match="aman:cilj">
            <h6 style="font-weight: bold; text-align: center; font-size: 12pt">
                Cilj
            </h6>
            <p>
                <xsl:value-of select="current()"/>
            </p>
        </xsl:template>

        <xsl:template match="aman:uticaj_na_budzetska_sredstva">
            <h6 style="font-weight: bold; text-align: center; font-size: 12pt">
                Uticaj na budzetska sredstva
            </h6>
            <p>
                <xsl:value-of select="current()"/>
            </p>
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

    </xsl:template>
</xsl:stylesheet>