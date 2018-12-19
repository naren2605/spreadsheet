package proj.redmart.models.spreadsheet.visitor.validators;

import proj.redmart.models.spreadsheet.visitor.RCNode;

public class InvalidExpressionFormatException extends Exception {
    private RCNode node;
    public InvalidExpressionFormatException(RCNode node){
        super("invalid expression syntax");
        this.node=node;
    }

    public RCNode getNode() {
        return node;
    }
}
