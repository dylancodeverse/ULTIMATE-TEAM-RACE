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
                <form method="post" action="/admin/importpoint" method="POST">
                    <div class="row">
                        <h2>Importation des donnees des points</h2>
                    </div>
                    <div class="row">
                        <input type="file" name="point">
                    </div>
                    <div class="row">
                        <input type="text" name="separateur" placeholder="separateur">
                    </div>
                </form>
            </header>
        </div>    
    </section>
    <%@ include file="../../templates/script.html" %>

</body>

</html>