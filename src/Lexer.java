class Lexer {
    String input;
    int pos;
    int line;
    Token[] tokens;
    int tokenCount;
    
    Lexer(String input) {
        this.input = input;
        this.pos = 0;
        this.line = 1;
        this.tokens = new Token[1000];
        this.tokenCount = 0;
    }
    
    Token[] tokenize() {
        while (pos < input.length()) {
            char c = input.charAt(pos);
            
            if (c == ' ') {
                pos = pos + 1;
            } else if (c == '\t') {
                pos = pos + 1;
            } else if (c == '\n') {
                line = line + 1;
                pos = pos + 1;
            } else if (c >= '0' && c <= '9') {
                String num = "";
                int sl = line;
                while (pos < input.length() && input.charAt(pos) >= '0' && input.charAt(pos) <= '9') {
                    num = num + input.charAt(pos);
                    pos = pos + 1;
                }
                tokens[tokenCount] = new Token(TokenType.NUMBER, num, sl);
                tokenCount = tokenCount + 1;
            } else if ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || c == '_') {
                String id = "";
                int sl = line;
                while (pos < input.length()) {
                    char ch = input.charAt(pos);
                    if ((ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z') || (ch >= '0' && ch <= '9') || ch == '_') {
                        id = id + ch;
                        pos = pos + 1;
                    } else {
                        break;
                    }
                }
                TokenType t = TokenType.IDENTIFIER;
                if (id.equals("int")) {
                    t = TokenType.INT;
                }
                if (id.equals("print")) {
                    t = TokenType.PRINT;
                }
                tokens[tokenCount] = new Token(t, id, sl);
                tokenCount = tokenCount + 1;
            } else if (c == '(') {
                Token t1 = new Token(TokenType.LPAREN, "(", line);
                tokens[tokenCount] = t1;
                tokenCount = tokenCount + 1;
                pos = pos + 1;
            } else if (c == ')') {
                Token t1 = new Token(TokenType.RPAREN, ")", line);
                tokens[tokenCount] = t1;
                tokenCount = tokenCount + 1;
                pos = pos + 1;
            } else if (c == ';') {
                Token t1 = new Token(TokenType.SEMICOLON, ";", line);
                tokens[tokenCount] = t1;
                tokenCount = tokenCount + 1;
                pos = pos + 1;
            } else if (c == '=') {
                Token t1 = new Token(TokenType.ASSIGN, "=", line);
                tokens[tokenCount] = t1;
                tokenCount = tokenCount + 1;
                pos = pos + 1;
            } else if (c == '+') {
                Token t1 = new Token(TokenType.PLUS, "+", line);
                tokens[tokenCount] = t1;
                tokenCount = tokenCount + 1;
                pos = pos + 1;
            } else if (c == '-') {
                Token t1 = new Token(TokenType.MINUS, "-", line);
                tokens[tokenCount] = t1;
                tokenCount = tokenCount + 1;
                pos = pos + 1;
            } else if (c == '*') {
                Token t1 = new Token(TokenType.MULTIPLY, "*", line);
                tokens[tokenCount] = t1;
                tokenCount = tokenCount + 1;
                pos = pos + 1;
            } else if (c == '/') {
                Token t1 = new Token(TokenType.DIVIDE, "/", line);
                tokens[tokenCount] = t1;
                tokenCount = tokenCount + 1;
                pos = pos + 1;
            } else {
                Token t1 = new Token(TokenType.UNKNOWN, String.valueOf(c), line);
                tokens[tokenCount] = t1;
                tokenCount = tokenCount + 1;
                pos = pos + 1;
            }
        }
        
        Token finalToken = new Token(TokenType.EOF, "", line);
        tokens[tokenCount] = finalToken;        return tokens;
    }
}