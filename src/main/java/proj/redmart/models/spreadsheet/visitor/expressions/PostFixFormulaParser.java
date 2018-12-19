package proj.redmart.models.spreadsheet.visitor.expressions;

import proj.redmart.models.spreadsheet.visitor.EdgeList;

public class PostFixFormulaParser {

    private EdgeList edgeList;

    public PostFixFormulaParser(EdgeList edgeList) {
        this.edgeList=edgeList;
    }


    public PostFixExpression parse(String formula) {
        String[] elemenents = formula.split(" +");
        ExpressionElementBuilder expressionElementBuilder=new ExpressionElementBuilder(edgeList);
        for(String element:elemenents){
                expressionElementBuilder.add(element);
        }
        return expressionElementBuilder.getPostFixExpression();
    }


}
