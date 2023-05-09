// IMPORTS ////////////////////////////////////////////////

import { initEditor } from "./editorScript.js";
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
}


// Chargement

window.addEventListener("load", function() {
    // Initialisation
    init();
});
