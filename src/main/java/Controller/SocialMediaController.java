package Controller;

import Service.AccountService;
import Service.MessageService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import Model.Account;
import Model.Message;
import io.javalin.Javalin;
import io.javalin.http.Context;

/**
 * TODO: You will need to write your own endpoints and handlers for your controller. The endpoints you will need can be
 * found in readme.md as well as the test cases. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
public class SocialMediaController {
    AccountService accountService;
    MessageService messageService;

    public SocialMediaController() {
        this.accountService = new AccountService();
        this.messageService = new MessageService();
    }

    /**
     * In order for the test cases to work, you will need to write the endpoints in the startAPI() method, as the test
     * suite must receive a Javalin object from this method.
     * @return a Javalin app object which defines the behavior of the Javalin controller.
     */
    public Javalin startAPI() {
        Javalin app = Javalin.create();
        // app.get("example-endpoint", this::exampleHandler);
        app.post("/register", this::createAccountHandler);
        app.post("/login", this::verifyAccountHandler);
        app.post("/messages", this::createMessageHandler);
        app.get("/messages", this::getAllMessagesHandler);
        app.get("/messages/{message_id}", this::getMessageByIDHandler);
        app.delete("/messages/{message_id}", this::deleteMessageByIDHandler);
        app.patch("messages/{message_id}", this::updateMessageByIDHandler);

        return app;
    }

    /**
     * This is an example handler for an example endpoint.
     * @param context The Javalin Context object manages information about both the HTTP request and response.
     */
    private void exampleHandler(Context context) {
        context.json("sample text");
    }

    // Handler for the registration of the account
    private void createAccountHandler(Context ctx) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Account account = mapper.readValue(ctx.body(), Account.class);
        Account addedAccount = accountService.createAccount(account);
        if(addedAccount!=null){
            ctx.json(mapper.writeValueAsString(addedAccount));
            ctx.status(200);
        }else{
            ctx.status(400);
        }
    }

    // Handler for the login verification of the account
    private void verifyAccountHandler(Context ctx) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Account account = mapper.readValue(ctx.body(), Account.class);
        Account verifyAccount = accountService.verifyLoginAccount(account.getUsername(), account.getPassword());
        if(verifyAccount!=null){
            ctx.json(mapper.writeValueAsString(verifyAccount));
            ctx.status(200);
        }else{
            ctx.status(401);
        }
    }

    // TO-DO: HANDLER FOR THE MESSAGES...

    // Handler for the creation of a new message
    private void createMessageHandler (Context ctx) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Message message = mapper.readValue(ctx.body(), Message.class);
        Message addedMessage = messageService.createMessage(message);
        if (addedMessage != null) {
            ctx.json(mapper.writeValueAsString(addedMessage));
            ctx.status(200);
        } else {
            ctx.status(400);
        }
    }

    // Handler for retrieving all messages
    private void getAllMessagesHandler (Context ctx) {
        ctx.json(messageService.getAllMessages());
        ctx.status(200);
    }

    // Handler for retrieving a message based on its id
    private void getMessageByIDHandler (Context ctx) {
        int message_id = Integer.parseInt(ctx.pathParam("message_id"));
        Message messageExist = messageService.getMessageByID(message_id);
        if (messageExist != null) {
            ctx.json(messageExist);
        } else {
            ctx.result("");
        }
        ctx.status(200);
    }

    // Handler for deleting a message based on its id
    private void deleteMessageByIDHandler (Context ctx) {
        int message_id = Integer.parseInt(ctx.pathParam("message_id"));
        Message messageExist = messageService.deleteMessageByID(message_id);
        if (messageExist != null) {
            ctx.json(messageExist);
        } else {
            ctx.result("");
        }
        ctx.status(200);
    }

    // Handler for updating a message's text based on its id
    private void updateMessageByIDHandler (Context ctx) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Message message = mapper.readValue(ctx.body(), Message.class);
        int message_id = Integer.parseInt(ctx.pathParam("message_id"));
        Message updatedMessage = messageService.updateMessageByID(message_id, message);
        if(updatedMessage == null){
            ctx.status(400);
        }else{
            ctx.json(mapper.writeValueAsString(updatedMessage));
        }
    }

}