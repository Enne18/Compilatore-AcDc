package test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;

import org.junit.jupiter.api.Test;

import ast.NodeProgram;
import parser.Parser;
import parser.SyntacticException;
import scanner.LexicalException;
import scanner.Scanner;
import visitor.CodeGenerationVisitor;
import visitor.TypeCheckinVisitor;

/**
 * @author Nicholas Ternullo 20045859
 *
 * Classe di test di generazione del codice
 */
class TestCodeGenerator {

	@Test
	void testPrint() throws FileNotFoundException, IOException, LexicalException, SyntacticException {
		NodeProgram nP = new Parser(new Scanner("src/test/data/testCodeGenerator/1_print.txt")).parse();
		TypeCheckinVisitor tcVisit = new TypeCheckinVisitor();
		nP.accept(tcVisit);
		CodeGenerationVisitor cgVisit = new CodeGenerationVisitor();
		nP.accept(cgVisit);
		Iterator<String> sCg = cgVisit.getIteratore();
		
		assertEquals("10 sa", sCg.next());
		assertEquals("la p P", sCg.next());
	}
	
	@Test
	void testGenerale() throws FileNotFoundException, IOException, LexicalException, SyntacticException {
		NodeProgram nP = new Parser(new Scanner("src/test/data/testCodeGenerator/2_generale.txt")).parse();
		TypeCheckinVisitor tcVisit = new TypeCheckinVisitor();
		nP.accept(tcVisit);
		CodeGenerationVisitor cgVisit = new CodeGenerationVisitor();
		nP.accept(cgVisit);
		Iterator<String> sCg = cgVisit.getIteratore();
		
		assertEquals("5 sa", sCg.next());
		assertEquals("0.9 sb", sCg.next());
		assertEquals("5 k la lb TIMES sb 0 k", sCg.next());
		assertEquals("lb p P", sCg.next());
	}
	
	@Test
	void testRegistriPieni() throws FileNotFoundException, IOException, LexicalException, SyntacticException {
		NodeProgram nP = new Parser(new Scanner("src/test/data/testCodeGenerator/3_registriPieni.txt")).parse();
		TypeCheckinVisitor tcVisit = new TypeCheckinVisitor();
		nP.accept(tcVisit);
		CodeGenerationVisitor cgVisit = new CodeGenerationVisitor();
		nP.accept(cgVisit);
		
		assertEquals("Registri insufficienti", cgVisit.getLog());
	}
	
	@Test
	void testConversioni() throws FileNotFoundException, IOException, LexicalException, SyntacticException {
		NodeProgram nP = new Parser(new Scanner("src/test/data/testCodeGenerator/4_conversioni.txt")).parse();
		TypeCheckinVisitor tcVisit = new TypeCheckinVisitor();
		nP.accept(tcVisit);
		CodeGenerationVisitor cgVisit = new CodeGenerationVisitor();
		nP.accept(cgVisit);
		Iterator<String> sCg = cgVisit.getIteratore();

		assertEquals("5 k 50 5. DIVIDE sa 0 k", sCg.next());
		assertEquals("4 20 MINUS sa", sCg.next());
	}
}
