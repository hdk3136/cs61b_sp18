package byog.lab6;

import edu.princeton.cs.introcs.StdDraw;

import java.awt.Color;
import java.awt.Font;
import java.util.Objects;
import java.util.Random;

public class MemoryGame {
    private final int width;
    private final int height;
    private int round;
    private Random rand;
    private boolean gameOver;
    private boolean playerTurn;
    private static final char[] CHARACTERS = "abcdefghijklmnopqrstuvwxyz".toCharArray();
    private static final String[] ENCOURAGEMENT = {"You can do this!", "I believe in you!",
                                                   "You got this!", "You're a star!", "Go Bears!",
                                                   "Too easy for you!", "Wow, so impressive!"};

    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Please enter a seed");
            return;
        }

        int seed = Integer.parseInt(args[0]);
        MemoryGame game = new MemoryGame(40, 40, seed);
        game.startGame();
    }

    public MemoryGame(int width, int height, int seed) {
        /* Sets up StdDraw so that it has a width by height grid of 16 by 16 squares as its canvas
         * Also sets up the scale so the top left is (0,0) and the bottom right is (width, height)
         */
        this.width = width;
        this.height = height;
        StdDraw.setCanvasSize(this.width * 16, this.height * 16);
        Font font = new Font("Monaco", Font.BOLD, 30);
        StdDraw.setFont(font);
        StdDraw.setXscale(0, this.width);
        StdDraw.setYscale(0, this.height);
        StdDraw.clear(Color.BLACK);
        StdDraw.enableDoubleBuffering();

        //Initialize random number generator
        rand = new Random(seed);
    }

    public String generateRandomString(int n) {
        //Generate random string of letters of length n
        StringBuilder rs = new StringBuilder();
        while (rs.length() < n) {
            rs.append(CHARACTERS[rand.nextInt(CHARACTERS.length)]);
        }
        return rs.toString();
    }

    public void drawFrame(String s) {
        StdDraw.clear(StdDraw.BLACK);
        if (!gameOver) {
            Font font = new Font("Monaco", Font.BOLD, 18);
            StdDraw.setFont(font);
            StdDraw.setPenColor(StdDraw.WHITE);
            StdDraw.textLeft(1, height - 1, "Round: " + round);

            // To keep from the encouragement change too fast, make it stable in specific round
            String encouragement = ENCOURAGEMENT[round % ENCOURAGEMENT.length];
            StdDraw.textRight(width - 1, height - 1, encouragement);
            if (playerTurn) {
                StdDraw.text(width / 2.0, height - 1, "Type!");
            } else {
                StdDraw.text(width / 2.0, height - 1, "Watch!");
            }
        }

        Font bigFont = new Font("Monaco", Font.BOLD, 30);
        StdDraw.setFont(bigFont);
        StdDraw.text(width / 2.0, height / 2.0, s);
        StdDraw.show();
    }

    public void flashSequence(String letters) {
        //Display each character in letters, making sure to blank the screen between letters
        for (char c : letters.toCharArray()) {
            drawFrame(Character.toString(c));
            StdDraw.pause(1000);
            drawFrame("");
            StdDraw.pause(500);
        }
    }

    public String solicitNCharsInput(int n) {
        //Read n letters of player input
        drawFrame("");
        StringBuilder sb = new StringBuilder();
        while (sb.length() < n) {
            if (StdDraw.hasNextKeyTyped()) {
                sb.append(StdDraw.nextKeyTyped());
            }
            drawFrame(sb.toString());
        }
        StdDraw.pause(500);
        return sb.toString();
    }

    public void startGame() {
        //Set any relevant variables before the game starts
        round = 1;
        gameOver = false;
        playerTurn = false;
        while (!gameOver) {
            playerTurn = false;
            drawFrame("Round:" + round);
            StdDraw.pause(1000);
            String randomString = generateRandomString(round);
            flashSequence(randomString);
            playerTurn = true;
            String playerInput = solicitNCharsInput(randomString.length());
            if (Objects.equals(playerInput, randomString)) {
                round += 1;
            } else {
                gameOver = true;
                drawFrame("Game Over! You made it to round:" + round);
                StdDraw.pause(1000);
            }
        }

    }

}
