package proj.redmart.models.spreadsheet.visitor.expressions;

import proj.redmart.models.spreadsheet.cellutils.CellIdentifier;
import proj.redmart.models.spreadsheet.visitor.EdgeList;
import proj.redmart.models.spreadsheet.visitor.RCNode;

public class ReferenceElement implements ExpressionElement {
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

        public Builder(EdgeList edgeList) {
            this.edgeList=edgeList;
        }

        public ReferenceElement getOperand(String  cellIdentity) {
            RCNode node=getNode(cellIdentity);
            if(node==null){
                return null;
            }
            return  new ReferenceElement(getNode(cellIdentity));
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
