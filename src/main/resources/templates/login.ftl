<#import "spring.ftl" as spring />

<!DOCTYPE html>
<html lang="en">
<head>
    <title>Log into your App</title>
    <meta charset="utf8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <link rel="stylesheet" href="assets/style.css?v=1.0.0" type="text/css"/>
    <link rel="stylesheet" href="assets/css/login.css?v=1.0.2" type="text/css"/>
</head>
<body>
<#if RequestParameters['error']??>
<div class="alert alert-danger">
    There was a problem logging in. Please try again.
</div>
</#if>
<main class="container">
    <div class="login__container">
        <div class="login__email">
            <h1 class="title">LOG IN WITH PASSWORD</h1>
            <form role="form" action="login" method="post">
                <div class="form__group">
                    <label class="form__label">Username</label>
                    <input type="text" class="form__input" id="username" name="username"/>
                </div>
                <div class="form__group">
                    <label class="form__label" for="">Password</label>
                    <input type="password" class="form__input" id="password" name="password"/>
                </div>
                <input type="hidden" id="csrf_token" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                <button type="submit" class="form__button">Log in</button>
            </form>
        </div>
        <a class="login__link facebook" href="<@spring.url '/login/facebook'/>">
            <span>Login with Facebook</span>
            <span class="material-icons">arrow_forward</span>
        </a>
        <a class="login__link google" href="<@spring.url '/login/google_plus'/>">
            <span>Login with Google</span>
            <span class="material-icons">arrow_forward</span>
        </a>
    </div>
</main>
</body>
</html>