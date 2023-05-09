package inside;

import java.util.LinkedList;
import java.util.List;

/**
 * Classe représentant l'ensemble des actions réalisées par l'utilisateur sur
 * l'UI (ex : sauvegarde, charge, etc)
 *
 * @author mourtaza
 *
 */
public class Actions implements IConfig {
	/**
	 * Liste des actions demandées et pas encore réalisées
	 */
	private List<Action> actionsList;

	/**
	 * Instancie un objet représentant l'ensemble d'actions
	 */
	public Actions() {
		actionsList = new LinkedList<>();
	}

	/**
	 * Retourne la taille de l'ensemble d'actions
	 *
	 * @return Taille de l'ensemble d'actions
	 */
	public synchronized int getSize() {
		return actionsList.size();
	}

	/**
	 * Retourne la tête de la liste
	 *
	 * @return Action en tête de la liste
	 */
	public synchronized Action getHeadAction() {
		if (actionsList.isEmpty())
			return null;
		return actionsList.get(0);
	}

	/**
	 * Retire la tête de la liste
	 */
	public synchronized void removeHeadAction() {
		if (actionsList.isEmpty())
			return;
		actionsList.remove(0);
	}

	/**
	 * Ajoute une action
	 *
	 * @param actionCode Code de l'action à ajouter
	 * @param message    Message à envoyer pour l'action
	 */
	public synchronized void addAction(Integer actionCode, String message) {
		actionsList.add(new Action(actionCode, message));
	}
}
