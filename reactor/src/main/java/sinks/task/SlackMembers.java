package sinks.task;

import lombok.Data;
import lombok.ToString;
import org.example.corseUtil.Util;
import reactor.core.publisher.Flux;

import java.time.LocalDate;

@Data
@ToString
public class SlackMembers {
    private String id;
    private String name;

    public SlackMembers() {
        this.id = Util.faker().idNumber().valid();
        this.name = Util.faker().name().fullName();
    }
    
    public Message emitMessage(String message) {
        Message mess = new Message(message, LocalDate.now(), this);
        return mess;
    }
    
    public void readMessage(Flux<Message> message) {
        System.out.println("{"+name + "}" + " Read message: \" " + message + " \"");
    }
}
