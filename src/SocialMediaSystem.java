package src;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

public class SocialMediaSystem {
    private static volatile SocialMediaSystem instance;
    private final Map<String, User> users;
    private final Map<String, Post> posts;

    private SocialMediaSystem(){
        posts = new HashMap<>();
        users = new HashMap<>();
    }

    public synchronized static SocialMediaSystem getInstance() {
        if(instance == null) {
            synchronized (SocialMediaSystem.class){
                if(instance == null){
                    instance = new SocialMediaSystem();
                }
            }
        }
        return instance;
    }

    public User registerUser(String name, String phoneNumber) {
        for(User user : users.values()) {
            if(Objects.equals(user.getPhoneNumber(), phoneNumber)) {
                System.out.println("Account already exists with this number");
                return user;
            }
        }
        String id = "US" + UUID.randomUUID().toString().substring(0,8);
        User user = new User(id, name, phoneNumber);
        users.put(id, user);
        System.out.println("Account created for " + user.getName());
        return user;
    }

    public void deleteAccount(User user) {
        users.remove(user.getId());
        System.out.println("Account deleted for " + user.getName());
    }

    public Post createPost (User user, String content) {
        String userID = user.getId();
        if(!users.containsKey(userID)) return null;
        String id = "P" + UUID.randomUUID().toString().substring(0,8);
        Post post = new Post(id, users.get(userID), content);
        posts.put(id, post);
        System.out.println(user.getName() + " created a post");
        return post;
    }

    public void deletePost (User user, Post post) {
        if(posts.containsKey(post.getId()) && posts.get(post.getId()).getUser().getId().equals(user.getId()))
            posts.remove(post.getId());
        System.out.println(user.getName() + " deleted a post");
    }

    public Comment addComment (User user, Post post, String content) {
        if(!users.containsKey(user.getId())) return null;
        String id = "C" + UUID.randomUUID().toString().substring(0,8);
        Comment comment = new Comment(id, user, content);
        System.out.println(user.getName() + " commented on post of " + post.getUser().getName());
        return post.addComment(id, comment);
    }

    public void deleteComment (User user, Post post, Comment comment) {
        post.removeComment(user.getId(), comment.getId());
        System.out.println(user.getName() + " removed comment form the post of " + post.getUser().getName());
    }

    public Reply addReply (User user, Post post, Comment comment, String content) {
        String id = "R" + UUID.randomUUID().toString().substring(0,8);
        if(posts.containsKey(post.getId())) {
            Reply reply = new Reply(id, user, content);
            System.out.println(user.getName() + " replied on comment of " + comment.getUser().getName());
            return posts.get(post.getId()).addReplyToComment(id, reply);
        }
        return null;
    }

    public void deleteReply (User user, Post post, Comment comment, Reply reply) {
        if(posts.containsKey(post.getId())) {
            post.deleteReplyFromComment(user, comment.getId(), reply);
            System.out.println(user.getName() + " removed reply form the comment of " + comment.getUser().getName());
        }
    }

    public React addReact (User user, Content content, Reaction reaction) {
        if(!users.containsKey(user.getId())) return null;
        String id = "react" + UUID.randomUUID().toString().substring(0,8);
        React react = new React(user, reaction);
        System.out.println(user.getName()+ " reacted " + reaction );
        return content.addReact(user.getId(), react);
    }

    public void removeReact (User user, Content content) {
        if(!users.containsKey(user.getId())) return;
        content.removeReact(user.getId());
        System.out.println(user.getName() + " removed reaction " );
    }

    public void displayPosts() {
        System.out.println("Displaying posts ");
        posts.values()
                .forEach(Post::display);
    }
}
