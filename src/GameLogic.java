package src;
import src.colors.Colors;
import java.util.*;

import src.GameClasses.*;
public class GameLogic {
    public static void printSeparator(int n) {
        for (int i=1;i<=n;i++) {
            System.out.print(Colors.BLACK_BOLD+"-");
        }
        System.out.println();
    }
    public static void printHeader(String header, int n) {
        printSeparator(n);
        System.out.println(Colors.BLACK_BOLD+header);
        printSeparator(n);
    }

    public static String getInput(String statement, String[] options, String[] keys) {
        Scanner sc = new Scanner(System.in);

        System.out.println(Colors.ANSI_RESET+statement);
        for (int i=0;i<options.length;i++) {
            System.out.println(Colors.ANSI_RESET+options[i]+Colors.BLACK_BOLD+" ["+keys[i]+"]");
        }
        int p = -1;
        String choice;
        do {
            System.out.print("> ");
            choice = sc.next().charAt(0)+"".toLowerCase();

            for (String key : keys) {
                if (choice.equalsIgnoreCase(key)) {
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

    public static void readStory() throws InterruptedException {
        writeAnimation("This is the story of the game");
    }

    public static void writeAnimation(String sentence) throws InterruptedException {
        int delay = 50;
        int l = sentence.length();
        for (int i=0;i<l;i++) {
            Thread.sleep(delay+(i* 2L));
            System.out.print(sentence.charAt(i));
        }
    }

    public static void createNewGame() throws InterruptedException {
        // Initialize Game Variables
        Game game = new Game();
        game.actI();
    }

    public static void genFightSummary(double ammoUsed, double healthLost, double totalAttacks, double totalEnemyAttacks) throws InterruptedException {
        Thread.sleep(2000);
        System.out.println();
        GameLogic.printHeader(Colors.BLACK_BOLD+"Fight Summary"+Colors.ANSI_RESET, 15);
        System.out.println(Colors.ANSI_RESET+"Ammo/Health: "+Colors.BLACK_BOLD+ammoUsed+" 🔫"+Colors.ANSI_RESET);
        System.out.println("Health Lost: "+Colors.BLACK_BOLD+healthLost+" 💖"+Colors.ANSI_RESET);
        System.out.println("Total Attacks: "+Colors.BLACK_BOLD+totalAttacks+" 🏹"+Colors.ANSI_RESET);
        System.out.println("Total Enemy Attacks: "+Colors.BLACK_BOLD+totalEnemyAttacks+" 🏹"+Colors.ANSI_RESET);

        Thread.sleep(2000);
    }

    public static int[] genLoot(Enemy enemy) throws InterruptedException {
        // generates loots depending on the enemy
        System.out.println("");
        int lootCode = enemy.lootCode - 1;
        Random rand = new Random();

        int[] minCoins = new int[] {200, 300, 600, 1000, 1700};
        int[] maxCoins = new int[] {300, 550, 800, 1500, 2000};

        int[] minRubies = new int[] {0, 1, 2, 4, 7};
        int[] maxRubies = new int[] {0, 2, 3, 5, 10};

        int[] minAmmo = new int[] {2, 5, 10, 15, 20};
        int[] maxAmmo = new int[] {5, 9, 14, 20, 25};

        String[] potions = new String[] {};

        int coins = rand.nextInt(maxCoins[lootCode]-minCoins[lootCode]) + minCoins[lootCode];
        int rubies = rand.nextInt(maxRubies[lootCode]-minRubies[lootCode]) + minRubies[lootCode];
        int ammo = rand.nextInt(maxAmmo[lootCode]-minAmmo[lootCode]) + minAmmo[lootCode];

        Thread.sleep(3000); // 3 seconds

        printHeader("Loot", 20);
        System.out.println(Colors.YELLOW_BOLD_BRIGHT+"Coins: "+coins+" ⭕"+(rubies==0 ? "" :Colors.BLUE_BOLD_BRIGHT+"\nRubies: " + rubies+" 💎"+Colors.WHITE_BOLD_BRIGHT+"\nAmmo: "+ammo+" 🔫"+Colors.ANSI_RESET));
        String choice = getInput("", new String[] {"Pick Up"}, new String[] {"p"});

        int[] loot = new int[] {coins, rubies, ammo};

        return loot;
    }
}
