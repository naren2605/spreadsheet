package proj.redmart.models.spreadsheet.visitor;

import proj.redmart.models.spreadsheet.Cell;
import proj.redmart.models.spreadsheet.RowColumnLayout;


public class RowColumnVisitorBuilder implements EdgeListGraphBuilder,NodeBuilder {


    private RowColumnLayout  rowColumnLayout;

    private EdgeList edgeList;

    private PostFixCellEvaluator postFixCellEvaluator;


    private RowColumnVisitorBuilder(RowColumnLayout rowColumnLayout){
        this.rowColumnLayout=rowColumnLayout;
    }

    public NodeBuilder createEmptyGraph() {
        edgeList=new EdgeList();
        edgeList.setContainer(new EdgeList.Container());
        this.postFixCellEvaluator=new PostFixCellEvaluator(rowColumnLayout,edgeList);
        return this;
    }




    public static EdgeListGraphBuilder getBuilder(RowColumnLayout rowColumnLayout){
        return  new RowColumnVisitorBuilder(rowColumnLayout);
    }

    private void addAllNodesInToGraphWithoutEdges() {
        for (int row:rowColumnLayout.getContainer().keySet()){
            for (int column:rowColumnLayout.getContainer().get(row).keySet()){
                Cell cell=rowColumnLayout.getContainer().get(row).get(column);
                RCNode node=RCNode.Builder.getBuilder().addRow(row).addColumn(column).build(cell);
                edgeList.getContainer().addNode(node);
            }
        }
    }

    public EdgeList buildGraph() {
        addAllNodesInToGraphWithoutEdges();
        postFixCellEvaluator.visitAllCellsAndFormDirectedGraph();
        return edgeList;
    }
}
