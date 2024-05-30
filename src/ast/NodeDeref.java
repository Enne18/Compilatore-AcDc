package ast;

import visitor.IVisitor;

/**
 * @author Nicholas Ternullo 20045859
 *
 * Nodo rappresentante una variabile presente nell'AST
 */
public class NodeDeref extends NodeExpr {

	/**
	 * Identificatore della variabile
	 */
	private NodeId id;
	
	/**
	 * @param id Identificatore della variabile
	 */
	public NodeDeref(NodeId id) {
		this.id = id;
	}
	
	/**
	 * @return Identificatore della variabile
	 */
	public NodeId getValueId(){
		return id;
	}
	
	/**
	 * @return Una stringa nel formato "<NodeDeref: id>"
	 */
	@Override
	public String toString() {
		return "<NodeDeref:" + getValueId() + ">";
	}
	
	/**
	 * Accetta un visitor
	 */
	@Override
	public void accept(IVisitor visitor) {
		visitor.visit(this);
	}
}
