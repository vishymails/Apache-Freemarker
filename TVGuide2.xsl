<?xml version="1.0" encoding="ISO-8859-1"?>
<html xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
      xsl:version="1.0">
<head>
  <title>TV Guide</title>

</head>

<body>
  <h1>TV Guide</h1>
  <xsl:for-each select="/TVGuide/Channel/Program">
    <h2><xsl:value-of select="Description" /></h2>
  </xsl:for-each>
</body>
</html>