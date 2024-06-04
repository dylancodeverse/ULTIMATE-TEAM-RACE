<%@ page import="scaffold.framework.demo.models.course.*" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">

    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>ULTIMATE RACE</title>

    <%@ include file="../../templates/headerLinkList.html" %>
    <script src="/static/js/chart.umd.min.js"></script>

</head>

<body>

    <%@ include file="../../templates/sidebar.html" %>
    <section class="home-section">
        <div class="container">
            <header class="hero">
                <h2>Liste des classements de la course selon la categorie de votre filtre </h2>
                <form class="prediction-form"  method="get">
                    <input type="prediction" name="categorie" placeholder="Filtrer">
                    <button type="submit">Voir le resultat</button>
                </form>
            </header>
            <section class="resources">
              <div class="table-responsive custom-table-responsive">
        
                  <table class="table custom-table">
                    <thead>
                      <tr>  
                        <th scope="col">RANG</th>
                        <th scope="col">EQUIPE</th>
                        <th scope="col">POINTS</th>
                        <th scope="col">Categorie</th>

                      </tr>
                    </thead>
                    <tbody>

                        <% Classementparequipeavecpointparcategorie[] classements =(Classementparequipeavecpointparcategorie[])(request.getAttribute("classement")) ;
                        for (Classementparequipeavecpointparcategorie classement :classements) { %>
                        <tr scope="row">
                            <td><%= classement.getRank() %></td>
                            <td>
                            <small class="d-block"><%= classement.getEquipe() %> </small>
                            </td>
                            <td><%= classement.getPoint() %></td>
                            <td>
                            <small class="d-block"><%= classement.getCategorie() %> </small>
                            </td>

                        </tr>
                        <tr class="spacer"><td colspan="100"></td></tr>
                      <% } %>
                      
                    </tbody>
                  </table>
              </div>
              <div class="chart">
                <canvas id="myChart" width="400" height="400"></canvas>
              </div>

            </section>

        </div>
    </section>

    <%@ include file="../../templates/script.html" %>
    <script>
      // Données pour le graphique
      var xValues = ["Italy", "France", "Spain", "USA", "Argentina"];
      var yValues = [55, 49, 44, 24, 15];
      var barColors = ["red", "green", "blue", "orange", "brown"];
  
      // Initialisation du graphique
      new Chart("myChart", {
          type: "pie",
          data: {
              labels: xValues,
              datasets: [{
                  backgroundColor: barColors,
                  data: yValues
              }]
          },
          options: {
              title: {
                  display: true,
                  text: "World Wide Wine Production"
              }
          }
      });
  </script>
  
</body>

</html>