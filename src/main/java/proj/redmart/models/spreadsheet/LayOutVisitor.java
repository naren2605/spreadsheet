package proj.redmart.models.spreadsheet;

public interface LayOutVisitor<T extends Layout> {

    public void visit(T layout);

}
