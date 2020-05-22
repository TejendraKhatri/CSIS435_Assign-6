package assign6.parser ;

import assign6.visitor.* ;

public class ForUpdateExpressionNode extends Node {

    public LiteralNode  left  ;
    public BinaryExpressionNode expRight;
    public char operator;

    public ForUpdateExpressionNode () {

    }

    public void accept(ASTVisitor v) {
        v.visit(this);
    }
}
