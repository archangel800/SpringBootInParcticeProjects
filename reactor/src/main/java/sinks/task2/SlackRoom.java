package sinks.task2;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

public class SlackRoom {
    private Sinks.Many<String> messages = Sinks.many().replay().all();

    private String name;

    public SlackRoom (String name) {
        this.name = name;
        System.out.println("SlackRoom named: " + name + " has been Created");
    }

    public Sinks.Many<String> getMessages() {
        return messages;
    }
    public Flux<String> getFlux() {
        return messages.asFlux();
    }
    public String getName (){
        return name;
    }
}
