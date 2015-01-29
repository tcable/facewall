<#macro main title activeTabIndex>

<!DOCTYPE html>

<html>
    <head profile="http://www.w3.org/2005/10/profile">
        <title>Facewall | ${title}</title>
        <link rel="icon" type="image/ico" href="/assets/images/favicon.ico" />
        <link rel="stylesheet" media="screen" href="/assets/css/main.css">
        <script src="/assets/javascripts/jquery-1.9.0.min.js"></script>
    </head>
    </head>
    <body>
        <div class="jumbotron">
            <div class="container">
                <h1 id="homeTitle" class="text-center">
                    <img class="smiley" src="/assets/images/smiley_happy.png"/>Facewall
                </h1>

                <ul class="nav nav-tabs nav-justified">
                    <li><a id="home" href="/">Overview</a></li>
                    <li><a id="teams" href="/team">Teams</a></li>
                    <li><a id="search" href="/search">Search</a></li>
                    <li><a id="register" href="/signup">Register</a></li>
                </ul>
            </div>
        </div>

        <script>
            $("ul.nav").children().eq(${activeTabIndex}).addClass("active");
        </script>

        <#nested>
    </body>
</html>

</#macro>