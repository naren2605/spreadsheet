package proj.redmart.models.spreadsheet.cellutils;

public class CellIdentifier {

    private static CellIdentifier identifier;

    private CellIdentifier() {

    }

    public static CellIdentifier getIdentifier() {
        if (identifier == null) {
            identifier = new CellIdentifier();
        }
        return identifier;
    }

    public int getColumnNumber(String cellIdentifier) {
        String column = cellIdentifier.replaceAll("[a-zA-Z]+", "");
        return new Integer(column);
    }

    public int getRowNumber(String cellIdentifier) {
        String row = cellIdentifier.replaceAll("[0-9]+", "");
        int result = 0;
        for (int i = 0; i < row.length(); i++) {
            result *= 26;
            result += row.charAt(i) - 'A' + 1;
        }
        return result;
    }

    public String getRow(int rowNumber) {
        StringBuilder rowName = new StringBuilder();

        while (rowNumber > 0) {
            int rem = rowNumber % 26;
            if (rem == 0) {
                rowName.append("Z");
                rowNumber = (rowNumber / 26) - 1;
            } else {
                rowName.append((char) ((rem - 1) + 'A'));
                rowNumber = rowNumber / 26;
            }
        }
        return rowName.toString();
    }

    public String getCellIdentity(int row, int column) {
        return getRow(row) + Integer.toString(column);
    }


}
