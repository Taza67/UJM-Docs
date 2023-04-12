package outside;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JScrollPane;

/**
 * Classe représentant une fenêtre de style éditeur de texte
 * @author mourtaza
 */
public class Window extends JFrame {
	private static final long serialVersionUID = 5686413746254777823L;
	/**
	 * Variable contenant le panneau d'édition de texte du programme
	 */
	private TextPanel textPanel;
	
	
	/**
	 * Construit une instance de la fenêtre de l'éditeur
	 */
	public Window() {
        // Titre de la fenêtre
        setTitle("UDoc");

        // Taille de la fenêtre
        setSize(1000, 900);

        // Centrage de la fenêtre au centre de l'écran
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        int w = getSize().width;
        int h = getSize().height;
        int x = (dim.width - w) / 2;
        int y = (dim.height - h) / 2;
        setLocation(x, y);
        
        // Ajout du panneau d'édition
        textPanel = new TextPanel("<html><head></head><body>Je m'appelle Mourtaza</body></html>");
        JScrollPane scrollPane = new JScrollPane(textPanel);
        add(scrollPane);
        
        // Terminer le processus Java lorsque la fenêtre est fermée
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
    /**
     * Point d'entrée du programme.
     * Crée une instance de la fenêtre de l'éditeur et l'affiche.
     */
    public static void main(String[] args) {
        // Création d'une instance de MaJFrame
        Window window = new Window();

        // Affichage de la fenêtre
        window.setVisible(true);
    }
	
}
