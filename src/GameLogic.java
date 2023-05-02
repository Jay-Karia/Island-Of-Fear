package src;

import src.colors.Colors;

import java.util.*;

import src.GameClasses.*;

public interface GameLogic {
    static void printSeparator(int n) {
        for (int i = 1; i <= n; i++) {
            System.out.print(Colors.BLACK_BOLD + "-");
        }
        System.out.println();
    }

    static void printHeader(String header, int n) {
        printSeparator(n);
        System.out.println(Colors.BLACK_BOLD + header);
        printSeparator(n);
    }

    static String getInput(String statement, String[] options, String[] keys) {
        Scanner sc = new Scanner(System.in);

        System.out.println(Colors.ANSI_RESET + statement);
        for (int i = 0; i < options.length; i++) {
            System.out.println(Colors.ANSI_RESET + options[i] + Colors.BLACK_BOLD + " [" + keys[i] + "]");
        }
        int p = -1;
        String choice;
        do {
            System.out.print("> ");
            choice = sc.next().charAt(0) + "".toLowerCase();

            for (String key : keys) {
                if (choice.equalsIgnoreCase(key)) {
                    p = -1;
                    break;
                } else
                    p = 0;

            }
            if (p == 0)
                System.out.println("Invalid Command");
        } while (p == 0);

        return choice;
    }

    static void readStory() throws InterruptedException {
        writeAnimation("This is the story of the game");
    }

    static void writeAnimation(String sentence) throws InterruptedException {
        int delay = 50;
        int l = sentence.length();
        for (int i = 0; i < l; i++) {
            Thread.sleep(delay + (i * 2L));
            System.out.print(sentence.charAt(i));
        }
    }

    static void createNewGame() throws InterruptedException {
        // Initialize Game Variables
        Game game = new Game();
        game.actI();
    }

    static void genFightSummary(double ammoUsed, double healthLost, double totalAttacks, double totalEnemyAttacks) throws InterruptedException {
        Thread.sleep(2000);
        System.out.println();
        GameLogic.printHeader(Colors.BLACK_BOLD + "Fight Summary" + Colors.ANSI_RESET, 15);
        System.out.println(Colors.ANSI_RESET + "Ammo/Health: " + Colors.BLACK_BOLD + ammoUsed + " 🔫" + Colors.ANSI_RESET);
        System.out.println("Health Lost: " + Colors.BLACK_BOLD + healthLost + " 💖" + Colors.ANSI_RESET);
        System.out.println("Total Attacks: " + Colors.BLACK_BOLD + totalAttacks + " 🏹" + Colors.ANSI_RESET);
        System.out.println("Total Enemy Attacks: " + Colors.BLACK_BOLD + totalEnemyAttacks + " 🏹" + Colors.ANSI_RESET);

        Thread.sleep(2000);
    }

    static int[] genLoot(Enemy enemy) throws InterruptedException, IllegalArgumentException {
        // generates loots depending on the enemy
        System.out.println("");
        int lootCode = enemy.lootCode - 1;
        Random rand = new Random();

        int[] minCoins = new int[]{200, 300, 600, 1000, 1700};
        int[] maxCoins = new int[]{300, 550, 800, 1500, 2000};

        int[] minRubies = new int[]{0, 1, 2, 4, 7};
        int[] maxRubies = new int[]{0, 2, 3, 5, 10};

        int[] minAmmo = new int[]{2, 5, 10, 15, 20};
        int[] maxAmmo = new int[]{5, 9, 14, 20, 25};

        String[] potions = new String[]{};

        int rubies = 0;
        int coins = rand.nextInt(maxCoins[lootCode] - minCoins[lootCode]) + minCoins[lootCode];
        if (lootCode == 0)
            rubies = 0;
        else
            rubies = rand.nextInt(maxRubies[lootCode] - minRubies[lootCode]) + minRubies[lootCode];

        int ammo = rand.nextInt(maxAmmo[lootCode] - minAmmo[lootCode]) + minAmmo[lootCode];

        Thread.sleep(3000); // 3 seconds

        printHeader("Loot", 10);
        System.out.println(Colors.YELLOW_BOLD_BRIGHT + "Coins: " + coins + " ⭕" + (rubies == 0 ? "" : Colors.BLUE_BOLD_BRIGHT + "\nRubies: " + rubies + " 💎" + Colors.WHITE_BOLD_BRIGHT + "\nAmmo: " + ammo + " 🔫" + Colors.ANSI_RESET));
        getInput("", new String[]{"Pick Up"}, new String[]{"p"});

        int[] loot = new int[]{coins, rubies, ammo};

        return loot;
    }

    static void buyAmmo(Player player, Weapon weapon, int[] cost, int[] qty) throws InterruptedException {
        GameLogic.printHeader(weapon.name + " Ammo", 20);
        String[] options = new String[cost.length];
        String[] keys = new String[cost.length];
        for (int j = 0; j < qty.length; j++) {
            options[j] = qty[j] + " 🔫" + Colors.YELLOW_BOLD + "(" + cost[j] + ")";
            keys[j] = j + 1 + "";
        }
        String bundle = GameLogic.getInput("", options, keys);
        int index = Integer.parseInt(bundle) - 1;

        if (player.coins >= cost[index]) {
            // Buying ammo
            weapon.ammo += qty[index];
            player.coins -= cost[index];
            System.out.println( Colors.YELLOW_BOLD + qty[index] + " " + Colors.GREEN_BOLD+weapon.name+" Ammo Successfuly bought! " + Colors.RED_BOLD + "(-"+cost[index]+")");
        } else {
            System.out.println(Colors.RED_BOLD + "You dont's have enough money to buy ammo!");
        }
        getInput("Enter any key to continue...", new String[]{}, new String[]{});
    }

    static int genFight(Player player, Enemy enemy, Potion[] potions, Potion selectedPotion) throws InterruptedException {
        int p = -1;

        do {
            switch (GameLogic.getInput("", new String[]{"Fight 🔪", "Run Away 🏃‍♂️", "Drink Potion 🍾", "Check Stats 📊"}, new String[]{"f", "r", "p", "c"})) {
                case "f": {
                    // Fight
                    player.fight(enemy, selectedPotion); // skeleton and selected potion
                    p = -1;
                    break;
                }
                case "r": {
                    System.out.println(Colors.BLACK_BOLD + "\nWarriors are the ones who always try");
                    p = -1;
                    break;
                }
                case "p": {
                    // Drink Potion
                    if (player.checkPotions() == 1) {
                        // use potion
                        String[] options = new String[player.potions.length];
                        String[] keys = new String[player.potions.length];

                        for (int i = 0; i < options.length; i++) {
                            options[i] = player.potions[i].name + " x" + player.qty[i];
                            keys[i] = player.potions[i].name.substring(player.potions[i].name.length() - 2, player.potions[i].name.length() - 1);
                        }


                        String choice = GameLogic.getInput("", options, keys);

                        for (int i = 0; i < keys.length; i++) {
                            if (keys[i].equals(choice)) {
                                selectedPotion = potions[i];
                                player.qty[i] -= 1;
                            }
                        }
                    } else {
                        System.out.println(Colors.ANSI_RESET + "You currently have " + Colors.RED_BOLD + "0" + Colors.ANSI_RESET + " potions");
                        GameLogic.getInput("\nEnter any key to continue...", new String[]{}, new String[]{});
                    }
                    p = 0;
                    break;
                }
                case "c": {
                    player.checkStats();
                    p = 0;
                }
            }
        } while (p == 0);
        return p;
    }

    static void openMap(int act) {
        String[] maps = new String[] {
                """
                |   _____| n |_____     \\_
                |   |_|    .    |_|____
                |   |_|    R1  . w ____
                |   |_|    .    |_|    
                |   |_|__| e ___|_|    
                |   |      [G]     \\  
                |   |     _____     \\ 
                |   |    /    |     |  
                |   |   \\     |  *  | 
                |    \\ * \\    |     |
                |     \\   \\   |  *  |
                |     |   |   |     |  
                |     |   |    \\   /  
                |_____| l |____| r |__________________________________                                                                                                                                                                                                                                                      |
                |                                                     |                                                                                                                                                                                                                                                     |
                |________________________________________________________
                """
        };
        System.out.println(Colors.BLACK_BOLD+maps[act-1]);
        GameLogic.getInput("Enter any key to continue...", new String[]{}, new String[]{});
    }
}