package Code;

/**
 * Represents the Human Player. Inherits from Player.
 */
public class HumanPlayer extends Player {

    public HumanPlayer(String name) {
        super(name);
    }

    @Override
    public String makeMove(String choice) {
        return choice; // Uses button selection
    }
}