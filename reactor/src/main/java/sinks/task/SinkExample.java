package sinks.task;

import org.example.corseUtil.Util;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class SinkExample {
    public static void main(String[] args) {
        Sinks.Many<SlackRoom> slackRoom = Sinks.many().multicast().onBackpressureBuffer();
        Flux<SlackRoom> flux = slackRoom.asFlux();
        SlackRoom slackRoom1 = generateSlackRoom();
        slackRoom1.setMessages(Flux.range(1, 10)
                .delayElements(Duration.ofMillis(500))
                .map(item -> {
                    SlackMembers members = generateMembers().get(Util.faker().random().nextInt(1, 5));
                    return members.emitMessage("Message from member with Id: " + members.getId());
                }));
        //flux.subscribe(item -> generateMembers().get(Util.faker().random().nextInt(1, 5)).readMessage(item.getMessages().doOnNext(o -> o.getMessage()))));
        slackRoom.tryEmitNext(slackRoom1);
    }

    public static SlackRoom generateSlackRoom() {
        return new SlackRoom(generateMembers());
    }

    public static List<SlackMembers> generateMembers() {
        return IntStream.range(1, 6)
                .mapToObj(item -> new SlackMembers())
                .collect(Collectors.toList());
    }

}
