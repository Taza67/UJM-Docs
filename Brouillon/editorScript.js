// VARIABLES GLOBALES /////////////////////////////////////

// Indicateurs du curseur
let pageIndicator, lineIndicator, wordIndicator, charIndicator;
let pageCount, lineCount, wordCount, charCount;

// Éditeur
let editor;


// FONCTIONS //////////////////////////////////////////////

// Initialisations ////////////////////

// Initialise l'éditeur
function initEditor() {
    // Indicateurs sur le curseur
    pageIndicator = document.getElementById("page-indicator");
    lineIndicator = document.getElementById("line-indicator");
    wordIndicator = document.getElementById("word-indicator");
    charIndicator = document.getElementById("char-indicator");

    // Éditeur
    editor = $("#editor");

    // Événements
    editor.on("input keyup mouseup", handleEditorEvent);
}


// Gestionnaires //////////////////////

// Gère les changements dans le textarea
function handleEditorEvent() {
    // Récupération des infos sur le curseur
    const cursorPos = this.selectionStart;
    const textBeforeCursor = this.value.substring(0, cursorPos);

    // Position du curseur
    const lineNumber = textBeforeCursor.split("\n").length;
    const wordNumber = textBeforeCursor.split(/\s+/).length;
    const charNumber = textBeforeCursor.length;

    // Compteurs
    const lineCount = this.value.split("\n").length;
    const wordCount = this.value.split(/\s+/).length;
    const charCount = this.value.length;

    // Modification des indicateurs
    lineIndicator.innerText = lineNumber + " / " + lineCount + " ligne" + (lineCount > 1 ? "s": "");
    wordIndicator.innerText = wordNumber + " / " + wordCount + " mot" + (wordCount > 1 ? "s": "");
    charIndicator.innerText = charNumber + " / " + charCount + " caractère" + (charCount > 1 ? "s": "");
}

// EXPORTS ////////////////////////////////////////////////

export {
    initEditor
};

