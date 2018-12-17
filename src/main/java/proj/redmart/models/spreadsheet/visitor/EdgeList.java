package proj.redmart.models.spreadsheet.visitor;

import java.util.List;
import java.util.Map;

public class EdgeList {
    private Map<Node,List<Node>> container;

    public void setContainer(Map<Node, List<Node>> container) {
        this.container = container;
    }

    public Map<Node, List<Node>> getContainer() {
        return container;
    }
}
