import java.util.Arrays;

/**
 * @author James Higgins (jah6187)
 *
 * Lexer
 */

public class Lexer {

    // Reserved keywords, array for reference to check if keyword or ID
    private String[] keywords = {"SELECT", "FROM", "WHERE", "AND"};

    private String input;
    private char currentChar;
    private int index;

    public Lexer(String input) {
        this.input = input + "$"; // add in '$' to indicate EOI
        this.index = 0;
        this.currentChar = nextChar();
    }

    public Token nextToken() {
        do {
            if (Character.isLetter(currentChar)) {
                // if it is a letter, build an identifier and check if it's a keyword
                String id = getIdSequence();
                if (Arrays.asList(keywords).contains(id)) {
                    return new Token(id, Token.TokenType.KEYWORD);
                }
                return new Token(id, Token.TokenType.ID);
            } else if (Character.isDigit(currentChar)) {
                String number = getNumberSequence();
                if (currentChar == '.') { // it's a float
                    number += '.';
                    currentChar = nextChar();
                    number += getNumberSequence();
                    return new Token(number, Token.TokenType.FLOAT);
                } else {
                    return new Token(number, Token.TokenType.INT);
                }
            } else {
                switch (currentChar) {
                    case '$':
                        return new Token("EOI", Token.TokenType.EOI);
                    case ',':
                        currentChar = nextChar();
                        movePastSpace();
                        return new Token(",", Token.TokenType.COMMA);
                    case '>':
                        currentChar = nextChar();
                        movePastSpace();
                        return new Token(">", Token.TokenType.OPERATOR);
                    case '<':
                        currentChar = nextChar();
                        movePastSpace();
                        return new Token("<", Token.TokenType.OPERATOR);
                    case '=':
                        currentChar = nextChar();
                        movePastSpace();
                        return new Token("=", Token.TokenType.OPERATOR);
                    default:
                        System.err.println("Error! Illegal token encountered: '" + currentChar + "'");
                        System.exit(1);
                }
            }
        } while (true);
    }

    /**
     * Walks through the current input assuming it's current reading a keyword or ID.
     * Stops when it reaches the end of a keyword or ID.
     * @return Keyword or ID
     */
    public String getIdSequence() {
        String id = "";
        while (Character.isLetter(currentChar) || Character.isDigit(currentChar)) {
            id += currentChar;
            currentChar = nextChar();
        }
        movePastSpace();
        return id;
    }

    /**
     * Walks through the input assuming it's currently on an integer. Stops when
     * it reaches a non-digit.
     * @return Integer read
     */
    public String getNumberSequence() {
        String number = "";
        if (!Character.isDigit(currentChar)) {
            System.err.println("Error! Expected number, got non-numerical charater");
            System.exit(1);
        }
        while (Character.isDigit(currentChar)) {
            number += currentChar;
            currentChar = nextChar();
        }
        movePastSpace();
        return number;
    }

    public void movePastSpace() {
        while (currentChar == ' ') {
            currentChar = nextChar();
        }
    }

    public char nextChar() {
        char ch = input.charAt(index);
        index++;
        return ch;
    }



}
