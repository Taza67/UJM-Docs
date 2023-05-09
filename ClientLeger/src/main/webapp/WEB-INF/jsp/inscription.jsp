<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Inscription</title>
    <link rel="stylesheet" type="text/css" href="<c:url value='/css/style.css'/>">

</head>
<body>

<c:choose>
    <c:when test="${error == 'diff'}">
        <p>Pas le même mdp</p>
    </c:when>
    <c:when test="${error == 'exists'}">
        <p> Ce compte existe déjà.</p>
        <a href="">Se connecter</a>
        <c:remove var="error" scope="session"/>
    </c:when>
</c:choose>

<h1>Inscription</h1>
<br/>

<h2>Inscription</h2>

<form method="post">
    <label for="pseudo">Pseudo:</label>
    <input type="text" id="pseudo" name="pseudo" required>
    <br>
    <label for="mot_de_passe">Mot de passe:</label>
    <input type="password" id="mot_de_passe" name="mot_de_passe" required>
    <label for="verification">Vérifier le mot de passe</label>
    <input type="password" id="verification" name="verification" required>
    <br>
    <input type="submit" value="S'inscrire">
</form>

<button id="bouton-retour" type="button">Connexion</button>

<script async>
    let cheminAccueil = window.location.pathname.split('/').slice(0, -1).join('/') + '/index';

    // Ajouter un gestionnaire d'événement au bouton
    var boutonRetour = document.getElementById('bouton-retour');
    boutonRetour.addEventListener('click', function() {
        // Rediriger l'utilisateur vers la page d'accueil
        window.location.href = cheminAccueil;
    });
</script>
</body>
</html>