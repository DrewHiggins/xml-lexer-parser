import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Created by drewhiggins on 1/28/17.
 */
class TokenTest {
    @Test
    void testTokens() {
        Token testInt = new Token("5", Token.TokenType.INT);
        Token testFloat = new Token("3.14", Token.TokenType.FLOAT);
        String testIntString = "Int(5)";
        String testFloatString = "Float(3.14)";
        assertEquals(testIntString, testInt.toString());
        assertEquals(testFloatString, testFloat.toString());
    }
}