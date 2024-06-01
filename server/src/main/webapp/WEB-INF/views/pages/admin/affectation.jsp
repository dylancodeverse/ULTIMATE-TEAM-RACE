<%@ page import="scaffold.framework.demo.models.equipe.*" %>
<%@ page import="scaffold.framework.demo.models.course.*" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">

    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>ULTIMATE RACE</title>

    <%@ include file="../../templates/headerLinkForm.html" %>

</head>

<body>

    <%@ include file="../../templates/sidebar.html" %>
    <section class="home-section">
        <div class="container">
            <header>
                <form method="post" action="/admin/affectation" method="POST">
                    <div class="row">
                        <h2>Affectation des coureurs a une etape de course</h2>
                    </div>
                    
                    <div class="row">
                        <label for="" class="label"> Equivalence a affecter</label>
                        <select class="input" name="ID" id="">
                            <option value="">Choisir</option>
                            <% CompletResultatEtape[] etapes = (CompletResultatEtape[])(request.getAttribute("completResultatEtapes"));
                            for(CompletResultatEtape etape : etapes){%>
                                <option value="<%=etape.getId()%>"><%= etape.getCoureurnom() %> de l'equipe <%= etape.getEquipe() %> sur <%= etape.getEtapenom() %></option>
                            <%}%>
                        </select>
                    </div>
                    <div class="row">
                        <label for="" class="label"> Heure de depart:</label>
                        <input name="depart" pattern="[0-9]+:[0-9]{2}:[0-9]{2}$">
                    </div>
                    <div class="row">
                        <label for="" class="label"> Heure d'arrivee:</label>
                        <input name="arrivee" pattern="[0-9]+:[0-9]{2}:[0-9]{2}$">
                    </div>

                    <div class="row">
                        <label for="" class="label"></label>
                        <button type="submit">Affecter</button>                        
                    </div>

        
                </form>
            </header>
        </div>    
    </section>

    <%@ include file="../../templates/script.html" %>

</body>

</html>