package proj.redmart.models.spreadsheet.cellutils;


import proj.redmart.models.spreadsheet.visitor.validators.CellParsingExceptionReport;

public class InvalidCellReferenceException extends Exception {
        private CellParsingExceptionReport report;
        public InvalidCellReferenceException(CellParsingExceptionReport  report){
            this.report=report;
        }

    public CellParsingExceptionReport getReport() {
        return report;
    }
}
