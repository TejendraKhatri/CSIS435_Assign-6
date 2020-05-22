package assign6.unparser;
import assign6.parser.*;
import assign6.visitor.* ;
import assign6.lexer.* ;
import java.io.* ;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;

public class Unparser extends ASTVisitor{
    public Parser parser = null;
    public CompilationUnit cu = null;
    public Token lookAhead = null;
    public BufferedWriter out;
    public int tabSpace = 0;


    public Unparser(Parser parser)
    {
        try
        {
            out = new BufferedWriter(new FileWriter("output.txt"));
            this.parser = parser;
            cu = parser.cu;
            visit(cu);
            out.close();
        }
            catch(IOException e){ System.out.println("Error while writing to file");}
    }

    public void visit (CompilationUnit n) {

        visit(n.block);
    }

    public void visit(BlockNode d){
        try {
           out.write("{\n");
           for (int i = 0; i < d.decls.length; i ++)
               if (d.decls[i] != null)
               {visit(d.decls[i]);}
           for (int i = 0; i < d.stmts.length;  i ++)
               if (d.stmts[i] != null)
               {visit(d.stmts[i]);}
               out.write('\n');
               for(int i =0;i<tabSpace;i++)
               {
                   out.write('\t');
               }
               out.write("}\n");
        } catch(IOException e)
        { System.out.println("Error while writing to file in BlockNode");}    
    }

    public void visit(DeclarationNode d){
        try {
            for(int i =-1;i<tabSpace;i++)
               {
                   out.write('\t');
               }
            visit(d.type);
            out.write(" ");
            visit(d.id);
            out.write(";\n");
        
        } catch(IOException e)
        { System.out.println("Error while writing to file in DeclarationNode");}
    }
 
    public void visit(StatementNode d){
        if(d.assign!=null)
        {
            try {
                for(int i =-1;i<tabSpace;i++)
               {
                   out.write('\t');
               }
            } catch (IOException e) {
                System.out.println("Error while writing to file in DeclarationNode");
            }
            visit(d.assign);
        }
        else if(d.ifCondition!=null)
        {
            visit(d.ifCondition);
        }
        else if(d.forStmt!=null)
        {
            visit(d.forStmt);
        }
    }

    public void removeLastChar()
    {
        try {
            out.close();
            String temp = "";
            temp = new String(Files.readAllBytes(Paths.get("output.txt")));
            temp = temp.substring(0,temp.length() -1);
            out = new BufferedWriter(new FileWriter("output.txt"));
            out.write(temp);
        } catch(IOException e){ System.out.println("Error while removing new line");}
    }

    public void visit(ForNode f)
    {
        tabSpace++;
        try
        {
            for(int i =0;i<tabSpace;i++)
               {
                   out.write('\t');
               }
            out.write("for( ");
            if(f.initializer!=null)
            {
                visit(f.initializer);
                removeLastChar(); 
                visit(f.logicalExpr);
                out.write("; "); 
                visit(f.updater);
                removeLastChar();
                removeLastChar();
                out.write(" )");
            }
            else
            {
                out.write(" ; ; )");
            }
        }catch(IOException e){ System.out.println("Error while writing to file in forNode");}
        visit(f.forBlock);  
        tabSpace--;  
    }

    public void visit(ForUpdateExpressionNode a)
    {
        try
        {
            visit(a.left);
            out.write(" = ");
            visit(a.expRight);
        }
        catch(IOException e){ System.out.println("Error while writing to file in ForUpdateExpressionNode");}
        
    }

    public void visit(IfNode i)
    {
        tabSpace++;
        try
        {
            for(int j =0;j<tabSpace;j++)
               {
                   out.write('\t');
               }
            out.write("if ( ");
            visit(i.logicalExpr);
            out.write(" )");
            visit(i.ifBlock);
            if(i.elseBlock != null)
            {
                out.write("\telse");
                visit(i.elseBlock);
            }
        }
        catch(IOException e){ System.out.println("Error while writing to file in IfNode");}
        tabSpace--;
        

    }

    public void visit(LogicalExpressionNode l)
    {
        try
        {
            visit(l.left);
            out.write(" "+l.operation+" ");
            visit(l.right);
        }
        catch(IOException e){ System.out.println("Error while writing to file in LogicalExpressionNode");}
    }

    public void visit(AssignmentNode a){
        try {
            //out.write("\t");
            visit(a.left);
            out.write(" = ");
            visit(a.expressionNode);
        
        } catch(IOException e)
        { System.out.println("Error while writing to file in AssignmentNode");}
    }

    public void visit(ExpressionNode e)
    {
        int size = 0;
        for(int i=0;i<e.tokenArray.length;i++)
        {
            if(e.tokenArray[i]!=null)
            {
                size++;
            }
        }
        if(size== 1)
        {
            visit(e.valNode);
        }
        if(size >= 3)
        {
           visit(e.expRight);
        }
    }

    public void visit(BinaryExpressionNode a){
        if(a.postfixExpression == null)
        {
            try {
                visit(a.left);
                out.write(" "+ a.operation+ " ");
                visit(a.right);
                out.write(";\n");
            
            } catch(IOException e)
            { System.out.println("Error while writing to file in BinaryExpressionNode");}
            
        }
        else
        {
            try 
            {
                for(int i=0;i<(a.tempStorage).length();i++)
                {
                    out.write((a.tempStorage).charAt(i));
                    if((i<(a.tempStorage).length()-1) && checkOperator((a.tempStorage).charAt(i+1)))
                    {
                        out.write(" ");
                    }
                    if(checkOperator((a.tempStorage).charAt(i)))
                    {
                        out.write(" ");
                    }    
                }
                out.write(';');out.write('\n');
            } catch(IOException e)
            { System.out.println("Error while writing to file in BinaryExpressionNode2");}
        }
    }

    public boolean checkOperator(char x)
    {
        if(x == '+' || x == '-' || x == '/' || x == '*' || x == '(' || x == ')')
        {return true;}
        return false;
    }
 
    public void visit(ValueNode a){
        try {
            visit(a.value);
            out.write(";\n");
        } catch(IOException e)
       { System.out.println("Error while writing to file in ValueNode");}
    }

    public void visit(TypeNode x){
        try {
            out.write(x.type.lexeme);
        } catch(IOException e)
        { System.out.println("Error while writing to file in TypeNode");}
       
    }

    public void visit(LiteralNode l){
        try {
            out.write(l.literal);
        } catch(IOException e)
      { System.out.println("Error while writing to file in LiteralNode");}
      
    }
}
