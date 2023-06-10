package sinks.chat;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

class Member {
    private final String name;

    public Member(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void joinSlackRoom(SlackRoom slackRoom) {
        System.out.println(name + " Joined");
        slackRoom.addMember(this);
    }

    public void sendMessage(String message, SlackRoom slackRoom) {
        System.out.println(name + " Sent message");
        slackRoom.sendMessage(new Message(this, message));
    }
}

class Message {
    private final Member sender;
    private final String content;

    public Message(Member sender, String content) {
        this.sender = sender;
        this.content = content;
    }

    public Member getSender() {
        return sender;
    }

    public String getContent() {
        return content;
    }
}

class SlackRoom {
    private final Sinks.Many<Message> messageSink;
    private final Flux<Message> messageFlux;

    public SlackRoom() {
        this.messageSink = Sinks.many().replay().limit(10);
        this.messageFlux = messageSink.asFlux();
    }

    public void addMember(Member member) {
        messageFlux.subscribe(message -> {
            if (message.getSender() != member)
            System.out.println(member.getName() + " received message: " + message.getContent());
        });
    }

    public void sendMessage(Message message) {
        messageSink.emitNext(message, Sinks.EmitFailureHandler.FAIL_FAST);
    }
}

public class MessagingSystemExample {
    public static void main(String[] args) {
        SlackRoom slackRoom = new SlackRoom();

        Member john = new Member("John");
        Member sarah = new Member("Sarah");
        Member Ahmed = new Member("Ahmed");

        john.joinSlackRoom(slackRoom);
        sarah.joinSlackRoom(slackRoom);

        john.sendMessage("Hello, everyone!", slackRoom);
        sarah.sendMessage("Hi John!", slackRoom);
        Ahmed.joinSlackRoom(slackRoom);
        john.sendMessage("How is everyone doing?", slackRoom);
    }
}
