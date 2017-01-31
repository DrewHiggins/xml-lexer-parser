/**
 * @author James Higgins (jah6187)
 *
 * Parser Class
 */
public class Parser {

    private Lexer lexer;
    private Token token;

    public Parser(String s) {
        this.lexer = new Lexer(s);
        this.token = lexer.nextToken();
    }

    /**
     * Runs the parser on the constructed input
     */
    public void run() {
        parseQuery();
    }

    public void parseQuery() {
        System.out.println("<Query>");
        if (token.getTokenType() == Token.TokenType.KEYWORD && token.getValue().equals("SELECT")) {
            System.out.println("\t<Keyword>SELECT</Keyword>");
            token = lexer.nextToken();
            parseIdList();
        } else {
            throw new Error("Expected token Keyword(SELECT), got: " + token.toString());
        }

        if (token.getTokenType() == Token.TokenType.KEYWORD && token.getValue().equals("FROM")) {
            System.out.println("\t<Keyword>FROM</Keyword>");
            token = lexer.nextToken();
            parseIdList();
        } else {
            throw new Error("Expected token Keyword(FROM), got: " + token.toString());
        }

        if (token.getTokenType() == Token.TokenType.KEYWORD && token.getValue().equals("WHERE")) {
            System.out.println("\t<Keyword>WHERE</Keyword>");
            token = lexer.nextToken();
            parseCondList();
        } else if (token.getTokenType() != Token.TokenType.EOI) {
            System.err.println("Error! Expecting EOI or Keyword(WHERE), got: " + token.toString());
            System.exit(1);
        }
        System.out.println("</Query>");
    }

    public void parseIdList() {
        System.out.println("\t<IdList>");
        if (token.getTokenType() != Token.TokenType.ID) {
            System.err.println("Error! Expected ID, got: " + token.toString());
            System.exit(1);
        }
        while (token.getTokenType() == Token.TokenType.ID) {
            System.out.println("\t\t<Id>" + token.getValue() + "</Id>");
            token = lexer.nextToken();
            if (token.getTokenType() == Token.TokenType.COMMA) {
                System.out.println("\t\t<Comma>,</Comma>");
                token = lexer.nextToken();
            } else if (token.getTokenType() != Token.TokenType.KEYWORD){
                System.err.println("Error! Expected comma, got: "  + token.toString());
                System.exit(1);
            }
        }
        System.out.println("\t</IdList>");
    }

    public void parseCondList() {
        System.out.println("\t<CondList>");
        while (token.getTokenType() == Token.TokenType.ID) {
            parseCond();
            if (token.getTokenType() == Token.TokenType.KEYWORD && token.getValue() == "AND") {
                System.out.print("\t\t<Keyword>AND</Keyword>");
                token = lexer.nextToken();
            }
        }
        System.out.println("\t</CondList>");
    }

    public void parseCond() {
        System.out.println("\t\t<Cond>");
        if (token.getTokenType() == Token.TokenType.ID) {
            System.out.println("\t\t\t<Id>" + token.getValue() + "</Id>");
            token = lexer.nextToken();
        } else {
            System.err.println("Error! Expected ID, got: " + token.toString());
            System.exit(1);
        }

        if (token.getTokenType() == Token.TokenType.OPERATOR) {
            System.out.println("\t\t\t<Operator>" + token.getValue() + "</Operator>");
            token = lexer.nextToken();
        } else {
            System.err.println("Error! Expected OPERATOR, got: " + token.toString());
            System.exit(1);
        }

        parseTerm();
        System.out.println("\t\t</Cond>");
    }

    public void parseTerm() {
        System.out.println("\t\t\t<Term>");
        switch (token.getTokenType().toString()) {
            case "ID":
                System.out.println("\t\t\t\t<Id>" + token.getValue() + "</Id>");
                break;
            case "INT":
                System.out.println("\t\t\t\t<Int>" + token.getValue() + "</Int>");
                break;
            case "FLOAT":
                System.out.println("\t\t\t\t<Float>" + token.getValue() + "</Float>");
                break;
            default:
                System.err.println("Error! Expected type ID, FLOAT, or INT, got: " + token.toString());
                System.exit(1);
        }
        token = lexer.nextToken();
        System.out.println("\t\t\t</Term>");
    }

}
