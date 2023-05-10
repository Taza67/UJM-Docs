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
    editor = ace.edit("editor");
    editor.setTheme("ace/theme/github");
    // editor.session.setMode("ace/mode/javascript");
    editor.session.setOption("wrap", true);
    editor.setOption("printMargin", false);

    // Indicateurs sur le curseur
    pageIndicator = document.getElementById("page-indicator");
    lineIndicator = document.getElementById("line-indicator");
    wordIndicator = document.getElementById("word-indicator");
    charIndicator = document.getElementById("char-indicator");

    // Événements
    editor.session.selection.on('changeSelection', handleEditorEvent);
    editor.session.on('change', handleEditorEvent);
}


// Gestionnaires //////////////////////

// Gère les changements dans le textarea
function handleEditorEvent() {
    // Récupération des infos sur le curseur
    const cursorPos = editor.getCursorPosition();
    const textBeforeCursor = editor.session.getTextRange({start: {row: 0, column: 0}, end: cursorPos});

    // Position du curseur
    // // Numéro de ligne
    const lineNumber = cursorPos.row + 1;
    // // Numéro de mot
    const wordNumber = textBeforeCursor.split(/\s+/).length;
    // // Numéro de caractère
    const charNumber = textBeforeCursor.length;


    // Compteurs
    const lineCount = editor.session.getLength();
    const wordCount = editor.getValue().split(/\s+/).filter(word => word.length > 0).length;
    const charCount = editor.getValue().length;

    // Modification des indicateurs
    lineIndicator.innerText = lineNumber + " / " + lineCount + " ligne" + (lineCount > 1 ? "s": "");
    wordIndicator.innerText = wordNumber + " / " + wordCount + " mot" + (wordCount > 1 ? "s": "");
    charIndicator.innerText = charNumber + " / " + charCount + " caractère" + (charCount > 1 ? "s": "");
}

// Gère l'annulation de modification (undo)
function handleUndo() {
    editor.undo();
}

// Gère l'annulation d'annulation (redo)
function handleRedo() {
    editor.redo();
}

// EXPORTS ////////////////////////////////////////////////

export {
    initEditor, handleRedo, handleUndo
};

