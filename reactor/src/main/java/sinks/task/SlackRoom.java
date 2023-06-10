package sinks.task;

import lombok.Data;
import lombok.ToString;
import org.example.corseUtil.Util;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;

@Data
@ToString
public class SlackRoom {
    private int roomId;
    private List<SlackMembers> members = new ArrayList<>();
    private Flux<Message> messages;

    public SlackRoom(List<SlackMembers> members) {
        this.roomId = Util.faker().random().nextInt(2, 10);
        this.members = members;
    }
}
