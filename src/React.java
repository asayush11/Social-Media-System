package src;

public class React {
    private final String id;
    private final User user;
    private Reaction reaction;

    public React(String id, User user, Reaction reaction) {
        this.id = id;
        this.user = user;
        this.reaction = reaction;
    }

    public Reaction getReaction() {
        return reaction;
    }

    public void setReaction(Reaction reaction) {
        this.reaction = reaction;
    }

    public String getId() {
        return id;
    }

    public User getUser() {
        return user;
    }
}
