package proj.redmart.models.spreadsheet;

import proj.redmart.models.spreadsheet.visitor.RowColumnLayoutVisitor;

import java.util.HashMap;
import java.util.Map;

public class RowColumnLayout extends Layout {

    private Map<Integer, Map<Integer, Cell>> container;

    public static void main(String[] args) {
        RowColumnLayout layout = RowColumnLayout.Builder
                .getBuilder().buildContainer().next()
                .addCell(1, 2, null)
                .addCell(2, 3, null)
                .build();
    }

    public void accept(RowColumnLayoutVisitor layOutVisitor) {
        layOutVisitor.visit(this);
    }

    public Map<Integer, Map<Integer, Cell>> getContainer() {
        return container;
    }

    public void setContainer(Map<Integer, Map<Integer, Cell>> container) {
        this.container = container;
    }


    public interface CellBuilder {
        CellBuilder addCell(int row, int column, Cell cell);

        RowColumnLayout build();

    }




    public interface ContainerBuilder {
        ContainerBuilder buildContainer();
        CellBuilder next();
    }

    public static class Builder implements ContainerBuilder, CellBuilder {
        RowColumnLayout rowColumnLayout;

        public static ContainerBuilder getBuilder() {
            return new Builder();
        }

        public ContainerBuilder buildContainer() {
            rowColumnLayout = new RowColumnLayout();
            rowColumnLayout.setContainer(new HashMap<Integer, Map<Integer, Cell>>());
            return this;
        }

        public Builder next() {
            return this;
        }

        public CellBuilder addCell(int row, int column, Cell cell) {
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
