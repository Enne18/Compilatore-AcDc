package test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.jupiter.api.Test;

import ast.*;
import parser.Parser;
import parser.SyntacticException;
import scanner.LexicalException;
import scanner.Scanner;
import visitor.TypeCheckinVisitor;

/**
 * @author Nicholas Ternullo 20045859
 *
 * Classe di test del TypeChecking
 */
class TestTypeCheckin {

	@Test
	void testDicRipetute() throws FileNotFoundException, IOException, LexicalException, SyntacticException {
		NodeProgram nP = new Parser(new Scanner("src/test/data/testTypeChecking/1_dicRipetute.txt")).parse();
		TypeCheckinVisitor tcVisit = new TypeCheckinVisitor();
		nP.accept(tcVisit);
		assertEquals(TipoTD.ERROR, tcVisit.getResType().getTipo());
		assertEquals("Riga: 3 ERRORE - Variabile già dichiarata.", tcVisit.getResType().getMsg());
	}
	
	@Test
	void testIdNonDec() throws FileNotFoundException, IOException, LexicalException, SyntacticException {
		NodeProgram nP = new Parser(new Scanner("src/test/data/testTypeChecking/2_idNonDec.txt")).parse();
		TypeCheckinVisitor tcVisit = new TypeCheckinVisitor();
		nP.accept(tcVisit);
		assertEquals(TipoTD.ERROR, tcVisit.getResType().getTipo());
		assertEquals("Riga: 5 b non dichiarato", tcVisit.getResType().getMsg());
	}
	
	@Test
	void testIdNonDec2() throws FileNotFoundException, IOException, LexicalException, SyntacticException {
		NodeProgram nP = new Parser(new Scanner("src/test/data/testTypeChecking/3_idNonDec.txt")).parse();
		TypeCheckinVisitor tcVisit = new TypeCheckinVisitor();
		nP.accept(tcVisit);
		assertEquals(TipoTD.ERROR, tcVisit.getResType().getTipo());
		assertEquals("Riga: 6 c non dichiarato", tcVisit.getResType().getMsg());
	}
	
	@Test
	void testTipoNonCompatibile() throws FileNotFoundException, IOException, LexicalException, SyntacticException {
		NodeProgram nP = new Parser(new Scanner("src/test/data/testTypeChecking/4_tipoNonCompatibile.txt")).parse();
		TypeCheckinVisitor tcVisit = new TypeCheckinVisitor();
		nP.accept(tcVisit);
		assertEquals(TipoTD.ERROR, tcVisit.getResType().getTipo());
		assertEquals("Riga: 1 Impossibile assegnare valore FLOAT ad ID INT", tcVisit.getResType().getMsg());
	}
	
	@Test
	void testCorretto1() throws FileNotFoundException, IOException, LexicalException, SyntacticException {
		NodeProgram nP = new Parser(new Scanner("src/test/data/testTypeChecking/5_corretto.txt")).parse();
		TypeCheckinVisitor tcVisit = new TypeCheckinVisitor();
		nP.accept(tcVisit);
		
		//var res = tcVisit.getRiga();
		assertEquals(TipoTD.OK, tcVisit.getResType().getTipo());
	}
	
	@Test
	void testCorretto2() throws FileNotFoundException, IOException, LexicalException, SyntacticException {
		NodeProgram nP = new Parser(new Scanner("src/test/data/testTypeChecking/6_corretto.txt")).parse();
		TypeCheckinVisitor tcVisit = new TypeCheckinVisitor();
		nP.accept(tcVisit);
		
		assertEquals(TipoTD.OK, tcVisit.getResType().getTipo());
	}
	
	@Test
	void testCorretto3() throws FileNotFoundException, IOException, LexicalException, SyntacticException {
		NodeProgram nP = new Parser(new Scanner("src/test/data/testTypeChecking/7_corretto.txt")).parse();
		TypeCheckinVisitor tcVisit = new TypeCheckinVisitor();
		nP.accept(tcVisit);
		
		assertEquals(TipoTD.OK, tcVisit.getResType().getTipo());
	}
	
}
