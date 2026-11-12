import java.util.Scanner;

/**
 * guessTheNumberGame
 */
public class guessTheNumberGame {

    private int toBeGuessed;
    private int guess;
    private Scanner scan;

    public guessTheNumberGame() {
        this.toBeGuessed = (int)(Math.random() * 101);
        this.scan = new Scanner(System.in);
        this.gameLoop();
    }

    public void gameLoop() {

        boolean gameOver = false;

        while (!gameOver) {
            this.userGuess();

            if (this.isGuessCorrect()) {
                gameOver = true;
                System.out.println("Correct!");
            } else {
                if (this.isGuessHigher()) {
                    System.out.println("Guess to high");
                } else {
                    System.out.println("Guess to low");
                }
            }
        }
    }

    public boolean isGuessCorrect() {
        return this.getGuess() == this.getToBeGuessed();
    }

    public boolean isGuessHigher() {
        return this.getGuess() > this.getToBeGuessed();
    }

    public void userGuess() {
        System.out.println("Guess a number: ");
        this.guess = Integer.parseInt(this.scan.nextLine());
    }

    public void setGuess(int newGuess) {
        this.guess = newGuess;
    }

    public int getGuess() {
        return this.guess;
    }

    public int getToBeGuessed() {
        return this.toBeGuessed;
    }

    public static void main(String[] args) {
        new guessTheNumberGame();
    }
}
