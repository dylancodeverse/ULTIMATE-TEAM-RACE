<%@ page import="scaffold.framework.demo.models.course.*" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">

    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>ULTIMATE RACE</title>

    <%@ include file="../../templates/headerLinkList.html" %>
    <link rel="stylesheet" href="/static/css/modal/modal.css">
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
                            <td> <button class="btnn" onclick="openModal('/admin/deletePenalite/<%= penalite.getId() %>')" id="<%= penalite.getId() %>" value="">Supprimer</button></td>
                              
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


  <!-- The Modal -->
  <div id="myModal" class="modal">
      <div class="modal-content">
        <form id="foo" action="/admin/deletePenalite/" method="get">
          <span class="close" onclick="closeModal()">&times;</span>
          <p>Etes-vous sur de vouloir supprimer cet element ?</p>
          <button class="modal-button confirm" type="submit">Confirmer</button>
          <button type="reset" class="modal-button cancel" onclick="closeModal()">Annuler</button>
        </form>

      </div>
  </div>
    <script>
      // Ouvrir la modal
      function openModal(link) {
        console.log(document.getElementById('myModal'))
          document.getElementById('myModal').style.display = 'block';
          document.getElementById('foo').action = link;
      }

      // Fermer la modal
      function closeModal() {
          document.getElementById('myModal').style.display = 'none';
      }

      // Confirmer la suppression

      // Fermer la modal en cliquant en dehors du contenu
      window.onclick = function(event) {
          var modal = document.getElementById('myModal');
          if (event.target == modal) {
              modal.style.display = 'none';
          }
      }
  </script>

</body>

</html>