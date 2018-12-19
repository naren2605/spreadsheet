package proj.redmart.models.spreadsheet.visitor.expressions;

import java.util.ArrayList;
import java.util.List;

public class PostFixExpression {
    private List<ExpressionElement> expressionElements;

    public PostFixExpression() {
        this.expressionElements = new ArrayList<ExpressionElement>();
    }

    public void add(ExpressionElement expressionElement) {
        expressionElements.add(expressionElement);
    }

    public List<ExpressionElement> getExpressionElements() {
        return expressionElements;
    }
}
