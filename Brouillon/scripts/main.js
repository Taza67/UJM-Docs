// IMPORTS ////////////////////////////////////////////////

import { initEditor } from "./editor.js";
import { initNewDocument } from "./newDocument.js";
import { initOpenDocument } from "./openDocument.js";
import { initSaveDocument } from "./saveDocument.js";
import { initZoomSlider } from "./zoom.js";
import { initExportDocument } from "./exportDocument.js";


// VARIABLES GLOBALES /////////////////////////////////////



// FONCTIONS //////////////////////////////////////////////

// Initialisations ////////////////////

// Initialise l'éditeur
function init() {
    // Initialisation du zoomSlider
    initZoomSlider();

    // Initialisation de l'éditeur
    initEditor();

    // Initialisation de la fonctionnalité de création
    initNewDocument();

    // Initialisation de la fonctionnalité d'ouverture
    initOpenDocument();

    // Initialisation de la fonctionnalité de sauvegarde
    initSaveDocument();

    // Initialisation de la fonctionnalité d'export
    initExportDocument();

    // Initialisation des raccourcis
    // initEditionShortcuts();
}


// Chargement

window.addEventListener("load", function() {
    // Initialisation
    init();
});
