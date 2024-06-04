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
                <h2>Liste des penalites</h2>
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
                        <th scope="col">ETAPE</th>
                        <th scope="col">EQUIPE</th>
                        <th scope="col">TEMPS PENALITE</th>
                      </tr>
                    </thead>
                    <tbody>

                        <% GetPenaliteAll[] penalites =(GetPenaliteAll[])(request.getAttribute("allPenalites")) ;
                        for (GetPenaliteAll penalite :penalites) { %>
                        <tr scope="row">
                            <td><%= penalite.getEtapes() %></td>

                            <td><%= penalite.getEquipes() %></td>
                            
                            <td><%= penalite.getTemps() %></td>
                            <td> <a href="/admin/deletePenalite/<%= penalite.getId() %>">Supprimer</td>

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