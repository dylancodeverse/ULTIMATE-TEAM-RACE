<%@ page import="scaffold.framework.demo.models.course.*" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">

    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>ULTIMATE RACE</title>

    <%@ include file="../../templates/headerLinkList.html" %>

</head>

<body>

    <%@ include file="../../templates/sidebar.html" %>
    <section class="home-section">
        <div class="container">
            <header class="hero">
                <h2>Liste des classements de la course</h2>
                <form class="prediction-form"  method="post">
                    <input type="prediction" name="prediction" placeholder="Recherche">
                    <button type="submit">Voir le resultat</button>
                </form>
            </header>
            <section class="resources">
              <div class="table-responsive custom-table-responsive">
        
                  <table class="table custom-table">
                    <thead>
                      <tr>  
                        <th scope="col">RANG</th>
                        <th scope="col">COUREUR</th>
                        <th scope="col">POINTS</th>
                        <th scope="col">ETAPE</th>
                        <th scope="col">Details</th>
                        
                        <th scope="col">GENRE</th>
                        <th scope="col">CHRONO</th>
                        <th scope="col">PENALITE</th>
                        <th scope="col">TEMPS FINAL</th>

                      </tr>
                    </thead>
                    <tbody>

                        <% ClassementCRparetape[] classements =(ClassementCRparetape[])(request.getAttribute("details")) ;
                        for (ClassementCRparetape classement :classements) { %>
                        <tr scope="row">
                            <td><%= classement.getRang() %></td>
                            <td>
                            <%= classement.getCoureurnom() %>
                            <small class="d-block">De l'equipe <%= classement.getEquipe() %> </small>
                            </td>
                            <td><%= classement.getPoint() %></td>
                            <td><%= classement.getEtapenom() %></td>
                            <td>
                                <small class="d-block">Arrivee: <%= classement.getArrivee() %></small>
                            </td>
                            <td><%= classement.getGenre() %></td>
                            <td><%= classement.getChronoAsSTR() %></td>
                            <td><%= classement.getTempspenaliteAsSTR() %></td>
                            <td><%= classement.getArriveeavecpenalite() %></td>

                        </tr>
                        <tr class="spacer"><td colspan="100"></td></tr>
                      <% } %>
                      
                    </tbody>
                  </table>
              </div>
            </section>
        </div>
    </section>

    <%@ include file="../../templates/script.html" %>

</body>

</html>