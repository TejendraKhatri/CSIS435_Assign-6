package assign6.parser ;

import assign6.visitor.* ;

public class CompilationUnit extends Node {

    //Node ast ;
    public BlockNode block ;
    public Env symbolTable;

    public CompilationUnit () {

    }

    public CompilationUnit (BlockNode block) {

        this.block = block ;
    }

    public void accept(ASTVisitor v) {

        v.visit(this);
    }
}
