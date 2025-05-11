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
        Post post1 = socialMediaSystem.createPost(user1, "post 1");
        Post post2 = socialMediaSystem.createPost(user1,"post 2");
        Post post3 = socialMediaSystem.createPost(user3, "post 3");
        Post post4 = socialMediaSystem.createPost(user2, "post 4");

        // commenting and replying
        Comment comment = socialMediaSystem.addComment(user2, post1, "Welcome");
        Reply reply = socialMediaSystem.addReply(user1,post1, comment, "Thanks");
        React react1 = socialMediaSystem.addReact(user1, comment, Reaction.LOVE);
        socialMediaSystem.deletePost(user3, post3);
        socialMediaSystem.displayPosts();

        socialMediaSystem.deleteAccount(user4);


}
}
