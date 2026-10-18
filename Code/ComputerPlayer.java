package Code;

import java.util.Random;

/**
 * Represents the Computer AI. Inherits from Player.
 */
public class ComputerPlayer extends Player {
    private String[] choices = {"Rock", "Paper", "Scissors"};
    private Random random;

    public ComputerPlayer(String name) {
        super(name);
        this.random = new Random();
    }

    @Override
    public String makeMove(String choice) {
        // AI selects move randomly
        return choices[random.nextInt(3)];
    }
}