package proj.redmart.models.spreadsheet.visitor.expressions;

public enum InvalidElement implements ExpressionElement{
    INVALID_REFERENCE("referenced cell is not valid "),
    DATA_PATTERN_MISMATCH("data present is cell is doesnt contain numeric or proper formula symbols");
    private String message;
    InvalidElement(String message){
        this.message=message;
    }

    @Override
    public String toString() {
        return message;
    }
}
