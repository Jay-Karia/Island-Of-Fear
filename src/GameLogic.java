package src;
import src.colors.Colors;
import java.util.Scanner;
import src.GameClasses.*;
public interface GameLogic {
    static void printSeparator(int n) {
        for (int i=1;i<=n;i++) {
            System.out.print(Colors.BLACK_BOLD+"-");
        }
        System.out.println();
    }
    static void printHeader(String header, int n) {
        printSeparator(n);
        System.out.println(Colors.BLACK_BOLD+header);
        printSeparator(n);
    }

    static String getInput(String statement, String[] options, String[] keys) {
        Scanner sc = new Scanner(System.in);

        System.out.println(Colors.ANSI_BLACK+statement);
        for (int i=0;i<options.length;i++) {
            System.out.println(Colors.ANSI_BLACK+options[i]+" ["+keys[i]+"]");
        }
        System.out.print("> ");
        String choice = sc.next().charAt(0)+"".toLowerCase();

        return choice;
    }

    static void readStory() {
        System.out.println("This is the story of the game");
    }

    static void createNewGame() throws InterruptedException {
        // Initialize Game Variables
        Game newGame = new Game();
    }

    static void displayDeath() {
        System.out.println(Colors.RED_BOLD+"You Died!");
    }
}
