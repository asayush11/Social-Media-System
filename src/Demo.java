package src;

public class Demo {
    public static void main(String[] args) {
        SocialMediaSystem socialMediaSystem = SocialMediaSystem.getInstance();

        // create users
        User user1 = socialMediaSystem.registerUser("Alice", "123456789");
        User user = socialMediaSystem.registerUser("Alice", "123456789");
        User user2 = socialMediaSystem.registerUser("Bob", "123456788");
        User user3 = socialMediaSystem.registerUser("Charlie", "123456787");
        User user4 = socialMediaSystem.registerUser("David", "123456786");

        // adding friends
        user1.sendRequest(user2);
        user1.sendRequest(user2);
        user3.sendRequest(user1);
        user4.sendRequest(user1);
        user1.acceptRequest();
        user2.acceptRequest();
        user1.unfriend(user4);

        // creating posts
        Post post1 = user1.createPost("post 1");
        Post post2 = user1.createPost("post 2");
        Post post3 = user3.createPost("post 3");
        Post post4 = user2.createPost("post 4");

        // commenting and replying
        Comment comment = user2.addComment(post1, "Welcome");
        Reply reply = user1.addReply(comment, "Thanks");
        React react1 = user1.addReact(comment, Reaction.LOVE);
        user3.deletePost(post3);
        user1.displayPosts();

        socialMediaSystem.deleteAccount(user4);


}
}
