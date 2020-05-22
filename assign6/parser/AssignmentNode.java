package assign6.parser ;

import assign6.visitor.* ;

public class AssignmentNode extends Node {

    public LiteralNode  left  ;
    public ExpressionNode expressionNode;
   // public LogicalExpressionNode expNode;

    public AssignmentNode () {
        
    }

    public void accept(ASTVisitor v) {

        v.visit(this);
    }
}
