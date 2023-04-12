package outside;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 * Classe représentant la barre de menu personnalisée
 * @author mourtaza
 *
 */
public class MenuBar extends JMenuBar {
	/**
	 * Construit une instance de la barre de menu personnalisée
	 */
	public MenuBar() {
		JMenu fileMenu, editionMenu, displayMenu, sizeSubMenu, showSubMenu, helpMenu;
		JMenuItem open, save, load, export, exit,
				  undo, redo,
				  tutorial, shortcuts, search, about;
		
		fileMenu = new JMenu("Fichier");
		open = new JMenuItem("Ouvrir");
		save = new JMenuItem("Sauvegarder");
		load = new JMenuItem("Charger");
		export = new JMenuItem("Exporter");
		exit = new JMenuItem("Quitter");
		
		editionMenu = new JMenu("Édition");
		undo = new JMenuItem("Défaire");
		redo = new JMenuItem("Refaire");
		
		displayMenu = new JMenu("Affichage");
		sizeSubMenu = new JMenu("Taille");
		showSubMenu = new JMenu("Afficher");
		
		helpMenu = new JMenu("Aide");
		tutorial = new JMenuItem("Tutoriel");
		shortcuts = new JMenuItem("Raccourcis");
		search = new JMenuItem("Recherche");
		about = new JMenuItem("À propos");
		
		fileMenu.add(open);
		fileMenu.add(save);
		fileMenu.add(load);
		fileMenu.add(export);
		fileMenu.add(exit);
		
		editionMenu.add(undo);
		editionMenu.add(redo);
		
		displayMenu.add(sizeSubMenu);
		displayMenu.add(showSubMenu);
		
		helpMenu.add(tutorial);
		helpMenu.add(shortcuts);
		helpMenu.add(search);
		helpMenu.add(about);
	}
}
