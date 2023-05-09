package inside;

/**
 * Classe permettant de représenter une action de l'utilisateur
 *
 * @author mourtaza
 *
 */
public class Action {
	/**
	 * Code de l'action
	 */
	private Integer code;
	/**
	 * Message associé à l'action
	 */
	private String message;

	/**
	 * Instancie une action
	 *
	 * @param c Code associé à l'action
	 * @param m Message associé à l'action
	 */
	public Action(Integer c, String m) {
		code = c;
		message = m;
	}

	/**
	 * Retourne le code associé à l'action
	 *
	 * @return Code de l'action
	 */
	public int getCode() {
		return code;
	}

	/**
	 * Retourne le message associé à l'action
	 *
	 * @return Message de l'action
	 */
	public String getMessage() {
		return message;
	}
}
