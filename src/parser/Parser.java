package parser;

import java.io.IOException;
import java.util.ArrayList;

import scanner.*;
import token.*;
import ast.*;

/**
 * @author Nicholas Ternullo 20045859
 *
 * Classe che rappresenta un parser e la creazione dell'AST
 * Analisi sintattica
 */
public class Parser {

	/**
	 * Scanner da usare per ottenere i Token
	 */
	private Scanner scanner;

	/**
	 * @param scanner Scanner da usare per il parsing
	 */
	public Parser(Scanner scanner) {
		this.scanner = scanner;
	}

	/**
	 * Controlla se il tipo del token successivo sia uguale a type, se uguale lo consuma
	 * 
	 * @param type					Tipo atteso
	 * @return						Token successivo
	 * @throws IOException			Se c'è un errore nella lettura del file
	 * @throws LexicalException		Se lo scanner trova errori
	 * @throws SyntacticException	Se type è diverso dal tipo del token successivo
	 */
	private Token match(TokenType type) throws IOException, LexicalException, SyntacticException {
		Token tk = scanner.peekToken();
		if (type.equals(tk.getTipo()))
			return scanner.nextToken();
		else
			throw new SyntacticException(type, tk.getRiga(), tk.getTipo());
	}

	/**
	 * @return 						Il NodeProgram dell'AST
	 * @throws IOException			Se c'è un errore nella lettura del file
	 * @throws LexicalException		Se lo scanner trova errori
	 * @throws SyntacticException	Se il parsing trova errori
	 */
	public NodeProgram parse() throws IOException, LexicalException, SyntacticException {
		try {
			return parsePrg();
		} catch (LexicalException | IOException e) {
			throw new SyntacticException(e.getMessage(), e);
		}
	}

	/**
	 * Regola della grammatica: Prg -> DSs $
	 * 
	 * @return 						Un ArrayList di nodeDecSt
	 * @throws IOException			Se c'è un errore nella lettura del file
	 * @throws LexicalException		Se lo scanner trova errori
	 * @throws SyntacticException	Se il parsing trova errori
	 */
	private NodeProgram parsePrg() throws IOException, LexicalException, SyntacticException {
		Token tk = scanner.peekToken();
		switch (tk.getTipo()) {
			case TYFLOAT, TYINT, ID, PRINT, EOF -> { // Prg -> DSs $
				ArrayList<NodeDecSt> nodeDecStList = parseDSs();
				match(TokenType.EOF);
				return new NodeProgram(nodeDecStList);
			}
			default -> {
				throw new SyntacticException("TYINT, TYFLOAT, ID, PRINT, EOF", tk.getRiga(), tk.getTipo());
				}
			}
	}

	/**
	 * Regole della grammatica: DSs -> Dcl DSs
	 * 							DSs -> Stm DSs
	 * 							DSs -> e
	 * 
	 * @return 						Un ArrayList di nodeDecSt
	 * @throws IOException			Se c'è un errore nella lettura del file
	 * @throws LexicalException		Se lo scanner trova errori
	 * @throws SyntacticException	Se il parsing trova errori
	 */
	private ArrayList<NodeDecSt> parseDSs() throws IOException, LexicalException, SyntacticException {
		Token tk = scanner.peekToken();
		NodeDecSt nodeDecSt;
		ArrayList<NodeDecSt> decStmList;

		switch (tk.getTipo()) {
			case TYFLOAT, TYINT -> { // DSs -> Dcl DSs
				nodeDecSt = parseDcl();
				decStmList = parseDSs();
				decStmList.add(0, nodeDecSt);
				return decStmList;
			}
			case ID, PRINT -> { // DSs -> Stm DSs
				nodeDecSt = parseStm();
				decStmList = parseDSs();
				decStmList.add(0, nodeDecSt);
				return decStmList;
			}
			case EOF -> { // DSs -> e
				return new ArrayList<>();
			}
			default -> {
				throw new SyntacticException("TYINT, TYFLOAT, ID, PRINT, EOF", tk.getRiga(), tk.getTipo());
			}
		}
	}

	/**
	 * Regola della grammatica: Dcl -> Ty id DclP
	 * 
	 * @return 						Nodo dichiarazione
	 * @throws IOException			Se c'è un errore nella lettura del file
	 * @throws LexicalException		Se lo scanner trova errori
	 * @throws SyntacticException	Se il parsing trova errori
	 */
	private NodeDecl parseDcl() throws IOException, LexicalException, SyntacticException {
		Token tk = scanner.peekToken();
		switch (tk.getTipo()) {
			case TYFLOAT, TYINT -> { // Dcl -> Ty id DclP
				LangType type = parseTy();
				NodeId id = new NodeId(match(TokenType.ID).getVal());
				NodeExpr expr = parseDclP();
				return new NodeDecl(id, type, expr);
			}
			default -> {
				throw new SyntacticException("TYINT, TYFLOAT", tk.getRiga(), tk.getTipo());
			}
		}
	}

	/**
	 * Regole della grammatica: Ty -> float
	 * 							Ty -> int
	 * 
	 * @return 						Il tipo (tra float e int)
	 * @throws IOException			Se c'è un errore nella lettura del file
	 * @throws LexicalException		Se lo scanner trova errori
	 * @throws SyntacticException	Se il parsing trova errori
	 */
	private LangType parseTy() throws IOException, LexicalException, SyntacticException {
		Token tk = scanner.peekToken();
		switch (tk.getTipo()) {
			case TYFLOAT -> { // Ty -> float
				match(TokenType.TYFLOAT);
				return LangType.FLOAT;
			}
			case TYINT -> { // Ty -> int
				match(TokenType.TYINT);
				return LangType.INTEGER;
			}
			default -> {
				throw new SyntacticException("TYINT, TYFLOAT", tk.getRiga(), tk.getTipo());
			}
		}
	}

	/**
	 * Regole della grammatica: DclP -> ;
	 * 							DclP -> opAss Exp;
	 * 
	 * @return 						Un nodo espressione
	 * @throws IOException			Se c'è un errore nella lettura del file
	 * @throws LexicalException		Se lo scanner trova errori
	 * @throws SyntacticException	Se il parsing trova errori
	 */
	private NodeExpr parseDclP() throws IOException, LexicalException, SyntacticException {
		Token tk = scanner.peekToken();
		switch (tk.getTipo()) {
			case SEMI -> { // DclP -> ;
				match(TokenType.SEMI);
				return null;
			}
			case OP_ASSIGN -> { // DclP -> opAss Exp;
				Token operator = match(TokenType.OP_ASSIGN);
				if(!operator.getVal().equals("=")) {
					throw new SyntacticException("=", tk.getRiga(), operator.getVal());
				}
				NodeExpr expr = parseExp();
				match(TokenType.SEMI);
				return expr;
			}
			default -> {
				throw new SyntacticException("SEMI, OP_ASSIGN", tk.getRiga(), tk.getTipo());
			}
		}
	}

	/**
	 * Regole della grammatica: Stm -> print id;
	 * 							Stm -> id opAss Exp;
	 * 
	 * @return 						Un nodo statement
	 * @throws IOException			Se c'è un errore nella lettura del file
	 * @throws LexicalException		Se lo scanner trova errori
	 * @throws SyntacticException	Se il parsing trova errori
	 */
	private NodeStm parseStm() throws IOException, LexicalException, SyntacticException {
		Token tk = scanner.peekToken();
		Token operator;
		NodeId id;
		NodeStm nodeStm;
		NodeExpr nodeExpr;
		switch (tk.getTipo()) {
			case PRINT -> { // Stm -> print id;
				match(TokenType.PRINT);
				id = new NodeId(match(TokenType.ID).getVal());
				nodeStm = new NodePrint(id);
				match(TokenType.SEMI);
				return nodeStm;
			}
			case ID -> { // Stm -> id opAss Exp;
				id = new NodeId(match(TokenType.ID).getVal());
				operator = match(TokenType.OP_ASSIGN);
				nodeExpr = parseExp();
				
				switch(operator.getVal()) {
					case "+=" -> {
						nodeExpr = new NodeBinOp(LangOper.PLUS, new NodeDeref(id), nodeExpr);
					}
					case "-=" -> {
						nodeExpr = new NodeBinOp(LangOper.MINUS, new NodeDeref(id), nodeExpr);
					}
					case "*=" -> {
						nodeExpr = new NodeBinOp(LangOper.TIMES, new NodeDeref(id), nodeExpr);
					}
					case "/=" -> {
						nodeExpr = new NodeBinOp(LangOper.DIVIDE, new NodeDeref(id), nodeExpr);
					}
				}
				nodeStm = new NodeAssing(id, nodeExpr);
				match(TokenType.SEMI);
				return nodeStm;
			}
			default -> {
				throw new SyntacticException("PRINT, ID", tk.getRiga(), tk.getTipo());
			}
		}
	}

	/**
	 * Regola della grammatica: Exp -> Tr ExpP
	 * 
	 * @return 						Un nodo espressione
	 * @throws IOException			Se c'è un errore nella lettura del file
	 * @throws LexicalException		Se lo scanner trova errori
	 * @throws SyntacticException	Se il parsing trova errori
	 */
	private NodeExpr parseExp() throws IOException, LexicalException, SyntacticException {
		Token tk = scanner.peekToken();
		switch (tk.getTipo()) {
			case ID, FLOAT, INT -> { // Exp -> Tr ExpP
				NodeExpr expr = parseTr();
				return parseExpP(expr);
			}
			default -> {
				throw new SyntacticException("ID, FLOAT, INT", tk.getRiga(), tk.getTipo());
			}
		}
	}

	/**
	 * Regole della grammatica: ExpP -> + Tr ExpP
	 * 							ExpP -> - Tr ExpP
	 * 							ExpP -> e
	 * 
	 * @param left					La parte sinistra dell'espressione
	 * @return 						Un nodo espressione
	 * @throws IOException			Se c'è un errore nella lettura del file
	 * @throws LexicalException		Se lo scanner trova errori
	 * @throws SyntacticException	Se il parsing trova errori
	 */
	private NodeExpr parseExpP(NodeExpr left) throws IOException, LexicalException, SyntacticException {
		Token tk = scanner.peekToken();
		NodeExpr exp1;
		NodeExpr exp2;
		switch (tk.getTipo()) {
			case PLUS -> { // ExpP -> + Tr ExpP
				match(TokenType.PLUS);
				exp1 = parseTr();
				exp2 = parseExpP(exp1);
				return new NodeBinOp(LangOper.PLUS, left, exp2);
			}
			case MINUS -> { // ExpP -> - Tr ExpP
				match(TokenType.MINUS);
				exp1 = parseTr();
				exp2 = parseExpP(exp1);
				return new NodeBinOp(LangOper.MINUS, left, exp2);
			}
			case SEMI -> { // ExpP -> e
				return left;
			}
			default -> {
				throw new SyntacticException("PLUS, MINUS", tk.getRiga(), tk.getTipo());
			}
		}
	}

	/**
	 * Regola della grammatica: Tr -> Val TrP
	 * 
	 * @return 						Un nodo espressione
	 * @throws IOException			Se c'è un errore nella lettura del file
	 * @throws LexicalException		Se lo scanner trova errori
	 * @throws SyntacticException	Se il parsing trova errori
	 */
	private NodeExpr parseTr() throws IOException, LexicalException, SyntacticException {
		Token tk = scanner.peekToken();
		switch (tk.getTipo()) {
			case ID, FLOAT, INT -> { // Tr -> Val TrP
				NodeExpr left = parseVal();
				return parseTrP(left);
			}
			default -> {
				throw new SyntacticException("ID, FLOAT, INT", tk.getRiga(), tk.getTipo());
			}
		}
	}

	/**
	 * Regole della grammatica: TrP -> * Val TrP
	 * 							TrP -> / Val TrP
	 * 							TrP -> e
	 * 
	 * @param left					La parte sinistra dell'espressione
	 * @return 						Un nodo espressione
	 * @throws IOException			Se c'è un errore nella lettura del file
	 * @throws LexicalException		Se lo scanner trova errori
	 * @throws SyntacticException	Se il parsing trova errori
	 */
	private NodeExpr parseTrP(NodeExpr left) throws IOException, LexicalException, SyntacticException {
		Token tk = scanner.peekToken();
		NodeExpr exp1;
		NodeExpr exp2;
		switch (tk.getTipo()) {
			case TIMES -> { // TrP -> * Val TrP
				match(TokenType.TIMES);
				exp1 = parseVal();
				exp2 = parseTrP(exp1);
				return new NodeBinOp(LangOper.TIMES, left, exp2);
			}
			case DIVIDE -> { // TrP -> / Val TrP
				match(TokenType.DIVIDE);
				exp1 = parseVal();
				exp2 = parseTrP(exp1);
				return new NodeBinOp(LangOper.DIVIDE, left, exp2);
			}
			case MINUS, PLUS, SEMI -> { // TrP -> e
				return left;
			}
			default -> {
				throw new SyntacticException("TIMES, DIVIDE, MINUS, PLUS, SEMI", tk.getRiga(), tk.getTipo());
			}
		}
	}

	/**
	 * Regole della grammatica: Val -> intVal
	 * 							Val -> floatVal
	 * 							Val -> id
	 * 
	 * @return 						Un nodo espressione
	 * @throws IOException			Se c'è un errore nella lettura del file
	 * @throws LexicalException		Se lo scanner trova errori
	 * @throws SyntacticException	Se il parsing trova errori
	 */
	private NodeExpr parseVal() throws IOException, LexicalException, SyntacticException {
		Token tk = scanner.peekToken();
		switch (tk.getTipo()) {
			case INT -> { // Val -> intVal
				return new NodeCost(match(TokenType.INT).getVal(), LangType.INTEGER);
			}
			case FLOAT -> { // Val -> floatVal
				return new NodeCost(match(TokenType.FLOAT).getVal(), LangType.FLOAT);
			}
			case ID -> { // Val -> id
				return new NodeDeref(new NodeId(match(TokenType.ID).getVal()));
			}
			default -> {
				throw new SyntacticException("INT, FLOAT, ID", tk.getRiga(), tk.getTipo());
			}
		}
	}
}
