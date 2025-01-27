package src;

import java.util.HashMap;
import java.util.Map;

public class Post implements Content{
    private final String id;
    private String content;
    private final User user;
    private final Map<String, React> reacts;
    private final Map<String, Comment> comments;

    public Post(String id, User user, String content) {
        this.id = id;
        this.user = user;
        this.content = content;
        reacts = new HashMap<>();
        comments = new HashMap<>();
    }


    public Comment addComment(String replyID, Comment comment) {
        comments.put(replyID, comment);
        return comment;
    }

    public void removeComment(String userID, String commentID) {
        if( comments.containsKey(commentID) && comments.get(commentID).getUser().getId().equals(userID)) {
            comments.remove(commentID);
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
        System.out.println("Post id: " + this.id + " content: " + this.content);
        System.out.println("The reactions to this post are");
        for (React react : reacts.values())
            System.out.println(react.getUser().getName() + " reacted " + react.getReaction());
        System.out.println("The comments on this post are");
        for (Comment comment : comments.values()) comment.display();
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
