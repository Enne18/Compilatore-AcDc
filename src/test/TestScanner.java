package test;

import static org.junit.jupiter.api.Assertions.*;

import scanner.Scanner;
import scanner.LexicalException;

import org.junit.jupiter.api.Test;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * @author Nicholas Ternullo 20045859
 *
 * Classe di test dello Scanner
 */
class TestScanner {

	@Test
	void testEOF() throws FileNotFoundException, IOException, LexicalException{
		Scanner s = new Scanner("src/test/data/testScanner/testEOF.txt");
		assertEquals("<EOF,r:3>", s.nextToken().toString());
	}

	@Test
	void testID() throws IOException, LexicalException {
		Scanner s = new Scanner("src/test/data/testScanner/testId.txt");
		assertEquals("<ID,r:1,jskjdsfhkjdshkf>", s.nextToken().toString());
		assertEquals("<ID,r:2,printl>", s.nextToken().toString());
		assertEquals("<ID,r:4,ffloat>", s.nextToken().toString());
		assertEquals("<ID,r:6,hhhjj>", s.nextToken().toString());
	}
	
	@Test
	void testOperators() throws IOException, LexicalException {
		Scanner s = new Scanner("src/test/data/testScanner/testOperators.txt");
		assertEquals("<PLUS,r:1>", s.nextToken().toString());
		assertEquals("<OP_ASSIGN,r:1,/=>", s.nextToken().toString());
		assertEquals("<MINUS,r:2>", s.nextToken().toString());
		assertEquals("<TIMES,r:2>", s.nextToken().toString());
		assertEquals("<DIVIDE,r:3>", s.nextToken().toString());
		assertEquals("<OP_ASSIGN,r:5,+=>", s.nextToken().toString());
		assertEquals("<OP_ASSIGN,r:6,=>", s.nextToken().toString());
		assertEquals("<OP_ASSIGN,r:6,-=>", s.nextToken().toString());
		assertEquals("<MINUS,r:8>", s.nextToken().toString());
		assertEquals("<OP_ASSIGN,r:8,=>", s.nextToken().toString());
		assertEquals("<OP_ASSIGN,r:8,*=>", s.nextToken().toString());
		assertEquals("<OP_ASSIGN,r:10,=>", s.nextToken().toString());
		assertEquals("<SEMI,r:10>", s.nextToken().toString());
	}
	
	@Test
	void testInt() throws IOException, LexicalException {
		Scanner s = new Scanner("src/test/data/testScanner/testINT.txt");
		assertEquals("<INT,r:2,698>", s.nextToken().toString());
		assertEquals("<INT,r:4,560099>", s.nextToken().toString());
		assertEquals("<INT,r:5,1234>", s.nextToken().toString());
	}
	
	@Test
    void testErroriNumbers() throws FileNotFoundException {
        Scanner s = new Scanner("src/test/data/testScanner/erroriNumbers.txt");
        LexicalException e = assertThrows(LexicalException.class, () -> { s.nextToken(); });
        assertEquals("Token: 00 - Riga: 1", e.getMessage());
        e = assertThrows(LexicalException.class, () -> { s.nextToken(); });
        assertEquals("Token: 123a - Riga: 2 - Carattere: a", e.getMessage());
        e = assertThrows(LexicalException.class, () -> { s.nextToken(); });
        assertEquals("Token: 12.a - Riga: 3 - Carattere: a", e.getMessage());
        e = assertThrows(LexicalException.class, () -> { s.nextToken(); });
        assertEquals("Token: 123.121212 - Riga: 4 - Carattere: 2", e.getMessage());
        e = assertThrows(LexicalException.class, () -> { s.nextToken(); });
        assertEquals("Token: 11.. - Riga: 4 - Carattere: .", e.getMessage());
        e = assertThrows(LexicalException.class, () -> { s.nextToken(); });
        assertEquals("Token: 22t - Riga: 6 - Carattere: t", e.getMessage());
    }
	
	@Test
    void testGenerale() throws IOException, LexicalException {
        Scanner s = new Scanner("src/test/data/testScanner/testGenerale.txt");
        assertEquals("<TYINT,r:1>", s.nextToken().toString());
        assertEquals("<ID,r:1,temp>", s.nextToken().toString());
        assertEquals("<SEMI,r:1>", s.nextToken().toString());
        assertEquals("<ID,r:2,temp>", s.nextToken().toString());
        assertEquals("<OP_ASSIGN,r:2,+=>", s.nextToken().toString());
        assertEquals("<FLOAT,r:2,5.>", s.nextToken().toString());
        assertEquals("<SEMI,r:2>", s.nextToken().toString());
        assertEquals("<TYFLOAT,r:4>", s.nextToken().toString());
        assertEquals("<ID,r:4,b>", s.nextToken().toString());
        assertEquals("<SEMI,r:4>", s.nextToken().toString());
        assertEquals("<ID,r:5,b>", s.nextToken().toString());
        assertEquals("<OP_ASSIGN,r:5,=>", s.nextToken().toString());
        assertEquals("<ID,r:5,temp>", s.nextToken().toString());
        assertEquals("<PLUS,r:5>", s.nextToken().toString());
        assertEquals("<FLOAT,r:5,3.2>", s.nextToken().toString());
        assertEquals("<SEMI,r:5>", s.nextToken().toString());
        assertEquals("<PRINT,r:6>", s.nextToken().toString());
        assertEquals("<ID,r:6,b>", s.nextToken().toString());
        assertEquals("<SEMI,r:6>", s.nextToken().toString());
        assertEquals("<EOF,r:7>", s.nextToken().toString());
    }
	
	@Test
	void testFloat() throws IOException, LexicalException {
		Scanner s = new Scanner("src/test/data/testScanner/testFLOAT.txt");
		assertEquals("<FLOAT,r:1,098.8095>", s.nextToken().toString());
		assertEquals("<FLOAT,r:2,0.>", s.nextToken().toString());
		assertEquals("<FLOAT,r:3,98.>", s.nextToken().toString());
		assertEquals("<FLOAT,r:5,89.99999>", s.nextToken().toString());
	}
	
	@Test
    void testIdKeyWords() throws IOException, LexicalException {
        Scanner s = new Scanner("src/test/data/testScanner/testIdKeyWords.txt");
        assertEquals("<TYINT,r:1>", s.nextToken().toString());
        assertEquals("<ID,r:1,inta>", s.nextToken().toString());
        assertEquals("<TYFLOAT,r:2>", s.nextToken().toString());
        assertEquals("<PRINT,r:3>", s.nextToken().toString());
        assertEquals("<ID,r:4,nome>", s.nextToken().toString());
        assertEquals("<ID,r:5,intnome>", s.nextToken().toString());
        assertEquals("<TYINT,r:6>", s.nextToken().toString());
        assertEquals("<ID,r:6,nome>", s.nextToken().toString());
        assertEquals("<EOF,r:6>", s.nextToken().toString());
    }
	
	@Test
    void testKeywords() throws IOException, LexicalException {
        Scanner s = new Scanner("src/test/data/testScanner/testKeywords.txt");
        assertEquals("<PRINT,r:2>", s.nextToken().toString());
        assertEquals("<TYFLOAT,r:2>", s.nextToken().toString());
        assertEquals("<TYINT,r:5>", s.nextToken().toString());
    }
	
	@Test
    void testErroriID() throws IOException, LexicalException {
        Scanner s = new Scanner("src/test/data/testScanner/erroriID.txt");
        s.nextToken();
        s.nextToken();

        LexicalException ex = assertThrows(LexicalException.class, s::nextToken);
        assertEquals("Token: nome2 - Riga: 3 - Carattere: 2", ex.getMessage());

        s.nextToken();

        ex = assertThrows(LexicalException.class, s::nextToken);
        assertEquals("Token: v1 - Riga: 5 - Carattere: 1", ex.getMessage());

        assertEquals("<ID,r:5,r>",s.nextToken().toString());
    }
	
	@Test
	void textPeekToken() throws IOException, LexicalException {
		Scanner s = new Scanner ("src/test/data/testScanner/testFLOAT.txt");
		assertEquals("<FLOAT,r:1,098.8095>", s.peekToken().toString());
	    assertEquals("<FLOAT,r:1,098.8095>", s.nextToken().toString());
	    assertEquals("<FLOAT,r:2,0.>", s.nextToken().toString());
	    assertEquals("<FLOAT,r:3,98.>", s.nextToken().toString());
	    assertEquals("<FLOAT,r:5,89.99999>", s.nextToken().toString());
	    assertEquals("<FLOAT,r:6,00.>", s.peekToken().toString());
	    assertEquals("<FLOAT,r:6,00.>", s.nextToken().toString());
	    assertEquals("<EOF,r:7>", s.peekToken().toString());
	    assertEquals("<EOF,r:7>", s.nextToken().toString());
	}
	
	
}
