<%@ page import="scaffold.framework.demo.models.equipe.*" %>
<%@ page import="scaffold.framework.demo.models.course.*" %>




<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">

    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>ULTIMATE RACE</title>

    <%@ include file="../../templates/headerLink.html" %>

</head>

<body>

    <%@ include file="../../templates/sidebar.html" %>
    <section class="home-section">
        <div class="container">
            <header class="hero">
                <h1>EQUIPE <%= request.getSession().getAttribute("USRID").toString() %></h1>
                <form class="prediction-form"  method="post">
                    <input type="prediction" name="prediction" placeholder="Recherche">
                    <button type="submit">Filter</button>
                </form>
            </header>
            <% for(HomeEquipe home: (HomeEquipe[])(request.getAttribute("homeequipes"))){%>
            <section class="resources">
                <h2><%= home.getEtapeId()+" "+home.getEtapenom()+" avec depart: " +home.getDepartEtape()%> :</h2>
                <div class="resource-cards">
                    <div class="card">
                        <%for(int i =0; i< home.getCoureurs().size(); i++){%>
                        <h3> <p> <%= home.getCoureurs().get(i) %></p></h3>
                        <h3> avec chrono :<p><%= home.getChrono().get(i) %></p></h3>
                        <%}%>
                        <a href="/equipe/affectation?etapeid=<%=home.getEtapeId()%>"> 
                            <button onClick("checkEtat(<%=home.getEtapeId()%>)")> Ajouter</button>
                        </a>
                        <span id="<%=home.getEtapeId()%>Error" style="color: red;"> </span>

                    </div>
                </div>
            </section>
            <% } %>        
        </div>        
    </section>
    <%@ include file="../../templates/script.html" %>


    <script>

    function checkEtat(params) {
        $.ajax({
                type: "GET",
                url: "/equipe/check?etapeId="+params,
                data: formData,
                dataType: "json", // Attendre une réponse JSON du backend
                success: function(response) {
                    //  rediriger l'utilisateur vers 
                    console.log("/equipe/affectation?etapeid="+params)
                },
                error: function(xhr, status, error) {
                    // Gérer les erreurs de validation provenant du backend
                    var errors = xhr.responseJSON;
                    $.each(errors, function(key, value) {
                        $('#' + key + 'Error').text(value); // Afficher les erreurs à côté des champs correspondants
                    });
                }
            });
    }



</script>

</body>

</html>