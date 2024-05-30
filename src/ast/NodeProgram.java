package ast;

import java.util.ArrayList;

import visitor.IVisitor;

/**
 * @author Nicholas Ternullo 20045859
 *
 * Nodo rappresentante l'inizio dell'AST
 */
public class NodeProgram extends NodeAST {

	/**
	 * Lista delle operazioni del programma
	 */
	private final ArrayList<NodeDecSt> decSts;
	
	/**
	 * Costruisce un nodo NodeProgram con la lista delle operazioni del programma ricevuta
	 * 
	 * @param decSts Lista delle operazioni del programma
	 */
	public NodeProgram(ArrayList<NodeDecSt> decSts) {
		this.decSts = decSts;
	}
	
	/**
	 * @return Lista delle operazioni del programma
	 */
	public ArrayList<NodeDecSt> getValue(){
		return decSts;
	}
	
	/**
	 * @return Una stringa nel formato "<NodeProgram: [decSts1, decSts2, ..., decStsn]>"
	 */
	@Override
	public String toString() {
		return "<NodeProgram: " + getValue().toString() + " >";
	}
	
	/**
	 * Accetta un visitor
	 */
	@Override
	public void accept(IVisitor visitor) {
		visitor.visit(this);
	}
}
