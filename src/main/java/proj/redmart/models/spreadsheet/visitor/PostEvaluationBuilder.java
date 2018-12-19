package proj.redmart.models.spreadsheet.visitor;

import proj.redmart.models.spreadsheet.RowColumnLayout;
import proj.redmart.models.spreadsheet.visitor.evaluators.EvaluationReport;

public interface PostEvaluationBuilder {

    PostEvaluationBuilder add(EdgeList edgeList, RowColumnLayout rowColumnLayout);

    EvaluationReport evaluate();

}
