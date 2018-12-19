package proj.redmart.models.spreadsheet.visitor.expressions;



public enum Operator implements ExpressionElement {
    PLUS("+"){
        @Override
        public String operate(String data1, String data2) {
            return Double.toString(new Double(data1)+new Double(data2));
        }
    }, MINUS("-"){
        @Override
        public String operate(String data1, String data2) {
            return Double.toString(new Double(data1)-new Double(data2));
        }
    }, MULTIPLY("*"){
        @Override
        public String operate(String data1, String data2) {
            return Double.toString(new Double(data1)*new Double(data2));
        }
    }, DIVIDE("/"){
        @Override
        public String operate(String data1, String data2) {
            return Double.toString(new Double(data1)/new Double(data2));
        }
    };
    private String symbol;

    Operator(String symbol) {
        this.symbol = symbol;
    }

    public abstract String operate(String data1,String data2);

    public static Operator getOperator(String string) {
        for (Operator op : Operator.values()) {
            if (op.symbol.equals(string)) {
                return op;
            }
        }
        return null;
    }


}
