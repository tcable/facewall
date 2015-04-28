<!DOCTYPE html>

<html>
    <head profile="http://www.w3.org/2005/10/profile">
        <title>Facewall | Search Facewall</title>
        <link rel="icon" type="image/ico" href="/assets/images/favicon.ico" />
        <link rel="stylesheet" media="screen" href="/assets/css/main.css">
        <link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css">
        <script src="/assets/javascripts/jquery-1.9.0.min.js"></script>
    </head>
    <body>
        <div class="containerTop">
            <div class="row">
                <p><span id="mainUser"><i class="fa fa-user"></i></span> ELLIOT MASSEY <br> 82 Connections <span class="floatRight">CHANNELS & INNOVATION</span></p>

            </div>
        </div>
        <div class="container">
            <div class="row">
                <div class="col-md-8 col-md-offset-2">
                    <div class="input-group input-group-lg entry-area-start" id="searchBar">
                        <span class="input-group-btn find-me">
                            <p>Find Me</p>
                        </span>
                        <input tabindex="1" id="searchInput" type="text" name="keywords" class="form-control icon-search" placeholder="Search by person or team">
                        <span class="input-group-btn">
                            <button class="btn btn-default" type="button"><i class="fa fa-search"></i></button>
                            <button class="btn btn-default" type="button"><i class="fa fa-microphone"></i></i></button>
                        </span>
                    </div>
                    <div class="tags" id="tags">
                    <h4>C&I Teams - Population <i id="usersimgPop" class="fa fa-users"></i> 1250</h4>
                        <a onclick="addTagTextToSearch('The Lab');" href="/team/The Lab"><p class="tag">The Lab &nbsp; <i id="usersimg" class="fa fa-users"></i> <span class="tagNumber"> 36</span></p></a>
                        <a href="/"><p class="tag">Ecommerce &nbsp; <i id="usersimg" class="fa fa-users"></i> <span class="tagNumber"> 205</span></p></a>
                        <a href="/"><p class="tag">Priority &nbsp; <i id="usersimg" class="fa fa-users"></i> <span class="tagNumber"> 40</span></p></a>
                        <a href="/"><p class="tag">My O2 &nbsp; <i id="usersimg" class="fa fa-users"></i> <span class="tagNumber"> 34</span></p></a>
                        <a href="/"><p class="tag">Digital SMB &nbsp; <i id="usersimg" class="fa fa-users"> </i><span class="tagNumber"> 68</span></p></a>
                        <a href="/"><p class="tag">Deployment &nbsp; <i id="usersimg" class="fa fa-users"> </i><span class="tagNumber"> 11</span></p></a>
                        <a href="/"><p class="tag">IT Leadership &nbsp; <i id="usersimg" class="fa fa-users"> </i><span class="tagNumber"> 26</span></p></a>
                        <a href="/"><p class="tag">+</p></a>
                    </div>
                </div>
            </div>

            <div id="quickLinks">
                <a href="/"><p class="quickLink blue">Just Joined</p></a>
                <a href="/"><p class="quickLink gold">My Contacts</p></a>
            </div>

            <div id="result"></div>
        </div>

        <script src="/assets/javascripts/search-box.js"></script>
        <script src="/assets/javascripts/jquery.fakecrop.js"></script>
        <script>
            var searchInput = $("#searchInput");
            var result = $("#result");
            searchInput.focus();
            autoSearchOnKeyUp(searchInput, "/results", result);
        </script>

    </body>
</html>

