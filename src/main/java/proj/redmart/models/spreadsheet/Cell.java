package proj.redmart.models.spreadsheet;

public class Cell {

    private String data;

    public void setData(String data) {
        this.data = data;
    }

    public String getData() {
        return data;
    }

    public static class Builder{

        Cell cell;

        public Builder add(String data){
            cell=new Cell();
            cell.setData(data);
            return this;
        }

        public Cell build(){
            return cell;
        }


        public static Builder getBuilder(){
            return new Builder();
        }

    }
}
