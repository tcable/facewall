var autoSearchOnKeyUp = function (input, target, result) {
    var recentness = 0;
    var searchBar = document.getElementById("searchBar");
    var tagsBar = document.getElementById("tags");

    $(input).keyup(function () {
        var searchstring = $(this).val();
            if (searchstring.length >= 1) {
                    recentness++;
                    searchBar.className="input-group input-group-lg entry-area-searching";
                    $("#tags").hide();
                    $("#quickLinks").hide();
                   var requestRecentness = recentness;
                   var params = {};
                   params[input.attr("name")] = input.val();

                   var updateResultWithMostRecent = function (data) {
                       if (recentness == requestRecentness) {
                           result.html(data);
                       }
                   };

            }  else if (searchstring.length == 0) {
                searchBar.className="input-group input-group-lg entry-area-start";
                $("#tags").show();
                $("#quickLinks").show();
            result.empty();

            }


        $.get(target, params, updateResultWithMostRecent, "html");
        return false;
    });
};


function addTagTextToSearch(text){
    var searchBar = $( "#searchInput" );
    //searchBar.val( searchBar.val() + input);
    $(searchBar).val($(searchBar).val() + text);
    $(searchBar).keyup();

    //var input_id = '#'+id_of_input;
    //$(input_id).val($(input_id).val() + text);
};