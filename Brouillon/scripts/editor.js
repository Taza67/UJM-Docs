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

    // Position du curseur
    // // Numéro de ligne
    const lineNumber = cursorPos.row + 1;
    // // Numéro de mot
    const text = editor.getValue();
    const words = text.split(/\s+/).filter(word => word.length > 0);
    const wordNumber = words.findIndex((word, index) => {
        const wordPosition = editor.session.getDocument().positionToIndex({row: cursorPos.row, column: cursorPos.column - word.length});
        return editor.session.getDocument().getTextRange({start: {row: cursorPos.row, column: wordPosition}, end: cursorPos}).split(/\s+/).length - 1 === index;
    }) + 1;
    // // Numéro de caractère
    const charNumber = cursorPos.column + 1;

    // Compteurs
    const lineCount = editor.session.getLength();
    const wordCount = words.length;
    const charCount = text.length;

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

