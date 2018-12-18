package proj.redmart.models.spreadsheet.cellutils;

public class CellIdentifier {

    private static CellIdentifier identifier;

    private  CellIdentifier(){

    }

    public int getRowNumber(String cellIdentifier){

        return 0;
    }

    public int getColumnNumber(String cellIdentifier){

        return 0;
    }
    public String getCellIdentity(int row,int column){

        return "";
    }


    public static CellIdentifier getIdentifier(){
        if(identifier==null){
            identifier=new CellIdentifier();
        }
        return identifier;
    }



}
