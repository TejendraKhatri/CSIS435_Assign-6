package assign6.parser;

import assign6.parser.StatementNode;
import assign6.visitor.* ;

public class ForNode extends Node {
    public BlockNode forBlock;
    public LogicalExpressionNode logicalExpr;
    public AssignmentNode initializer;
    public ForUpdateExpressionNode updater;
    public ForNode () { 
        
    }

    public void accept(ASTVisitor v) {

        v.visit(this);
    }
}
