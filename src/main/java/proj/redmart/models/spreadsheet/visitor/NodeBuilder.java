package proj.redmart.models.spreadsheet.visitor;

import proj.redmart.models.spreadsheet.cellutils.InvalidCellReferenceException;

public interface NodeBuilder {
    public EdgeList buildGraph() throws InvalidCellReferenceException;
}
