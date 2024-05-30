package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import token.Token;
import token.TokenType;

/**
 * @author Nicholas Ternullo 20045859
 *
 * Classe di test dei Token
 */
class TestToken {

	@Test
	void testToken() {
		Token T1 = new Token(TokenType.INT, 4, "tempa");
		assertEquals(T1.toString(), "<INT,r:4,tempa>");
		assertEquals(TokenType.INT, T1.getTipo());
		assertEquals(4, T1.getRiga());
		assertEquals("tempa", T1.getVal());
		
	}

}
