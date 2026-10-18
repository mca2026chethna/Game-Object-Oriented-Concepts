package Code;

/**
 * Abstract class representing a general Player.
 * Demonstrates Abstraction and Encapsulation.
 */
public abstract class Player {
    private String name;
    private int score;

    public Player(String name) {
        this.name = name;
        this.score = 0;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public void incrementScore() {
        this.score++;
    }

    // Abstract method to demonstrate Polymorphism in subclasses
    public abstract String makeMove(String choice);
}