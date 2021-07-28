<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Messages</title>
</head>
<body>

<div>
    <h4>${cityname}</h4>
    <h4>${description}</h4>
</div>

<form action="/messages" method="post" enctype="multipart/form-data">
    <label>
        Message -
        <input type="text" name="message">
    </label>
    <label>
        Image -
        <input type="file" name="image">
    </label>
    <br>
    <select name="userId">
        <#list users as user>
            <option value="${user.id}">${user.name}</option>
        </#list>
    </select>
    <br>
    <input type="hidden" name="_csrf" value="${_csrf.token}"/>
    <input type="submit">
</form>

<br>
<#list messages as message>
    ${message.message} -- ${message.creationDate?date('yyyy-MM-dd')?string('dd/MM/yyyy')} --
    <a href="/user/info/${message.author.id}" target="_blank">${message.author.name}</a>
    <a href="/messages/delete/${message.id}">delete</a> <br>
    <#if message.image??>
        <img src="image/${message.image}" height="100px"><br>
    </#if>
</#list>

<form action="/logout" method="post">
    <input type="hidden" name="_csrf" value="${_csrf.token}"/>
    <input type="submit" value="Logout">
</form>

</body>
</html>
