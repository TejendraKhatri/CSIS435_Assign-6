package assign6.parser ;

import assign6.lexer.*;
import assign6.visitor.* ;

public class LiteralNode extends Node {

    public String literal ;

    public LiteralNode () {

    }
    
    public LiteralNode (String literal) {

        this.literal = literal ;
    }

    public LiteralNode (Word w) {

        this.literal = w.lexeme;
    }

    public void accept(ASTVisitor v) {
       // System.out.println("OUUUTin accept of litN");
        v.visit(this);
    }

    public void store(String n) {

        this.literal = n;
    }

    void printNode () {

        System.out.println("LiteralNode: " + literal) ;
    }
}
