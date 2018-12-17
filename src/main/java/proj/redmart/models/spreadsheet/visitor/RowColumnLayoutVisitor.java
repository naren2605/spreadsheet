package proj.redmart.models.spreadsheet.visitor;

import proj.redmart.models.spreadsheet.LayOutVisitor;
import proj.redmart.models.spreadsheet.Layout;
import proj.redmart.models.spreadsheet.RowColumnLayout;

public class RowColumnLayoutVisitor implements LayOutVisitor {
    private RowColumnLayout rowColumnLayout;

    public void visit(RowColumnLayout layout) {
        this.rowColumnLayout=layout;
    }



    public void visit(Layout layout) {
        visit((RowColumnLayout) layout);
    }

}
