package Service;

import java.util.List;

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
        if (!(message.getMessage_text().isEmpty()) && message.getMessage_text().length() <= 255) {
            return messageDAO.createMessage(message);
        }
        return null;
    }

    // method that retireves all messages
    public List<Message> getAllMessages() {
        return messageDAO.getAllMessages();
    }

    // method that retrieves a message based on its id
    public Message getMessageByID(int id) {
        return messageDAO.getMessageByID(id);
    }

    // method that deletes a message based on its id
    public Message deleteMessageByID(int id) {
        return messageDAO.deleteMessageByID(id);
    }

    // method that updates the message's text based on its id
    public Message updateMessageByID(int id, Message message) {
        if (messageDAO.getMessageByID(id) != null && !(message.getMessage_text().isEmpty()) && message.getMessage_text().length() <= 255) {
            messageDAO.updateMessageByID(id, message);
            return messageDAO.getMessageByID(id);
        }
        return null;
    }
}
