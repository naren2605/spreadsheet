package proj.redmart.models.spreadsheet.visitor.validators;

import proj.redmart.models.spreadsheet.visitor.EdgeList;
import proj.redmart.models.spreadsheet.visitor.Node;
import proj.redmart.models.spreadsheet.visitor.Report;

import java.util.*;

public class CyclicEdgeListValidator implements EdgeListValidator {


    private EdgeList edgeList;
    private Set<Node> visited;
    private Map<Node,Set<Node>> cycleNodes;

    public CyclicEdgeListValidator(EdgeList edgeList) {
        this.edgeList = edgeList;
        visited = new HashSet<Node>();
        cycleNodes=new HashMap<Node, Set<Node>>();
    }

    public Report validate(Report report) {
        checkForCycles((CyclicValidationReport) report);
        return report;
    }

    private void checkForCycles(CyclicValidationReport report) {
        Iterator<Node> allNodes = edgeList.getContainer().getAllNodes();
        while (allNodes.hasNext()) {
            Node node = allNodes.next();
            Map<Node,Boolean> path=new HashMap<Node, Boolean>();
            boolean hasCycle = checkForCycles(node,path,node);
            if (hasCycle) {
                report.addMessage("found cyclic dependency in " + node );
            }
        }
        report.setCyclicNodes(cycleNodes);
    }


    private void addCyclicNodes( Node n1,Node n2){
        if(cycleNodes.get(n1)==null){
            cycleNodes.put(n1,new HashSet<Node>());
        }else{
            if(cycleNodes.get(n2)!=null){
                cycleNodes.get(n1).add(n2);
                cycleNodes.get(n1).addAll(cycleNodes.get(n2));
            }else {
                cycleNodes.get(n1).add(n2);
            }
        }
    }
    private boolean hasCycle(Node node){
        if(cycleNodes.get(node)!=null){
            return true;
        }else{
            return false;
        }
    }



    private boolean checkForCycles(Node node, Map<Node, Boolean> path,Node source) {
        if(hasCycle(node)){
            addCyclicNodes(source,node);
            return true;
        }
        boolean hasCycle = false;
        if (visited.add(node)) {
            if (path.get(node) == null) {
                path.put(node, true);
            } else if (path.get(node)) {
                addCyclicNodes(source,node);
                addCyclicNodes(node,node);
                return true;
            }
            Iterator<Node> linkedNodes = edgeList.getContainer().getLinkedNodes(node);
            while (linkedNodes.hasNext()) {
                Node next=linkedNodes.next();
                boolean check=false;
                if(check=checkForCycles(next, path,node)) {
                    addCyclicNodes(source,next);
                }
                hasCycle=hasCycle||check;
            }
            if (hasCycle) return true;
            path.put(node, false);
        }
        if (path.get(node)!=null&&path.get(node)) {
            addCyclicNodes(source,node);
            return true;
        }
        return hasCycle;
    }
}
