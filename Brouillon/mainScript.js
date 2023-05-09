// IMPORTS ////////////////////////////////////////////////

import { initEditor } from "./editorScript.js";
import { initNewDocument } from "./newDocumentScript.js";
import { initZoomSlider } from "./zoomScript.js";


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
    // de document
    initNewDocument();
}


// Chargement

window.addEventListener("load", function() {
    // Initialisation
    init();
});
