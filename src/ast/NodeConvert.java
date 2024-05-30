package ast;

import visitor.IVisitor;

/**
 * @author Nicholas Ternullo 20045859
 *
 * Nodo rappresentante un'istruzione di conversione(casting) presente nell'AST
 */
public class NodeConvert extends NodeExpr {

	/**
	 * Espressione da convertire
	 */
	private NodeExpr nodo;
	
	/**
	 * Costruisce un nuovo nodo con l'espressione da convertire
	 * 
	 * @param nodo Il nodo da convertire
	 */
	public NodeConvert(NodeExpr nodo){
		this.nodo = nodo;
	}

	/**
	 * @return Nodo convertito
	 */
	public NodeExpr getNodo() {
		return nodo;
	}

	/**
	 * @param nodo Nodo da convertire
	 */
	public void setNodo(NodeExpr nodo) {
		this.nodo = nodo;
	}
	
	/**
	 * @return Una stringa nel formato "<NodeConvert: nodo>"
	 */
	@Override
	public String toString() {
		return "NoceConvert: " + nodo.toString() + ">";
	}
	
	/**
	 * Accettta un visitor
	 */
	public void accept(IVisitor visitor) {
		visitor.visit(this);
	}
}
