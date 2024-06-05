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
                <h2>Liste des classements de la course</h2>
                <form class="prediction-form"  method="GET" action="/admin/classementGeneralgeneratePDF">
                    <button type="submit"> Generer PDF pour 1ere place</button>
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
                      </tr>
                    </thead>
                    <tbody>

                        <% ClassementEQ[] classements =(ClassementEQ[])(request.getAttribute("classements")) ;
                        for (ClassementEQ classement :classements) { %>
                        <tr scope="row">
                            <td><%= classement.getRang() %></td>
                            <td>
                            <small class="d-block"> <a href="/home/classementequipedetail?equipe=<%= classement.getEquipe() %>"> <%= classement.getEquipe() %> </a> </small>
                            </td>
                            <td><%= classement.getPoints() %></td>
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
      <% String[] chartInf = (String[])(request.getAttribute("chart")); %>
      var xValues = <%= chartInf[2] %>
      var yValues = <%= chartInf[0] %>;
      var barColors = <%= chartInf[1] %>;
      


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