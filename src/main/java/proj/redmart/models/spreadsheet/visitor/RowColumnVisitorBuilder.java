package proj.redmart.models.spreadsheet.visitor;

import proj.redmart.models.spreadsheet.RowColumnLayout;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class RowColumnVisitorBuilder implements EdgeListGraphBuilder,NodeBuilder {


    private RowColumnLayout  rowColumnLayout;

    private EdgeList edgeList;

    private PostFixCellEvaluator postFixCellEvaluator;


    private RowColumnVisitorBuilder(RowColumnLayout rowColumnLayout){
        this.rowColumnLayout=rowColumnLayout;
    }

    public NodeBuilder createEmptyGraph() {
        edgeList=new EdgeList();
        edgeList.setContainer(new HashMap<Node, List<Node>>());
        this.postFixCellEvaluator=new PostFixCellEvaluator(rowColumnLayout,edgeList);
        return this;
    }




    public static EdgeListGraphBuilder getBuilder(RowColumnLayout rowColumnLayout){
        return  new RowColumnVisitorBuilder(rowColumnLayout);
    }

    public void addAllNodes() {

    }

    public EdgeList build() {
        return edgeList;
    }
}
