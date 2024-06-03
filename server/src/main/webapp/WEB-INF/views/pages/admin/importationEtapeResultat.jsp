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
                <form method="post" action="/admin/importetaperesultats" method="POST" enctype="multipart/form-data">
                    <div class="row">
                        <h2>Importation des donnees des etape et des resultats</h2>
                    </div>
                    
                    <div class="row">
                        <label for="" class="label">Fichier etape</label>
                        <input type="file" name="etape">
                    </div>
                    <div class="row">
                        <label for="" class="label">Fichier resultat</label>
                        <input type="file" name="resultat">
                    </div>
                    <div class="row">
                        <label for="" class="label">Separateur</label>
                        <input type="text" name="separateur" placeholder="separateur">
                    </div>
                    <div class="row">
                        <label for="" class="label"></label>
                        <button type="submit">Affecter</button>                        
                    </div>
                </div>
                </form>
            </header>
        </div>    
    </section>

    <%@ include file="../../templates/script.html" %>

</body>

</html>