package ast;

import visitor.IVisitor;

/**
 * @author Nicholas Ternullo 20045859
 *
 * Nodo rappresentante una costante presente nell'AST
 */
public class NodeCost extends NodeExpr {

	/**
	 * Valore della costante
	 */
	private String value;
	
	/**
	 * Tipo della costante
	 */
	private LangType type;
	
	/**
	 * Costruisce un nodo NodeCost con il valore e il tipo ricevuti
	 * 
	 * @param value	Il valore della costante
	 * @param type	Il tipo della costante
	 */
	public NodeCost(String value, LangType type) {
		this.value = value;
		this.type = type;
	}
	
	/**
	 * @return Il valore della costante
	 */
	public String getValue(){
		return value;
	}
	
	/**
	 * @return Il tipo della costante
	 */
	public LangType getValueType(){
		return type;
	}
	
	/**
	 * @return Una stringa nel formato "<NodeCost: type value>"
	 */
	@Override
	public String toString() {
		return "<NodeCost: " + getValueType() + " " + getValue() + ">";
	}
	
	/**
	 * Accetta il visitor
	 */
	@Override
	public void accept(IVisitor visitor) {
		visitor.visit(this);
	}
}
