package assign6.parser ;

import assign6.visitor.* ;

public class IfNode extends Node {
    public BlockNode ifBlock;
    public LogicalExpressionNode logicalExpr;
    public BlockNode elseBlock;
    public IfNode () {
        
    }

    public void accept(ASTVisitor v) {

        v.visit(this);
    }
}
