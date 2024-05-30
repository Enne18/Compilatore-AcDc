package visitor;

import ast.*;

/**
 * @author Nicholas Ternullo 20045859
 *
 * Interfaccia per un visitor che implementa le operazioni di visit
 */
public interface IVisitor {

	/**
	 * Metodo per la visita di un nodo Program
	 * 
	 * @param node	Il nodo Program da visitare
	 */
	public abstract void visit(NodeProgram node);
	
	/**
	 * Metodo per la visita di un nodo Id
	 * 
	 * @param node	Il nodo Id da visitare
	 */
	public abstract void visit(NodeId node);
	
	/**
	 * Metodo per la visita di un nodo Constant
	 * 
	 * @param node	Il nodo Constant da visitare
	 */
	public abstract void visit(NodeCost node);
	
	/**
	 * Metodo per la visita di un nodo Convert
	 * 
	 * @param node	Il nodo Convert da visitare
	 */
	public abstract void visit(NodeConvert node);
	
	/**
	 * Metodo per la visita di un nodo Print
	 * 
	 * @param node	Il nodo Print da visitare
	 */
	public abstract void visit(NodePrint node);
	
	/**
	 * Metodo per la visita di un nodo Deref
	 * 
	 * @param node	Il nodo Deref da visitare
	 */
	public abstract void visit(NodeDeref node);
	
	/**
	 * Metodo per la visita di un nodo Binary Operation
	 * 
	 * @param node	Il nodo Binary Operation da visitare
	 */
	public abstract void visit(NodeBinOp node);
	
	/**
	 * Metodo per la visita di un nodo Declaration
	 * 
	 * @param node	Il nodo Declaration da visitare
	 */
	public abstract void visit(NodeDecl node);
	
	/**
	 * Metodo per la visita di un nodo Assign
	 * 
	 * @param node	Il nodo Assign da visitare
	 */
	public abstract void visit(NodeAssing node);
	
}
