package proj.redmart.models.spreadsheet.visitor;

public class Node {
    private Integer row;
    private Integer column;

    public Node(Integer row,Integer column){
        if(row==null||column==null) throw new RuntimeException("row or column value cannot be null");
        this.row=row;
        this.column=column;
    }



    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Node){
            Node node=(Node)obj;
            if(node.column.equals(this.column)&&node.row.equals(this.row)){
                return true;
            }else{
                return false;
            }
        }
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return (row%100000000+column%1000000000)%1000000000;
    }

    public Integer getColumn() {
        return column;
    }

    public Integer getRow() {
        return row;
    }

    @Override
    public String toString() {
        return "[ "+row+":"+column+"]";
    }
}
