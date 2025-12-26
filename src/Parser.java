class Parser {
    Token[] tokens;
    int pos;
    String[] errors;
    int errorCount;
    
    Parser(Token[] tokens) {
        this.tokens = tokens;
        this.pos = 0;
        this.errors = new String[100];
        this.errorCount = 0;
    }
    
    String[] parse() {
        while (pos < tokens.length) {
            if (tokens[pos] == null) {
                break;
            }
            if (tokens[pos].type == TokenType.EOF) {
                break;
            }
            parseStatement();
        }
        return errors;
    }
    
    private void parseStatement() {
        if (pos >= tokens.length) return;
        if (tokens[pos] == null) return;
        
        Token actual = tokens[pos];
        TokenType tipo = actual.type;
        
        if (tipo == TokenType.INT) {
            pos = pos + 1;
            if (pos < tokens.length && tokens[pos] != null && tokens[pos].type == TokenType.IDENTIFIER) {
                pos = pos + 1;
                if (pos < tokens.length && tokens[pos] != null && tokens[pos].type == TokenType.SEMICOLON) {
                    pos = pos + 1;
                } else {
                    errors[errorCount] = "Error: falta punto y coma";
                    errorCount = errorCount + 1;
                }
            } else {
                errors[errorCount] = "Error: falta identificador";
                errorCount = errorCount + 1;
            }
        } else if (tipo == TokenType.PRINT) {
            pos = pos + 1;
            if (pos < tokens.length && tokens[pos] != null && tokens[pos].type == TokenType.LPAREN) {
                pos = pos + 1;
                parseExpression();
                if (pos < tokens.length && tokens[pos] != null && tokens[pos].type == TokenType.RPAREN) {
                    pos = pos + 1;
                    if (pos < tokens.length && tokens[pos] != null && tokens[pos].type == TokenType.SEMICOLON) {
                        pos = pos + 1;
                    } else {
                        errors[errorCount] = "Error: falta punto y coma";
                        errorCount = errorCount + 1;
                    }
                } else {
                    errors[errorCount] = "Error: falta parentesis de cierre";
                    errorCount = errorCount + 1;
                }
            } else {
                errors[errorCount] = "Error: falta parentesis de apertura";
                errorCount = errorCount + 1;
            }
        } else if (tipo == TokenType.IDENTIFIER) {
            pos = pos + 1;
            if (pos < tokens.length && tokens[pos] != null && tokens[pos].type == TokenType.ASSIGN) {
                pos = pos + 1;
                parseExpression();
                if (pos < tokens.length && tokens[pos] != null && tokens[pos].type == TokenType.SEMICOLON) {
                    pos = pos + 1;
                } else {
                    errors[errorCount] = "Error: falta punto y coma";
                    errorCount = errorCount + 1;
                }
            } else {
                errors[errorCount] = "Error: falta asignacion";
                errorCount = errorCount + 1;
            }
        } else {
            errors[errorCount] = "Error: sentencia no valida";
            errorCount = errorCount + 1;
            pos = pos + 1;
        }
    }
    
    private void parseExpression() {
        parseTerm();
        while (true) {
            if (pos >= tokens.length || tokens[pos] == null) {
                break;
            }
            TokenType t = tokens[pos].type;
            if (t == TokenType.PLUS || t == TokenType.MINUS) {
                pos = pos + 1;
                parseTerm();
            } else {
                break;
            }
        }
    }
    
    private void parseTerm() {
        parseFactor();
        while (true) {
            if (pos >= tokens.length || tokens[pos] == null) {
                break;
            }
            TokenType t = tokens[pos].type;
            if (t == TokenType.MULTIPLY || t == TokenType.DIVIDE) {
                pos = pos + 1;
                parseFactor();
            } else {
                break;
            }
        }
    }
    
    private void parseFactor() {
        if (pos >= tokens.length) return;
        if (tokens[pos] == null) return;
        
        Token tok = tokens[pos];
        TokenType t = tok.type;
        
        if (t == TokenType.NUMBER) {
            pos = pos + 1;
        } else if (t == TokenType.IDENTIFIER) {
            pos = pos + 1;
        } else if (t == TokenType.LPAREN) {
            pos = pos + 1;
            parseExpression();
            if (pos < tokens.length && tokens[pos] != null && tokens[pos].type == TokenType.RPAREN) {
                pos = pos + 1;
            } else {
                errors[errorCount] = "Error: falta parentesis de cierre";
                errorCount = errorCount + 1;
            }
        } else {
            errors[errorCount] = "Error: factor no valido";
            errorCount = errorCount + 1;
        }
    }
}
