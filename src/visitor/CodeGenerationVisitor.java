package visitor;

import java.util.ArrayList;
import java.util.Iterator;

import ast.*;
import symbolTable.Registri;
import symbolTable.SymbolTable;

/**
 * @author Nicholas Ternullo 20045859
 *
 * Classe che implementa un visitor per la generazione di codice
 */
public class CodeGenerationVisitor implements IVisitor {

	/**
	 * Stringa del codice
	 */
	private String codiceDc;
	
	/**
	 * Stringa con il log di errore
	 */
	private String log = "";
	
	/**
	 * Lista delle linee di codice
	 */
	private ArrayList<String> linesCode;
	
	/**
	 * Costruttore che inizializza i registri, inizializza codiceDc e crea l'ArrayList
	 */
	public CodeGenerationVisitor() {
		Registri.inizializza();
		codiceDc = "";
		linesCode = new ArrayList<>();
	}
	
	/**
	 * @return	La stringa con il codice
	 */
	public String getCodiceDc() {
		return codiceDc;
	}

	/**
	 * @return	La stringa con il log di errore
	 */
	public String getLog() {
		return log;
	}
	
	/**
	 * @return	Un iteratore sull'ArrayList
	 */
	public Iterator<String> getIteratore(){
		return linesCode.iterator();
	}

	/**
	 * Scorre tutti i nodi contenuti nel nodo NodeProgram e mette i risultati dell'accept nell'iteratore
	 * 
	 * @param node NodeProgram da visitare
	 */
	@Override
	public void visit(NodeProgram node) {
		for(NodeDecSt nodeSt: node.getValue()) {
			nodeSt.accept(this);
			linesCode.add(codiceDc);
			codiceDc = "";
		}
	}
	
	/**
	 * Assegna a codiceDc il registro assegnato all'identificatore
	 * 
	 * @param node NodeId da visitare
	 */
	@Override
	public void visit(NodeId node) {
		char registro = SymbolTable.lookup(node.getValue()).getRegistro();
		codiceDc = String.valueOf(registro);
	}

	/**
	 * Assegna a codiceDc il valore del numero
	 * 
	 * @param node NodeCost da visitare
	 */
	@Override
	public void visit(NodeCost node) {
		codiceDc = node.getValue();
	}

	/**
	 * Aumenta la precisione e ne assegna il cambiamento a codiceDc
	 * 
	 * @param node NodeConvert da visitare
	 */
	@Override
	public void visit(NodeConvert node) {
		node.getNodo().accept(this);
		codiceDc = "5 k " + codiceDc;
	}

	/**
	 * Assegna a codiceDc il valore per la stampa del valore associato al'identificatore
	 * 
	 * @param node NodePrint da visitare
	 */
	@Override
	public void visit(NodePrint node) {
		node.getValue().accept(this);
		codiceDc = "l" + codiceDc + " p P";
	}

	/**
	 * Assegna a codiceDc il valore per caricare in cima allo stack il valore del registro dell'identificatore
	 * 
	 * @param node NodeDeref da visitare
	 */
	@Override
	public void visit(NodeDeref node) {
		node.getValueId().accept(this);
		codiceDc = "l" + codiceDc;
	}

	/**
	 * Assegna a codiceDc il valore a sinistra e a destra dell'operazione e l'operazione (Notazione polacca inversa)
	 * 
	 * @param node NodeBinOp da visitare
	 */
	@Override
	public void visit(NodeBinOp node) {
		node.getValueLeft().accept(this);
		String left = codiceDc;
		node.getValueRight().accept(this);
		String right = codiceDc;
		
		codiceDc = left + " " + right + " " + node.getValueOp();
	}

	/**
	 * Associa il nome della variabile al registro
	 * Assegna a codiceDc il valore per salvare il valore nel registro
	 * 
	 * @param node NodeDecl da visitare
	 */
	@Override
	public void visit(NodeDecl node) {
		var att = SymbolTable.lookup(node.getValueId().getValue());
		Character c = Registri.newRegister();
		
		if(c == null) {
			log = "Registri insufficienti";
			return;
		}
		att.setRegistro(c);
		
		if(node.getValueExpr() != null) {
			node.getValueExpr().accept(this);
			String expr = codiceDc;
			node.getValueId().accept(this);
			String id = codiceDc;
			
			codiceDc = expr + " s" + id;
			if(codiceDc.contains(" k ")) {
				codiceDc = codiceDc.concat(" 0 k");
			}
		}
	}

	/**
	 * Assegna a codiceDc il valore per assegnare ad un registro il risultato di un'espressione
	 * 
	 * @param node NodeAssign da visitare
	 */
	@Override
	public void visit(NodeAssing node) {
		node.getValueExpr().accept(this);
		String expr = codiceDc;
		node.getValueId().accept(this);
		String id = codiceDc;
		
		codiceDc = expr + " s" + id;
		if(codiceDc.contains(" k ")) {
			codiceDc = codiceDc.concat(" 0 k");
		}
	}
}
