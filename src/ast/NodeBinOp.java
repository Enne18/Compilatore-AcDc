package ast;

import visitor.IVisitor;

/**
 * @author Nicholas Ternullo 20045859
 *
 * Nodo rappresentante un'istruzione di operazione binaria presente nell'AST
 */
public class NodeBinOp extends NodeExpr {

	/**
	 * Operatore dell'operazione
	 */
	private LangOper op;
	
	/**
	 * Valore sinistro dell'operazione
	 */
	private NodeExpr left;
	
	/**
	 * Valore destro dell'operazione
	 */
	private NodeExpr right;
	
	/**
	 * Costruisce un nodo NodeBinOp con il valore sinistro, l'operatore e il valore destro
	 * 
	 * @param op	Operatore
	 * @param left	Valore sinistro
	 * @param right	Valore destro
	 */
	public NodeBinOp(LangOper op, NodeExpr left, NodeExpr right) {
		this.op = op;
		this.left = left;
		this.right = right;
	}
	
	/**
	 * @return Operatore
	 */
	public LangOper getValueOp(){
		return op;
	}
	
	/**
	 * @return Valore sinistro
	 */
	public NodeExpr getValueLeft(){
		return left;
	}
	
	/**
	 * @return Valore destro
	 */
	public NodeExpr getValueRight(){
		return right;
	}
	
	/**
	 * Imposta il valore sinistro convertito
	 * 
	 * @param left Nuovo nodo sinistro dell'espressione
	 */
	public void setLeft(NodeConvert left) {
		this.left = left;
	}

	/**
	 * Imposta il valore destra convertito
	 * 
	 * @param right Nuovo nodo destro dell'espressione
	 */
	public void setRight(NodeConvert right) {
		this.right = right;
	}

	/**
	 * @return Una stringa nel formato "<NodeBinOp: left op rght>"
	 */
	@Override
	public String toString() {
		return "<NodeBinOp: " + getValueLeft() + " " + getValueOp() + " " + getValueRight() + ">";
	}
	
	/**
	 * Accetta un visitor
	 */
	@Override
	public void accept(IVisitor visitor) {
		visitor.visit(this);
	}
}
