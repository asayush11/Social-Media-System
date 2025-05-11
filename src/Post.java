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
        reacts.values().parallelStream()
                .forEach(r -> System.out.println(r.getUser().getName() + " reacted " + r.getReaction()));
        System.out.println("The comments on this post are");
        comments.values()
                .forEach(Comment::display);
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
    public Reply addReplyToComment(String commentID, Reply reply){
        if(comments.containsKey(commentID)) return comments.get(commentID).addReply(reply.getId(), reply);
        return null;
    }

    public void deleteReplyFromComment(User user, String commentID, Reply reply){
        if(comments.containsKey(commentID)) comments.get(commentID).removeReply(user.getId(), reply.getId());
    }

}
