package assign6.parser ;
import assign6.visitor.* ;
import assign6.lexer.*;

public class TypeNode extends Node {

    public Type type;

    public TypeNode () {

    }
    
    public TypeNode (Type type) {

        this.type = type;
    }

    public void accept(String v) {

        type.lexeme = v;
    }
}
