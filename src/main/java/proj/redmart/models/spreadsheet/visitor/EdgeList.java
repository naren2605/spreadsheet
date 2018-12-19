package proj.redmart.models.spreadsheet.visitor;

import java.util.*;

public class EdgeList {

    public static class Container{
        private Map<Node,Set<Node>> container;
        private Map<Node,Node> actualNodeMap;

        public Container(){
            container = new HashMap<Node, Set<Node>>();
            actualNodeMap=new HashMap<Node, Node>();
        }

        public void addNode(Node node){
            if(actualNodeMap.get(node)==null){
                actualNodeMap.put(node,node);
                container.put(node,new HashSet<Node>());
            }
        }

        public void link(Node source,Node target){
            Node actualSource=actualNodeMap.get(source);
            Node actualTarget=actualNodeMap.get(target);
            container.get(actualSource).add(actualTarget);
        }

        public Node getNode(Node queryNode){
            return actualNodeMap.get(queryNode);
        }

        public Iterator<Node> getAllNodes(){
            return actualNodeMap.keySet().iterator();
        }

        public Iterator<Node> getLinkedNodes(Node node){
            return container.get(node).iterator();
        }

    }




    private Container container;

    public Container getContainer() {
        return container;
    }

    public void setContainer(Container container) {
        this.container = container;
    }
}
