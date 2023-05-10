// VARIABLES GLOBALES /////////////////////////////////////

let newDocumentButtons;


// FONCTIONS //////////////////////////////////////////////

// Initialisations ////////////////////

// Initialise la fonctionnalité de création de nouveau documents
function initNewDocument() {
    newDocumentButtons = document.querySelectorAll("#new-document");
    newDocumentButtons.forEach(function(element, index) {
        element.onclick = handleNewDocumentEvent;
    });
}


// Gestion d'événements ///////////////

// Gère les clics sur les boutons de création de documents
function handleNewDocumentEvent(event) {
    let documentName = window.prompt("Entrez le nom du fichier :");
    console.log(documentName);
}


// EXPORTS ////////////////////////////////////////////////

export {
    initNewDocument
}


