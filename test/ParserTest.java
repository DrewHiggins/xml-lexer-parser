import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Created by drewhiggins on 1/29/17.
 */
class ParserTest {

    /**
     * This is test case in the PDF provided on Canvas
     */
    @Test
    void testFromPDF() {
        String expectedString = "<Query>\n\t<Keyword>SELECT</Keyword>\n\t<IdList>\n\t\t<Id>C1</Id>\n\t\t<Comma>,</Comma>\n\t\t<Id>C2</Id>\n\t</IdList>\n\t<Keyword>FROM</Keyword>\n\t<IdList>\n\t\t<Id>T1</Id>\n\t</IdList>\n\t<Keyword>WHERE</Keyword>\n\t<CondList>\n\t\t<Cond>\n\t\t\t<Id>C1</Id>\n\t\t\t<Operator>=</Operator>\n\t\t\t<Term>\n\t\t\t\t<Float>5.23</Float>\n\t\t\t</Term>\n\t\t</Cond>\n\t</CondList>\n</Query>\n";
        ByteArrayOutputStream boas = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(boas);
        PrintStream oldOut = System.out;
        System.setOut(ps);
        Parser parser = new Parser("SELECT C1,C2 FROM T1 WHERE C1=5.23");
        parser.run();
        System.out.flush();
        System.setOut(oldOut);
        assertEquals(expectedString, boas.toString());
    }

}