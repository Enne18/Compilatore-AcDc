package ast;

import visitor.IVisitor;

/**
 * @author Nicholas Ternullo 20045859
 *
 * Nodo rappresentante una dichiarazione presente nell'AST
 */
public class NodeDecl extends NodeDecSt {

	/**
	 * Identificatore della variabile dichiarata
	 */
	private NodeId id;
	
	/**
	 * Tipo della variabile dichiarata
	 */
	private LangType type;
	
	/**
	 * Espressione di inizializzazione della variabile dichiarata
	 */
	private NodeExpr expr;
	
	/**
	 * Costruisce un nodo NodeDecl con il tipo, l'identificatore e l'espressione di inizializzazione
	 * 
	 * @param id	Identificatore della variabile dichiarata
	 * @param type	Tipo della variabile dichiarata
	 * @param expr	Espressione di inizializzazione della variabile dichiarata
	 */
	public NodeDecl(NodeId id, LangType type, NodeExpr expr) {
		this.id = id;
		this.type = type;
		this.expr = expr;
	}
	
	/**
	 * @return Identificatore della variabile dichiarata
	 */
	public NodeId getValueId(){
		return id;
	}
	
	/**
	 * @return Tipo della variabile dichiarata
	 */
	public LangType getValueType(){
		return type;
	}
	
	/**
	 * @return Espressione di inizializzazione della variabile dichiarata
	 */
	public NodeExpr getValueExpr(){
		return expr;
	}
	
	/**
	 * @return Una stringa nel formato "<NodeDecl: type id expr>"
	 */
	@Override
	public String toString() {
		return "<NodeDecl: " + getValueType() + " " + getValueId() + " " + getValueExpr() + ">";
	}
	
	/**
	 * Accetta un visitor
	 */
	@Override
	public void accept(IVisitor visitor) {
		visitor.visit(this);
	}
}
