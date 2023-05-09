package inside.utilities;

/**
 * Classe représentant un tuple d'objets
 *
 * @author mourtaza
 *
 */
public class Tuple {
	/**
	 * Premier objet
	 */
	private final Object FIRST;
	/**
	 * Second objet
	 */
	private final Object SECOND;

	/**
	 * Instancie un tuple
	 *
	 * @param Valeur du premier objet
	 * @param Valeur du second objet
	 */
	public Tuple(Object f, Object s) {
		FIRST = f;
		SECOND = s;
	}

	/**
	 * Retourne le premier objet
	 *
	 * @return Premier objet
	 */
	public Object getFIRST() {
		return FIRST;
	}

	/**
	 * Retourne le second objet
	 *
	 * @return Second objet
	 */
	public Object getSECOND() {
		return SECOND;
	}
}
