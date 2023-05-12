<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>UJM Docs - Connexion</title>
	<link rel="stylesheet" type="text/css" href="<c:url value='/css/inscriptionConnexionStylesheet.css'/>">
</head>


<body>
	<div class="title">
		<h1>UJM<br>DOCS</h1>
	</div>
	<c:if test="${sessionScope['inscrit']== 'true'}">

		<span class="error"> Nouvel Inscrit ! <br/>
			Merci de vous connecter.
		</span>
		<%
			session.removeAttribute("inscrit");
		%>
	</c:if>
	<form method="post">
		<input type="text" id="pseudo" name="pseudo" placeholder="Pseudo..." required>
		<input type="password" id="mot_de_passe" name="mot_de_passe" placeholder="Mot de passe..." required>
		<input type="submit" value="Se connecter">
		<c:if test="${requestScope['error'] eq 'pseudo'}">
			<span class="error">Identifiant(s) incorrect(s)</span>
			<%
				request.removeAttribute("error");
			%>
		</c:if>

		<c:if test="${sessionScope['errorRedirect'] eq 'inaccessible'}">
            <span class="error">
                Vous ne pouvez pas accéder à cette page.
                <br/>
                Veuillez vous connecter.
            </span>

			<%
				session.removeAttribute("errorRedirect");
			%>
		</c:if>

		<c:if test="${sessionScope['errorRedirect'] eq 'weird'}">
            <span class="error">
                Ce compte n'est pas valide.
            </span>

			<%
				session.removeAttribute("weird");
			%>
		</c:if>

		<a href="${pageContext.request.contextPath}/inscription">Inscription</a>
	</form>
	</body>
</html>