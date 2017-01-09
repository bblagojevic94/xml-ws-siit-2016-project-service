<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="2.0"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:fo="http://www.w3.org/1999/XSL/Format"
                xmlns:aman="http://www.parlament.gov.rs/schema/amandman"
                xmlns:elem="http://www.parlament.gov.rs/schema/elementi">

    <xsl:template match="aman:amandmani/aman:body">
        <fo:block>
            <xsl:apply-templates/>
        </fo:block>
    </xsl:template>

    <xsl:template match="aman:pravni_osnov">
        <fo:block font-family="Arial" font-weight="bold" text-align="center" font-size="18pt">
            <!--xsl:value-of select="translate(@name, 'abcdefghijklmnopqrstuvwxyzčćžđš', 'ABCDEFGHIJKLMNOPQRSTUVWXYZČĆŽĐŠ')"/-->
            Pravni osnov
        </fo:block>
        <fo:block>
            <!--xsl:apply-templates select="elem:glava"/-->
        </fo:block>
    </xsl:template>

    <xsl:template match="aman:amandman">
        <fo:block>
            Amandman
        </fo:block>
        <fo:block>
            <xsl:apply-templates/>
        </fo:block>
    </xsl:template>

    <xsl:template match="aman:odredba">
        <fo:block>
            Odredba
        </fo:block>
        <fo:block>
            <xsl:apply-templates/>
        </fo:block>
    </xsl:template>

    <xsl:template match="aman:obrazlozenje">
        <fo:block>
            Obrazlozenje
        </fo:block>
        <fo:block>
            <xsl:apply-templates/>
        </fo:block>
    </xsl:template>

    <!-- Elementi za obrazlozenje amandmana -->
    <xsl:template match="aman:razlog">
        <fo:block>
            Razlog
        </fo:block>
    </xsl:template>

    <xsl:template match="aman:objasnjene_predlozenog_rjesenja">
        <fo:block>
            Objasnjenje predlozenog rjesenja
        </fo:block>
    </xsl:template>

    <xsl:template match="aman:cilj">
        <fo:block>
            Cilj
        </fo:block>
    </xsl:template>

    <xsl:template match="aman:uticaj_na_budzetska_sredstva">
        <fo:block>
            Uticaj na budzetska sredstva
        </fo:block>
    </xsl:template>

    <!-- Elementi koji se mogu naci u odredbi amandmana -->
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
        </fo:block>
        <fo:block>
            <xsl:apply-templates select="elem:tacka"/>
        </fo:block>
    </xsl:template>

    <xsl:template match="elem:tacka">
        <!-- New Line -->
        <fo:block><xsl:value-of select="'&#x2028;'"/></fo:block>
        <fo:block font-family="Arial" font-size="11pt" text-align="justify" start-indent="0.2in">
            <xsl:value-of select="position()"/>) <xsl:value-of select="current()"/>
        </fo:block>
        <fo:block>
            <xsl:apply-templates select="elem:podtacka"/>
        </fo:block>
    </xsl:template>

    <xsl:template match="elem:podtacka">
        <!-- New Line -->
        <fo:block><xsl:value-of select="'&#x2028;'"/></fo:block>
        <fo:block font-family="Arial" font-size="11pt" text-align="justify" start-indent="0.4in">
            (<xsl:value-of select="position()"/>) <xsl:value-of select="current()"/>
        </fo:block>
    </xsl:template>

    <xsl:template match="/">
        <fo:root>
            <fo:layout-master-set>
                <fo:simple-page-master
                        master-name="amendments-page"
                        page-height="29.7cm"
                        page-width="21cm"
                        margin-top="1cm"
                        margin-bottom="2cm"
                        margin-left="2.5cm"
                        margin-right="2.5cm">

                    <fo:region-body
                            region-name="amendments-body"
                            margin-bottom="1.5cm">
                    </fo:region-body>

                    <fo:region-after
                            region-name="amendments-footer"
                            extent="1cm"/>

                </fo:simple-page-master>
            </fo:layout-master-set>

            <fo:page-sequence
                    master-name="amendments-pages"
                    master-reference="amendments-page"
                    initial-page-number="1">
                <fo:static-content flow-name="amendments-footer">
                    <fo:block font-size="11pt" font-weight="normal" font-family="Arial" text-align="right">
                        <fo:page-number/>
                    </fo:block>
                </fo:static-content>

                <fo:flow flow-name="amendments-body">
                    <fo:block>
                        <xsl:apply-templates select="aman:amandmani/aman:body"/>
                    </fo:block>
                </fo:flow>
            </fo:page-sequence>
        </fo:root>
    </xsl:template>
</xsl:stylesheet>