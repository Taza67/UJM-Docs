package exterieur;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import java.awt.BorderLayout;
import java.awt.Button;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Component;
import javax.swing.JMenuItem;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.event.ChangeListener;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.StyleSheet;
import javax.swing.event.ChangeEvent;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JToolBar;
import javax.swing.JSeparator;
import java.awt.Font;
import javax.swing.JEditorPane;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.JScrollPane;
import java.awt.CardLayout;
import javax.swing.BoxLayout;
import java.awt.TextField;

public class UJMDocs {
	public static final int DEFAULT_EDITOR_MARGE = 200;
	public static final int DEFAULT_PAGE_MARGE = 60;

	private JFrame frmUjmDocs;
	private boolean isMenuBarVisible = true;
	private boolean isToolBarVisible = true;
	private float zoomLevel = 1f;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UJMDocs window = new UJMDocs();
					window.frmUjmDocs.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public UJMDocs() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmUjmDocs = new JFrame();
		frmUjmDocs.getContentPane().setFont(new Font("Gentium Book Basic", Font.PLAIN, 15));
		frmUjmDocs.getContentPane().setLayout(new CardLayout(0, 0));
		frmUjmDocs.setFont(new Font("Gentium Book Basic", Font.PLAIN, 12));
		frmUjmDocs.setSize(new Dimension(1080, 720));
		frmUjmDocs.setVisible(true);
		frmUjmDocs.requestFocusInWindow();
		frmUjmDocs.setForeground(new Color(102, 102, 102));
		frmUjmDocs.setBackground(new Color(51, 51, 51));
		frmUjmDocs.getContentPane().setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		frmUjmDocs.setTitle("UJM Docs");
		frmUjmDocs.setBounds(100, 100, 1080, 720);
		frmUjmDocs.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setAlignmentX(Component.LEFT_ALIGNMENT);
		frmUjmDocs.setJMenuBar(menuBar);
		
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
		
		JMenuItem mntmCopier = new JMenuItem("Copier");
		mntmCopier.setFont(new Font("Gentium Book Basic", Font.PLAIN, 15));
		mndition.add(mntmCopier);
		
		JMenuItem mntmCouper = new JMenuItem("Couper");
		mntmCouper.setFont(new Font("Gentium Book Basic", Font.PLAIN, 15));
		mndition.add(mntmCouper);
		
		JMenuItem mntmColler = new JMenuItem("Coller");
		mntmColler.setFont(new Font("Gentium Book Basic", Font.PLAIN, 15));
		mndition.add(mntmColler);
		
		JMenu mnVue = new JMenu("Vue");
		mnVue.setFont(new Font("Gentium Book Basic", Font.PLAIN, 15));
		menuBar.add(mnVue);
		
		JCheckBoxMenuItem chckbxmntmBarreDeMenu = new JCheckBoxMenuItem("Barre de menu");
		chckbxmntmBarreDeMenu.setFont(new Font("Gentium Book Basic", Font.PLAIN, 15));
		mnVue.add(chckbxmntmBarreDeMenu);
		
		JCheckBoxMenuItem chckbxmntmBarreDoutils = new JCheckBoxMenuItem("Barre d'outils");
		chckbxmntmBarreDoutils.setFont(new Font("Gentium Book Basic", Font.PLAIN, 15));
		mnVue.add(chckbxmntmBarreDoutils);
		
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
		frmUjmDocs.getContentPane().add(connection, "name_22684711362580");
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
		
		JPanel editeur = new JPanel();
		frmUjmDocs.getContentPane().add(editeur, "name_22610450387445");
		editeur.setLayout(new BorderLayout(0, 0));
		
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
		
		JPanel editorContainerPanel = new JPanel();
		editorContainerPanel.setBorder(BorderFactory.createEmptyBorder(DEFAULT_EDITOR_MARGE, DEFAULT_EDITOR_MARGE, DEFAULT_EDITOR_MARGE, DEFAULT_EDITOR_MARGE));
		editorContainerPanel.setLayout(new BorderLayout(0, 0));
		
		JEditorPane editorPane = new JEditorPane();
		editorPane.setContentType("text/html");
		editorPane.setText("<html><body></body></html>");
		editorPane.setBorder(BorderFactory.createEmptyBorder(DEFAULT_PAGE_MARGE, DEFAULT_PAGE_MARGE, DEFAULT_PAGE_MARGE, DEFAULT_PAGE_MARGE));
		editorPane.setPreferredSize(new Dimension(80, 1000));
		editorPane.setSize(new Dimension(80, 1000));
		editorPane.setMinimumSize(new Dimension(80, 1000));
		editorContainerPanel.add(editorPane, BorderLayout.CENTER);
		
		JScrollPane scrollPane = new JScrollPane(editorContainerPanel);
		scrollPane.setAutoscrolls(true);
		editeur.add(scrollPane);
		
		JToolBar statusBar = new JToolBar();
		statusBar.setBorder(null);
		statusBar.setAlignmentX(0.0f);
		editeur.add(statusBar, BorderLayout.SOUTH);
		
		Component rigidArea = Box.createRigidArea(new Dimension(20, 20));
		statusBar.add(rigidArea);
		
		JLabel NumeroPage = new JLabel("Page 1/1");
		NumeroPage.setFont(new Font("Gentium Book Basic", Font.PLAIN, 15));
		statusBar.add(NumeroPage);
		
		Component rigidArea1 = Box.createRigidArea(new Dimension(20, 20));
		rigidArea1.setPreferredSize(new Dimension(50, 20));
		statusBar.add(rigidArea1);
		
		JLabel MotsCaracteres = new JLabel("0 Mots, 0 caractères");
		MotsCaracteres.setFont(new Font("Gentium Book Basic", Font.PLAIN, 15));
		statusBar.add(MotsCaracteres);
		
		Component glue = Box.createGlue();
		statusBar.add(glue);
		
		JSlider zoomSlider = new JSlider();
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
		
		chckbxmntmBarreDeMenu.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				isMenuBarVisible = !isMenuBarVisible;
				menuBar.setVisible(isMenuBarVisible);
			}
		});
		
		chckbxmntmBarreDoutils.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				isToolBarVisible = !isToolBarVisible;
				toolBar.setVisible(isToolBarVisible);
			}
		});
		
		zoomSlider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				int editorMarge = DEFAULT_EDITOR_MARGE * (200 - zoomSlider.getValue()) / 100;
				
				editorContainerPanel.setBorder(BorderFactory.createEmptyBorder(editorMarge, editorMarge, editorMarge, editorMarge));
				editorContainerPanel.revalidate();
				
				zoomLevel = zoomSlider.getValue() - 100;
				
				System.err.println(zoomLevel);
				
				// HTML
				String content = editorPane.getText();
	            HTMLEditorKit editorKit = new HTMLEditorKit();
	            editorPane.setEditorKit(editorKit);
	            StyleSheet styleSheet = new StyleSheet();
	            styleSheet.addRule("body { transform: scale(" + zoomLevel + "); color: \"purple\"; }");
	            editorKit.setStyleSheet(styleSheet);
	            editorPane.setText(content);
			}
		});
		
		frmUjmDocs.addKeyListener(new KeyAdapter() {
		    @Override
		    public void keyPressed(KeyEvent e) {
		        if (e.getKeyCode() == KeyEvent.VK_ALT) {
		        	isMenuBarVisible = !isMenuBarVisible;
		        	menuBar.setVisible(isMenuBarVisible);
		        	chckbxmntmBarreDeMenu.setEnabled(isMenuBarVisible);
		        }
		    }
		});
	}
}
