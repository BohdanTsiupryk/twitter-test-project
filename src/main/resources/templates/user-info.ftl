<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Info about ${user.name}</title>
</head>
<body>
<h3>id - ${user.id}</h3>
<h3>name - ${user.name}</h3>

<#if messages??>
    <h3>message :<br></h3>
    <#list messages as message>
        <h4 style="color: darkslategrey; margin-left: 50px">${message.message}</h4>
    </#list>
</#if>
</body>
</html>
