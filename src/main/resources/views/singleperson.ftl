<#import 'main.ftl' as main>

<@main.main title = "Your face" activeTabIndex = 4>

<div class="container">
    <!---<div class="row profile-message">
        <p>Is it me you're looking for? Then add me to your <strong>Found People</strong> list! <a class="FPbtn" id="yesbtn" href="/yes">YES!</a> <a class="FPbtn" id="nobtn" href="/no">NO</a></p>
    </div>-->

    <div class="row">
        <div class="col-md-8 col-md-offset-2 entry">
            <img class="single-person-pic" width="200px" height="200px" src="${picture}"></img>
            <div class="quickContact">
                <h5 class="personName">${name}</h5>
                <p class="lastUpdated">Last updated 20/04/2015</p>
                <h5 class="personRole">${role}</h5>
                <h5 class="teamName">${teamName}</h5>
                <h5 class="personLocation">Bath Road, Slough</h5>

                <h5 class="personEmail">${email}</h5>
                <h5 class="personSkype">john.smith</h5>
                <h5 class="personPhone">0771234567</h5>
            </div>
        </div>
    </div>
</div>

</@main.main>