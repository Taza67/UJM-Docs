<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="connexion.User" %> <!-- import the User class -->
<jsp:useBean id="user" type="connexion.User" scope="session"/>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>UJM-Docs</title>
  <link rel="stylesheet" type="text/css" href="<c:url value='/css/style.css'/>">
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/nouislider@14.7.0/distribute/nouislider.min.css">
  <link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
</head>
<body>

<!-- Récupération des attributs -->

<!-- Utilisé pour pouvoir accéder aux données de l'utilisateur en JS -->
<div id="user-data" data-user="${userJson}" style="display:none;"></div>

<!-- Barre de navigation -->
<body>
<!-- Barre de navigation -->
<nav class="menu-bar">
  <ul class="menu-bar-list">
    <li class="menu-bar-submenu-item">
                <span class="menu-bar-submenu-title">
                    Fichier
                </span>
      <ul class="menu-bar-submenu-items">
        <li id="new-document">Nouveau</li>
        <li>Ouvrir</li>
        <li>Sauvegarder</li>
        <li>Télécharger</li>
        <li>Se&nbspdéconnecter</li>
      </ul>
    </li>
    <li class="menu-bar-submenu-item">
                <span class="menu-bar-submenu-title">
                    Édition
                </span>
      <ul class="menu-bar-submenu-items">
        <li>Annuler</li>
        <li>Refaire</li>
        <li>Couper</li>
        <li>Copier</li>
        <li>Coller</li>
      </ul>
    </li>
    <li class="menu-bar-submenu-item">
                <span class="menu-bar-submenu-title">
                    Affichage
                </span>
      <ul class="menu-bar-submenu-items">
        <li>Barre d'outils</li>
        <li>Barre de statut</li>
      </ul>
    </li>
    <li class="menu-bar-submenu-item">
                <span class="menu-bar-submenu-title">
                    Aide
                </span>
    </li>
  </ul>
</nav>

<!-- Barre d'outils -->
<nav class="tools-bar">
  <div class="bar-section">
    <button class="bar-button" id="new-document">Nouveau</button>
    <button class="bar-button">Charger</button>
    <button class="bar-button">Sauvegarder</button>
  </div>
  <div class="bar-section">
    <span class="bar-section-legend">Collaborateur</span>
    <span class="bar-section-legend" id="pseudo">${user.pseudo}</span>
  </div>
  <div class="bar-section">
    <button class="bar-button">Couper</button>
    <button class="bar-button">Copier</button>
    <button class="bar-button">Coller</button>
  </div>
  <div class="bar-section">
    <span class="bar-section-legend">Collaborateur</span>
    <button class="bar-button">+</button>
    <button class="bar-button">-</button>
  </div>
  <div class="bar-section">
    <span class="bar-section-legend">Actions</span>
    <form method="post">
      <button class="bar-button" type="submit" name="deconnexion">Se déconnecter</button>
    </form>
  </div>
</nav>

<div class="editor-container">
  <div class="editor-subcontainer" id="editor-subcontainer">
    <textarea class="editor" id="editor"></textarea>
  </div>
</div>

<!-- Barre de statut -->
<nav class="status-bar">
  <div class="bar-section">
    <span class="bar-section-legend" id="page-indicator">1 / 1 page</span>
    <span class="bar-section-legend" id="line-indicator">1 / 1 ligne</span>
    <span class="bar-section-legend" id="word-indicator">0 / 0 mot</span>
    <span class="bar-section-legend" id="char-indicator">0 / 0 caractère</span>
  </div>
  <div class="bar-section">
    <span class="bar-section-legend" id="collaborator-indicator">0 collaborateur</span>
  </div>
  <div class="bar-section">
    <div class="zoom-slider" id="zoom-slider"></div>
  </div>
</nav>

<!-- Scripts -->
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/nouislider@14.7.0/distribute/nouislider.min.js"></script>
<script src="<c:url value='/js/mainScript.js'/>"></script>

</body>
</html>
