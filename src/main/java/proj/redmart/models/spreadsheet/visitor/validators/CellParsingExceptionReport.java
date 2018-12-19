package proj.redmart.models.spreadsheet.visitor.validators;

import proj.redmart.models.spreadsheet.visitor.Report;

import java.util.ArrayList;

public class CellParsingExceptionReport extends Report {
        public CellParsingExceptionReport(){
            setMessages(new ArrayList<String>());
        }
}
