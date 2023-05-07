package outside.application;

import java.awt.EventQueue;
import outside.userInterface.JFramePerso;
import inside.Manager;

public class UJMDocs {
	/**
	 * Lance l'application
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				JFramePerso window = new JFramePerso();
				Manager manager = new Manager(window.getTextEditorPane());
				window.setManager(manager);
				window.getTextEditorPane().setManager(manager);
			}
		});
	}
}
