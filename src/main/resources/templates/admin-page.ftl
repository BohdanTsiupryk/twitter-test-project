<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>ADMIN PAGE</title>
</head>
<body>

<#list users as user>
    <p>${user.id} -- ${user.name} -- ${user.role} -- <a href="/admin/add-admin/${user.id}">Make ADMIN</a></p>
</#list>


</body>
</html>
