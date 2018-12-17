package proj.redmart.models.spreadsheet.visitor;


import proj.redmart.models.spreadsheet.RowColumnLayout;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PostFixCellEvaluator {

    private RowColumnLayout rowColumnLayout;

    private EdgeList edgeList;

    private Set<RCNode> visited;

    public PostFixCellEvaluator(RowColumnLayout rowColumnLayout, EdgeList edgeList) {
        this.rowColumnLayout = rowColumnLayout;
        this.edgeList = edgeList;
        visited = new HashSet<RCNode>();
    }

    public void evaluate(RCNode node){

        if(node.isFormulaCell()){
                String formula=rowColumnLayout.getContainer().get(node.getRow()).get(node.getColumn()).getData();
                PostFixFormulaParser parser =new PostFixFormulaParser(formula);
                PostFixExpression postFixExpression=parser.parse();
                for (ExpressionElement expressionElement:postFixExpression.getExpressionElements()){

                    if(expressionElement instanceof Operator){

                    }else if(expressionElement instanceof Operand){

                    }else if(expressionElement instanceof DataElement){

                    }

                }
        }
    }




































    public enum Operator implements ExpressionElement {
        PLUS("+"), MINUS("-"), MULTIPLY("*"), DIVIDE("/");
        private String symbol;

        Operator(String symbol) {
            this.symbol = symbol;
        }

        public static Operator getOperator(String string) {
            for (Operator op : Operator.values()) {
                if (op.symbol.equals(string)) {
                    return op;
                }
            }
            return null;
        }


    }


    public static interface ExpressionElement {

    }

    public static class PostFixExpression {
        private List<ExpressionElement> expressionElements;

        public PostFixExpression() {
            this.expressionElements = new ArrayList<ExpressionElement>();
        }

        public void add(ExpressionElement expressionElement) {
            expressionElements.add(expressionElement);
        }

        public List<ExpressionElement> getExpressionElements() {
            return expressionElements;
        }
    }

    public static class PostFixFormulaParser {

        private String formula;

        public PostFixFormulaParser(String formula) {
            this.formula = formula;
        }


        PostFixExpression parse() {
            String[] elemenents = formula.split(" +");
            ExpressionElementBuilder expressionElementBuilder=new ExpressionElementBuilder();
            for(String element:elemenents){
                    expressionElementBuilder.add(element);
            }
            return expressionElementBuilder.getPostFixExpression();
        }

    }

    public static class ExpressionElementBuilder {

        private PostFixExpression postFixExpression;


        public ExpressionElementBuilder() {
            postFixExpression = new PostFixExpression();
        }

        public void add(String element) {
            if (Operator.getOperator(element) != null) {
                postFixExpression.add(Operator.getOperator(element));
            }else if(element.matches("[0-9]+")){
                postFixExpression.add(new DataElement(element));
            }
            else {
                postFixExpression.add(new Operand.Builder(element).getOperand());
            }
        }

        public PostFixExpression getPostFixExpression() {
            return postFixExpression;
        }
    }

    public static class DataElement implements ExpressionElement{
        private String data;
        public DataElement(String data){
            this.data=data;
        }
    }

    public static class Operand implements ExpressionElement {
        private RCNode node;
        private boolean evaluated;
        private String evaluationResult;

        public Operand(RCNode rcNode) {
            this.node = rcNode;
        }

        public RCNode getNode() {
            return node;
        }

        public void setNode(RCNode node) {
            this.node = node;
        }

        public String getEvaluationResult() {
            return evaluationResult;
        }

        public void setEvaluationResult(String evaluationResult) {
            this.evaluationResult = evaluationResult;
        }

        public boolean isEvaluated() {
            return evaluated;
        }

        public void setEvaluated(boolean evaluated) {
            this.evaluated = evaluated;
        }

        public static class Builder {
            private Operand operand;

            public Builder(String data) {
                operand = new Operand(getNode(data));
            }

            public Operand getOperand() {
                return operand;
            }

            private RCNode getNode(String data) {
                /*
                convert string representation of column to row column rc node represetation
                 */
                return null;
            }

        }
    }
}
