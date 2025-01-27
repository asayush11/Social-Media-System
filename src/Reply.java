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
       if(reacts.containsKey(userID)) {
           reacts.remove(userID);
           reacts.put(userID, react);
           System.out.println(userID + " reacted " + react.getReaction() + " to this reply");
           return react;
       }
        reacts.put(userID, react);
       return react;
    }

    @Override
    public void removeReact(String userID) {
        System.out.println(userID + " removed Reaction from this reply");
        reacts.remove(userID);
    }

    @Override
    public void display() {
        System.out.println("Reply id: " + this.id + " content: " + this.content);
        System.out.println("The reactions to this reply are");
        for (React react : reacts.values())
            System.out.println(react.getUser().getName() + " reacted " + react.getReaction());
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        System.out.println("Content of reply updated");
        this.content = content;
    }

    public User getUser() {
        return user;
    }
}
