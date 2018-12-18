package proj.redmart.models.spreadsheet.visitor;


import proj.redmart.models.spreadsheet.RowColumnLayout;
import proj.redmart.models.spreadsheet.cellutils.CellIdentifier;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PostFixCellEvaluator {

    private RowColumnLayout rowColumnLayout;

    private EdgeList edgeList;

    private Set<RCNode> visited;

    private PostFixFormulaParser postFixFormulaParser;

    public PostFixCellEvaluator(RowColumnLayout rowColumnLayout, EdgeList edgeList) {
        this.rowColumnLayout = rowColumnLayout;
        this.edgeList = edgeList;
        visited = new HashSet<RCNode>();
        postFixFormulaParser=new PostFixFormulaParser(edgeList);
    }









    public void visitAllCellsAndFormDirectedGraph(){
        for (int row:rowColumnLayout.getContainer().keySet()){
            for (int col:rowColumnLayout.getContainer().get(row).keySet()){
                PostFixExpression postFixExpression=postFixFormulaParser.parse(rowColumnLayout.getContainer().get(row).get(col).getData());
                for (ExpressionElement expressionElement:postFixExpression.getExpressionElements()){
                    if(expressionElement instanceof ReferenceElement){
                        ReferenceElement element=(ReferenceElement)expressionElement;
                        RCNode source=RCNode.Builder.getBuilder().addRow(row).addColumn(col).build(rowColumnLayout.getContainer().get(row).get(col));
                        RCNode target=element.getNode();
                        edgeList.getContainer().link(source,target);
                    }
                    visit(expressionElement);
                }
            }
        }
    }


    private void visit(ExpressionElement parentExpressionElement){
        if(parentExpressionElement instanceof ReferenceElement){
            ReferenceElement parent=(ReferenceElement) parentExpressionElement;
            RCNode node = ((ReferenceElement)parentExpressionElement).getNode();
                String formula=rowColumnLayout.getContainer().get(node.getRow()).get(node.getColumn()).getData();
                PostFixExpression postFixExpression=postFixFormulaParser.parse(formula);
                for (ExpressionElement expressionElement:postFixExpression.getExpressionElements()){
                    if(expressionElement instanceof ReferenceElement){
                        ReferenceElement target=(ReferenceElement) expressionElement;
                        edgeList.getContainer().link(parent.getNode(),target.getNode());
                        visit(expressionElement);
                    }
                }
        }
    }




































    private enum Operator implements ExpressionElement {
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


    private static interface ExpressionElement {

    }

    private static class PostFixExpression {
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

        private EdgeList edgeList;

        public PostFixFormulaParser(EdgeList edgeList) {
            this.edgeList=edgeList;
        }


        PostFixExpression parse(String formula) {
            String[] elemenents = formula.split(" +");
            ExpressionElementBuilder expressionElementBuilder=new ExpressionElementBuilder(edgeList);
            for(String element:elemenents){
                    expressionElementBuilder.add(element);
            }
            return expressionElementBuilder.getPostFixExpression();
        }

    }

    private static class ExpressionElementBuilder {

        private PostFixExpression postFixExpression;
        private EdgeList edgeList;


        private ExpressionElementBuilder(EdgeList edgeList) {
            postFixExpression = new PostFixExpression();
            this.edgeList=edgeList;
        }

        public void add(String element) {
            if (Operator.getOperator(element) != null) {
                postFixExpression.add(Operator.getOperator(element));
            }else if(element.matches("[0-9]+")){
                postFixExpression.add(new DataElement(element));
            }
            else {
                postFixExpression.add(new ReferenceElement.Builder(edgeList).getOperand(element));
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

    public static class ReferenceElement implements ExpressionElement {
        private RCNode node;
        private boolean evaluated;
        private String evaluationResult;


        public ReferenceElement(RCNode rcNode) {
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
            this.evaluated=true;
        }

        public boolean isEvaluated() {
            return evaluated;
        }

        public void setEvaluated(boolean evaluated) {
            this.evaluated = evaluated;
        }

        public static Builder getBuilder(EdgeList edgeList){
            return new Builder(edgeList);
        }



        public static class Builder {
            private EdgeList edgeList;

            private Builder(EdgeList edgeList) {
                this.edgeList=edgeList;
            }

            public ReferenceElement getOperand(String  cellIdentity) {
                return  new ReferenceElement(getNode(cellIdentity));
            }


            private int getRow(String cellIdentity){
                return 0;
            }

            private int getColumn(String cellIdentity){
                return 0;
            }

            private RCNode getNode(String cellIdentity) {
                CellIdentifier identifier=CellIdentifier.getIdentifier();
                RCNode queryNode=RCNode.Builder.getBuilder()
                        .addRow(identifier.getRowNumber(cellIdentity))
                        .addColumn(identifier.getColumnNumber(cellIdentity))
                        .build(null);
                return (RCNode)edgeList.getContainer().getNode(queryNode);
            }

        }
    }
}
