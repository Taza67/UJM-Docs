package exterieur.application;

import java.awt.Font;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.EventObject;

import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.undo.UndoManager;
import interieur.IConfig;
import interieur.TextUtilities;

/**
 * Classe représentant un panneau d'édition de texte personnalisé
 * @author mourtaza
 *
 */
public class JEditorPanePerso extends JEditorPane implements IConfig {
	private static final long serialVersionUID = -1414889860922748909L;
	
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
	 * Instancie un panneau d'édition de texte personnalisé
	 * @param li JLabel indiquant le nombre de lignes
	 * @param wi JLabel indiquant le nombre de mots
	 * @param ci JLabel indiquant le nombre de caractères
	 */
	public JEditorPanePerso(JLabel li, JLabel wi, JLabel ci) {
		super("text/plain", "");
		// Dimmensions du panneau
		this.setPreferredSize(textPaneDimensions);
		this.setMinimumSize(textPaneDimensions);
		this.setMargin(new Insets(50, 50, 50, 50));
		
		// Gestionnaire de undo/redo
		undoManager = new UndoManager();
        this.getDocument().addUndoableEditListener(undoManager);
        
        // Indicateurs
        lineIndicator = li;
        wordIndicator = wi;
        charIndicator = ci;
        
        // Gestion d'événements
        initEventManagement();
	}
	
	
	/**
	 * Retourne le gestionnaire undo/redo
	 * @return Gestionnaire undo/redo
	 */
	public UndoManager getUndoManager() {
		return undoManager;
	}
	
	
	/**
	 * Initialisate la gestion d'événements
	 */
	public void initEventManagement() {
		// Undo/Redo
        this.addKeyListener((KeyListener)new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if ((e.getKeyCode() == KeyEvent.VK_Z) && ((e.getModifiersEx() & KeyEvent.CTRL_DOWN_MASK) != 0)) {
                    if (undoManager.canUndo()) undoManager.undo();
                } else if ((e.getKeyCode() == KeyEvent.VK_Y) && ((e.getModifiersEx() & KeyEvent.CTRL_DOWN_MASK) != 0)) {
                    if (undoManager.canRedo())undoManager.redo();
                }
            }
        });
        
        // Modification du texte
        JEditorPanePerso epp = this;
        this.getDocument().addDocumentListener((DocumentListener)new DocumentListener() {

			@Override
			public void changedUpdate(DocumentEvent e) {
				
			}

			@Override
			public void insertUpdate(DocumentEvent e) {
                updateCounts(epp.getText());
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
                updateCounts(epp.getText());
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
                lineIndicator.setText("1 / " + lineCount + " lignes");
                wordIndicator.setText("0 / " + wordCount + " mots");
                charIndicator.setText("0 / " + charCount + " caractères");
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
	}
	
	/**
	 * Coupe le texte sélectionné dans l'éditeur et le stocke dans le presse-papiers système
	 */
    public void cutSelectedText() {
        String selectedText = this.getSelectedText();
        if (selectedText != null) {
        	// Copie du texte sélectionné dans le presse-papiers
            StringSelection stringSelection = new StringSelection(selectedText);
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            clipboard.setContents(stringSelection, null);

            // On retire le texte sélectionné
            this.replaceSelection("");
        }
    }
	
	/**
	 * Copie le texte sélectionné dans l'éditeur dans le presse-papiers système
	 */
    public void copySelectedText() {
        String selectedText = this.getSelectedText();
        if (selectedText != null) {
        	// Copie du texte sélectionné dans le presse-papiers
            StringSelection stringSelection = new StringSelection(selectedText);
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            clipboard.setContents(stringSelection, null);
        }
    }
    
    /**
     * Colle le texte qui est dans le presse-papiers système à l'emplacement du curseur
     */
    public void pasteText() {
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        try {
            String clipboardText = (String) clipboard.getData(DataFlavor.stringFlavor);
            this.replaceSelection(clipboardText);
        } catch (IOException | UnsupportedFlavorException e) {
            e.printStackTrace();
        }
    }
}
