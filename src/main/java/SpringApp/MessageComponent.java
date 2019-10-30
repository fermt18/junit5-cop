package SpringApp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MessageComponent {

    private MessageService messageService;

    @Autowired // DI through constructor (Spring 4.3+, no need for tag)
    public MessageComponent(MessageService messageService) {
        this.messageService = messageService;
    }

    public String getMessage() {
        return messageService.getMessage();
    }
}
