package proj.redmart.models.spreadsheet;

import proj.redmart.models.spreadsheet.visitor.Output;
import proj.redmart.models.spreadsheet.visitor.RowColumnLayoutVisitor;

import java.util.HashMap;
import java.util.Map;

public class RowColumnLayout extends Layout {

    private Map<Integer, Map<Integer, Cell>> container;

    private int rows;
    private int columns;

    public int getColumns() {
        return columns;
    }

    public int getRows() {
        return rows;
    }


    public Output accept(RowColumnLayoutVisitor layOutVisitor) {
        return layOutVisitor.visit(this);
    }

    public Map<Integer, Map<Integer, Cell>> getContainer() {
        return container;
    }

    public void setContainer(Map<Integer, Map<Integer, Cell>> container) {
        this.container = container;
    }


    public interface CellBuilder {
        CellBuilder addCell(int row, int column, String cell);

        RowColumnLayout build();

    }




    public interface ContainerBuilder {
        CellBuilder buildContainer(int rows,int columns);
    }

    public static class Builder implements ContainerBuilder, CellBuilder {
        RowColumnLayout rowColumnLayout;

        public static ContainerBuilder getBuilder() {
            return new Builder();
        }

        public CellBuilder buildContainer(int rows, int columns) {
            rowColumnLayout = new RowColumnLayout();
            rowColumnLayout.columns=columns;
            rowColumnLayout.rows=rows;
            rowColumnLayout.setContainer(new HashMap<Integer, Map<Integer, Cell>>());
            return this;
        }


        public CellBuilder addCell(int row, int column, String data) {
            Cell cell=Cell.Builder.getBuilder().add(data).build();
            if (!rowColumnLayout.getContainer().containsKey(row)) {
                rowColumnLayout.getContainer().put(row, new HashMap<Integer, Cell>());
            }
            rowColumnLayout.getContainer().get(row).put(column, cell);
            return this;
        }

        public RowColumnLayout build() {
            return rowColumnLayout;
        }
    }
}
