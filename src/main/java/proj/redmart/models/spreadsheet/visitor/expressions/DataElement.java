package proj.redmart.models.spreadsheet.visitor.expressions;

public class DataElement implements ExpressionElement{
    private String data;
    public DataElement(String data){
        this.data=data;
    }


    public String getData() {
        return data;
    }
}
