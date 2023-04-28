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
        Enemy Skeleton = new Enemy("Skeleton", 50, 10, 5);
        Enemy Zombie = new Enemy("Zombie", 75, 8.5, 7);
        Enemy Goblin = new Enemy("Goblin", 90, 7, 10);
        Enemy Org = new Enemy("Org", 120, 6.5, 12);
        Enemy Witch = new Enemy("Witch", 170, 5.5, 15);
        Enemy Vampire = new Enemy("Vampire", 190, 5, 17.5);
        Enemy Werewolf =  new Enemy("Werewolf", 220, 4.5, 19);
        Enemy Golem = new Enemy("Golem", 240, 4, 22);
        Enemy Wizard = new Enemy("Wizard", 280, 3.5, 25);
        Enemy Pharaoh = new Enemy("Pharaoh", 280, 3.5, 25);
        Enemy Dragon = new Enemy("Dragon", 300, 3, 27);
        Enemy Phoenix = new Enemy("Phoenix", 350, 2.5, 30);

        // Potions
        Potion rage = new Potion("Potion of Rage", 10, "+150% damage", 2, "d");
        Potion shield = new Potion("Protector's Shield", 10, "50% less damage", 3, "s");
        Potion regeneration = new Potion("Potion of Regeneration", 0, "Restores 100% of health", 5, "r");
        Potion time = new Potion("Sorcerer of Time", 10, "Slows down enemies", 7, "t");
        Potion blacksmith = new Potion("BlackSmith's Secret", 10, "Restores 100% health of equipment", 10, "b");

        // Weapons
        Weapon Knife = new Weapon("Knife", 100, 0, 15, -1, 2.5);
        Weapon Pistol = new Weapon("Pistol", 150, 2000, 25, 10, 2);
        Weapon Submachine = new Weapon("Submachine", 200, 3500, 35, 10, 1);
        Weapon Missile = new Weapon("Missile", 250, 5000, 50, 15, 3.5);
        Weapon Rifle = new Weapon("Rifle", 300, 7000, 60, 15, 4);

        enemies = new Enemy[] {Skeleton, Zombie, Goblin, Org, Witch, Vampire, Werewolf, Golem, Wizard, Pharaoh, Dragon, Phoenix};
        weapons = new Weapon[] {Knife, Pistol, Submachine, Missile, Rifle};
        potions = new Potion[] {rage, shield, regeneration, time, blacksmith};

        player.weapons = new Weapon[] {Knife, Rifle};
        player.potions = new Potion[] {rage, shield, regeneration};

        selectedPotion = null;
//        player.potions = new Potion[] {rage};
    }

    public void actI() throws InterruptedException {
//        System.out.println("\nWelcome to ACT I");
//        GameLogic.writeAnimation("Player: What's this? A letter!");
//        System.out.println();

//        System.out.print("Player: What's this? A letter!");
//        if (GameLogic.getInput("", new String[]{"Take", "Leave"}, new String[]{"t", "l"}).equals("t")) {
//            GameLogic.readStory();
//        }

        switch (GameLogic.getInput("\nI see two paths, where should I go now??", new String[]{"Left", "Right"}, new String[]{"l", "r"})) {
            case "l": {
                // Left Turn
                System.out.println("\nWalking...");
//                Thread.sleep(5000);
                System.out.println("\nWhoosh!!!");
//                Thread.sleep(2000);
                System.out.println("Player: Oh, a... "+ Colors.GREEN_BOLD+enemies[11].name+Colors.ANSI_RESET+"?");
//                Thread.sleep(2000);
                int p = -1;
                do {
                    switch (GameLogic.getInput("", new String[]{"Fight 🔪", "Run Away 🏃‍♂️", "Drink Potion 🍾", "Check Stats 📊"}, new String[]{"f", "r", "p", "c"})) {
                        case "f": {
                            // Fight
                            player.fight(enemies[11], selectedPotion); // skeleton and selected potion
                            p = -1;
                            break;
                        }
                        case "r": {
                            System.out.println(Colors.BLACK_BOLD+"\nWarriors are the ones who always try");
                            p=-1;
                            break;
                        }
                        case "p": {
                            // Drink Potion
                            if (player.checkPotions() == 1) {
                                // use potion
                                String[] options = new String[player.potions.length];
                                String[] keys = new String[player.potions.length];
                                
                                for (int i =0;i<options.length;i++) {
                                    options[i] = player.potions[i].name + " x" + player.qty[i];
                                    keys[i] = player.potions[i].name.substring(player.potions[i].name.length()-2, player.potions[i].name.length()-1); }
                                
                                
                                String choice = GameLogic.getInput("", options, keys);
                                
                                for (int i = 0;i<keys.length;i++) {
                                    if (keys[i].equals(choice)) {
                                        selectedPotion = potions[i];
                                        player.qty[i] -= 1;
                                    }
                                }
                             } else
                                System.out.println(Colors.ANSI_RESET+"You currently have " + Colors.RED_BOLD + "0" + Colors.ANSI_RESET + " potions");
                            p = 0;
                            break;
                        }
                        case "c": {
                            player.checkStats();
                            p = 0;
                        }
                    }
                } while (p==0);
            }
            case "r" : {
                // Right Turn
            }
        }

    }
}
