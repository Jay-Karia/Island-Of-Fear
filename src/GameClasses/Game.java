package src.GameClasses;

import src.GameLogic;
import src.colors.Colors;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

public class Game {
    public Player player;
    public Enemy[] enemies;
    public Weapon[] weapons;
    public Potion[] potions;
    public Potion selectedPotion;

    public Game() throws InterruptedException {

        // Player
        Player player = new Player();
        this.player = player;

        // Enemies
        Enemy Skeleton = new Enemy("Skeleton", 50, 10, 5, Colors.BLACK_BOLD, 1); // loot code: 1
        Enemy Zombie = new Enemy("Zombie", 75, 8.5, 7, Colors.GREEN_BOLD, 1); // 1
        Enemy Goblin = new Enemy("Goblin", 90, 7, 10, Colors.GREEN_BOLD, 1); // 2
        Enemy Org = new Enemy("Org", 120, 6.5, 12, Colors.GREEN_BOLD, 2); // 2
        Enemy Witch = new Enemy("Witch", 170, 5.5, 15, Colors.PURPLE_BOLD, 2); // 3
        Enemy Vampire = new Enemy("Vampire", 190, 5, 17.5, Colors.PURPLE_BOLD, 3); // 3
        Enemy Werewolf = new Enemy("Werewolf", 220, 4.5, 19, Colors.BLACK_BOLD, 3); // 3
        Enemy Golem = new Enemy("Golem", 240, 4, 22, Colors.BLACK_BOLD, 3); // 4
        Enemy Wizard = new Enemy("Wizard", 280, 3.5, 25, Colors.PURPLE_BOLD, 4); // 4
        Enemy Pharaoh = new Enemy("Pharaoh", 280, 3.5, 25, Colors.YELLOW_BOLD, 4); // 4
        Enemy Dragon = new Enemy("Dragon", 300, 3, 27, Colors.RED_BOLD, 5); // 5
        Enemy Phoenix = new Enemy("Phoenix", 350, 2.5, 30, Colors.YELLOW_BOLD, 5); // 5

        // Potions
        Potion rage = new Potion("Potion of Rage", 10, "+150% damage", 2, "d");
        Potion shield = new Potion("Protector's Shield", 10, "50% less damage", 3, "s");
        Potion regeneration = new Potion("Potion of Regeneration", 0, "Restores 100% of health", 5, "r");
        Potion time = new Potion("Sorcerer of Time", 10, "Slows down enemies", 7, "t");
        Potion blacksmith = new Potion("BlackSmith's Secret", 10, "Restores 100% health of equipment", 10, "b");


        // Weapons
        Weapon Knife = new Weapon("Knife", 100, 0, 15, -1, 2.5);
        Weapon Pistol = new Weapon("Pistol", 150, 2000, 25, 10, 2);
        Weapon Submachine = new Weapon("Submachine", 200, 3500, 15, 10, 0.5);
        Weapon Rifle = new Weapon("Rifle", 250, 5000, 60, 5, 4);
        Weapon Missile = new Weapon("Missile", 300, 7000, 80, 5, 5.5);

        enemies = new Enemy[]{Skeleton, Zombie, Goblin, Org, Witch, Vampire, Werewolf, Golem, Wizard, Pharaoh, Dragon, Phoenix};
        weapons = new Weapon[]{Knife, Pistol, Submachine, Missile, Rifle};
        potions = new Potion[]{rage, shield, regeneration, time, blacksmith};

        player.weapons = new Weapon[]{Knife};
        player.potions = new Potion[5];

        selectedPotion = null;
    }

    public void actI() throws InterruptedException {
//        System.out.println("\nWelcome to ACT I");
//        System.out.println(Colors.BLACK_BOLD+"Player: "+Colors.ANSI_RESET+"What's this? A letter!");
//        System.out.println();
//
//        if (GameLogic.getInput("", new String[]{"Take", "Leave"}, new String[]{"t", "l"}).equals("t")) {
//
//        }

        switch (GameLogic.getInput(Colors.BLACK_BOLD + "\nPlayer: " + Colors.ANSI_RESET + "I see two paths, where should I go now??", new String[]{"Left", "Right"}, new String[]{"l", "r"})) {
            case "l": {
                // Left Turn
                GameLogic.writeAnimation("\nWalking...\n");
//                Thread.sleep(5000);
                System.out.println("\nWhoosh!!!");
//                Thread.sleep(2000);
                System.out.println(Colors.BLACK_BOLD + "Player: " + Colors.ANSI_RESET + "Oh, a... " + enemies[0].color + enemies[0].name + Colors.ANSI_RESET + "?");
//                Thread.sleep(2000);
                int p = -1;
                do {
                    switch (GameLogic.getInput("", new String[]{"Fight 🔪", "Run Away 🏃‍♂️", "Drink Potion 🍾", "Check Stats 📊"}, new String[]{"f", "r", "p", "c"})) {
                        case "f": {
                            // Fight
                            player.fight(enemies[0], selectedPotion); // skeleton and selected potion
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
            }
            case "r": {
                // Right Turn
            }
            GameLogic.writeAnimation("\nWalking...");
            Goldsmith goldsmith = new Goldsmith(1);
            System.out.println("\nGoldsmith: " + Colors.ANSI_RESET + goldsmith.introduce());
//            Thread.sleep(3000);
            System.out.println("           Anything I can do for you?");
//            Thread.sleep(2000);

            // Interactions with Goldsmith
            int k = 0;
            do {
                System.out.println();
                GameLogic.printHeader(Colors.BLACK_BOLD + "Buy", 5);
                String choice = GameLogic.getInput("", new String[]{"Weapons", "Ammo", "Potions", Colors.GREEN_BOLD + "\n[Check Stats]", Colors.RED_BOLD + "[Exit]"}, new String[]{"w", "a", "p", "c", "x"});

                // Buying weapons
                if (choice.equals("w")) {
                    goldsmith.speak("Let's buy some cheeky weapons!!");
                    String[] options = new String[weapons.length - 1];
                    String[] keys = new String[weapons.length - 1];
                    int p = 0;
                    int u = 0;
                    StringBuilder playerWeapons = new StringBuilder();
                    for (int i = 0; i < player.weapons.length; i++) {
                        playerWeapons.append(player.weapons[i].name);
                    }
                    for (int i = 1; i < weapons.length; i++) {
                        options[p] = weapons[i].name + Colors.YELLOW_BOLD + " (" + weapons[i].cost + ")" + Colors.ANSI_RESET;
                        keys[p] = weapons[i].name.toLowerCase().charAt(0) + "";
                        p++;
                    }
                    String ch = GameLogic.getInput("", options, keys);
                    p = player.weapons.length - 1;
                    for (int i = 0; i < options.length; i++) {
                        if (ch.equals(keys[i])) {
                            // Getting info of the weapon
                            weapons[i + 1].getInfo();
                            if (GameLogic.getInput("", new String[]{"Buy", "Cancel"}, new String[]{"b", "x"}).equals("b")) {
                                // check for enough money
                                if (player.coins >= weapons[i + 1].cost) {
                                    player.coins -= weapons[i + 1].cost;
                                    player.weapons[p] = weapons[i + 1];
                                    k = 0;
                                    System.out.println(Colors.GREEN_BOLD + player.weapons[p].name + " Successfully bought! " + Colors.RED_BOLD + "(-" + weapons[i + 1].cost + ")");
                                    p++;
                                } else {
                                    System.out.println(Colors.RED_BOLD+"\nCould not buy " + weapons[i + 1].name);
                                    k = -1;
                                }

                            }

                        }
                    }
                    k = -1;
                    GameLogic.getInput("\nEnter any key to continue...", new String[]{}, new String[]{});
                } else if (choice.equalsIgnoreCase("x")) {
                    k = 0;
                    break;
                } else if (choice.equalsIgnoreCase("c")) {
                    player.checkStats();
                    k = -1;
                }
                // Buying Ammo
                else if (choice.equalsIgnoreCase("a")) {
                    goldsmith.speak("The heart of a weapon... Ammo!");
                    String[] options = new String[player.weapons.length - 1];
                    String[] keys = new String[player.weapons.length - 1];

                    int p = 0;
                    if (player.weapons.length == 1) {
                        System.out.println(Colors.RED_BOLD + "It seems like you don't have any guns! Come back next time");
                        k = -1;
                    }
                    for (int i = 1; i < player.weapons.length; i++) {
                        options[p] = player.weapons[i].name + " Ammo";
                        keys[p] = player.weapons[i].name.toLowerCase().charAt(0) + "";
                        p++;
                    }

                    String ammo = GameLogic.getInput("", options, keys);
                    switch (ammo) {
                        case "p": {
                            GameLogic.buyAmmo(player, weapons[1], new int[]{200, 400, 600, 800}, new int[]{5, 10, 15, 20});
                            break;
                        }
                        case "s": {
                            GameLogic.buyAmmo(player, weapons[2], new int[]{150, 350, 50, 750}, new int[]{5, 10, 15, 20});
                            break;
                        }
                        case "r": {
                            GameLogic.buyAmmo(player, weapons[3], new int[]{250, 600, 800, 1250}, new int[]{2, 5, 7, 10});
                            break;
                        }
                        case "m": {
                            GameLogic.buyAmmo(player, weapons[4], new int[]{150, 750, 1000, 1500}, new int[]{2, 5, 7, 10});
                            break;
                        }
                    }
                    k = -1;
                }
                // Buying Potions
                else if (choice.equalsIgnoreCase("p")) {

                    GameLogic.printHeader("Potions", 15);

                    String[] options = new String[potions.length];
                    String[] keys = new String[potions.length];
                    for (int i = 0;i< potions.length;i++) {
                        options[i] = potions[i].name + Colors.BLUE_BOLD + " ("+potions[i].value+")";
                        keys[i] = potions[i].name.toLowerCase().charAt(potions[i].name.length()-2)+"";
                    }

                    String potion = GameLogic.getInput("", options, keys);
                    for (int i = 0;i< potions.length;i++) {
                        if (keys[i].equalsIgnoreCase(potion)) {
                            // Getting the info of the potion
                            potions[i].getInfo();

                            if (GameLogic.getInput("", new String[]{"Buy", "Cancel"}, new String[]{"b", "x"}).equals("b")) {
                                // check for enough money
                                if (player.rubies >= potions[i].value) {
                                    player.rubies -= potions[i].value;
                                    player.potions[i] = potions[i];
                                    System.out.println(Colors.GREEN_BOLD+"Successfully bought: "+potions[i].name);
                                    k = 0;
                                } else {
                                    System.out.println(Colors.RED_BOLD+"\nCould not buy " + potions[i].name);
                                }
                            }
                        }
                    }
                    GameLogic.getInput("Enter any key to continue...", new String[] {}, new String[] {});
                    k = 0;
                }
            } while (k == -1);
        }
    }
}