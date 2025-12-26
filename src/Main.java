import java.util.*;

public class Main {
    
    public static void main(String[] args) {
        System.out.println("=== Analizador Lexico y Sintactico ===\n");
        System.out.println("Escribe el codigo (END para terminar):\n");
        
        String source = "";
        Scanner input = new Scanner(System.in);
        
        while (true) {
            String line = input.nextLine();
            if (line.equals("END")) {
                break;
            }
            source = source + line + "\n";
        }
        
        System.out.println("\n--- ANALISIS LEXICO ---\n");
        Lexer lexer = new Lexer(source);
        Token[] tokens = lexer.tokenize();
        
        int count = 0;
        int i = 0;
        while (i < tokens.length) {
            if (tokens[i] == null) {
                break;
            }
            if (tokens[i].type != TokenType.EOF) {
                count = count + 1;
                System.out.println(count + ". " + tokens[i].type + " -> " + tokens[i].value + " (linea " + tokens[i].line + ")");
            }
            i = i + 1;
        }
        
        System.out.println("\n--- ANALISIS SINTACTICO ---\n");
        Parser parser = new Parser(tokens);
        String[] syntaxErrors = parser.parse();
        
        int numErrores = 0;
        int j = 0;
        while (j < syntaxErrors.length) {
            if (syntaxErrors[j] != null) {
                numErrores = numErrores + 1;
            }
            j = j + 1;
        }
        
        if (numErrores == 0) {
            System.out.println("OK: Sintaxis correcta");
        } else {
            System.out.println("Errores sintacticos:");
            int k = 0;
            while (k < syntaxErrors.length) {
                if (syntaxErrors[k] != null) {
                    System.out.println("  - " + syntaxErrors[k]);
                }
                k = k + 1;
            }
        }
        
        System.out.println("\n--- RESUMEN ---");
        System.out.println("Total tokens: " + count);
        System.out.println("Errores sintacticos: " + numErrores);
        
        if (numErrores == 0) {
            System.out.println("\nOK: Analisis completado sin errores");
        } else {
            System.out.println("\nERROR: Se encontraron errores");
        }
        
        input.close();
    }
}

