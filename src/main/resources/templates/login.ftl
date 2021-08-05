<#import "macro/index-macro.ftl" as main>

<@main.main>
    ${message!""}

    <div class="mt5">
        <form method="post" action="/login">

            <div class="input-group my-3">
                <span class="input-group-text" id="inputGroup-sizing-default">Login</span>
                <input class="form-control" name="username" type="text">
            </div>

            <div class="input-group mb-3">
                <span class="input-group-text" id="inputGroup-sizing-default">Password</span>
                <input class="form-control" name="password" type="password">
            </div>

            <input type="hidden" name="_csrf" value="${_csrf.token}"/>

            <input class="btn btn-outline-success" type="submit" value="Login">
        </form>
    </div>
</@main.main>

