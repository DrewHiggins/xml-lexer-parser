import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Created by drewhiggins on 1/28/17.
 */
class LexerTest {

    @Test
    void testIdentifiers() {
        String testString = "SELECT x FROM y";
        Lexer testLexer = new Lexer(testString);
        assertEquals("Keyword(SELECT)", testLexer.nextToken().toString());
        assertEquals("ID(x)", testLexer.nextToken().toString());
        assertEquals("Keyword(FROM)", testLexer.nextToken().toString());
        assertEquals("ID(y)", testLexer.nextToken().toString());
        assertEquals("EOI(EOI)", testLexer.nextToken().toString());
    }

    @Test
    void testNumbers() {
        String testString = "1 5 5.0 3.14";
        Lexer testLexer = new Lexer(testString);
        assertEquals("Int(1)", testLexer.nextToken().toString());
        assertEquals("Int(5)", testLexer.nextToken().toString());
        assertEquals("Float(5.0)", testLexer.nextToken().toString());
        assertEquals("Float(3.14)", testLexer.nextToken().toString());
    }

    @Test
    void testOperators() {
        String testString = "5 > 4.0, 1=1";
        Lexer testLexer = new Lexer(testString);
        assertEquals("Int(5)", testLexer.nextToken().toString());
        assertEquals("Operator(>)", testLexer.nextToken().toString());
        assertEquals("Float(4.0)", testLexer.nextToken().toString());
        assertEquals("Comma(,)", testLexer.nextToken().toString());
        assertEquals("Int(1)", testLexer.nextToken().toString());
        assertEquals("Operator(=)", testLexer.nextToken().toString());
        assertEquals("Int(1)", testLexer.nextToken().toString());
        assertEquals("EOI(EOI)", testLexer.nextToken().toString());
    }

}