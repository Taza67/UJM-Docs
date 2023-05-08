package outside.userInterface;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.SwingUtilities;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import inside.IConfig;
import inside.Manager;
import inside.utilities.ResearchUtilities;

/**
 * Classe représentant un panneau d'édition de texte personnalisé
 * @author mourtaza
 *
 */
public class JFramePerso extends JFrame implements IConfig {
	private static final long serialVersionUID = -4199820632154473949L;

	private JFramePerso self;
	private JEditorPanePerso textEditorPane;
	
	private Manager manager;
	private boolean isStatusBarVisible = true;
	private boolean isToolBarVisible = true;

	// Barre de menu
	private JMenuBar menuBar;
	// // Sous-menu Fichier
	private JMenu mnFichier;
	private JMenuItem mntmNouveauDocument;
	private JMenuItem mntmChargerDocument;
	private JMenuItem mntmSauvegarder;
	private JMenuItem mntmExporter;
	private JMenuItem mntmQuitter;
	// // Sous-menu Édition
	private JMenu mndition;
	private JMenuItem mntmAnnuler;
	private JMenuItem mntmRefaire;
	private JMenuItem mntmCouper;
	private JMenuItem mntmCopier;
	private JMenuItem mntmColler;
	// // Sous-menu Vue
	private JMenu mnVue;
	private JCheckBoxMenuItem chckbxmntmBarreDoutils;
	private JCheckBoxMenuItem chckbxmntmBarreDeStatut;
	// // Sous-menu Insertion
	private JMenu mnInsertion;
	// // Sous-menu Format
	private JMenu mnFormat;
	// // Sous-menu Fenêtre
	private JMenu mnFenetre;
	private JCheckBoxMenuItem chckbxmntmPleinEcran;
	// // Sous-menu Aide
	private JMenu mnAide;
	
	// Panneau de connexion
	JPanel connection;
	JPanel formContainerPanel;
	
	// Barre d'outils
	private JToolBar toolBar;
	private Button NouveauFichier;
	private Button Charger;
	private Button Sauvegarder;
	private Button Exporter;
	private Button nouvellePage;
	private Button supprimerPage;
	private Button Couper;
	private Button Copier;
	private Button Coller;
	private JTextField BarreRecherche;
	private JLabel Recherche;
	
	// PopupMenu
	protected JPopupMenu popupMenu;
	private JMenuItem ppMnCouper;
	private JMenuItem ppMnCopier;
	private JMenuItem ppMnColler;
	
	// Barre de statut
	protected JToolBar statusBar;
	private JLabel pageIndicator;
	private JLabel lineIndicator;
	private JLabel wordIndicator;
	private JLabel charIndicator;
	private Button pageSuivante;
	private Button pagePrecedente;
	private JLabel pageNumberIndicator;
	private JLabel cursorIndicator;
	private JSlider zoomSlider;
	protected JPanel editorContainerPanel;
	
	

	/**
	 * Instancie une fenêtre personnalisée
	 */
	public JFramePerso() {
		super();
		this.getContentPane().setFont(new Font("Gentium Book Basic", Font.PLAIN, 14));
		this.getContentPane().setLayout(new CardLayout(0, 0));
		this.setFont(new Font("Gentium Book Basic", Font.PLAIN, 14));
		this.setSize(new Dimension(1080, 720));
		this.setVisible(true);
		this.requestFocusInWindow();
		this.setForeground(new Color(102, 102, 102));
		this.setBackground(new Color(51, 51, 51));
		this.getContentPane().setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		this.setTitle("UJM Docs");
		this.setBounds(100, 100, 1080, 720);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		
		self = this;
		
		menuBar = new JMenuBar();
		menuBar.setAlignmentX(Component.LEFT_ALIGNMENT);
		this.setJMenuBar(menuBar);
		menuBar.setVisible(false);
		
		mnFichier = new JMenu("Fichier");
		mnFichier.setFont(new Font("Gentium Book Basic", Font.PLAIN, 14));
		menuBar.add(mnFichier);
		
		mntmNouveauDocument = new JMenuItem("Nouveau");
		mntmNouveauDocument.setFont(new Font("Gentium Book Basic", Font.PLAIN, 14));
		mnFichier.add(mntmNouveauDocument);
		
		mntmChargerDocument = new JMenuItem("Ouvrir");
		mntmChargerDocument.setFont(new Font("Gentium Book Basic", Font.PLAIN, 14));
		mnFichier.add(mntmChargerDocument);
		
		mntmSauvegarder = new JMenuItem("Sauvegarder");
		mntmSauvegarder.setFont(new Font("Gentium Book Basic", Font.PLAIN, 14));
		mnFichier.add(mntmSauvegarder);
		
		mntmExporter = new JMenuItem("Exporter");
		mntmExporter.setFont(new Font("Gentium Book Basic", Font.PLAIN, 14));
		mnFichier.add(mntmExporter);
		
		mntmQuitter = new JMenuItem("Quitter");
		mntmQuitter.setFont(new Font("Gentium Book Basic", Font.PLAIN, 14));
		mnFichier.add(mntmQuitter);
		
		mndition = new JMenu("Édition");
		mndition.setFont(new Font("Gentium Book Basic", Font.PLAIN, 14));
		menuBar.add(mndition);
		
		mntmAnnuler = new JMenuItem("Annuler");
		mntmAnnuler.setFont(new Font("Gentium Book Basic", Font.PLAIN, 14));
		mndition.add(mntmAnnuler);
		
		mntmRefaire = new JMenuItem("Refaire");
		mntmRefaire.setFont(new Font("Gentium Book Basic", Font.PLAIN, 14));
		mndition.add(mntmRefaire);
		
		mntmCouper = new JMenuItem("Couper");
		mntmCouper.setFont(new Font("Gentium Book Basic", Font.PLAIN, 14));
		mndition.add(mntmCouper);
		
		mntmCopier = new JMenuItem("Copier");
		mntmCopier.setFont(new Font("Gentium Book Basic", Font.PLAIN, 14));
		mndition.add(mntmCopier);
		
		mntmColler = new JMenuItem("Coller");
		mntmColler.setFont(new Font("Gentium Book Basic", Font.PLAIN, 14));
		mndition.add(mntmColler);
		
		mnVue = new JMenu("Vue");
		mnVue.setFont(new Font("Gentium Book Basic", Font.PLAIN, 14));
		menuBar.add(mnVue);
		
		chckbxmntmBarreDoutils = new JCheckBoxMenuItem("Barre d'outils");
		chckbxmntmBarreDoutils.setFont(new Font("Gentium Book Basic", Font.PLAIN, 14));
		mnVue.add(chckbxmntmBarreDoutils);
		
		chckbxmntmBarreDeStatut = new JCheckBoxMenuItem("Barre de statut");
		chckbxmntmBarreDeStatut.setFont(new Font("Gentium Book Basic", Font.PLAIN, 14));
		mnVue.add(chckbxmntmBarreDeStatut);
		
		mnInsertion = new JMenu("Insertion");
		mnInsertion.setFont(new Font("Gentium Book Basic", Font.PLAIN, 14));
		menuBar.add(mnInsertion);
		
		mnFormat = new JMenu("Format");
		mnFormat.setFont(new Font("Gentium Book Basic", Font.PLAIN, 14));
		menuBar.add(mnFormat);
		
		mnFenetre = new JMenu("Fenêtre");
		mnFenetre.setFont(new Font("Gentium Book Basic", Font.PLAIN, 14));
		menuBar.add(mnFenetre);
		
		chckbxmntmPleinEcran = new JCheckBoxMenuItem("Plein écran");
		chckbxmntmPleinEcran.setFont(new Font("Gentium Book Basic", Font.PLAIN, 14));
		mnFenetre.add(chckbxmntmPleinEcran);
		
		mnAide = new JMenu("Aide");
		mnAide.setFont(new Font("Gentium Book Basic", Font.PLAIN, 14));
		menuBar.add(mnAide);
		
		connection = new JPanel();
		this.getContentPane().add(connection, "name_22684711362580");
		connection.setLayout(new BorderLayout(0, 0));
		
		Component rigidAreaConnectionLeft = Box.createRigidArea(new Dimension(350, 107));
		connection.add(rigidAreaConnectionLeft, BorderLayout.WEST);
		
		Component rigidAreaConnectionUp = Box.createRigidArea(new Dimension(1080, 250));
		connection.add(rigidAreaConnectionUp, BorderLayout.NORTH);
		
		Component rigidAreaConnectionDown = Box.createRigidArea(new Dimension(1080, 250));
		connection.add(rigidAreaConnectionDown, BorderLayout.SOUTH);
		
		Component rigidAreaConnectionRight = Box.createRigidArea(new Dimension(350, 107));
		connection.add(rigidAreaConnectionRight, BorderLayout.EAST);
		
		formContainerPanel = new JPanel();
		connection.add(formContainerPanel, BorderLayout.CENTER);
		formContainerPanel.setLayout(new BoxLayout(formContainerPanel, BoxLayout.Y_AXIS));
		
		TextField pseudo = new TextField();
		pseudo.setFont(new Font("Gentium Book Basic", Font.BOLD, 14));
		pseudo.setName("Pseudo");
		pseudo.setMaximumSize(new Dimension(300, 30));
		pseudo.setMinimumSize(new Dimension(300, 30));
		pseudo.setPreferredSize(new Dimension(300, 30));
		formContainerPanel.add(pseudo);
		
		TextField motDePasse = new TextField();
		motDePasse.setFont(new Font("Gentium Book Basic", Font.BOLD, 14));
		motDePasse.setMinimumSize(new Dimension(300, 30));
		motDePasse.setMaximumSize(new Dimension(300, 30));
		motDePasse.setPreferredSize(new Dimension(300, 30));
		formContainerPanel.add(motDePasse);
		
		Button connecter = new Button("Se connecter");
		connecter.setPreferredSize(new Dimension(300, 30));
		connecter.setMinimumSize(new Dimension(300, 30));
		connecter.setMaximumSize(new Dimension(300, 30));
		formContainerPanel.add(connecter);
		
		Button inscrire = new Button("S'incrire");
		inscrire.setPreferredSize(new Dimension(300, 30));
		inscrire.setMinimumSize(new Dimension(300, 30));
		inscrire.setMaximumSize(new Dimension(300, 30));
		formContainerPanel.add(inscrire);
		
		Button horsLigne = new Button("Mode hors-ligne");
		horsLigne.setPreferredSize(new Dimension(300, 30));
		horsLigne.setMinimumSize(new Dimension(300, 30));
		horsLigne.setMaximumSize(new Dimension(300, 30));
		formContainerPanel.add(horsLigne);
		
		////////////////////////// EDITEUR-CARD ////////////////////////////////
		
		JPanel editeur = new JPanel();
		this.getContentPane().add(editeur, "name_22610450387445");
		editeur.setLayout(new BorderLayout(0, 0));
		
		// Barre d'outils //////////////////////////////////////////////////////
		
		toolBar = new JToolBar();
		editeur.add(toolBar, BorderLayout.NORTH);
		toolBar.setAlignmentX(Component.LEFT_ALIGNMENT);
		toolBar.setBorder(null);
		
		Charger = new Button("Ouvrir");
		Charger.setFont(new Font("Gentium Book Basic", Font.PLAIN, 14));
		toolBar.add(Charger);
		
		NouveauFichier = new Button("Nouveau");
		NouveauFichier.setFont(new Font("Gentium Book Basic", Font.PLAIN, 14));
		toolBar.add(NouveauFichier);
		
		Sauvegarder = new Button("Sauvegarder");
		Sauvegarder.setFont(new Font("Gentium Book Basic", Font.PLAIN, 14));
		toolBar.add(Sauvegarder);
		
		Exporter = new Button("Exporter");
		Exporter.setFont(new Font("Gentium Book Basic", Font.PLAIN, 14));
		toolBar.add(Exporter);
		
		JSeparator separator = new JSeparator();
		toolBar.add(separator);
		
		Component rigidArea10 = Box.createHorizontalStrut(5);
		toolBar.add(rigidArea10);
		
		Recherche = new JLabel("Page");
		Recherche.setFont(new Font("Gentium Book Basic", Font.PLAIN, 14));
		toolBar.add(Recherche);
		
		Component rigidArea9 = Box.createHorizontalStrut(5);
		toolBar.add(rigidArea9);
		
		supprimerPage = new Button("-");
		supprimerPage.setFont(new Font("Gentium Book Basic", Font.PLAIN, 14));
		toolBar.add(supprimerPage);
		
		nouvellePage = new Button("+");
		nouvellePage.setFont(new Font("Gentium Book Basic", Font.PLAIN, 14));
		toolBar.add(nouvellePage);
		
		JSeparator separator2 = new JSeparator();
		toolBar.add(separator2);
		
		Couper = new Button("Couper");
		Couper.setFont(new Font("Gentium Book Basic", Font.PLAIN, 14));
		toolBar.add(Couper);
		
		Copier = new Button("Copier");
		Copier.setFont(new Font("Gentium Book Basic", Font.PLAIN, 14));
		toolBar.add(Copier);
		
		Coller = new Button("Coller");
		Coller.setFont(new Font("Gentium Book Basic", Font.PLAIN, 14));
		toolBar.add(Coller);
		
		JSeparator separator1 = new JSeparator();
		toolBar.add(separator1);
		
		Component rigidArea6 = Box.createHorizontalStrut(5);
		toolBar.add(rigidArea6);
		
		Recherche = new JLabel("Recherche");
		Recherche.setFont(new Font("Gentium Book Basic", Font.PLAIN, 14));
		toolBar.add(Recherche);
		
		Component rigidArea5 = Box.createHorizontalStrut(5);
		toolBar.add(rigidArea5);
		
		BarreRecherche = new JTextField(0);
		BarreRecherche.setMaximumSize(new Dimension(200, 2147483647));
		BarreRecherche.setPreferredSize(new Dimension(200, 19));
		BarreRecherche.setFont(new Font("Gentium Book Basic", Font.PLAIN, 14));
		toolBar.add(BarreRecherche);
		
        // PopupMenu de l'éditeur //////////////////////////////////////////////
        popupMenu = new JPopupMenu();
        
        ppMnCouper = new JMenuItem("Couper");
        ppMnCopier = new JMenuItem("Copier");
        ppMnColler = new JMenuItem("Coller");
        
        popupMenu.add(ppMnCouper);
        popupMenu.add(ppMnCopier);
        popupMenu.add(ppMnColler);
		
		// Barre de statut /////////////////////////////////////////////////////
		
		statusBar = new JToolBar();
		statusBar.setBorder(null);
		statusBar.setAlignmentX(0.0f);
		editeur.add(statusBar, BorderLayout.SOUTH);
		
		Component rigidArea8 = Box.createRigidArea(new Dimension(20, 20));
		rigidArea8.setPreferredSize(new Dimension(20, 20));
		statusBar.add(rigidArea8);
		
		pageIndicator = new JLabel("1 Page");
		pageIndicator.setFont(new Font("Gentium Book Basic", Font.PLAIN, 14));
		statusBar.add(pageIndicator);
		
		Component rigidArea = Box.createRigidArea(new Dimension(50, 20));
		rigidArea.setPreferredSize(new Dimension(50, 20));
		statusBar.add(rigidArea);
		
		lineIndicator = new JLabel("1 Ligne");
		lineIndicator.setFont(new Font("Gentium Book Basic", Font.PLAIN, 14));
		statusBar.add(lineIndicator);
		
		Component rigidArea1 = Box.createRigidArea(new Dimension(50, 20));
		rigidArea1.setPreferredSize(new Dimension(50, 20));
		statusBar.add(rigidArea1);
		
		wordIndicator = new JLabel("0 Mot");
		wordIndicator.setFont(new Font("Gentium Book Basic", Font.PLAIN, 14));
		statusBar.add(wordIndicator);
		
		Component rigidArea2 = Box.createRigidArea(new Dimension(50, 20));
		rigidArea2.setPreferredSize(new Dimension(50, 20));
		statusBar.add(rigidArea2);
		
		charIndicator = new JLabel("0 Caractère");
		charIndicator.setFont(new Font("Gentium Book Basic", Font.PLAIN, 14));
		statusBar.add(charIndicator);
		
		Component glue = Box.createGlue();
		statusBar.add(glue);
		
		pageNumberIndicator = new JLabel("1 / 1p");
		pageNumberIndicator.setFont(new Font("Gentium Book Basic", Font.PLAIN, 14));
		statusBar.add(pageNumberIndicator);
		
		Component rigidArea11 = Box.createRigidArea(new Dimension(50, 20));
		rigidArea11.setPreferredSize(new Dimension(20, 20));
		statusBar.add(rigidArea11);
		
		cursorIndicator = new JLabel("0 / 0c      1 / 1l");
		cursorIndicator.setFont(new Font("Gentium Book Basic", Font.PLAIN, 14));
		statusBar.add(cursorIndicator);
		
		Component rigidArea7 = Box.createRigidArea(new Dimension(50, 20));
		rigidArea7.setPreferredSize(new Dimension(50, 20));
		statusBar.add(rigidArea7);
		
		pagePrecedente = new Button("<<");
		pagePrecedente.setFont(new Font("Gentium Book Basic", Font.PLAIN, 20));
		pagePrecedente.setSize(new Dimension(40, 20));
		pagePrecedente.setMinimumSize(new Dimension(40, 20));
		pagePrecedente.setMaximumSize(new Dimension(40, 20));
		pagePrecedente.setPreferredSize(new Dimension(40, 20));
		statusBar.add(pagePrecedente);
		
		pageSuivante = new Button(">>");
		pageSuivante.setFont(new Font("Gentium Book Basic", Font.PLAIN, 20));
		pageSuivante.setSize(new Dimension(40, 20));
		pageSuivante.setMinimumSize(new Dimension(40, 20));
		pageSuivante.setMaximumSize(new Dimension(40, 20));
		pageSuivante.setPreferredSize(new Dimension(40, 20));
		statusBar.add(pageSuivante);
		
		Component rigidArea3 = Box.createRigidArea(new Dimension(50, 20));
		rigidArea3.setPreferredSize(new Dimension(50, 20));
		statusBar.add(rigidArea3);
		
		zoomSlider = new JSlider();
		zoomSlider.setMinimum(50);
		zoomSlider.setValue(100);
		zoomSlider.setSnapToTicks(true);
		zoomSlider.setName("Zoom");
		zoomSlider.setToolTipText("Zoom");
		zoomSlider.setPreferredSize(new Dimension(5, 20));
		zoomSlider.setMinimumSize(new Dimension(8500, 20));
		zoomSlider.setMaximum(200);
		zoomSlider.setMaximumSize(new Dimension(8500, 20));
		statusBar.add(zoomSlider);
		
		Component rigidArea4 = Box.createRigidArea(new Dimension(20, 20));
		rigidArea4.setName("Zoom");
		statusBar.add(rigidArea4);
        
		// Conteneur de l'éditeur //////////////////////////////////////////////
		
		editorContainerPanel = new JPanel();
		editorContainerPanel.setMinimumSize(DEFAUTL_EDITOR_PANE_MIN_DIMENSIONS);
		editorContainerPanel.setPreferredSize(DEFAUTL_EDITOR_PANE_MIN_DIMENSIONS);
		editorContainerPanel.setBorder(BorderFactory.createEmptyBorder(
			DEFAULT_EDITOR_MARGE, DEFAULT_EDITOR_MARGE, DEFAULT_EDITOR_MARGE, DEFAULT_EDITOR_MARGE
			)
		);
		
        // Éditeur /////////////////////////////////////////////////////////////
        
		JScrollPane scrollPane = new JScrollPane(editorContainerPanel);
		editorContainerPanel.setLayout(new BoxLayout(editorContainerPanel, BoxLayout.Y_AXIS));
		
		textEditorPane = new JEditorPanePerso(lineIndicator, wordIndicator, charIndicator, cursorIndicator);
		editorContainerPanel.add(textEditorPane);
		
		scrollPane.setAutoscrolls(true);
		editeur.add(scrollPane);
		
		////////////////////////////////////////////////////////////////////////
		/////////////////////////// GESTION EVENEMENTS /////////////////////////
		
		// Panneau de connexion ////////////////////////////////////////////////
		
		// Gestion des clics sur le bouton connecter
		connecter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Vérification des arguments
				if (pseudo.getText() == null || pseudo.getText().isEmpty() || pseudo.getText().isBlank() || 
					motDePasse.getText() == null || motDePasse.getText().isEmpty() || motDePasse.getText().isBlank()) {
					System.err.println("- Appui sur le bouton connexion + Un, au moins, des champs vide");
			        JOptionPane.showMessageDialog(null, "Un, au moins, des champs vide !!!", "Erreur", JOptionPane.ERROR_MESSAGE);
			        
			        return;
				}
				
				System.err.println("- Appui sur le bouton se connexion");
				((CardLayout)self.getContentPane().getLayout()).next(self.getContentPane());
				
				// Ajout de la barre de menu
				menuBar.setVisible(true);
				
				// Ajout de la gestion d'événements à l'éditeur
				// Les modifications seront envoyées au serveur (=>true)
				textEditorPane.initEventManagement(true);
				
				// Ajout de la gestion d'événéments
				self.initEventManagement(true);
				
				manager = new Manager(textEditorPane, pageIndicator, pageNumberIndicator);
				textEditorPane.setManager(manager);
				
				// Connection
				if (!manager.connectApplication(pseudo.getText(), motDePasse.getText()))
					return;
				
				// Demande du nom du fichier
				String nomFichier = JOptionPane.showInputDialog(self, "Entrez le nom du fichier");
				
				// // Annulation
				if (nomFichier == null) {
					JOptionPane.showMessageDialog(self, "Vous avez décidé d'annuler. Fermeture de l'application","Fermeture", JOptionPane.WARNING_MESSAGE);  
					manager.disconnectApplication();
					self.dispose();
				}
				
				// // Nom incorrect
				while (nomFichier.isBlank() || nomFichier.isEmpty())
					nomFichier = JOptionPane.showInputDialog(self, "Attention ! Vous n'avez entré un nom valide ! Entrez le nom du fichier");
				
				// Création du nouveau document
				manager.askNewDocument(nomFichier, true);
			}
		});
		
		// Gestion des clics sur le bouton mode hors-ligne
		horsLigne.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.err.println("- Appui sur le bouton mode hors-ligne");
				((CardLayout)self.getContentPane().getLayout()).next(self.getContentPane());
				
				// Ajout de la barre de menu
				menuBar.setVisible(true);
				
				// Ajout de la gestion d'événements à l'éditeur
				// Les modifications ne seront toutefois pas envoyées
				// au serveur de données (=>false)
				textEditorPane.initEventManagement(false);
				
				// Ajout de la gestion d'événéments
				self.initEventManagement(false);
				
				// Initialisation du manager
				manager = new Manager(textEditorPane, pageIndicator, pageNumberIndicator);
				textEditorPane.setManager(manager);
				
				// Création du premier document automatique en mode hors-ligne
				manager.askNewDocument("default", false);
			}
		});
	}
	
	
	/**
	 * Ajoute les gestionnaires d'événements
	 */
	public void initEventManagement(boolean isOnline) {	
		// Barre de menu
		// // Fichier
		// // // Nouveau document
		mntmNouveauDocument.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (isOnline) {
					String newDocument = "default";
					manager.askNewDocument(newDocument, isOnline);
				} else {
					manager.askNewDocument("default", isOnline);
				}	
			}
		});
		// // // Charger
		mntmChargerDocument.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.err.println("- Clic sur le bouton charger de la barre de menu");
				manager.askLoadDocument("default");
			}
		});
		// // // Sauvegarder
		mntmSauvegarder.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.err.println("- Clic sur le bouton Sauvegarder de la barre de menu");
				manager.askSaveDocument();	
			}
		});
		if (!isOnline) {
			mntmChargerDocument.setVisible(false);
			mntmSauvegarder.setVisible(false);
		}
		// // // Exporter
		mntmExporter.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				manager.exportDocument();
			}
			
		});
		// // // Quitter
		mntmQuitter.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				self.dispose();
			}
		});
		
		// // Édition
		// // // Annuler
		mntmAnnuler.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Annulation de la dernière modification
				if (textEditorPane.getUndoManager().canUndo()) {
					textEditorPane.getUndoManager().undo();
                }
			}
		});
		
		// // // Refaire
		mntmRefaire.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Annulation de la dernière annulation
				if (textEditorPane.getUndoManager().canRedo()) {
					textEditorPane.getUndoManager().redo();
                }
			}
		});
		
		// // // Couper
		mntmCouper.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Coupure du texte sélectionné
				textEditorPane.cut();
			}
		});
		
		// // // Copier
		mntmCopier.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Copie du texte sélectionné
				textEditorPane.copy();
			}
		});
		
		// // // Coller
		mntmColler.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Collage à la position du curseur
				textEditorPane.paste();
			}
		});
		
		// // Vue
		// // // Barre d'outils
		chckbxmntmBarreDoutils.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				isToolBarVisible = !isToolBarVisible;
				toolBar.setVisible(isToolBarVisible);
			}
		});
		
		// // // Barre de statut
		chckbxmntmBarreDeStatut.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				isStatusBarVisible = !isStatusBarVisible;
				statusBar.setVisible(isStatusBarVisible);
			}
		});
		
		// // Fenêtre
		// // // Plein écran
		chckbxmntmPleinEcran.addActionListener(new ActionListener() {
			// Récupération du GraphicsDevice par défaut (écran principal)
		    GraphicsEnvironment graphicsEnvironment = GraphicsEnvironment.getLocalGraphicsEnvironment();
		    GraphicsDevice graphicsDevice = graphicsEnvironment.getDefaultScreenDevice();
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (chckbxmntmPleinEcran.getState()) {
					self.setVisible(false);
					
				    // On retire la barre de titre et les bordures de la fenêtre
				    // self.setUndecorated(true);
	
				    // Activation du mode plein écran
				    graphicsDevice.setFullScreenWindow(self);
				    
				    self.setVisible(true);
				} else {
					self.setVisible(false);
					
				    // On quitte le mode plein écran
					graphicsDevice.setFullScreenWindow(null);
					
					// On remet la barre de titre et les bordures de la fenêtre
				    // self.setUndecorated(false);
					
					self.setVisible(true);
				}
			}
		});
		
		// Barre de statut
		// // Page précédente
		pagePrecedente.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				manager.moveBackward();
			}
			
		});
		
		// // Page suivante
		pageSuivante.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				manager.moveForward();
			}

		});
		
		// // Zoom
		zoomSlider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				int editorMarge = DEFAULT_EDITOR_MARGE * (200 - zoomSlider.getValue()) / 100;
				
				// Marges
				editorContainerPanel.setBorder(BorderFactory.createEmptyBorder(editorMarge, editorMarge, editorMarge, editorMarge));
				editorContainerPanel.revalidate();
				
				// Document
				double scaleFactor = zoomSlider.getValue() / 100.;
				textEditorPane.scale(scaleFactor);
			}
		});
		
		// Barre d'outils
		// // Nouveau fichier
		NouveauFichier.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					if (isOnline) {
						String newDocument = "default";
						manager.askNewDocument(newDocument, isOnline);
					} else {
						manager.askNewDocument("default", isOnline);
					}
				}
		});
		// // Charger
		Charger.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.err.println("- Clic sur le bouton charger de la barre de menu");
				manager.askLoadDocument("default");
			}
		});
		// // Sauvegarder
		Sauvegarder.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.err.println("- Clic sur le bouton Sauvegarder de la barre de menu");
				manager.askSaveDocument();		
			}
		});
		if (!isOnline) {
			Charger.setVisible(false);
			Sauvegarder.setVisible(false);
		}
		// // Exporter
		Exporter.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				manager.exportDocument();
			}
			
		});
		// // Supprimer page
		supprimerPage.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.err.println("- Suppression de la page");
			}
		});
		
		// // Nouvelle page
		nouvellePage.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				manager.addNewPage();
			}
		});
		
		// // Couper
		Couper.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// Coupure du texte sélectionné
				textEditorPane.cut();
			}
		});
		
		// // Copier
		Copier.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Copie du texte sélectionné
				textEditorPane.copy();
			}
		});
		
		// // Coller
		Coller.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// Collage à la position du curseur
				textEditorPane.paste();
			}
		});
		
		// // Recherche
		BarreRecherche.getDocument().addDocumentListener(new DocumentListener() {
			
			@Override
			public void removeUpdate(DocumentEvent arg0) {
				ResearchUtilities.searchAndHighlight(textEditorPane, BarreRecherche.getText());
			}
			
			@Override
			public void insertUpdate(DocumentEvent arg0) {
				ResearchUtilities.searchAndHighlight(textEditorPane, BarreRecherche.getText());
			}
			
			@Override
			public void changedUpdate(DocumentEvent arg0) {

			}
		});
		
		// Souris
		// // Clic sur le panneau d'édition
		textEditorPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
                if (SwingUtilities.isRightMouseButton(e)) {
                    popupMenu.show(textEditorPane, e.getX(), e.getY());
                }
			}
		});
		
		// PopupMenu
		// // Couper
		ppMnCouper.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// Coupure du texte sélectionné
				textEditorPane.cut();
			}
		});
		
		// // Copier
		ppMnCopier.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Copie du texte sélectionné
				textEditorPane.copy();
			}
		});
		
		// // Coller
		ppMnColler.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// Collage à la position du curseur
				textEditorPane.paste();
			}
		});
		
		// Touches
       KeyListener keyboardHandler = (KeyListener)new KeyAdapter() {
    	   // Récupération du GraphicsDevice par défaut (écran principal)
		    GraphicsEnvironment graphicsEnvironment = GraphicsEnvironment.getLocalGraphicsEnvironment();
		    GraphicsDevice graphicsDevice = graphicsEnvironment.getDefaultScreenDevice();
    	   
            @Override
            public void keyPressed(KeyEvent e) {
            	// Nouveau document
                if ((e.getKeyCode() == KeyEvent.VK_N) && ((e.getModifiersEx() & KeyEvent.ALT_DOWN_MASK) != 0)) {
    				if (isOnline) {
    					String newDocument = "default";
    					manager.askNewDocument(newDocument, isOnline);
    				} else {
    					manager.askNewDocument("default", isOnline);
    				}
                }
                // Ouverture
                else if ((e.getKeyCode() == KeyEvent.VK_O) && ((e.getModifiersEx() & KeyEvent.ALT_DOWN_MASK) != 0)) {
                	if (isOnline) {
                		String document = "default";
                		manager.askLoadDocument(document);
                	}	
                }
                // Sauvegarde
                else if ((e.getKeyCode() == KeyEvent.VK_S) && ((e.getModifiersEx() & KeyEvent.ALT_DOWN_MASK) != 0)) {
                	if (isOnline) {
                		manager.askSaveDocument();
                	}
                }
                // Export
                else if ((e.getKeyCode() == KeyEvent.VK_E) && ((e.getModifiersEx() & KeyEvent.ALT_DOWN_MASK) != 0)) {
                	manager.exportDocument();
                }
                // Quitter
                else if ((e.getKeyCode() == KeyEvent.VK_Q) && ((e.getModifiersEx() & KeyEvent.ALT_DOWN_MASK) != 0)) {
                	self.dispose();
                }
                // Barre d'outils
                else if ((e.getKeyCode() == KeyEvent.VK_O) && ((e.getModifiersEx() & KeyEvent.CTRL_DOWN_MASK) != 0)) {
    				toolBar.setVisible(!isToolBarVisible);
    				chckbxmntmBarreDoutils.setSelected(isToolBarVisible);
                }
                // Barre de statut
                else if ((e.getKeyCode() == KeyEvent.VK_S) && ((e.getModifiersEx() & KeyEvent.CTRL_DOWN_MASK) != 0)) {
    				statusBar.setVisible(!isStatusBarVisible);
    				chckbxmntmBarreDeStatut.setSelected(isStatusBarVisible);
                }
                // Plein écran
                else if ((e.getKeyCode() == KeyEvent.VK_F11)) {
    				if (chckbxmntmPleinEcran.getState()) {
    					self.setVisible(false);
    	
    				    // Activation du mode plein écran
    				    graphicsDevice.setFullScreenWindow(self);
    				    
    				    self.setVisible(true);
    				    
    				    chckbxmntmPleinEcran.setSelected(true);
    				} else {
    					self.setVisible(false);
    					
    				    // On quitte le mode plein écran
    					graphicsDevice.setFullScreenWindow(null);
    					
    					self.setVisible(true);
    					
    					chckbxmntmPleinEcran.setSelected(false);
    				}
                }
                // Recherche
                else if ((e.getKeyCode() == KeyEvent.VK_F) && ((e.getModifiersEx() & KeyEvent.CTRL_DOWN_MASK) != 0)) {
                	int startIndex = textEditorPane.getSelectionStart();
                    int endIndex = textEditorPane.getSelectionEnd();
                    String selectedText = textEditorPane.getText().substring(startIndex, endIndex);
                	
                    if (selectedText != "") {
                    	BarreRecherche.setText(selectedText);
                    	ResearchUtilities.searchAndHighlight(textEditorPane, selectedText);
                    }
                    
                	BarreRecherche.requestFocusInWindow();
                }
                // Page suivante
                else if ((e.getKeyCode() == KeyEvent.VK_RIGHT) && ((e.getModifiersEx() & KeyEvent.ALT_DOWN_MASK) != 0)) {
                	manager.moveForward();
                }
                // Page précédente
                else if ((e.getKeyCode() == KeyEvent.VK_LEFT) && ((e.getModifiersEx() & KeyEvent.ALT_DOWN_MASK) != 0)) {
                	manager.moveBackward();
                }
                // Nouvelle page
                else if ((e.getKeyCode() == KeyEvent.VK_N) && ((e.getModifiersEx() & KeyEvent.CTRL_DOWN_MASK) != 0)) {
                	manager.addNewPage();
                }
                // Supprimer page
                else if ((e.getKeyCode() == KeyEvent.VK_N) && ((e.getModifiersEx() & KeyEvent.CTRL_DOWN_MASK) != 0)) {
                	System.err.println("- Suppression de la page");
                }
            }
        };
        // // Editeur
        textEditorPane.addKeyListener(keyboardHandler);
        // // Barre de recherche
        BarreRecherche.addKeyListener(keyboardHandler);
        // // Fenêtre
        self.getContentPane().addKeyListener(keyboardHandler);
	}
}
