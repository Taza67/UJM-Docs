// IMPORTS ////////////////////////////////////////////////

import { handleRedo, handleUndo } from "./historyManager.js";

// VARIABLES GLOBALES /////////////////////////////////////

let copyButtons, cutButtons, pasteButtons, undoButtons, redoButtons;


// FONCTIONS //////////////////////////////////////////////

// Initialisations ////////////////////

// Initialise les raccourcis d'édition de document
function initEditionShortcuts() {
    copyButtons = document.querySelectorAll("#copy-text");
    cutButtons = document.querySelectorAll("#cut-text");
    pasteButtons = document.querySelectorAll("#paste-text");
    undoButtons = document.querySelectorAll("#undo-action");
    redoButtons = document.querySelectorAll("#redo-action");

    copyButtons.forEach(function(element, index) {
        element.onclick = handleCopyEvent;
    });

    cutButtons.forEach(function(element, index) {
        element.onclick = handleCutEvent;
    });

    pasteButtons.forEach(function(element, index) {
        element.onclick = handlePasteEvent;
    });

    undoButtons.forEach(function(element, index) {
        element.onclick = handleUndoEvent;
    });

    redoButtons.forEach(function(element, index) {
        element.onclick = handleRedoEvent;
    });
}


// Gestion d'événements ///////////////

// Gère les clics sur les boutons de copie
function handleCopyEvent(event) {
    window.alert("Vous avez demandé un copier");
}

// Gère les clics sur les boutons de coupure
function handleCutEvent(event) {
    window.alert("Vous avez demandé un couper");
}

// Gère les clics sur les boutons de collage
function handlePasteEvent(event) {
    window.alert("Vous avez demandé un coller");
}

// Gère les clics sur les boutons de défaire
function handleUndoEvent(event) {
    handleUndo();
}

// Gère les clics sur les boutons de refaire
function handleRedoEvent(event) {
    handleRedo();
}


// EXPORTS ////////////////////////////////////////////////

export {
    initEditionShortcuts
}


