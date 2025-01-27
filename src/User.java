package src;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class User {
    private final String id;
    private final String name;
    private final String phoneNumber;
    private final Map<String, User> friends;
    private final Map<String, User> requests;
    private final Map<String,Post> posts;

    public User (String id, String name, String phoneNumber) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.friends = new HashMap<>();
        this.posts = new HashMap<>();
        this.requests = new HashMap<>();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void sendRequest (User friend) {
        if(friends.containsKey(friend.getId())){
            System.out.println("You are already friends with " + friend.getName());
            return;
        }
        if(requests.containsKey(friend.getId())) {
            System.out.println(friend.getName() + " already requested so we are making you friend");
            addFriend(friend);
            friend.addFriend(this);
            return;
        }
        friend.addRequest(this);
    }

    private void addRequest( User sender) {
        if(requests.containsKey(sender.getId())){
            System.out.println("You have already sent friend request");
            return;
        }
        requests.put(sender.getId(), sender);
        System.out.println(sender.getName() + " sent request to " + this.getName());
    }

    public void acceptRequest() {
        for( User request : requests.values() ) {
            friends.put(request.getId(), request);
            request.addFriend(this);
            System.out.println(this.name + " and " + request.getName() + " are friends now");
        }
        requests.clear();
    }

    private void addFriend(User friend) {
        friends.put(friend.getId(), friend);
        requests.remove(friend.getId());
    }

    public void unfriend(User friend) {
        removeFriend(friend);
        friend.removeFriend(this);
       // friends.remove(friend.getId());
       // friend.unfriend(this);
        System.out.println(this.name + " and " + friend.getName() + " are not friends now");
    }

    private void removeFriend(User friend) {
        friends.remove(friend.getId());
    }

    public Post createPost (String content) {
        String id = "P" + UUID.randomUUID().toString().substring(0,8);
        Post post = new Post(id, this, content);
        posts.put(id, post);
        System.out.println(this.name + " created a post");
        return post;
    }

    public void deletePost (Post post) {
        posts.remove(post.getId());
        System.out.println(this.name + " deleted a post");
    }

    public Comment addComment (Post post, String content) {
        String id = "C" + UUID.randomUUID().toString().substring(0,8);
        Comment comment = new Comment(id, this, content);
        System.out.println(this.name + " commented on post of " + post.getUser().getName());
        return post.addComment(id, comment);
    }

    public void deleteComment (Post post, Comment comment) {
        post.removeComment(this.getId(), comment.getId());
        System.out.println(this.name + " removed comment form the post of " + post.getUser().getName());
    }

    public Reply addReply (Comment comment, String content) {
        String id = "R" + UUID.randomUUID().toString().substring(0,8);
        Reply reply = new Reply(id, this, content);
        System.out.println(this.name + " replied on comment of " + comment.getUser().getName());
        return comment.addReply(id, reply);
    }

    public void deleteReply (Comment comment, Reply reply) {
        comment.removeReply(this.getId(), comment.getId());
        System.out.println(this.name + " removed reply form the comment of " + comment.getUser().getName());
    }

    public React addReact (Content content, Reaction reaction) {
        String id = "react" + UUID.randomUUID().toString().substring(0,8);
        React react = new React(id, this, reaction);
        System.out.println(this.name + " reacted " + reaction );
        return content.addReact(this.getId(), react);
    }

    public void removeReact (Content content) {
        content.removeReact(this.id);
        System.out.println(this.name + " removed reaction " );
    }

    public void displayPosts() {
        System.out.println("Displaying posts of " + this.getName());
        for(Post post : posts.values()) post.display();
    }
}
