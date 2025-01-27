package src;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

public class SocialMediaSystem {
    private static SocialMediaSystem instance;
    private final Map<String, User> users;

    private SocialMediaSystem(){
        users = new HashMap<>();
    }

    public synchronized static SocialMediaSystem getInstance() {
        if(instance == null) {
            instance = new SocialMediaSystem();
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
}
