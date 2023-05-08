<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>UJM-Docs</title>
  <link rel="stylesheet" href="../css/style.css">
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/nouislider@14.7.0/distribute/nouislider.min.css">
  <link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
</head>
<body>
<!-- Barre de navigation -->
<nav class="menu-bar">
  <ul class="menu-bar-list">
    <li class="menu-bar-submenu-item">
                <span class="menu-bar-submenu-title">
                    Fichier
                </span>
      <ul class="menu-bar-submenu-items">
        <li>Nouveau</li>
        <li>Charger</li>
        <li>Sauvegarder</li>
        <li>Quitter</li>
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
    <button class="bar-button">Nouveau</button>
    <button class="bar-button">Charger</button>
    <button class="bar-button">Sauvegarder</button>
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
</nav>

<div class="editor-container">
  <div class="editor-subcontainer" id="editor-subcontainer">
            <textarea class="editor" id="editor">
            </textarea>
  </div>
</div>

<!-- Barre de statut -->
<nav class="status-bar">
  <div class="bar-section">
    <span class="bar-section-legend">0 / 0 lignes</span>
    <span class="bar-section-legend">0 / 0 mots</span>
    <span class="bar-section-legend">0 / 0 caractères</span>
  </div>
  <div class="bar-section">
    <span class="bar-section-legend">0 collaborateurs</span>
  </div>
  <div class="bar-section">
    <div id="zoom-slider" class="zoom-slider"></div>
  </div>
</nav>

<!-- Scripts -->
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/nouislider@14.7.0/distribute/nouislider.min.js"></script>
<script src="../js/script.js"></script>
</body>
</html>
