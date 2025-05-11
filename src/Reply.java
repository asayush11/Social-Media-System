package src;

import java.util.HashMap;
import java.util.Map;

public class Reply implements Content{

    private final String id;
    private String content;
    private final User user;
    private final Map<String, React> reacts;

    public Reply(String id, User user, String content) {
        this.id = id;
        this.user = user;
        this.content = content;
        reacts = new HashMap<>();
    }

    @Override
    public React addReact(String userID, React react) {
        reacts.put(userID, react);
        System.out.println(userID + " reacted " + react.getReaction() + " to this reply");
        return react;
    }

    @Override
    public void removeReact(String userID) {
        System.out.println(userID + " removed Reaction from this reply");
        reacts.remove(userID);
    }

    @Override
    public void display() {
        System.out.println("Reply id: " + this.id + " content: " + this.content + " by " + this.getUser().getName());
        System.out.println("The reactions to this reply are");
        reacts.values().parallelStream().
        forEach(r -> System.out.println(r.getUser().getName() + " reacted " + r.getReaction()));
    }

    public String getContent() {
        return content;
    }

    public void updateContent(String content) {
        System.out.println("Content of reply updated");
        this.content = content;
    }

    public User getUser() {
        return user;
    }

    public String getId() {
        return id;
    }
}
