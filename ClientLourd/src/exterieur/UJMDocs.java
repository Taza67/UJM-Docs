package exterieur;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import java.awt.Panel;
import java.awt.BorderLayout;
import java.awt.Button;

import javax.swing.BoxLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Component;
import javax.swing.JMenuItem;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class UJMDocs {

	private JFrame frmUjmDocs;
	private boolean isMenuBarVisible = true;
	private boolean isToolsBarVisible = true;
	
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
		frmUjmDocs.setSize(new Dimension(1080, 720));
		frmUjmDocs.setVisible(true);
		frmUjmDocs.requestFocusInWindow();
		frmUjmDocs.setPreferredSize(new Dimension(720, 480));
		frmUjmDocs.setMinimumSize(new Dimension(720, 480));
		frmUjmDocs.setMaximumSize(new Dimension(1280, 720));
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
		menuBar.add(mnFichier);
		
		JMenuItem mntmNouveauDocument = new JMenuItem("Nouveau");
		mnFichier.add(mntmNouveauDocument);
		
		JMenuItem mntmChargerDocument = new JMenuItem("Charger");
		mnFichier.add(mntmChargerDocument);
		
		JMenuItem mntmSauvegarder = new JMenuItem("Sauvegarder");
		mnFichier.add(mntmSauvegarder);
		
		JMenuItem mntmExporter = new JMenuItem("Exporter");
		mnFichier.add(mntmExporter);
		
		JMenuItem mntmQuitter = new JMenuItem("Quitter");
		mnFichier.add(mntmQuitter);
		
		JMenu mndition = new JMenu("Édition");
		menuBar.add(mndition);
		
		JMenuItem mntmAnnuler = new JMenuItem("Annuler");
		mndition.add(mntmAnnuler);
		
		JMenuItem mntmRefaire = new JMenuItem("Refaire");
		mndition.add(mntmRefaire);
		
		JMenuItem mntmCopier = new JMenuItem("Copier");
		mndition.add(mntmCopier);
		
		JMenuItem mntmCouper = new JMenuItem("Couper");
		mndition.add(mntmCouper);
		
		JMenuItem mntmColler = new JMenuItem("Coller");
		mndition.add(mntmColler);
		
		JMenu mnVue = new JMenu("Vue");
		menuBar.add(mnVue);
		
		JCheckBoxMenuItem chckbxmntmBarreDeMenu = new JCheckBoxMenuItem("Barre de menu");
		mnVue.add(chckbxmntmBarreDeMenu);
		
		JCheckBoxMenuItem chckbxmntmBarreDoutils = new JCheckBoxMenuItem("Barre d'outils");
		mnVue.add(chckbxmntmBarreDoutils);
		
		JMenu mnInsertion = new JMenu("Insertion");
		menuBar.add(mnInsertion);
		
		JMenu mnFormat = new JMenu("Format");
		menuBar.add(mnFormat);
		
		JMenu mnFenetre = new JMenu("Fenetre");
		menuBar.add(mnFenetre);
		
		JMenu mnAide = new JMenu("Aide");
		menuBar.add(mnAide);
		
		Panel pnlBarreDoutils = new Panel();
		frmUjmDocs.getContentPane().add(pnlBarreDoutils, BorderLayout.NORTH);
		pnlBarreDoutils.setLayout(new BoxLayout(pnlBarreDoutils, BoxLayout.X_AXIS));
		
		Button button_1 = new Button("New button");
		pnlBarreDoutils.add(button_1);
		
		Button button_2 = new Button("New button");
		pnlBarreDoutils.add(button_2);
		
		Button button_3 = new Button("New button");
		pnlBarreDoutils.add(button_3);
		
		Button button_4 = new Button("New button");
		pnlBarreDoutils.add(button_4);
		
		Button button_5 = new Button("New button");
		pnlBarreDoutils.add(button_5);
		
		Button button_6 = new Button("New button");
		pnlBarreDoutils.add(button_6);
		
		chckbxmntmBarreDeMenu.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				isMenuBarVisible = !isMenuBarVisible;
				menuBar.setVisible(isMenuBarVisible);
			}
		});
		
		chckbxmntmBarreDoutils.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				isToolsBarVisible = !isToolsBarVisible;
				pnlBarreDoutils.setVisible(isToolsBarVisible);
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
