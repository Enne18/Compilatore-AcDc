package ast;

import visitor.IVisitor;


/**
 * @author Nicholas Ternullo 20045859
 * 
 * Nodo rappresentante un'istruzione di assegnamento presente nell'AST
 */
public class NodeAssing extends NodeStm {

	/**
	 * Nodo Identificatore a sinistra dell'assegnazione
	 */
	private NodeId id;
	
	/**
	 * Nodo Espressione a destra dell'assegnazione
	 */
	private NodeExpr expr;
	
	/**
	 * Costruisce il Nodo Assign
	 * 
	 * @param id	Nodo Identificatore
	 * @param expr	Nodo Espressione
	 */
	public NodeAssing(NodeId id, NodeExpr expr) {
		this.id = id;
		this.expr = expr;
	}
	
	/**
	 * @return Il Nodo Identificatore
	 */
	public NodeId getValueId(){
		return id;
	}
	
	/**
	 * @return Il Nodo Espressione
	 */
	public NodeExpr getValueExpr(){
		return expr;
	}
	
	/**
	 * Inizializza il Nodo Espressione
	 * 
	 * @param expr Nodo Espressione
	 */
	public void setExpr(NodeExpr expr) {
		this.expr = expr;
	}

	/**
	 *  @return Una stringa nel formato "<NodeAssign - Id: id, NodeExpr: expr>"
	 */
	@Override
	public String toString() {
		return "<NodeAssing - Id: " + getValueId() + ", NodeExpr: " + getValueExpr().toString() + ">";
	}

	/**
	 * Accetta un visitor
	 */
	@Override
	public void accept(IVisitor visitor) {
		visitor.visit(this);
	}
}
