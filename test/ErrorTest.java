/**
 * Created by drewhiggins on 1/31/17.
 */
public class ErrorTest {

    public static void main(String args[]) {
        Parser p = new Parser("SELECT x, y FROM z,a WHERE x > 1.3");
        p.run();
    }

}
