<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<title>Accueil</title>
</head>
<body>

	<c:if var="error" test="${error != null}" scope="session">
		<div id="error">
		<c:choose>

			<c:when test="${error == 'pseudo'}">
				<p>Pseudo incorrect.</p>
				<br />

				<c:remove var="error" scope="session"/>
			</c:when>

			<c:when test="${error == 'inaccessible'}">
				<p>Vous ne pouvez pas accéder à cette page</p>
				<p>Merci de vous connecter</p>
				<c:remove var="error" scope="session"/>
			</c:when>

			<c:when test="${error == 'weird'}">
				<p>Cet utilisateur n'est pas valide</p>
				<p>Merci de vous connecter avec un utilisateur valide</p>

				<c:remove var="error" scope="session"/>
			</c:when>
		</c:choose>
		</div>
	</c:if>

	<c:if var="inscrit" test="${inscrit != null}" scope="session">
		<div class="info">
			<p>Nouvel inscrit</p>
			<p>Vous pouvez vous connecter</p>
			<br />
		</div>
		<c:remove var="inscrit" scope="session"/>
	</c:if>

	<h2>Connexion</h2>

	<form method="post">
		<label for="pseudo">Pseudo:</label> <input type="text" id="pseudo"
			name="pseudo" required> <br> <label for="mot_de_passe">Mot
			de passe:</label> <input type="password" id="mot_de_passe"
			name="mot_de_passe" required> <br> <input type="submit"
			value="Se connecter">
	</form>
	<a href="inscription">Inscription</a>

	<form method="post" id="invite">

		<input type="hidden" name="invite" value="true"> <input
			type="submit" value="Connexion en tant qu'invité">

	</form>

</body>
</html>