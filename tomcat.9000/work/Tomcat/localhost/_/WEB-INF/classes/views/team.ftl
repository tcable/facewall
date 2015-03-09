<#import 'main.ftl' as main>

<@main.main title = "Teams" activeTabIndex = 1>
    <div class="container">
        <#if entries?has_content>
            <div class="row">
                <#list entries as entry>
                    <div class="col-md-3 col-sm-4 entry" data-qa-team-name="${entry.name}">
                        <h5 class="text-center teamName">
                            <a style="color: #${entry.colour}" href="/team/${entry.name}">${entry.name}</a>
                        </h5>
                        <h5>${entry.size} <#if entry.size == 1>person<#else>people</#if> in this team.</h5>
                    </div>
                </#list>
            </div>
        <#else>
            <h1 class="no-results main text-center"><img class="smiley" src="/assets/images/smiley_sad.png"/>There are no teams!</h1>
        </#if>
    </div>

</@main.main>