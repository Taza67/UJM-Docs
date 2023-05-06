package interieur;

import java.util.LinkedList;
import java.util.List;

/**
 * Classe représentant l'ensemble des actions réalisées
 * par l'utilisateur sur l'UI (ex : sauvegarde, charge)
 * @author mourtaza
 *
 */
public class Actions implements IConfig {
	/**
	 * Liste des codes associés aux actions demandées et pas encore réalisées
	 */
	private List<Integer> actionsCodes;
	
	
	/**
	 * Instancie un objet représentant l'ensemble d'actions
	 */
	public Actions() {
		actionsCodes = new LinkedList<Integer>();
	}
	
	
	/**
	 * Retourne la taille de l'ensemble d'actions
	 * @return Taille de l'ensemble d'actions
	 */
	public synchronized int getSize() {
		return actionsCodes.size();
	}
	
	
	/**
	 * Retourne la tête de la liste
	 */
	public synchronized Integer getHeadAction() {
		if (actionsCodes.isEmpty()) return null;
		return actionsCodes.get(0);
	}
	
	/**
	 * Retire la tête de la liste
	 */
	public synchronized void removeHeadAction() {
		if (actionsCodes.isEmpty()) return;
		actionsCodes.remove(0);
	}
	
	/**
	 * Ajoute une action
	 * @param actionCode Code de l'action à ajouter
	 */
	public synchronized void addAction(Integer actionCode) {
		actionsCodes.add(actionCode);
	}
}
