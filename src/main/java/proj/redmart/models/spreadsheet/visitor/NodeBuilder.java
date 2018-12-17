package proj.redmart.models.spreadsheet.visitor;

public interface NodeBuilder {
    public void addAllNodes();
    public EdgeList build();
}
