package ast;

import symbolTable.SymbolTable.Attributes;
import visitor.IVisitor;

/**
 * @author Nicholas Ternullo 20045859
 *
 * Nodo rappresentante un'identificatore presente nell'AST
 */
public class NodeId extends NodeAST {

	/**
	 * Il nome dell'identificatore
	 */
	private String name;
	
	/**
	 * L'attributo dell'identificatore
	 */
	private Attributes attributo;
	
	/**
	 * Costruisce un nodo NodeId con il nome dell'identificatore
	 * 
	 * @param name Il nome dell'identificatore
	 */
	public NodeId(String name) {
		this.name = name;
	}
	
	/**
	 * @return Il nome dell'identificatore
	 */
	public String getValue(){
		return name;
	}
	
	/**
	 * @return L'attributo dell'identificatore
	 */
	public Attributes getAttributo() {
		return attributo;
	}
	
	/**
	 * Imposta l'attributo dell'identificatore
	 * 
	 * @param attributo L'attributo dell'identificatore
	 */
	public void setAttributo(Attributes attributo) {
		this.attributo = attributo;
	}
	
	/**
	 * @return Una stringa nel formato "<NodeId: name>"
	 */
	@Override
	public String toString() {
		return "<NodeId: " + getValue() + " >";
	}
	
	/**
	 * Accetta un visitor
	 */
	@Override
	public void accept(IVisitor visitor) {
		visitor.visit(this);
	}
}
