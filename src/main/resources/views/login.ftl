<!DOCTYPE html>

<html>
    <head profile="http://www.w3.org/2005/10/profile">
        <title>Facewall | Login</title>
        <link rel="icon" type="image/ico" href="/assets/images/favicon.ico" />
        <link rel="stylesheet" media="screen" href="/assets/css/main.css">
        <script src="/assets/javascripts/jquery-1.9.0.min.js"></script>
    </head>
    <body>
        <div class="jumbotron">
            <div class="container">
                <h1 id="loginTitle" class="text-center">
                    <img class="smiley" src="/assets/images/smiley_happy.png"/>Facewall
                </h1>
            </div>
        </div>
        <div class="container">
            <div class="row">
                <div class="col-md-6 col-md-offset-3 form">
                    <form role="form" method="POST" action="/login">
                        <#if error??>
                            <div class="alert alert-danger">
                                <span class="glyphicon glyphicon-remove"></span>${error}
                            </div>
                        </#if>

                        <div class="form-group">
                            <label>Email address</label>
                            <input class="form-control" type="email" name="email" placeholder="Enter email" value="${email!""}" required>
                        </div>
                        <input id="login" class="btn btn-primary btn-lg btn-block" type="submit" value="Login">
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>