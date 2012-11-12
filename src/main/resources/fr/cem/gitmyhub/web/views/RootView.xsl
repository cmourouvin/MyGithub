<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
    xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:template match="RootView">
    <html>
    <head>
        <title>Context Framework Quickstart</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <script type="text/javascript" src="{$contextPath}/scripts/jquery.js"></script>
        <script type="text/javascript" src="{$contextPath}/scripts/contextfw.js"></script>
        <script type="text/javascript" src="{$contextPath}/scripts/json.js"></script>
        <script type="text/javascript" src="{$contextPath}/resources.js"></script>
        <link rel="stylesheet" type="text/css" href="{$contextPath}/resources.css"></link>
        <link rel="stylesheet" type="text/css" href="{$contextPath}/main.css"></link>
        <script type="text/javascript"><![CDATA[
jQuery(document).ready(function() {
contextfw.init("]]><xsl:value-of select="$contextPath" /><![CDATA[", "]]><xsl:value-of select="$pageHandle" /><![CDATA[");
]]><xsl:apply-templates select="//Script" mode="script" /><![CDATA[
});]]>
        </script>
    </head>
 <body>
 <h1>Quickstart-project</h1>
 <xsl:apply-templates select="child" />
</body>
</html>
    </xsl:template>
</xsl:stylesheet>