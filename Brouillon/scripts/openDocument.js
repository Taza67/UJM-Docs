// VARIABLES GLOBALES /////////////////////////////////////

let openDocumentButtons;


// FONCTIONS //////////////////////////////////////////////

// Initialisations ////////////////////

// Initialise la fonctionnalité d'ouverture de documents
function initOpenDocument() {
    openDocumentButtons = document.querySelectorAll("#open-document");
    openDocumentButtons.forEach(function(element, index) {
        element.onclick = handleOpenDocumentEvent;
    });
}


// Gestion d'événements ///////////////

// Gère les clics sur les boutons d'ouverture de document
function handleOpenDocumentEvent(event) {
    let documentName = window.prompt("Entrez le nom du fichier :");
    console.log(documentName);
}


// EXPORTS ////////////////////////////////////////////////

export {
    initOpenDocument
}
