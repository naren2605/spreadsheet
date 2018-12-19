package proj.redmart.models.spreadsheet;

import proj.redmart.models.spreadsheet.cellutils.CyclicValidationException;
import proj.redmart.models.spreadsheet.cellutils.InvalidCellReferenceException;
import proj.redmart.models.spreadsheet.visitor.Output;

import java.util.List;

public interface LayOutVisitor<T extends Layout> {

    public Output visit(T layout);

}
