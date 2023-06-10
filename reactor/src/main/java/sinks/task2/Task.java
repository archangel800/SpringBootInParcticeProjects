package sinks.task2;

public class Task
{
    public static void main(String[] args) {
        SlackRoom slackRoom = new SlackRoom("Giorgi's Slack Room");

        SlackMember giorgi = new SlackMember();
        SlackMember mate = new SlackMember();
        SlackMember tato = new SlackMember();

        giorgi.joinSlackRoom(slackRoom);
        mate.joinSlackRoom(slackRoom);
        giorgi.writeMessage("Ravaxar mate dzamia");
        mate.writeMessage("Kargad gio shen rogor xar");
        tato.joinSlackRoom(slackRoom);
        tato.writeMessage("Vaa bichebo rogor xart ee");
    }
}
