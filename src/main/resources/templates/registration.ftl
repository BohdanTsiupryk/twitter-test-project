<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>REGISTRATION</title>
</head>
<body>
${error_message!"Hello"}

<form method="post" action="/registration">
    <input type="hidden" name="_csrf" value="${_csrf.token}"/>
    <input name="name" type="text">
    <input name="password" type="password">
    <input name="password_confirm" type="password">
    <input type="submit">
</form>
</body>
</html>
