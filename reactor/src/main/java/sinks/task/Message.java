package sinks.task;

import lombok.Data;
import lombok.ToString;

import java.time.LocalDate;
import java.util.concurrent.atomic.AtomicInteger;

@ToString
@Data
public class Message {
    private String message;
    private LocalDate date;
    private AtomicInteger seenCount;

    private SlackMembers slackMembers;

    public Message(String message, LocalDate date, SlackMembers slackMembers) {
        this.message = message;
        this.date = date;
        this.slackMembers = slackMembers;
        seenCount = new AtomicInteger(0);
    }

    public void setSeenCount() {
        seenCount.incrementAndGet();
    }
}
