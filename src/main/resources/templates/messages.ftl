<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Messages</title>
</head>
<body>

<form action="/messages" method="post">
    <label>
        Message -
        <input type="text" name="message">
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
</#list>

</body>
</html>
