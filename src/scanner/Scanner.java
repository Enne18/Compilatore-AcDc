package scanner;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PushbackReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import token.Token;
import token.TokenType;

/**
 * @author Nicholas Ternullo 20045859
 *
 * Classe che rappresenta uno scanner che legge i caratteri da un file e ne ritorna i token
 * Analisi lessicale
 */
public class Scanner {
	
	/**
	 * Carattere EOF
	 */
	final char EOF = (char) -1;
	
	/**
	 * Numero della riga
	 */
	private int riga;
	
	/**
	 * Oggetto che permettere di leggere (o annullare la lettura) di un carattere dal file
	 */
	private PushbackReader buffer;
	
	/**
	 * Token letto
	 */
	private Token nextTk;

	/**
	 * Insieme caratteri di skip (include EOF) e inizializzazione
	 */
	final ArrayList<Character> skipChars = new ArrayList<Character>(Arrays.asList(' ', '\n', '\t', '\r', EOF));

	/**
	 * Insieme lettere e inizializzazione
	 */
	final ArrayList<Character> letters = new ArrayList<Character>(Arrays.asList('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h',
			'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'));

	
//	final ArrayList<Character> digits = new ArrayList<Character>(
//			Arrays.asList('1', '2', '3', '4', '5', '6', '7', '8', '9'));
	
	
	/**
	 * Insieme cifre e inizializzazione
	 */
	final ArrayList<Character> digits0 = new ArrayList<Character>(
			Arrays.asList('0', '1', '2', '3', '4', '5', '6', '7', '8', '9'));

	/**
	 * mapping fra caratteri '+', '-', '*', '/', ';', '=', ';' e il TokenType corrispondente
	 */
	final static Map<Character, TokenType> charTypeMap = new HashMap<>();

	static {
	    charTypeMap.put('+', TokenType.PLUS);
	    charTypeMap.put('-', TokenType.MINUS);
	    charTypeMap.put('*', TokenType.TIMES);
	    charTypeMap.put('/', TokenType.DIVIDE);
	    charTypeMap.put('=', TokenType.OP_ASSIGN);
	    charTypeMap.put(';', TokenType.SEMI);
	}

	/**
	 * mapping fra le stringhe "print", "float", "int" e il TokenType corrispondente
	 */
	Map<String, TokenType> keyWordsMap = new HashMap<>();

	{
	    keyWordsMap.put("print", TokenType.PRINT);
	    keyWordsMap.put("float", TokenType.TYFLOAT);
	    keyWordsMap.put("int", TokenType.TYINT);
	}


	/**
	 * Costruisce lo scanner e ne inizializza i parametri
	 * 
	 * @param fileName					Il file da leggere
	 * @throws FileNotFoundException	Errore nell'apertura del file
	 */
	public Scanner(String fileName) throws FileNotFoundException {

		this.buffer = new PushbackReader(new FileReader(fileName));
		riga = 1;
		// inizializzare campi che non hanno inizializzazione
	}

	/**
	 * Scorre il file e associa un token per ogni stringa del file
	 * Se il token ritornato è un Token lo ritorna
	 * Consuma il token
	 * 
	 * @return						Il token
	 * @throws IOException			Se c'è un errore nella lettura del file
	 * @throws LexicalException		Se la stringa non è riconosciuta
	 */
	public Token nextToken() throws IOException, LexicalException {

		char nextChar; // nextChar contiene il prossimo carattere dell'input (non consumato)

		if (nextTk != null) {
			Token temp = nextTk;
			nextTk = null;
			return temp;
		}

		try {
			nextChar = peekChar();
		} catch (IOException e) { // Catturate l'eccezione IOException e
			throw new LexicalException("Errore alla riga: " + riga, e); // ritornate una LexicalException che la contiene
		}

		// Avanza nel buffer leggendo i carattere in skipChars
		// incrementando riga se leggi '\n'.
		// Se raggiungi la fine del file ritorna il Token EOF

		while (skipChars.contains(nextChar)) {
			readChar();
			if (nextChar == '\n') {
				riga++;
			}
			if (nextChar == EOF) {
				return new Token(TokenType.EOF, riga);
			}
			nextChar = peekChar();
		}

		// Se nextChar e' in letters
		// return scanId()

		if (letters.contains(nextChar)) {
			return scanId();
		}

		// che legge tutte le lettere minuscole e ritorna un Token ID o
		// il Token associato Parola Chiave (per generare i Token per le
		// parole chiave usate l'HashMap di corrispondenza

		// Se nextChar e' o in operators oppure
		// ritorna il Token associato con l'operatore o il delimitatore

		if (charTypeMap.containsKey(nextChar)) {
			return scanOperator();
		}

		// Se nextChar e' in numbers
		// return scanNumber()
		// che legge sia un intero che un float e ritorna il Token INUM o FNUM
		// i caratteri che leggete devono essere accumulati in una stringa
		// che verra' assegnata al campo valore del Token

		if (digits0.contains(nextChar)) {
			return scanNumber();
		}

		// Altrimenti il carattere NON E' UN CARATTERE LEGALE sollevate una
		// eccezione lessicale dicendo la riga e il carattere che la hanno
		// provocata.

		throw new LexicalException(String.valueOf(nextChar), riga, readChar());
	}

	/**
	 * Scorre il file
	 * 
	 * @return						Token del numero (INT o il Token FLOAT dalla funzione scanDecimal())
	 * @throws IOException			Se c'è un errore nella lettura del file
	 * @throws LexicalException		Se la stringa non è riconosciuta
	 */
	private Token scanNumber() throws IOException, LexicalException {
		StringBuilder stringa = new StringBuilder();
		char nextChar = peekChar();

		if(nextChar == '0') {
			stringa.append(readChar());
			nextChar = peekChar();
			if(notExist(nextChar) || !digits0.contains(nextChar) && nextChar != '.') {
				return new Token(TokenType.INT, riga, stringa.toString());
			}
				
			while(digits0.contains(nextChar)) {
				stringa.append(readChar());
				nextChar = peekChar();
			}
			
			if(nextChar != '.') {
				if(letters.contains(nextChar)) {
					stringa.append(nextChar);
					throw new LexicalException(stringa.toString(), riga, readChar());
				}
				throw new LexicalException(stringa.toString(), riga);
			}
		}
		else {
			while(digits0.contains(nextChar)) {
				stringa.append(readChar());
				nextChar = peekChar();
			}
			if(letters.contains(nextChar)) {
				stringa.append(nextChar);
				throw new LexicalException(stringa.toString(), riga, readChar());
			}
			if(nextChar != '.') {
				return new Token(TokenType.INT, riga, stringa.toString());
			}
		}
		return scanDecimal(stringa);
	}
	
	/**
	 * Scorre il file
	 * 
	 * @param stringa				Stringa con il valore letto
	 * @return						Token del numero (FLOAT)
	 * @throws IOException			Se c'è un errore nella lettura del file
	 * @throws LexicalException		Se la stringa non è riconosciuta
	 */
	private Token scanDecimal(StringBuilder stringa) throws IOException, LexicalException {
		stringa.append(readChar());
		char nextChar = peekChar();
		int decimal = 0;
		char error = ' ';
		
		while(digits0.contains(nextChar)) {
			decimal++;
			if(decimal == 6) {
				error = nextChar;
			}
			stringa.append(readChar());
			nextChar = peekChar();
		}
		
		if(decimal >= 6) {
			throw new LexicalException(stringa.toString(), riga, error);
		}
		
		if(letters.contains(nextChar) || nextChar == '.') {
			stringa.append(nextChar);
			throw new LexicalException(stringa.toString(), riga, readChar());
		}
		
		return new Token(TokenType.FLOAT, riga, stringa.toString());
	}

	/**
	 * Scorre il file
	 * 
	 * @return						Token Id
	 * @throws IOException			Se c'è un errore nella lettura del file
	 * @throws LexicalException		Se la stringa non è riconosciuta
	 */
	private Token scanId() throws IOException, LexicalException {
		StringBuilder stringa = new StringBuilder();
		char nextChar = peekChar();

		while (letters.contains(nextChar)) {
			stringa.append(readChar());
			nextChar = peekChar();
		}
		if(digits0.contains(nextChar)) {
			stringa.append(readChar());
			throw new LexicalException(stringa.toString(), riga, nextChar);
		}
		if (keyWordsMap.containsKey(stringa.toString())) {
			return new Token(keyWordsMap.get(stringa.toString()), riga);
		}
		else return new Token(TokenType.ID, riga, stringa.toString());
	}
	
	/**
	 * Scorre il file
	 * 
	 * @return					Token di un operatore presa da charTypeMap
	 * @throws IOException		Se c'è un errore nella lettura del file
	 */
	private Token scanOperator() throws IOException {
		char nextChar = peekChar();
		char operatore;

		if(nextChar == '=')	{
			readChar();
			return new Token(TokenType.OP_ASSIGN, riga, "=");
		}
		if(nextChar == ';'){
			readChar();
			return new Token(TokenType.SEMI, riga);
		}
		
		operatore = readChar();
		
		if(peekChar() == '='){
			return new Token(TokenType.OP_ASSIGN, riga, operatore + String.valueOf(readChar()));
		}

		return new Token(charTypeMap.get(operatore), riga);
	}

	/**
	 * Legge e consuma un carattere dal file e lo ritorna
	 * 
	 * @return					Il carattere letto dal file
	 * @throws IOException		Se c'è un errore nella lettura del file
	 */
	private char readChar() throws IOException {
		return ((char) this.buffer.read());
	}

	/**
	 * Legge ma non consuma un carattere dal file e lo ritorna
	 * 
	 * @return					Il carattere letto dal file
	 * @throws IOException		Se c'è un errore nella lettura del file
	 */
	private char peekChar() throws IOException {
		char c = (char) buffer.read();
		buffer.unread(c);
		return c;
	}

	/**
	 * @param carattere Carattere da valutare
	 * @return			True se non appartiene al linguaggio, altrimenti False
	 */
	private boolean notExist(char carattere) {
		return !letters.contains(carattere) && !digits0.contains(carattere) && !charTypeMap.containsKey(carattere) && !skipChars.contains(carattere) && carattere != '.';
	}
	
	/**
	 * Ritorna il Token successivo
	 * 
	 * @return						Il Token successivo
	 * @throws LexicalException		Se la stringa non è riconosciuta
	 */
	public Token peekToken() throws LexicalException {
		try {
			if(nextTk == null) {
				nextTk = nextToken();
			}
		} catch (IOException e) {
			throw new LexicalException("Errore alla rga: " + riga, e);
		}
		return nextTk;
	}
}
