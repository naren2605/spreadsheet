package proj.redmart.models.spreadsheet.visitor;

import proj.redmart.models.spreadsheet.Cell;

public class RCNode extends Node {

    private Cell cell;

    public RCNode(Integer row, Integer column, Cell cell) {
        super(row, column);
        this.cell=cell;
    }


    public boolean isFormulaCell() {
        if (cell.getData() != null && !cell.getData().matches("[0-9]+")) {
            return true;
        }
        return false;
    }




}
