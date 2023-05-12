// VARIABLES GLOBALES /////////////////////////////////////

let saveDocumentButtons;


// FONCTIONS //////////////////////////////////////////////

// Initialisations ////////////////////

// Initialise la fonctionnalité de sauvegarde de documents
function initSaveDocument() {
    saveDocumentButtons = document.querySelectorAll("#save-document");
    saveDocumentButtons.forEach(function(element, index) {
        element.onclick = handleSaveDocumentEvent;
    });
}


// Gestion d'événements ///////////////

// Gère les clics sur les boutons de sauvegarde de documents
function handleSaveDocumentEvent(event) {
    window.alert("Vous avez demandé une sauvegarde");
}


// EXPORTS ////////////////////////////////////////////////

export {
    initSaveDocument
}


