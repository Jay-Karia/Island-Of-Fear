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
        int p = -1;
        String choice = "";
        do {
            System.out.print("> ");
            choice = sc.next().charAt(0)+"".toLowerCase();

            for (int i = 0; i<keys.length;i++) {
                if (choice.equalsIgnoreCase(keys[i])) {
//                    System.out.println(options[i]);
                    p = -1;
                    break;
                } else
                    p = 0;

            }
            if (p==0)
                System.out.println("Invalid Command");
        } while (p==0);

        return choice;
    }

    static void readStory() throws InterruptedException {
        writeAnimation("This is the story of the game");
    }


    static void displayDeath() {
        System.out.println(Colors.RED_BOLD+"You Died!");
    }

    static void writeAnimation(String sentence) throws InterruptedException {
        int delay = 50;
        int l = sentence.length();
        for (int i=0;i<l;i++) {
            Thread.sleep(delay+(i* 2L));
            System.out.print(sentence.charAt(i));
        }
    }

    static void createNewGame() throws InterruptedException {
        // Initialize Game Variables
        Game game = new Game();
        game.actI();
    }
}
