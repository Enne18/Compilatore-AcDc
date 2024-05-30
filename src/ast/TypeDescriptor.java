package ast;

/**
 * @author Nicholas Ternullo 20045859
 *
 */
public class TypeDescriptor {
	
	/**
	 * Il tipo
	 */
	private TipoTD tipo;
	
	/**
	 * Il messaggio
	 */
	private String msg;
	
	/**
	 * Il numero della riga
	 */
	private int riga;

	/**
	 * Costruisce un descriptor con il tipo ricevuto
	 * 
	 * @param tipo Il tipo
	 */
	public TypeDescriptor(TipoTD tipo) {
		this.tipo = tipo;
	}
	
	/**
	 * Costruisce un descriptor con il tipo, il messaggio ricevuto e la riga
	 * 
	 * @param tipo Il tipo
	 * @param msg Il messaggio
	 * @param riga La riga
	 */
	public TypeDescriptor(TipoTD tipo, String msg, int riga) {
		this.tipo = tipo;
		this.msg = msg;
		this.riga = riga;
	}

	/**
	 * @return Il tipo
	 */
	public TipoTD getTipo() {
		return tipo;
	}
	
	/**
	 * @return la riga
	 */
	public int getRiga() {
		return riga;
	}

	/**
	 * Imposta il tipo
	 * 
	 * @param tipo Il tipo
	 */
	public void setTipo(TipoTD tipo) {
		this.tipo = tipo;
	}

	/**
	 * @return Il messaggio
	 */
	public String getMsg() {
		return "Riga: " + riga + " " + msg;
	}

	/**
	 * Imposta il messaggio
	 * 
	 * @param msg Il messaggio
	 */
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	/**
	 * Imposta il numero della riga
	 * 
	 * @param riga Il numero della riga
	 */
	public void setRiga(int riga) {
		this.riga = riga;
	}
}
