<#recurse doc>

<#macro Master>
  <html>
    <head>
      <title><#recurse .node.title></title>
    </head>
    <body>
      <h1><#recurse .node.title></h1>
      <#recurse>
    </body>
  </html>
</#macro>

<#macro topic>
  <h2><#recurse .node.title></h2>
  <#recurse>
</#macro>

<#macro subject>
  <p><#recurse>
</#macro>

<#macro title>
  <#--
    We have handled this element imperatively,
    so we do nothing here.
  -->
</#macro>

<#macro @text>${.node?html}</#macro>