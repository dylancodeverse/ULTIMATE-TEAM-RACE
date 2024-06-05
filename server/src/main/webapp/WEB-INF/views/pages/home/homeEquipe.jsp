<%@ page import="scaffold.framework.demo.models.equipe.*" %>
<%@ page import="scaffold.framework.demo.models.course.*" %>




<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">

    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>ULTIMATE RACE</title>

    <%@ include file="../../templates/headerLink.html" %>

</head>

<body>

    <%@ include file="../../templates/sidebar.html" %>
    <section class="home-section">
        <div class="container">
            <header class="hero">
                <h1>EQUIPE <%= request.getSession().getAttribute("USRID").toString() %></h1>
                <form class="prediction-form"  method="post">
                    <input type="prediction" name="prediction" placeholder="Recherche">
                    <button type="submit">Filter</button>
                </form>
            </header>
            <% for(HomeEquipe home: (HomeEquipe[])(request.getAttribute("homeequipes"))){%>
            <section class="resources">
                <h2><%= home.getEtapenom() %> :</h2>
                <div class="resource-cards">
                    <div class="card">
                        <%for(int i =0; i< home.getCoureurs().size(); i++){%>
                        <h3> <p> <%= home.getCoureurs().get(i) %></p></h3>
                        <h3> avec chrono :<p><%= home.getChrono().get(i) %></p></h3>
                        <%}%>
                        <a href="/equipe/affectation?etapeid=<%=home.getEtapeId()%>"> 
                            <button > Ajouter</button>
                        </a>
                    </div>
                </div>
            </section>
            <% } %>        
        </div>        
    </section>
    <%@ include file="../../templates/script.html" %>

</body>

</html>