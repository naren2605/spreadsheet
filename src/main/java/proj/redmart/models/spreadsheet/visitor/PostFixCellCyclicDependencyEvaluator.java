package proj.redmart.models.spreadsheet.visitor;


import proj.redmart.models.spreadsheet.RowColumnLayout;
import proj.redmart.models.spreadsheet.visitor.expressions.*;
import proj.redmart.models.spreadsheet.visitor.validators.CellParsingExceptionReport;

import java.util.*;

public class PostFixCellCyclicDependencyEvaluator {

    private RowColumnLayout rowColumnLayout;

    private EdgeList edgeList;


    private PostFixFormulaParser postFixFormulaParser;

    private CellParsingExceptionReport cellParsingExceptionReport;

    public PostFixCellCyclicDependencyEvaluator(RowColumnLayout rowColumnLayout, EdgeList edgeList) {
        this.rowColumnLayout = rowColumnLayout;
        this.edgeList = edgeList;
        postFixFormulaParser=new PostFixFormulaParser(edgeList);
        cellParsingExceptionReport = new CellParsingExceptionReport();
    }

    public CellParsingExceptionReport visitAllCellsAndFormDirectedGraph() {
        for (int row:rowColumnLayout.getContainer().keySet()){
            for (int col:rowColumnLayout.getContainer().get(row).keySet()){
                PostFixExpression postFixExpression=postFixFormulaParser.parse(rowColumnLayout.getContainer().get(row).get(col).getData());
                for (ExpressionElement expressionElement:postFixExpression.getExpressionElements()){
                    if(expressionElement instanceof InvalidElement){
                        cellParsingExceptionReport.addMessage(expressionElement+" row="+row+" column="+col+" data="+rowColumnLayout.getContainer().get(row).get(col).getData());
                    }
                    if(expressionElement instanceof ReferenceElement){
                        ReferenceElement element=(ReferenceElement)expressionElement;
                        RCNode source=RCNode.Builder.getBuilder().addRow(row).addColumn(col).build(rowColumnLayout.getContainer().get(row).get(col));
                        RCNode target=element.getNode();
                        edgeList.getContainer().link(source,target);
                    }
                }
            }
        }
        return  cellParsingExceptionReport;
    }


    private void evaluate(ExpressionElement parentExpressionElement){
        if(parentExpressionElement instanceof ReferenceElement){
            ReferenceElement parent=(ReferenceElement) parentExpressionElement;
            RCNode node = ((ReferenceElement)parentExpressionElement).getNode();
                String formula=rowColumnLayout.getContainer().get(node.getRow()).get(node.getColumn()).getData();
                PostFixExpression postFixExpression=postFixFormulaParser.parse(formula);
                for (ExpressionElement expressionElement:postFixExpression.getExpressionElements()){
                    if(expressionElement instanceof ReferenceElement){
                        ReferenceElement target=(ReferenceElement) expressionElement;
                    }
                }
        }
    }
}
