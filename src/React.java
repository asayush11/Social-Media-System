package src;

public class React {
    private final User user;
    private Reaction reaction;

    public React(User user, Reaction reaction) {
        this.user = user;
        this.reaction = reaction;
    }

    public Reaction getReaction() {
        return reaction;
    }

    public void setReaction(Reaction reaction) {
        this.reaction = reaction;
    }

    public User getUser() {
        return user;
    }
}
