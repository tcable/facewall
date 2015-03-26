<#import 'mainDoor.ftl' as main>

<@main.main title = "Login to Facewall" activeTabIndex = 0>

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

</@main.main>