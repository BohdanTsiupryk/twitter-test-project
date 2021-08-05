<#import "macro/index-macro.ftl" as main>

<@main.main>
    <h4>${cityname} <span class="badge bg-secondary">${description}</span></h4>

    <p>
        <button class="btn btn-primary" type="button" data-bs-toggle="collapse" data-bs-target="#collapseExample" aria-expanded="false" aria-controls="collapseExample">
            Add message
        </button>
    </p>
    <div class="collapse" id="collapseExample">
        <div class="container">
            <div class="row align-items-start">
                <form action="/messages" method="post" enctype="multipart/form-data">
                    <div class="input-group my-3">
                        <span class="input-group-text" id="inputGroup-sizing-default">Message</span>

                        <input class="form-control" type="text" name="message">

                    </div>
                    <div class="input-group my-3">
                        <span class="input-group-text" id="inputGroup-sizing-default">Image</span>

                        <input class="form-control" type="file" name="image">

                    </div>
                    <select class="form-select" name="userId">
                        <#list users as user>
                            <option value="${user.id}">${user.name}</option>
                        </#list>
                    </select>
                    <br>
                    <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                    <div class="d-grid gap-2">
                        <input class="btn btn-primary" type="submit" value="ADD">
                    </div>
                </form>
            </div>
            <div class="row align-items-center"></div>
            <div class="row align-items-end"></div>
        </div>
    </div>

    <#list messages as message>

        <div class="card" style="width: 18rem;">
            <div class="card-body">
                <h5 class="card-title">${message.author.name}</h5>
                <h6 class="card-subtitle mb-2 text-muted">${message.creationDate?date('yyyy-MM-dd')?string('dd/MM/yyyy')}</h6>
                <p class="card-text">${message.message}</p>
                <a class="card-link" href="/user/info/${message.author.id}" target="_blank">${message.author.name}</a>
                <a class="card-link" href="/messages/delete/${message.id}">delete</a> <br>
            </div>
        </div>
<#--        -->
<#--        <#if message.image??>-->
<#--            <img src="image/${message.image}" height="100px"><br>-->
<#--        </#if>-->
    </#list>


</@main.main>
