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

    public User (String id, String name, String phoneNumber) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.friends = new HashMap<>();
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
        System.out.println(this.name + " and " + friend.getName() + " are not friends now");
    }

    private void removeFriend(User friend) {
        friends.remove(friend.getId());
    }
}
