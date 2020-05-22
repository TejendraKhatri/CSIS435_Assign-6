package assign6.parser;

import assign6.visitor.* ;
import assign6.lexer.*;

public class ExpressionNode extends Node {
    public BinaryExpressionNode expRight;
    public ValueNode valNode;
    public LogicalExpressionNode logicExpression;
    public Token[] tokenArray;
    
    public ExpressionNode () {
        
    }

    public void accept(ASTVisitor v) {

        v.visit(this);
    }
}
