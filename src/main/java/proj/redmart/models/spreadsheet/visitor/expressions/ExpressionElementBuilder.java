package proj.redmart.models.spreadsheet.visitor.expressions;

import proj.redmart.models.spreadsheet.visitor.EdgeList;

public class ExpressionElementBuilder {

    private PostFixExpression postFixExpression;
    private EdgeList edgeList;


    public ExpressionElementBuilder(EdgeList edgeList) {
        postFixExpression = new PostFixExpression();
        this.edgeList=edgeList;
    }

    public void add(String element) {
        if (Operator.getOperator(element) != null) {
            postFixExpression.add(Operator.getOperator(element));
        }else if(element.matches("[0-9]+")){
            postFixExpression.add(new DataElement(element));
        }
        else if(element.matches("[a-zA-Z]+[0-9]+")){
            ReferenceElement referenceElement=new ReferenceElement.Builder(edgeList).getOperand(element);
            if(referenceElement==null){
                postFixExpression.add(InvalidElement.INVALID_REFERENCE);
            }else {
                postFixExpression.add(referenceElement);
            }
        }else{
            postFixExpression.add(InvalidElement.DATA_PATTERN_MISMATCH);
        }
    }

    public PostFixExpression getPostFixExpression() {
        return postFixExpression;
    }
}
