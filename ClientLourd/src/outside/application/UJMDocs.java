package outside.application;

import java.awt.EventQueue;

import outside.userInterface.JFramePerso;

public class UJMDocs {
	/**
	 * Lance l'application
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				JFramePerso window = new JFramePerso();
			}
		});
	}
}
