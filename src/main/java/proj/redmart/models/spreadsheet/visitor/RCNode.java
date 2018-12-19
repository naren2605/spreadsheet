package proj.redmart.models.spreadsheet.visitor;

import proj.redmart.models.spreadsheet.Cell;

public class RCNode extends Node {

    private Cell cell;

    private String result;

    public RCNode(Integer row, Integer column, Cell cell) {
        super(row, column);
        this.cell = cell;
    }

    public boolean isFormulaCell() {
        if (cell.getData() != null && !cell.getData().matches("[0-9]+")) {
            return true;
        }
        return false;
    }

    public Cell getCell() {
        return cell;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getResult() {
        return result;
    }

    public interface RowAddition {
        public ColumnAddition addRow(int row);

    }

    public interface ColumnAddition {
        public RCNodeBuilder addColumn(int column);
    }

    public interface RCNodeBuilder {
        public RCNode build(Cell cell);
    }

    public static class Builder implements RowAddition, ColumnAddition, RCNodeBuilder {
        int row;
        int column;

        private Builder() {
        }

        public static RowAddition getBuilder() {
            return new Builder();
        }

        public ColumnAddition addRow(int row) {
            this.row = row;
            return this;
        }

        public RCNodeBuilder addColumn(int column) {
            this.column = column;
            return this;
        }

        public RCNode build(Cell cell) {
            return new RCNode(this.row, this.column, cell);
        }


    }



}
