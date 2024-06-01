<%@ page import="scaffold.framework.demo.models.course.*" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">

    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>HUHU</title>

    <%@ include file="../../templates/headerLinkList.html" %>

</head>

<body>

    <%@ include file="../../templates/sidebar.html" %>
    <section class="home-section">
        <div class="container">
            <header class="hero">
                <h2>Liste des etapes de la course</h2>
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
                        <th scope="col">ID</th>
                        <th scope="col">RANG</th>
                        <th scope="col">NOM</th>
                        <th scope="col">INFO</th>
                      </tr>
                    </thead>
                    <tbody>
                        <% Etape[] etapes =(Etape[])(request.getAttribute("etapes")) ;
                        for (Etape etape :etapes) { %>
                        <tr scope="row">
                            <td>
                            <%= etape.getID() %>
                            </td>
                            <td><%= etape.getRangEtape() %></td>
                            <td><a href="#"><%= etape.getNom() %></a></td>
                            <td>
                            <%= etape.getLongueurKM() %> KM de course
                            <small class="d-block"><%= etape.getNbCoureur() %> coureurs par equipe</small>
                            </td>

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