package Service;

import DAO.MessageDAO;
import Model.Message;

public class MessageService {
    MessageDAO messageDAO;

    // No-args constructor
    public MessageService() {
        messageDAO = new MessageDAO();
    }

    public MessageService(MessageDAO messageDAO) {
        this.messageDAO = messageDAO;
    }

    // method that returns the new message created
    public Message createMessage(Message message) {
        if (!(message.getMessage_text().isEmpty() && message.getMessage_text().length() > 255)) {
            return messageDAO.createMessage(message);
        }
        return null;
    }
}
