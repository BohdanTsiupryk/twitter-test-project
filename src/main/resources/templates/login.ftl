<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>LOGIN</title>
</head>
<body>
<form method="post" action="/login">
    <input type="hidden" name="_csrf" value="${_csrf.token}"/>
    <input name="username" type="text">
    <input name="password" type="password">
    <input type="submit">
</form>
</body>
</html>
