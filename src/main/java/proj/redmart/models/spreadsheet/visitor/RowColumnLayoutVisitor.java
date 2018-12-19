package proj.redmart.models.spreadsheet.visitor;

import proj.redmart.models.spreadsheet.Cell;
import proj.redmart.models.spreadsheet.LayOutVisitor;
import proj.redmart.models.spreadsheet.Layout;
import proj.redmart.models.spreadsheet.RowColumnLayout;
import proj.redmart.models.spreadsheet.cellutils.CellIdentifier;
import proj.redmart.models.spreadsheet.cellutils.CyclicValidationException;
import proj.redmart.models.spreadsheet.cellutils.InvalidCellReferenceException;
import proj.redmart.models.spreadsheet.visitor.validators.CyclicEdgeListValidator;
import proj.redmart.models.spreadsheet.visitor.validators.CyclicValidationReport;
import proj.redmart.models.spreadsheet.visitor.validators.InvalidExpressionFormatException;
import proj.redmart.models.spreadsheet.visitor.validators.PostFixCellEvaluator;

import java.util.Iterator;

public class RowColumnLayoutVisitor implements LayOutVisitor {


    public Output visit(RowColumnLayout layout) {
        RowColumnLayout rowColumnLayout = layout;
        EdgeList edgeList = null;
        Output.Builder builder = new Output.Builder();
        try {
            edgeList = RowColumnVisitorBuilder.getBuilder(layout).createEmptyGraph().buildGraph();

            CyclicEdgeListValidator validator = new CyclicEdgeListValidator(edgeList);
            CyclicValidationReport report = (CyclicValidationReport) validator.validate(CyclicValidationReport.Builder.getInstance().createEmptyReport());
            Iterator<String> messages = report.getMessages();
            PostFixCellEvaluator cellValidator = PostFixCellEvaluator.getInstance(edgeList);

            for (int i = 1; i <= layout.getRows(); i++) {
                for (int j = 1; j <= layout.getColumns(); j++) {
                    if (rowColumnLayout.getContainer().get(i) != null) {
                        Cell cell = rowColumnLayout.getContainer().get(i).get(j);
                        if (cell != null) {
                            RCNode node = RCNode.Builder.getBuilder().addRow(i).addColumn(j).build(cell);
                            String cellId = CellIdentifier.getIdentifier().getCellIdentity(i, j);
                            if (report.getCyclicNodes().containsKey(node)) {
                                builder.addErrorMessage("cyclic dependency on  cell  = " + cellId + " data=" + cell.getData());
                            } else {
                                String evaluatedResult = null;
                                try {
                                    evaluatedResult = cellValidator.directedGraphEvaluation((RCNode) node);
                                    builder.addStandardMessage(String.format("%.5f", Double.parseDouble(evaluatedResult)));
                                } catch (InvalidExpressionFormatException e) {
                                    String issueCellId = CellIdentifier.getIdentifier().getCellIdentity(e.getNode().getRow(), e.getNode().getColumn());
                                    builder.addErrorMessage("invalid formula  cell reference of =" + cellId + " result=" + evaluatedResult + " celldata=" + cell.getData()
                                            + " with " + issueCellId + " celldata=" + e.getNode().getCell().getData());
                                }
                            }
                        }
                    }
                }
            }

        } catch (InvalidCellReferenceException e) {
            Iterator<String> messages=e.getReport().getMessages();
            while (messages.hasNext()){
                builder.addErrorMessage(messages.next());
            }
        }
        return builder.build();


    }


    public Output visit(Layout layout) {
        return visit((RowColumnLayout) layout);
    }

}
