<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet
        xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
        version="2.0">

    <xsl:template match="/">
        <html>
            <head>
                <title>Test</title>
            </head>

            <body>
                <xsl:for-each select="result/glave/glava">
                    <h2 style="text-align: center; font-weight: bold;">
                        <xsl:value-of select="@name" />
                    </h2>

                    <p style="text-align: justify;">
                        <xsl:value-of select="odjeljak" />
                    </p>
                </xsl:for-each>
            </body>
        </html>

    </xsl:template>
</xsl:stylesheet>