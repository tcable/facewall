<#assign resultsCount = persons?size + teams?size>
<div class="container">
    <#if !persons?has_content && !teams?has_content>
            <div class="col-md-8 col-md-offset-2">
                <h1 class="no-results search"><img src="/assets/images/smiley_sad.png" height="55" />No results found!</h1>
            </div>
        </div>
    <#else>
        <div>
            <h3 class="results-counter">There were ${resultsCount} matching results</h3>
        </div>
        <#if persons?has_content>
            <div>
                <ul class="nav nav-tabs nav-justified">
                    <li><a id="people" href="/">People</a></li>
                    <li><a id="teams" href="/">Teams</a></li>
                    <li><a id="companies" href="/">Companies</a></li>
                    <li><a id="jobRole" href="/">Job Role</a></li>
                </ul>
            </div>

            <script>
                $("ul.nav").children().eq(0).addClass("active");
            </script>

            <div>
                <h1>People</h1>
            </div>
            <div class="row">
                <#list persons as result>


                    <div class="col-md-3 col-sm-4 entry">
                        <a href="/person/${result.link}">
                        <div class="imgWrapper ${result.colour}" style="border: 15px solid #${result.colour}">
                            <img class="avatar" src="${result.picture}"/>
                        </div>
                        <h4 class="text-center entryName">
                            <a href="/person/${result.link}">${result.name}</a>
                        </h4>
                        <h5 class="text-center teamName">
                            ${result.role} - O2
                        </h5>
                        <h5 class="text-center teamName">
                            ${result.teamName} - Active
                        </h5>
                        <#if result.location??>
                            <h5 class="text-center teamName">
                                ${result.location}
                            </h5>
                        <#else>
                            <h5 class="text-center teamName">
                                Bath Road, Slough
                            </h5>
                        <i class="fa fa-envelope-o"></i> &nbsp; <i class="fa fa-skype"></i> &nbsp; <i class="fa fa-phone"></i>
                        </#if>
                        </a>
                    </div>

                </#list>
            </div>
        </#if>

        <#if teams?has_content>
            <div>
                <h1>Teams</h1>
            </div>
            <div class="row">
                <#list teams as result>
                    <div class="col-md-4 col-md-offset-4 team-entry">
                        <div style="border: 10px solid #${result.colour};" class="thumbnail">
                            <h3 class="text-center"><a href="/team/${result.name}">${result.name}</a></h3>
                        </div>
                    </div>
                </#list>
            </div>
        </#if>
    </#if>
</div>
<script>
    $(document).ready(function () {
        $('img.avatar').fakecrop();
    });
</script>