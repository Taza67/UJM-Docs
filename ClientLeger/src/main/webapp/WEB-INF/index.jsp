<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Accueil</title>
</head>
<body>
<c:choose>

    <c:when test="${error == 'pseudo'}">
        <p>Pseudo incorrect.</p>
    </c:when>

    <br/>

    <c:when test="${invite!= null}">
        <p>Invité</p>
        <br/>
    </c:when>

    <c:when test="${inscrit!= null}">
        <p>Nouvel inscrit</p>
        <p>Vous pouvez vous connecter</p>
        <br/>

    </c:when>
</c:choose>

<h2>Connexion</h2>

<form method="post">
    <label for="pseudo">Pseudo:</label>
    <input type="text" id="pseudo" name="pseudo" required>
    <br>
    <label for="mot_de_passe">Mot de passe:</label>
    <input type="password" id="mot_de_passe" name="mot_de_passe" required>
    <br>
    <input type="submit" value="Se connecter">
</form>
<a href="inscription">Inscription</a>

<form method="post" id="invite">

    <input type="hidden" name="invite" value="true">
    <input type="submit" value="Connexion en tant qu'invité">

</form>



</body>
</html>