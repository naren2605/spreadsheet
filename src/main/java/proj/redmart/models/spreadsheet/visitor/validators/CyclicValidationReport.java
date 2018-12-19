package proj.redmart.models.spreadsheet.visitor.validators;

import proj.redmart.models.spreadsheet.visitor.Node;
import proj.redmart.models.spreadsheet.visitor.Report;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CyclicValidationReport extends Report {

    private Map<Node, Set<Node>> cyclicNodes;

    private CyclicValidationReport() {
        super();
    }

    public void setCyclicNodes(Map<Node, Set<Node>> cyclicNodes) {
        this.cyclicNodes = cyclicNodes;
    }

    public Map<Node, Set<Node>> getCyclicNodes() {
        return cyclicNodes;
    }

    @Override
    protected void setMessages(List<String> messages) {
        super.setMessages(messages);
    }

    public static class Builder{
        private CyclicValidationReport report=new CyclicValidationReport();;
        public CyclicValidationReport createEmptyReport(){
            report.setMessages(new ArrayList<String>());
            return report;
        }

        public Builder setCyclicNodes(Map<Node, Set<Node>> cyclicNodes) {
           report.setCyclicNodes(cyclicNodes);
           return this;
        }
        public static Builder getInstance(){
            return  new Builder();
        }
    }
}
