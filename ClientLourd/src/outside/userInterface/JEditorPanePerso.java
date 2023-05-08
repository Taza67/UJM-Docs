package outside.userInterface;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.undo.UndoManager;

import inside.IConfig;
import inside.Manager;
import inside.utilities.TextUtilities;

/**
 * Classe représentant un panneau d'édition de texte personnalisé
 * @author mourtaza
 *
 */
public class JEditorPanePerso extends JEditorPane implements IConfig {
	private static final long serialVersionUID = -1414889860922748909L;
	
	/**
	 * Manager de l'application
	 */
	private Manager manager;
	/**
	 * Gestionnaire de undo/redo (défaire/refaire)
	 */
	private UndoManager undoManager;
	/**
	 * Nombre de lignes dans le document
	 */
	private int lineCount = 1;
	/**
	 * Nombre de mots dans le document 
	 */
	private int wordCount = 0;
	/**
	 * Nombre de caractères dans le document
	 */
	private int charCount = 0;
	/**
	 * JLabel indiquant le nombre de lignes sur la fenêtre
	 */
	private JLabel lineIndicator;
	/**
	 * JLabel indiquant le nombre de mots sur la fenêtre
	 */
	private JLabel wordIndicator;
	/**
	 * JLabel indiquant le nombre de caractères sur la fenêtre
	 */
	private JLabel charIndicator;
	/**
	 * JLabel indiquant la position du curseur
	 */
	private JLabel cursorIndicator;
	/**
	 * Référence à l'éditeur lui-même
	 */
	private JEditorPanePerso self;
	
	 
	/**
	 * Instancie un panneau d'édition de texte personnalisé
	 * @param li Référence au JLabel indiquant le nombre de lignes
	 * @param wi Référence au JLabel indiquant le nombre de mots
	 * @param ci Référence au JLabel indiquant le nombre de caractères
	 */
	public JEditorPanePerso(JLabel li, JLabel wi, JLabel ci, JLabel curi) {
		super("text/plain", "");
		// Dimmensions du panneau
		this.setSize(DEFAULT_EDITOR_PANE_DIMENSIONS);
		this.setMinimumSize(DEFAULT_EDITOR_PANE_DIMENSIONS);
		this.setMargin(new Insets(
				DEFAULT_PAGE_MARGE, DEFAULT_PAGE_MARGE, DEFAULT_PAGE_MARGE, DEFAULT_PAGE_MARGE
			)	
		);
		
		// Gestionnaire de undo/redo
		undoManager = new UndoManager();
        this.getDocument().addUndoableEditListener(undoManager);
        
        // Indicateurs
        lineIndicator = li;
        wordIndicator = wi;
        charIndicator = ci;
        cursorIndicator = curi;
        
        self = this;
	}
	
	
	/**
	 * Retourne le gestionnaire undo/redo
	 * @return Gestionnaire undo/redo
	 */
	public UndoManager getUndoManager() {
		return undoManager;
	}
	
	/**
	 * Modifie la référence au undomanager de l'application
	 * @param undomanager Nouvelle référence à un undomanager
	 */
	public void setUndoManager(UndoManager uma) {
		undoManager = uma;
		this.getDocument().addUndoableEditListener(undoManager);
	}
	
	/**
	 * Modifie la référence au manager de l'application
	 * @param ma Nouvelle référence à un manager
	 */
	public void setManager(Manager ma) {
		manager = ma;
	}
	
	
	/**
	 * Initialisate la gestion d'événements
	 * @param isOnline true si l'utilisateur est connecté, false sinon
	 */
	public void initEventManagement(boolean isOnline) {
		// Undo/Redo
        this.addKeyListener((KeyListener)new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if ((e.getKeyCode() == KeyEvent.VK_Z) && ((e.getModifiersEx() & KeyEvent.CTRL_DOWN_MASK) != 0)) {
                    if (undoManager.canUndo()) {
                    	// Annuler
                    	undoManager.undo();
                    	
                    	if (!isOnline) return;
                    	// manager.faisQuelqueChose() //////////////////////////////
                        //
                        //
                    }
                } else if ((e.getKeyCode() == KeyEvent.VK_Y) && ((e.getModifiersEx() & KeyEvent.CTRL_DOWN_MASK) != 0)) {
                    if (undoManager.canRedo()) {
                    	// Refaire
                    	undoManager.redo();
                    	
                    	if (!isOnline) return;
                    	// manager.faisQuelqueChose() //////////////////////////////
                    	//
                    	//
                    }
                } 
            }
        });
        
        JEditorPanePerso epp = this;
        
        // Déplacement de curseur
        this.addCaretListener(new CaretListener() {
			
			@Override
			public void caretUpdate(CaretEvent e) {
				int lineNumber = TextUtilities.getCarretLine(epp.getText(), e.getDot());
				
				cursorIndicator.setText(e.getDot() + " / " + charCount + "c      " + 
					lineNumber + " / " + lineCount + "l");
			}
		});
        
        // Modification du texte
        this.getDocument().addDocumentListener((DocumentListener)new DocumentListener() {

			@Override
			public void changedUpdate(DocumentEvent e) {
				updateSize();
			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				updateSize();
                updateCounts(epp.getText());
                
                if (!isOnline) return;
                // manager.faisQuelqueChose() //////////////////////////////
                //
                //
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				updateSize();
                updateCounts(epp.getText());
                
                if (!isOnline) return;
                // manager.faisQuelqueChose() //////////////////////////////
                //
                //
			}
			
			/**
			 * Met à jour les compteurs de lignes, mots, caractères
			 * @param text Texte inséré/retiré
			 * @param isInsert True si insertion, false si suppression
			 */
            private void updateCounts(String text) {
            	// Incrémentation / décrémentation des compteurs
                lineCount = TextUtilities.countLines(text);
                wordCount = TextUtilities.countWords(text);
                charCount = TextUtilities.countChars(text);
                
                // Modification des labels
                lineIndicator.setText(lineCount + " ligne" + (lineCount > 1 ? "s" : ""));
                wordIndicator.setText(wordCount + " mot" + (wordCount > 1 ? "s" : ""));
                charIndicator.setText(charCount + " caractère" + (charCount > 1 ? "s" : ""));
            }
        });
	}
	
	/**
	 * Met à une certaine échelle le panneau
	 * @param newScale Nouveau facteur d'échelle
	 */
	public void scale(double newScale) {
        Font currentFont = this.getFont();
        float newFontSize = (float)(DEFAULT_DOCUMENT_FONT_SIZE * newScale);
        Font newFont = currentFont.deriveFont(newFontSize);

        this.setFont(newFont);
        
        updateSize();
	}
	
    /**
     * Met à jour les dimensions de l'éditeur et de son conteneur
     * en fonction des dimensions adaptées au texte
     */
	private int size = 1;
    private void updateSize() {
    	boolean needMoreSpace, haveMoreSpace;
    	
    	// Revalidation préalable
    	self.revalidate();
    	
    	// Récupération des dimensions adéquates pour l'éditeur
    	Dimension preferredDimensions = self.getPreferredSize();
    	
    	// Gestion de la taille de la page
		size = (preferredDimensions.height + 500) / DEFAULT_EDITOR_PANE_DIMENSIONS.height + 1;
    	
		// Vérification de la situation du document (trop/pas assez d'espace)
		needMoreSpace = preferredDimensions.height > DEFAULT_EDITOR_PANE_DIMENSIONS.height * size - 500;
		haveMoreSpace = preferredDimensions.height < DEFAULT_EDITOR_PANE_DIMENSIONS.height * (size - 1) - 500;
		
		// Aucun besoin de changement
		if (!needMoreSpace && !haveMoreSpace) return;

		// Mise à jour des dimensions
		self.setSize(
			new Dimension(
				DEFAULT_EDITOR_PANE_DIMENSIONS.width,
				DEFAULT_EDITOR_PANE_DIMENSIONS.height * size
			)
		);
		self.getParent().setPreferredSize(
			new Dimension(
				DEFAULT_EDITOR_PANE_DIMENSIONS.width,
				DEFAULT_EDITOR_PANE_DIMENSIONS.height * size
			)
		);
		
		// Revalidation
		self.revalidate();
		((JPanel)self.getParent()).revalidate();
    }
}
