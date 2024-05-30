package ast;

import visitor.IVisitor;

/**
 * @author Nicholas Ternullo 20045859
 *
 * Nodo rappresentante un'istruzione di stampa presente nell'AST
 */
public class NodePrint extends NodeStm {

	/**
	 * Identificatore della variabile da stampare
	 */
	private NodeId id;
	
	/**
	 * Costruisce un nodo NodePrint con l'identificatore della variabile da stampare ricevuto
	 * 
	 * @param id Identificatore della variabile da stampare
	 */
	public NodePrint(NodeId id) {
		this.id = id;
	}
	
	/**
	 * @return Identificatore della variabile da stampare
	 */
	public NodeId getValue(){
		return id;
	}
	
	/**
	 * @return Una stringa nel formato "<NodePrint: id>"
	 */
	public String toString() {
		return "<NodePrint: " + getValue() + " >";
	}
	
	/**
	 * Accetta un visitor
	 */
	@Override
	public void accept(IVisitor visitor) {
		visitor.visit(this);
	}
}
