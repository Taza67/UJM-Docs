// VARIABLES GLOBALES /////////////////////////////////////

let newDocumentButtons;
let documentName;

// FONCTIONS //////////////////////////////////////////////

// Initialisations ////////////////////

// Initialise la fonctionnalité de création de nouveau document
function initNewDocument() {
    newDocumentButtons = document.querySelectorAll("#new-document");
    newDocumentButtons.forEach(function(element, index) {
        element.onclick = handleNewDocumentEvent;
    });
}

// Gestion d'événements ///////////////

// Gère les clics sur les boutons de création de document
function handleNewDocumentEvent(event) {
    documentName = window.prompt("Entrez le nom du fichier :");
}




// EXPORTS ////////////////////////////////////////////////

export {
    initNewDocument
}


