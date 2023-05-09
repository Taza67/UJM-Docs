// VARIABLES GLOBALES /////////////////////////////////////

let exportDocumentButtons;


// FONCTIONS //////////////////////////////////////////////

// Initialisations ////////////////////

// Initialise la fonctionnalité d'export de documents
function initExportDocument() {
    exportDocumentButtons = document.querySelectorAll("#export-document");
    exportDocumentButtons.forEach(function(element, index) {
        element.onclick = handleExportDocumentEvent;
    });
}


// Gestion d'événements ///////////////

// Gère les clics sur les boutons d'export de documents
function handleExportDocumentEvent(event) {
    window.alert("Vous avez demandé un export");
}


// EXPORTS ////////////////////////////////////////////////

export {
    initExportDocument
}


