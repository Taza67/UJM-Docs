// VARIABLES GLOBALES /////////////////////////////////////

let zoomSliderDom;
let zoomSlider;
let editorSubcontainerDom;


// FONCTIONS //////////////////////////////////////////////

// Initialisations ////////////////////

// Initialise le slider du zoom
function initZoomSlider() {
    // Récupération de l'élément DOM qui contiendra le slider
    zoomSliderDom = document.getElementById('zoom-slider');

    // Récupération de l'élément DOM contenant l'éditeur
    editorSubcontainerDom = document.getElementById('editor-subcontainer');

    // Création du slider du zoom
    zoomSlider = noUiSlider.create(zoomSliderDom, {
        start: 100,
        range: {
            min: 50,
            max: 100,
        },
        step: 1,
        connect: [true, false],
        tooltips: true,
        format: {
            to: (value) => value.toFixed(1),
            from: (value) => parseFloat(value),
        },
    });

    // Gestion du zoom
    zoomSlider.on('update', (values) => {
        const zoomValue = values[0] / 100.;
        editorSubcontainerDom.style.transform = `scale(${zoomValue})`;
    });
}


// CHARGEMENT /////////////////////////////////////////////

$(document).ready(function () {
    initZoomSlider();
});