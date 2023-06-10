package sinks.task2;

import org.example.corseUtil.Util;

public class SlackMember {
    private String name;
    private SlackRoom slackRoom;

    public SlackMember() {
        this.name = Util.faker().name().firstName();
    }

    public SlackMember(SlackRoom slackRoom) {
        this.slackRoom = slackRoom;
    }

    public void writeMessage(String message) {
        slackRoom.getMessages().tryEmitNext(message);
    }

    public void joinSlackRoom(SlackRoom slackRoom) {
        if(this.slackRoom == null) {
            this.slackRoom = slackRoom;
            System.out.println(name + " Joined slackRoom: " + slackRoom.getName());
            slackRoom.getFlux().subscribe(item -> {
                System.out.println(name + " Received Message: " + item);
            });
        }
        else {
            System.out.println("No need for Join, Already A member");
        }
    }
}
