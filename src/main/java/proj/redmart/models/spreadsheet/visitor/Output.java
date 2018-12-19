package proj.redmart.models.spreadsheet.visitor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Output {
    private List<Message> messages=new ArrayList<Message>();
    private boolean exception;

    public List<Message> getMessages() {
        return messages;
    }

    public boolean hasException() {
        return exception;
    }

    public interface Message{
        String getData();
    }

    private static class ErrorMessage implements Message{
        private String message;
        private ErrorMessage(String message){
            this.message=message;
        }

        public String getData() {
            return message;
        }
    }

    private static class StandardMessage implements Message{
        private String message;
        private StandardMessage(String message){
            this.message=message;
        }

        public String getData() {
            return message;
        }
    }

    public static class Builder{

        private Output output=new Output();

        public Builder addErrorMessage(String message){
            output.getMessages().add(new ErrorMessage(message));
            output.exception=true;
            return this;
        }

        public Builder addStandardMessage(String message){
            output.getMessages().add(new StandardMessage(message));
            return this;
        }

        public Output build() {
            return output;
        }
    }
}
