package ast;

import visitor.IVisitor;

/**
 * @author Nicholas Ternullo 20045859
 *
 * Classe astratta che viene estesa da ogni altro Nodo.
 */
public abstract class NodeAST {

	/**
	 * @param visitor Oggetto che implementa molteplici funzioni
	 */
	public abstract void accept(IVisitor visitor);
	
}
