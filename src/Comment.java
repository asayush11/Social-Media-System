package src;

import java.util.HashMap;
import java.util.Map;

public class Comment implements Content{
    private final String id;
    private String content;
    private final User user;
    private final Map<String, React> reacts;
    private final Map<String, Reply> replies;

    public Comment(String id, User user, String content) {
        this.id = id;
        this.user = user;
        this.content = content;
        reacts = new HashMap<>();
        replies = new HashMap<>();
    }


    public Reply addReply(String replyID, Reply reply) {
        replies.put(replyID, reply);
        return reply;
    }

    public void removeReply(String userID, String replyID) {
        if(replies.containsKey(replyID) && replies.get(replyID).getUser().getId().equals(userID)) {
            replies.remove(replyID);
        }
    }

    @Override
    public React addReact(String userID, React react) {
        if(reacts.containsKey(userID)) {
            reacts.remove(userID);
            reacts.put(userID, react);
            return react;
        }
        reacts.put(userID, react);
        return react;
    }

    @Override
    public void removeReact(String userID) {
        reacts.remove(userID);
    }

    @Override
    public void display() {
        System.out.println("Comment id: " + this.id + " content: " + this.content);
        System.out.println("The reactions to this comment are");
        for (React react : reacts.values())
            System.out.println(react.getUser().getName() + " reacted " + react.getReaction());
        System.out.println("The replies on this comment are");
        for (Reply reply : replies.values()) reply.display();
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public User getUser() {
        return user;
    }

    public String getId() {
        return id;
    }
}
