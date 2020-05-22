package assign6 ;

import java.io.IOException;

import assign6.lexer.* ;
import assign6.parser.* ;
import assign6.unparser.*;
import assign6.typechecker.*;
    
public class Main {

    public static void main (String[] args) {
        Lexer lexer = new Lexer() ;
        Parser parser = new Parser(lexer) ;
        TypeChecker typeCheck = new TypeChecker(parser);
        Unparser unpretty = new Unparser(parser);
       
    }
}
