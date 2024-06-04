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
                <form method="post" action="/admin/penalite" method="GET">
                    <div class="row">
                        <h2>Formulaire d'affectation de penalités des equipes </h2>
                    </div>
                    
                    <div class="row">
                        <label for="" class="label"> Choix Etape</label>
                        <select class="input" name="etape" id="">
                            <option value="">Choisir</option>
                            <% Etape[] etapes = (Etape[])(request.getAttribute("etapes"));
                            for(Etape etape : etapes){%>
                                <option value="<%=etape.getID()%>"><%= etape.getNom() %> </option>
                            <%}%>
                        </select>
                    </div>    
                    <div class="row">

                        <label for="" class="label"> Choix equipe</label>
                        <select class="input" name="equipe" id="">
                            <option value="">Choisir</option>
                            <% Equipe[] equipes = (Equipe[])(request.getAttribute("equipes"));
                            for(Equipe equipe : equipes){%>
                                <option value="<%=equipe.getID()%>"><%= equipe.getNom() %> </option>
                            <%}%>
                        </select>
                    </div>    

                        <div class="row">
                            <label for="" class="label"> Temps de pénalité:</label>
                            <input name="temps" type="time" step="1">
                            <!--  pattern="[0-9]+:[0-9]{2}:[0-9]{2}$" -->
                        </div>
    
                        <div class="row">
                            <label for="" class="label"></label>
                            <button type="submit">Valider</button>                        
                        </div>
    
                    </div>
                    

        
                </form>
            </header>
        </div>    
    </section>

    <%@ include file="../../templates/script.html" %>

</body>

</html>