package proj.redmart.models.spreadsheet.visitor.validators;

import proj.redmart.models.spreadsheet.visitor.EdgeList;
import proj.redmart.models.spreadsheet.visitor.Node;
import proj.redmart.models.spreadsheet.visitor.RCNode;
import proj.redmart.models.spreadsheet.visitor.expressions.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class PostFixCellEvaluator {

    private EdgeList edgeList;
    private Map<Node,String> processed;


    private PostFixCellEvaluator(EdgeList edgeList){
        this.edgeList=edgeList;
        processed=new HashMap<Node, String>();
    }




    public String directedGraphEvaluation(RCNode node) throws InvalidExpressionFormatException {
        if(processed.get(node)==null) {
            PostFixExpression expression = new PostFixFormulaParser(edgeList).parse(node.getCell().getData());
            Stack<String> evalStack = new Stack<String>();
            for (int i = 0; i < expression.getExpressionElements().size(); i++) {
                ExpressionElement expressionElement = expression.getExpressionElements().get(i);
                if (expressionElement instanceof DataElement) {
                    DataElement dataElement=(DataElement) expressionElement;
                    evalStack.push(dataElement.getData());
                } else if (expressionElement instanceof Operator) {
                    Operator op = (Operator) expressionElement;
                    if(evalStack.isEmpty()){
                      throw new InvalidExpressionFormatException(node);
                    }
                    String value1=evalStack.pop();
                    if(evalStack.isEmpty()){
                        throw new InvalidExpressionFormatException(node);
                    }
                    String value2=evalStack.pop();
                    evalStack.push(op.operate(value2,value1));
                }else if(expressionElement instanceof ReferenceElement){
                    ReferenceElement element=(ReferenceElement)expressionElement;
                    String value= directedGraphEvaluation(element.getNode());
                    evalStack.push(value);
                }
            }
            String evaluatedValue=evalStack.pop();
            processed.put(node,evaluatedValue);
        }
        return processed.get(node);

    }




    public static PostFixCellEvaluator getInstance(EdgeList edgeList){
        return new PostFixCellEvaluator(edgeList);
    }
}
