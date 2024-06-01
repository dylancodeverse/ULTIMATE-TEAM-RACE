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
                <form method="post" action="/equipe/affectation" method="POST">
                    <div class="row">
                        <h2>Affectation des coureurs a une etape de course</h2>
                    </div>
                    
                    <div class="row">
                        <label for="" class="label"> Etape :</label>
                        <select class="input" name="etape" id="">
                            <option value="">Choisir</option>
                            <% Etape[] etapes = (Etape[])(request.getAttribute("etapes"));
                            for(Etape etape : etapes){%>
                                <option value="<%=etape.getID()%>"><%=etape.getNom() %></option>
                            <%}%>
                        </select>

                    </div>
                    <div class="row">
                        <label for="" class="label"> Coureur :</label>
                        <select name="coureur" id="">
                            <option value="">Choisir</option>
                            <% Coureur[] coureurs = (Coureur[])(request.getAttribute("coureurs"));
                            for(Coureur coureur : coureurs){%>
                                <option value="<%=coureur.getID()%>"><%=coureur.getNom() %></option>
                            <%}%>
                        </select>
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