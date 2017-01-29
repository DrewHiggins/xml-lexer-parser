import org.junit.jupiter.api.Test;

/**
 * Created by drewhiggins on 1/29/17.
 */
class ParserTest {

    @Test
    void testParsing() {
        String testInput = "SELECT x,y FROM z WHERE x = 1.0";
        Parser parser = new Parser(testInput);
        parser.run();
        System.out.println("===================");
        parser = new Parser("SELECT C1,C2 FROM T1 WHERE C1=5.23");
        parser.run();
    }

}