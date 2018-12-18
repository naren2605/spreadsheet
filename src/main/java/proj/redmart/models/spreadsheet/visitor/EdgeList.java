package proj.redmart.models.spreadsheet.visitor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EdgeList {

    public static class Container{
        private Map<Node,List<Node>> container;
        private Map<Node,Node> actualNodeMap;

        public Container(){
            container = new HashMap<Node, List<Node>>();
        }

        public void addNode(Node node){
            if(actualNodeMap.get(node)==null){
                actualNodeMap.put(node,node);
                container.put(node,new ArrayList<Node>());
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

    }




    private Container container;

    public Container getContainer() {
        return container;
    }

    public void setContainer(Container container) {
        this.container = container;
    }
}
