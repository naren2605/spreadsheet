package proj.redmart.models.spreadsheet.visitor;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public abstract class Report {
    private List<String> messages;

    public Iterator<String> getMessages(){
        return messages.iterator();
    }

    public void addMessage(String message) {
        messages.add(message);
    }

    protected void setMessages(List<String> messages) {
        this.messages = messages;
    }
}
