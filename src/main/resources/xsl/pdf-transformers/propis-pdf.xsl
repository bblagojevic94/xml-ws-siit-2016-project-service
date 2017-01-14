<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="2.0"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:fo="http://www.w3.org/1999/XSL/Format"
                xmlns:propis="http://www.parlament.gov.rs/schema/propis"
                xmlns:elem="http://www.parlament.gov.rs/schema/elementi">

    <xsl:template match="propis:propis/propis:head">
        <fo:block font-family="Arial" font-size="10pt">
            <fo:inline font-weight="bold">Datum prijedloga: </fo:inline>
            <xsl:value-of select="propis:datum_predloga"/>
        </fo:block>
        <fo:block font-family="Arial" font-size="10pt">
            <fo:inline font-weight="bold">Datum izglasavanja: </fo:inline>
            <xsl:value-of select="propis:datum_izglasavanja"/>
        </fo:block>
        <fo:block font-family="Arial" font-size="10pt">
            <fo:inline font-weight="bold">Mjesto: </fo:inline>
            <xsl:value-of select="propis:mjesto"/>
        </fo:block>
    </xsl:template>

    <xsl:template match="propis:propis/propis:body">
        <fo:block>
            <xsl:apply-templates/>
        </fo:block>
    </xsl:template>

    <xsl:template match="elem:dio">
        <fo:block font-family="Arial" font-weight="bold" text-align="center" font-size="18pt" margin-top="16pt">
            <xsl:value-of select="translate(@name, 'abcdefghijklmnopqrstuvwxyzčćžđš', 'ABCDEFGHIJKLMNOPQRSTUVWXYZČĆŽĐŠ')"/>
        </fo:block>
        <fo:block>
            <xsl:apply-templates select="elem:glava"/>
        </fo:block>
    </xsl:template>

    <xsl:template match="elem:glava">
        <fo:block font-family="Arial" font-weight="bold" text-align="center" font-size="16pt" margin-top="14pt">
            <xsl:number format="I" value="position()"></xsl:number>.
            <xsl:value-of select="translate(@name, 'abcdefghijklmnopqrstuvwxyzčćžđš', 'ABCDEFGHIJKLMNOPQRSTUVWXYZČĆŽĐŠ')"/>
        </fo:block>
        <fo:block>
            <xsl:apply-templates select="elem:odjeljak"/>
        </fo:block>
    </xsl:template>

    <xsl:template match="elem:odjeljak">
        <!-- New Line -->
        <fo:block><xsl:value-of select="'&#x2028;'"/></fo:block>
        <fo:block font-family="Arial" font-weight="bold" text-align="center" font-size="14pt">
            <xsl:value-of select="position()"/>. <xsl:value-of select="@name"/>
        </fo:block>
        <fo:block>
            <xsl:apply-templates/>
        </fo:block>
    </xsl:template>

    <xsl:template match="elem:pododjeljak">
        <!-- New Line -->
        <fo:block><xsl:value-of select="'&#x2028;'"/></fo:block>
        <fo:block font-family="Arial" font-weight="bold" text-align="center" font-size="14pt">
            <xsl:value-of select="@name"/>
        </fo:block>
        <fo:block linefeed-treatment="preserve" font-family="Arial" font-style="italic" text-align="center" font-size="12pt">
            <xsl:number format="a" select="position()"/>) <xsl:value-of select="@name"/>
        </fo:block>
        <fo:block>
            <xsl:apply-templates/>
        </fo:block>
    </xsl:template>

    <xsl:template match="elem:clan">
        <!-- New Line -->
        <fo:block><xsl:value-of select="'&#x2028;'"/></fo:block>
        <fo:block font-family="Arial" font-weight="bold" text-align="center" font-size="12pt">
            <xsl:value-of select="@name"/>
        </fo:block>
        <fo:block linefeed-treatment="preserve" font-family="Arial" font-style="italic" text-align="center" font-size="12pt">
            Član <xsl:number format="1." level="any" count="elem:clan"/>
        </fo:block>
        <fo:block>
            <xsl:apply-templates select="elem:stav"/>
        </fo:block>
    </xsl:template>

    <xsl:template match="elem:stav">
        <!-- New Line -->
        <fo:block><xsl:value-of select="'&#x2028;'"/></fo:block>
        <fo:block font-family="Arial" font-size="11pt" text-align="justify">
            <xsl:value-of select="current()"/>
            <xsl:if test="elem:tacka">
                <xsl:for-each select="elem:tacka">
                    <fo:block><xsl:value-of select="'&#x2028;'"/></fo:block>
                    <fo:block font-family="Arial" font-size="11pt" text-align="justify" start-indent="0.2in">
                        <xsl:value-of select="position()"/>. <xsl:value-of select="current()"/>
                        <!-- All subcaluses -->
                        <xsl:if test="elem:podtacka">
                            <xsl:for-each select="elem:podtacka">
                                <fo:block font-family="Arial" font-size="11pt" text-align="justify" start-indent="0.4in">
                                    (<xsl:value-of select="position()"/>) <xsl:value-of select="current()"/>
                                </fo:block>
                                <xsl:apply-templates select="elem:alineja"/>
                            </xsl:for-each>
                        </xsl:if>
                    </fo:block>
                    <xsl:apply-templates select="elem:alineja"/>
                </xsl:for-each>
            </xsl:if>
        </fo:block>
    </xsl:template>

    <xsl:template match="elem:alineja">
        <fo:block font-family="Arial" font-size="10pt" text-align="justify" start-indent="0.6in">
            - <xsl:value-of select="current()"/>
        </fo:block>
    </xsl:template>

    <xsl:template match="/">
        <fo:root>
            <fo:layout-master-set>
                <fo:simple-page-master
                        master-name="law-page"
                        page-height="29.7cm"
                        page-width="21cm"
                        margin-top="1cm"
                        margin-bottom="2cm"
                        margin-left="2.5cm"
                        margin-right="2.5cm">

                    <fo:region-body
                            region-name="law-body"
                            margin-bottom="1.5cm">
                    </fo:region-body>

                    <fo:region-after
                            region-name="law-footer"
                            extent="1cm"/>

                </fo:simple-page-master>
            </fo:layout-master-set>

            <fo:page-sequence
                    master-name="law-pages"
                    master-reference="law-page"
                    initial-page-number="1">
                <fo:static-content flow-name="law-footer">
                    <fo:block font-size="11pt" font-weight="normal" font-family="Arial" text-align="right">
                        <fo:page-number/>
                    </fo:block>
                </fo:static-content>

                <fo:flow flow-name="law-body">
                    <fo:block>
                        <xsl:apply-templates/>
                    </fo:block>
                </fo:flow>
            </fo:page-sequence>
        </fo:root>
    </xsl:template>
</xsl:stylesheet>