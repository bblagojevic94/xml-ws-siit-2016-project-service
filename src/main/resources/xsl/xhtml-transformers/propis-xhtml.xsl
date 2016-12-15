<xsl:stylesheet
        xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
        xmlns:prop="http://www.parlament.gov.rs/schema/propis"
        xmlns:elem="http://www.parlament.gov.rs/schema/elementi"
        version="2.0">

    <xsl:template match="/">
        <html>
            <head>
                <title>Propis HTML</title>
                <style>
                    ol.podtacka li {
                    list-style: none;
                    counter-increment: myIndex;
                    }

                    ol.podtacka li:before{
                    content:"("counter(myIndex)") ";
                    }
                </style>
            </head>
            <body style="font-family: Arial;">
                <xsl:apply-templates select="prop:propis/prop:body"/>
            </body>
        </html>
    </xsl:template>

    <xsl:template match="elem:dio">
        <h2 style="text-align: center; font-weight: bold;">
            <xsl:value-of select="@name" />
        </h2>
        <xsl:apply-templates select="elem:glava"/>
    </xsl:template>

    <xsl:template match="elem:glava">
        <h2 style="text-align: center; font-weight: bold;">
            <xsl:value-of select="@name" />
        </h2>
        <xsl:apply-templates select="elem:odjeljak"/>
    </xsl:template>

    <xsl:template match="elem:odjeljak">
        <h4 style="font-weight: bold; text-align: center; font-size: 14pt">
            <xsl:value-of select="position()"/>. <xsl:value-of select="@name"/>
        </h4>
        <xsl:apply-templates select="elem:clan"/>
    </xsl:template>

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