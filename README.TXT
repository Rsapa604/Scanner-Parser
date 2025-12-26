Scanner-Parser - Analizador Lexico y Sintactico

Un analizador de codigo en Java que valida tokens y sintaxis.


EJECUCION:

cd src
javac *.java
java Main

Luego escribe el codigo linea por linea y termina con END:

  int x;
  x = 5;
  print(x);
  END


ARCHIVOS
--------

TokenType.java - Tipos de tokens
Token.java - Clase Token
Lexer.java - Analisis lexico
Parser.java - Analisis sintactico
Main.java - Interfaz principal


GRAMATICA
---------

Declaracion:
  int identificador;

Asignacion:
  identificador = expresion;

Print:
  print(expresion);

Expresion:
  termino + termino
  termino - termino

Termino:
  factor * factor
  factor / factor

Factor:
  numero
  identificador
  (expresion)


EJEMPLOS DE USO
---------------

Ejemplo 1: Codigo valido
  int x;
  x = 10;
  print(x);

Ejemplo 2: Declaracion y asignacion
  int a;
  a = (5 + 2) * 3;
  print(a);

Ejemplo 3: Error - parentesis no balanceado
  int y;
  y = (5 + 2;

Ejemplo 4: Error - falta asignacion
  int x;
  x;
