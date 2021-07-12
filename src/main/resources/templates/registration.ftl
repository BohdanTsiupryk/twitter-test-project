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
    <label>
        Name -
        <input name="name" type="text">
    </label>
    <br>
    <label>
        Email -
        <input name="email" type="text" required>
    </label>
    <br>
    <label>
        Password -
        <input name="password" type="password" required>
    </label>
    <br>
    <label>
        Repeat password -
        <input name="password_confirm" type="password" required>
    </label>
    <br>
    <input type="submit">
</form>
</body>
</html>
