package src;

public interface Content {
     public React addReact(String userID, React react);

     void removeReact(String userID);

    void display();
}
