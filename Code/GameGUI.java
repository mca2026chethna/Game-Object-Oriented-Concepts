package Code;

import java.awt.*;
import java.io.File;
import javax.swing.*;

/**
 * GUI for Rock-Paper-Scissors with visual image displays for Player and Computer moves.
 */
public class GameGUI extends JFrame {
    private CardLayout cardLayout;
    private JPanel mainPanel;

    private Player human;
    private Player computer;

    private JLabel scoreLabel;
    private JLabel resultLabel;
    private JLabel playerImageLabel;
    private JLabel computerImageLabel;

    public GameGUI() {
        human = new HumanPlayer("Player");
        computer = new ComputerPlayer("Computer");

        setTitle("Rock Paper Scissors (Visual GUI)");
        setSize(550, 420);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        mainPanel.add(createStartScreen(), "StartScreen");
        mainPanel.add(createGameScreen(), "GameScreen");

        add(mainPanel);
        cardLayout.show(mainPanel, "StartScreen");
        setVisible(true);
    }

    /**
     * Start Screen Panel
     */
    private JPanel createStartScreen() {
        JPanel startPanel = new JPanel(new BorderLayout(15, 15));
        startPanel.setBorder(BorderFactory.createEmptyBorder(30, 20, 30, 20));

        JLabel titleLabel = new JLabel("Rock Paper Scissors", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 28));

        JLabel instructionsLabel = new JLabel(
            "<html><center>Welcome to the OOP Rock-Paper-Scissors Game!<br><br>" +
            "Click 'Start Game' to play against the Computer AI.</center></html>",
            SwingConstants.CENTER
        );
        instructionsLabel.setFont(new Font("Arial", Font.PLAIN, 15));

        JButton startButton = new JButton("Start Game");
        startButton.setFont(new Font("Arial", Font.BOLD, 16));
        startButton.setFocusable(false);
        startButton.addActionListener(e -> cardLayout.show(mainPanel, "GameScreen"));

        startPanel.add(titleLabel, BorderLayout.NORTH);
        startPanel.add(instructionsLabel, BorderLayout.CENTER);
        startPanel.add(startButton, BorderLayout.SOUTH);

        return startPanel;
    }

    /**
     * Game Screen Panel with Image Displays
     */
    private JPanel createGameScreen() {
        JPanel gamePanel = new JPanel(new BorderLayout(15, 15));
        gamePanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        // Score display (Top)
        scoreLabel = new JLabel("Player: 0  |  Computer: 0", SwingConstants.CENTER);
        scoreLabel.setFont(new Font("Arial", Font.BOLD, 18));
        gamePanel.add(scoreLabel, BorderLayout.NORTH);

        // Center Area: Images for Player vs Computer
        JPanel centerPanel = new JPanel(new BorderLayout(10, 10));

        JPanel matchPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 10));

        // Player move display
        JPanel playerBox = new JPanel(new BorderLayout());
        JLabel pTitle = new JLabel("Your Choice", SwingConstants.CENTER);
        pTitle.setFont(new Font("Arial", Font.BOLD, 14));
        playerImageLabel = new JLabel();
        playerImageLabel.setPreferredSize(new Dimension(120, 120));
        playerImageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        playerImageLabel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        playerBox.add(pTitle, BorderLayout.NORTH);
        playerBox.add(playerImageLabel, BorderLayout.CENTER);

        // VS Label
        JLabel vsLabel = new JLabel("VS");
        vsLabel.setFont(new Font("Arial", Font.BOLD, 22));

        // Computer move display
        JPanel computerBox = new JPanel(new BorderLayout());
        JLabel cTitle = new JLabel("Computer", SwingConstants.CENTER);
        cTitle.setFont(new Font("Arial", Font.BOLD, 14));
        computerImageLabel = new JLabel();
        computerImageLabel.setPreferredSize(new Dimension(120, 120));
        computerImageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        computerImageLabel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        computerBox.add(cTitle, BorderLayout.NORTH);
        computerBox.add(computerImageLabel, BorderLayout.CENTER);

        matchPanel.add(playerBox);
        matchPanel.add(vsLabel);
        matchPanel.add(computerBox);

        // Outcome Result Text
        resultLabel = new JLabel("Select Rock, Paper, or Scissors to begin!", SwingConstants.CENTER);
        resultLabel.setFont(new Font("Arial", Font.BOLD, 15));

        centerPanel.add(matchPanel, BorderLayout.CENTER);
        centerPanel.add(resultLabel, BorderLayout.SOUTH);

        gamePanel.add(centerPanel, BorderLayout.CENTER);

        // Action Buttons (Bottom)
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        JButton rockButton = new JButton("Rock");
        JButton paperButton = new JButton("Paper");
        JButton scissorsButton = new JButton("Scissors");

        rockButton.setFocusable(false);
        paperButton.setFocusable(false);
        scissorsButton.setFocusable(false);

        rockButton.addActionListener(e -> playRound("Rock"));
        paperButton.addActionListener(e -> playRound("Paper"));
        scissorsButton.addActionListener(e -> playRound("Scissors"));

        buttonPanel.add(rockButton);
        buttonPanel.add(paperButton);
        buttonPanel.add(scissorsButton);

        gamePanel.add(buttonPanel, BorderLayout.SOUTH);

        return gamePanel;
    }

    /**
     * Helper to load and scale images from the 'images' folder
     */
    private ImageIcon loadScaledIcon(String moveName) {
        String path = "images" + File.separator + moveName.toLowerCase() + ".png";
        File file = new File(path);

        if (file.exists()) {
            ImageIcon icon = new ImageIcon(path);
            Image img = icon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
            return new ImageIcon(img);
        } else {
            return null; // Fallback text will show if image is missing
        }
    }

    /**
     * Executes round logic and updates UI images & text
     */
    private void playRound(String humanChoice) {
        String pMove = human.makeMove(humanChoice);
        String cMove = computer.makeMove(null);

        // Update Image Labels
        ImageIcon pIcon = loadScaledIcon(pMove);
        ImageIcon cIcon = loadScaledIcon(cMove);

        if (pIcon != null) {
            playerImageLabel.setIcon(pIcon);
            playerImageLabel.setText("");
        } else {
            playerImageLabel.setIcon(null);
            playerImageLabel.setText(pMove);
        }

        if (cIcon != null) {
            computerImageLabel.setIcon(cIcon);
            computerImageLabel.setText("");
        } else {
            computerImageLabel.setIcon(null);
            computerImageLabel.setText(cMove);
        }

        // Evaluate Round Winner
        if (pMove.equals(cMove)) {
            resultLabel.setText("It's a Tie! Both chose " + pMove);
            resultLabel.setForeground(Color.DARK_GRAY);
        } else if ((pMove.equals("Rock") && cMove.equals("Scissors")) ||
                   (pMove.equals("Paper") && cMove.equals("Rock")) ||
                   (pMove.equals("Scissors") && cMove.equals("Paper"))) {
            human.incrementScore();
            resultLabel.setText("You Win! " + pMove + " beats " + cMove);
            resultLabel.setForeground(new Color(0, 128, 0));
        } else {
            computer.incrementScore();
            resultLabel.setText("Computer Wins! " + cMove + " beats " + pMove);
            resultLabel.setForeground(Color.RED);
        }

        scoreLabel.setText("Player: " + human.getScore() + "  |  Computer: " + computer.getScore());
    }
}