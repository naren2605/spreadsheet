package proj.redmart.models.spreadsheet.visitor.validators;

import proj.redmart.models.spreadsheet.visitor.Report;

public interface EdgeListValidator {
    Report validate(Report inputReport);
}
