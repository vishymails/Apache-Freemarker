<xsl:stylesheet version="1.0"
     xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

<xsl:variable name="table-heading">
    <tr>
        <td><b>Date</b></td>
        <td><b>Home Team</b></td>
        <td><b>Away Team</b></td>
        <td><b>Result</b></td>
    </tr>
</xsl:variable>

<xsl:template match="/">
<html><body>
    <h1>Matches in Group <xsl:value-of select="/*/@group"/></h1>

    <xsl:for-each select="//match">

    <h2><xsl:value-of select="concat(team[1], ' versus ', team[2])"/></h2>

    <table bgcolor="#cccccc" border="1" cellpadding="5">
        <xsl:copy-of select="$table-heading"/>        
        <tr>
        <td><xsl:value-of select="date"/>&#xa0;</td>
        <td><xsl:value-of select="team[1]"/>&#xa0;</td>
        <td><xsl:value-of select="team[2]"/>&#xa0;</td>   
        <td><xsl:value-of select="concat(team[1]/@score, '-', team[2]/@score)"/>&#xa0;</td>   
        </tr>        
    </table>
    </xsl:for-each>   
</body></html>
</xsl:template>

</xsl:stylesheet>
