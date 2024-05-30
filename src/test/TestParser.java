package test;

import static org.junit.jupiter.api.Assertions.*;

import scanner.Scanner;
import parser.Parser;
import parser.SyntacticException;
import scanner.LexicalException;

import org.junit.jupiter.api.Test;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * @author Nicholas Ternullo 20045859
 *
 * Classe di test del Parser
 */
class TestParser {

	@Test
	void TestParser0() throws FileNotFoundException {
		Scanner s = new Scanner("src/test/data/testParser/testParserCorretto0.txt");
		Parser p = new Parser(s);
		assertDoesNotThrow(() -> p.parse());
	}
	
	@Test
	void TestParser1() throws FileNotFoundException {
		Scanner s = new Scanner("src/test/data/testParser/testParserCorretto1.txt");
		Parser p = new Parser(s);
		assertDoesNotThrow(() -> p.parse());
	}
	
	@Test
	void TestParser2() throws IOException, LexicalException, SyntacticException {
		Scanner s = new Scanner("src/test/data/testParser/testParserCorretto2.txt");
		Parser p = new Parser(s);
		assertDoesNotThrow(() -> p.parse());
	}
	
	@Test
	void TestParser3() throws IOException, LexicalException, SyntacticException {
		Scanner s = new Scanner("src/test/data/testParser/testParserCorretto3.txt");
		Parser p = new Parser(s);
		assertDoesNotThrow(() -> p.parse());
	}
	
	@Test
	void TestParserDich() throws FileNotFoundException {
		Scanner s = new Scanner("src/test/data/testParser/testSoloDichPrint1.txt");
		Parser p = new Parser(s);
		assertDoesNotThrow(() -> p.parse());
	}
	
	@Test
	void TestParserEcc0() throws FileNotFoundException {
		Scanner s = new Scanner("src/test/data/testParser/testParserEcc_0.txt");
		Parser p = new Parser(s);
		SyntacticException ecc = assertThrows(SyntacticException.class, () -> { p.parse(); });
		assertEquals("Tipo atteso: OP_ASSIGN - Riga: 1 - Tipo trovato: SEMI", ecc.getMessage());
	}
	
	@Test
	void TestParserEcc1() throws FileNotFoundException {
		Scanner s = new Scanner("src/test/data/testParser/testParserEcc_1.txt");
		Parser p = new Parser(s);
		SyntacticException ecc = assertThrows(SyntacticException.class, () -> { p.parse(); });
		assertEquals("Tipo atteso: ID, FLOAT, INT - Riga: 2 - Tipo trovato: TIMES", ecc.getMessage());
	}
	
	@Test
	void TestParserEcc2() throws FileNotFoundException {
		Scanner s = new Scanner("src/test/data/testParser/testParserEcc_2.txt");
		Parser p = new Parser(s);
		SyntacticException ecc = assertThrows(SyntacticException.class, () -> { p.parse(); });
		assertEquals("Tipo atteso: TYINT, TYFLOAT, ID, PRINT, EOF - Riga: 3 - Tipo trovato: INT", ecc.getMessage());
	}
	
	@Test
	void TestParserEcc3() throws FileNotFoundException {
		Scanner s = new Scanner("src/test/data/testParser/testParserEcc_3.txt");
		Parser p = new Parser(s);
		SyntacticException ecc = assertThrows(SyntacticException.class, () -> { p.parse(); });
		assertEquals("Tipo atteso: OP_ASSIGN - Riga: 2 - Tipo trovato: PLUS", ecc.getMessage());
	}
	
	@Test
	void TestParserEcc4() throws FileNotFoundException {
		Scanner s = new Scanner("src/test/data/testParser/testParserEcc_4.txt");
		Parser p = new Parser(s);
		SyntacticException ecc = assertThrows(SyntacticException.class, () -> { p.parse(); });
		assertEquals("Tipo atteso: ID - Riga: 2 - Tipo trovato: INT", ecc.getMessage());
	}
	
	@Test
	void TestParserEcc5() throws FileNotFoundException {
		Scanner s = new Scanner("src/test/data/testParser/testParserEcc_5.txt");
		Parser p = new Parser(s);
		SyntacticException ecc = assertThrows(SyntacticException.class, () -> { p.parse(); });
		assertEquals("Tipo atteso: ID - Riga: 3 - Tipo trovato: INT", ecc.getMessage());
	}
	
	@Test
	void TestParserEcc6() throws FileNotFoundException {
		Scanner s = new Scanner("src/test/data/testParser/testParserEcc_6.txt");
		Parser p = new Parser(s);
		SyntacticException ecc = assertThrows(SyntacticException.class, () -> { p.parse(); });
		assertEquals("Tipo atteso: ID - Riga: 4 - Tipo trovato: TYFLOAT", ecc.getMessage());
	}
	
	@Test
	void TestParserEcc7() throws FileNotFoundException {
		Scanner s = new Scanner("src/test/data/testParser/testParserEcc_7.txt");
		Parser p = new Parser(s);
		SyntacticException ecc = assertThrows(SyntacticException.class, () -> { p.parse(); });
		assertEquals("Tipo atteso: ID - Riga: 2 - Tipo trovato: OP_ASSIGN", ecc.getMessage());
	}
	
	@Test
	void TestParserEcc8() throws FileNotFoundException {
		Scanner s = new Scanner("src/test/data/testParser/testParserEcc_8.txt");
		Parser p = new Parser(s);
		SyntacticException ecc = assertThrows(SyntacticException.class, () -> { p.parse(); });
		assertEquals("Tipo atteso: OP_ASSIGN - Riga: 1 - Tipo trovato: PLUS", ecc.getMessage());
	}
	
	@Test
	void TestParserEcc9() throws FileNotFoundException {
		Scanner s = new Scanner("src/test/data/testParser/testParserEcc_9.txt");
		Parser p = new Parser(s);
		SyntacticException ecc = assertThrows(SyntacticException.class, () -> { p.parse(); });
		assertEquals("Token: 4w - Riga: 1 - Carattere: w", ecc.getMessage());
	}

}
