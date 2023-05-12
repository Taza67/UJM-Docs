<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Inscription</title>
    <link rel="stylesheet" type="text/css" href="<c:url value='/css/inscriptionConnexionStylesheet.css'/>" />
</head>


<body>
    <div class="title">

    <h1>UJM<br>DOCS</h1>

    <form method="post">
        <input type="text" id="pseudo" name="pseudo" placeholder="Pseudo..." required>
        <input type="password" id="mot_de_passe" name="mot_de_passe" placeholder="Mot de passe, une fois..." required>
        <input type="password" id="verification" name="verification" placeholder="Mot de passe, deux fois..." required>
        <c:if test="${requestScope['error'] == 'diff'}">
            <span class="error">Mots de passe différents</span>
            <%
                request.removeAttribute("error");
            %>
        </c:if>
        <input type="submit" value="S'inscrire">

        <c:if test="${requestScope['error'] == 'exists'}">
            <span class="error">Compte déjà existant</span>
            <%
                request.removeAttribute("error");
            %>
        </c:if>

        <a href="${pageContext.request.contextPath}/index">Connexion</a>
    </form>
    </div>
</body>