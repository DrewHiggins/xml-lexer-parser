/**
 * @author James Higgins (jah6187)
 *
 * Token Class
 * ===========
 * Stores the value and type of a token
 */
public class Token {

    public enum TokenType { INT, FLOAT, ID, KEYWORD, OPERATOR, COMMA, EOI }

    private String value;
    private TokenType type;

    public Token(String value, TokenType type) {
        this.value = value;
        this.type = type;
    }

    public TokenType getTokenType() {
        return type;
    }

    public String getValue() {
        return value;
    }

    public String toString() {
        String tokenString = "";
        switch (type) {
            case COMMA:
                tokenString += "Comma(";
                break;
            case OPERATOR:
                tokenString += "Operator(";
                break;
            case KEYWORD:
                tokenString += "Keyword(";
                break;
            case ID:
                tokenString += "ID(";
                break;
            case FLOAT:
                tokenString += "Float(";
                break;
            case INT:
                tokenString += "Int(";
                break;
            case EOI:
                tokenString += "EOI(";
                break;
        }
        tokenString += value + ")";
        return tokenString;
    }

}
