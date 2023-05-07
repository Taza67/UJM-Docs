package outside.userInterface;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import javax.swing.JToolBar;
import javax.swing.SwingUtilities;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import inside.Manager;

/**
 * Classe représentant un panneau d'édition de texte personnalisé
 * @author mourtaza
 *
 */
public class JFramePerso extends JFrame {
	private static final long serialVersionUID = -4199820632154473949L;
	
	public static final int DEFAULT_EDITOR_MARGE = 200;
	public static final int DEFAULT_PAGE_MARGE = 60;

	private JFrame self;
	private JEditorPanePerso textEditorPane;
	private Manager manager;
	private boolean isStatusBarVisible = false;
	private boolean isToolBarVisible = true;

	/**
	 * Instancie une fenêtre personnalisée
	 */
	public JFramePerso() {
		super();
		this.getContentPane().setFont(new Font("Gentium Book Basic", Font.PLAIN, 15));
		this.getContentPane().setLayout(new CardLayout(0, 0));
		this.setFont(new Font("Gentium Book Basic", Font.PLAIN, 12));
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
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setAlignmentX(Component.LEFT_ALIGNMENT);
		this.setJMenuBar(menuBar);
		menuBar.setVisible(false);
		
		JMenu mnFichier = new JMenu("Fichier");
		mnFichier.setFont(new Font("Gentium Book Basic", Font.PLAIN, 15));
		menuBar.add(mnFichier);
		
		JMenuItem mntmNouveauDocument = new JMenuItem("Nouveau");
		mntmNouveauDocument.setFont(new Font("Gentium Book Basic", Font.PLAIN, 15));
		mnFichier.add(mntmNouveauDocument);
		
		JMenuItem mntmChargerDocument = new JMenuItem("Charger");
		mntmChargerDocument.setFont(new Font("Gentium Book Basic", Font.PLAIN, 15));
		mnFichier.add(mntmChargerDocument);
		
		JMenuItem mntmSauvegarder = new JMenuItem("Sauvegarder");
		mntmSauvegarder.setFont(new Font("Gentium Book Basic", Font.PLAIN, 15));
		mnFichier.add(mntmSauvegarder);
		
		JMenuItem mntmExporter = new JMenuItem("Exporter");
		mntmExporter.setFont(new Font("Gentium Book Basic", Font.PLAIN, 15));
		mnFichier.add(mntmExporter);
		
		JMenuItem mntmQuitter = new JMenuItem("Quitter");
		mntmQuitter.setFont(new Font("Gentium Book Basic", Font.PLAIN, 15));
		mnFichier.add(mntmQuitter);
		
		JMenu mndition = new JMenu("Édition");
		mndition.setFont(new Font("Gentium Book Basic", Font.PLAIN, 15));
		menuBar.add(mndition);
		
		JMenuItem mntmAnnuler = new JMenuItem("Annuler");
		mntmAnnuler.setFont(new Font("Gentium Book Basic", Font.PLAIN, 15));
		mndition.add(mntmAnnuler);
		
		JMenuItem mntmRefaire = new JMenuItem("Refaire");
		mntmRefaire.setFont(new Font("Gentium Book Basic", Font.PLAIN, 15));
		mndition.add(mntmRefaire);
		
		JMenuItem mntmCouper = new JMenuItem("Couper");
		mntmCouper.setFont(new Font("Gentium Book Basic", Font.PLAIN, 15));
		mndition.add(mntmCouper);
		
		JMenuItem mntmCopier = new JMenuItem("Copier");
		mntmCopier.setFont(new Font("Gentium Book Basic", Font.PLAIN, 15));
		mndition.add(mntmCopier);
		
		JMenuItem mntmColler = new JMenuItem("Coller");
		mntmColler.setFont(new Font("Gentium Book Basic", Font.PLAIN, 15));
		mndition.add(mntmColler);
		
		JMenu mnVue = new JMenu("Vue");
		mnVue.setFont(new Font("Gentium Book Basic", Font.PLAIN, 15));
		menuBar.add(mnVue);
		
		JCheckBoxMenuItem chckbxmntmBarreDoutils = new JCheckBoxMenuItem("Barre d'outils");
		chckbxmntmBarreDoutils.setFont(new Font("Gentium Book Basic", Font.PLAIN, 15));
		mnVue.add(chckbxmntmBarreDoutils);
		
		JCheckBoxMenuItem chckbxmntmBarreDeStatut = new JCheckBoxMenuItem("Barre de statut");
		chckbxmntmBarreDeStatut.setFont(new Font("Gentium Book Basic", Font.PLAIN, 15));
		mnVue.add(chckbxmntmBarreDeStatut);
		
		JMenu mnInsertion = new JMenu("Insertion");
		mnInsertion.setFont(new Font("Gentium Book Basic", Font.PLAIN, 15));
		menuBar.add(mnInsertion);
		
		JMenu mnFormat = new JMenu("Format");
		mnFormat.setFont(new Font("Gentium Book Basic", Font.PLAIN, 15));
		menuBar.add(mnFormat);
		
		JMenu mnFenetre = new JMenu("Fenetre");
		mnFenetre.setFont(new Font("Gentium Book Basic", Font.PLAIN, 15));
		menuBar.add(mnFenetre);
		
		JMenu mnAide = new JMenu("Aide");
		mnAide.setFont(new Font("Gentium Book Basic", Font.PLAIN, 15));
		menuBar.add(mnAide);
		
		JPanel connection = new JPanel();
		this.getContentPane().add(connection, "name_22684711362580");
		connection.setLayout(new BorderLayout(0, 0));
		
		Component rigidAreaConnectionLeft = Box.createRigidArea(new Dimension(350, 107));
		connection.add(rigidAreaConnectionLeft, BorderLayout.WEST);
		
		Component rigidAreaConnectionUp = Box.createRigidArea(new Dimension(1080, 275));
		connection.add(rigidAreaConnectionUp, BorderLayout.NORTH);
		
		Component rigidAreaConnectionDown = Box.createRigidArea(new Dimension(1080, 275));
		connection.add(rigidAreaConnectionDown, BorderLayout.SOUTH);
		
		Component rigidAreaConnectionRight = Box.createRigidArea(new Dimension(350, 107));
		connection.add(rigidAreaConnectionRight, BorderLayout.EAST);
		
		JPanel connectionContainerPanel = new JPanel();
		connection.add(connectionContainerPanel, BorderLayout.CENTER);
		connectionContainerPanel.setLayout(new BoxLayout(connectionContainerPanel, BoxLayout.Y_AXIS));
		
		TextField pseudo = new TextField();
		pseudo.setFont(new Font("Gentium Book Basic", Font.BOLD, 15));
		pseudo.setName("Pseudo");
		pseudo.setMaximumSize(new Dimension(300, 30));
		pseudo.setMinimumSize(new Dimension(300, 30));
		pseudo.setPreferredSize(new Dimension(300, 30));
		connectionContainerPanel.add(pseudo);
		
		TextField motDePasse = new TextField();
		motDePasse.setFont(new Font("Gentium Book Basic", Font.BOLD, 15));
		motDePasse.setMinimumSize(new Dimension(300, 30));
		motDePasse.setMaximumSize(new Dimension(300, 30));
		motDePasse.setPreferredSize(new Dimension(300, 30));
		connectionContainerPanel.add(motDePasse);
		
		Button Connecter = new Button("Connecter");
		Connecter.setPreferredSize(new Dimension(300, 30));
		Connecter.setMinimumSize(new Dimension(300, 30));
		Connecter.setMaximumSize(new Dimension(300, 30));
		connectionContainerPanel.add(Connecter);
		
		////////////////////////// EDITEUR-CARD ////////////////////////////////
		
		JPanel editeur = new JPanel();
		this.getContentPane().add(editeur, "name_22610450387445");
		editeur.setLayout(new BorderLayout(0, 0));
		
		// Barre d'outils //////////////////////////////////////////////////////
		
		JToolBar toolBar = new JToolBar();
		editeur.add(toolBar, BorderLayout.NORTH);
		toolBar.setAlignmentX(Component.LEFT_ALIGNMENT);
		toolBar.setBorder(null);
		
		Button Ouvrir = new Button("Ouvrir");
		Ouvrir.setFont(new Font("Gentium Book Basic", Font.PLAIN, 12));
		toolBar.add(Ouvrir);
		
		Button NouveauFichier = new Button("Nouveau fichier");
		NouveauFichier.setFont(new Font("Gentium Book Basic", Font.PLAIN, 12));
		toolBar.add(NouveauFichier);
		
		Button Sauvegarder = new Button("Sauvegarder");
		Sauvegarder.setFont(new Font("Gentium Book Basic", Font.PLAIN, 12));
		toolBar.add(Sauvegarder);
		
		Button Exporter = new Button("Exporter");
		Exporter.setFont(new Font("Gentium Book Basic", Font.PLAIN, 12));
		toolBar.add(Exporter);
		
		JSeparator separator = new JSeparator();
		toolBar.add(separator);
		
		Button Couper = new Button("Couper");
		Couper.setFont(new Font("Gentium Book Basic", Font.PLAIN, 12));
		toolBar.add(Couper);
		
		Button Copier = new Button("Copier");
		Copier.setFont(new Font("Gentium Book Basic", Font.PLAIN, 12));
		toolBar.add(Copier);
		
		Button Coller = new Button("Coller");
		Coller.setFont(new Font("Gentium Book Basic", Font.PLAIN, 12));
		toolBar.add(Coller);
		
		// Conteneur de l'éditeur //////////////////////////////////////////////
		
		JPanel editorContainerPanel = new JPanel();
		editorContainerPanel.setSize(new Dimension(90, 1000));
		editorContainerPanel.setMinimumSize(new Dimension(90, 1000));
		editorContainerPanel.setPreferredSize(new Dimension(90, 1000));
		editorContainerPanel.setMaximumSize(new Dimension(90, 1000));
		editorContainerPanel.setBorder(BorderFactory.createEmptyBorder(DEFAULT_EDITOR_MARGE, DEFAULT_EDITOR_MARGE, DEFAULT_EDITOR_MARGE, DEFAULT_EDITOR_MARGE));
		
        // PopupMenu de l'éditeur //////////////////////////////////////////////
        JPopupMenu popupMenu = new JPopupMenu();
        
        JMenuItem ppMnCouper = new JMenuItem("Couper");
        JMenuItem ppMnCopier = new JMenuItem("Copier");
        JMenuItem ppMnColler = new JMenuItem("Coller");
        
        popupMenu.add(ppMnCouper);
        popupMenu.add(ppMnCopier);
        popupMenu.add(ppMnColler);
		
		// Barre de statut /////////////////////////////////////////////////////
		
		JToolBar statusBar = new JToolBar();
		statusBar.setBorder(null);
		statusBar.setAlignmentX(0.0f);
		editeur.add(statusBar, BorderLayout.SOUTH);
		
		Component rigidArea = Box.createRigidArea(new Dimension(20, 20));
		statusBar.add(rigidArea);
		
		JLabel lineIndicator = new JLabel("1 / 1 Lignes");
		lineIndicator.setFont(new Font("Gentium Book Basic", Font.PLAIN, 15));
		statusBar.add(lineIndicator);
		
		Component rigidArea1 = Box.createRigidArea(new Dimension(50, 20));
		rigidArea1.setPreferredSize(new Dimension(50, 20));
		statusBar.add(rigidArea1);
		
		JLabel wordIndicator = new JLabel("0 / 0 Mots");
		wordIndicator.setFont(new Font("Gentium Book Basic", Font.PLAIN, 15));
		statusBar.add(wordIndicator);
		
		Component rigidArea2 = Box.createRigidArea(new Dimension(50, 20));
		rigidArea1.setPreferredSize(new Dimension(50, 20));
		statusBar.add(rigidArea2);
		
		JLabel charIndicator = new JLabel("0 / 0 Caractères");
		charIndicator.setFont(new Font("Gentium Book Basic", Font.PLAIN, 15));
		statusBar.add(charIndicator);
		
		Component glue = Box.createGlue();
		statusBar.add(glue);
		
		JSlider zoomSlider = new JSlider();
		zoomSlider.setMinimum(50);
		zoomSlider.setValue(100);
		zoomSlider.setSnapToTicks(true);
		zoomSlider.setName("Zoom");
		zoomSlider.setToolTipText("Zoom");
		zoomSlider.setPreferredSize(new Dimension(5, 20));
		zoomSlider.setMinimumSize(new Dimension(10000, 20));
		zoomSlider.setMaximum(200);
		zoomSlider.setMaximumSize(new Dimension(10000, 20));
		statusBar.add(zoomSlider);
		
		Component rigidArea3 = Box.createRigidArea(new Dimension(20, 20));
		rigidArea3.setName("Zoom");
		statusBar.add(rigidArea3);
        
        // Éditeur /////////////////////////////////////////////////////////////
        
		JScrollPane scrollPane = new JScrollPane(editorContainerPanel);
		editorContainerPanel.setLayout(new BoxLayout(editorContainerPanel, BoxLayout.X_AXIS));
		
		textEditorPane = new JEditorPanePerso(lineIndicator, wordIndicator, charIndicator);
		editorContainerPanel.add(textEditorPane);
		scrollPane.setAutoscrolls(true);
		editeur.add(scrollPane);
		
		////////////////////////////////////////////////////////////////////////
		/////////////////////////// GESTION EVENEMENTS /////////////////////////
		
		// Panneau de connexion ////////////////////////////////////////////////
		
		// Gestion des clics sur le bouton connecter
		Connecter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Vérification des arguments
				if (pseudo.getText() == null || pseudo.getText().isEmpty() || pseudo.getText().isBlank() || 
					motDePasse.getText() == null || motDePasse.getText().isEmpty() || motDePasse.getText().isBlank()) {
					System.err.println("- Appui sur le bouton connexion + Un, au moins, des champs vide");
			        JOptionPane.showMessageDialog(null, "Un, au moins, des champs vide !!!", "Erreur", JOptionPane.ERROR_MESSAGE);
			        
			        return;
				}
				
				System.err.println("- Appui sur le bouton connexion");
				((CardLayout)self.getContentPane().getLayout()).next(self.getContentPane());
				
				// Ajout de la barre d'outils
				isStatusBarVisible = true;
				menuBar.setVisible(isStatusBarVisible);
			}
		});
		
		// Barre de menu
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
				textEditorPane.pasteText();
			}
		});
		
		// // Vue
		// // // Barre d'outils
		chckbxmntmBarreDoutils.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				isToolBarVisible = !isToolBarVisible;
				// Coupure du texte sélectionné
				textEditorPane.cut();toolBar.setVisible(isToolBarVisible);
			}
		});
		
		// // // Barre de statut
		chckbxmntmBarreDeStatut.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				isStatusBarVisible = !isStatusBarVisible;
				statusBar.setVisible(isStatusBarVisible);
			}
		});
		
		// Barre de statut
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
				textEditorPane.pasteText();
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
				textEditorPane.pasteText();
			}
		});
	}
	
	
	/**
	 * Retourne la référence au panneau de l'éditeur
	 * @return Référence au panneau de l'éditeur
	 */
	public JEditorPanePerso getTextEditorPane() {
		return textEditorPane;
	}
	
	/**
	 * Change la référence au manager de l'application
	 */
	public void setManager(Manager ma) {
		manager = ma;
	}
}
