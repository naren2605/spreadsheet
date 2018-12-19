package proj.redmart.models.spreadsheet.cellutils;

import proj.redmart.models.spreadsheet.visitor.validators.CyclicValidationReport;

public class CyclicValidationException extends Exception {
    private CyclicValidationReport report;
    public CyclicValidationException(CyclicValidationReport report){
        this.report=report;
    }

    public CyclicValidationReport getReport() {
        return report;
    }
}
