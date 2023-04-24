package src.GameClasses;

import src.GameLogic;

import java.util.concurrent.TimeUnit;

public class Game {
    public Player player;
    public String[] enemies;
    public String[] weapons;
    public String[] potions;
    public Game() throws InterruptedException {

        // Player
        Player player = new Player();
        this.player = player;

        // Enemies
        Enemy Skeleton = new Enemy("Skelton", 50, 10, 5);
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
        Potion rage = new Potion("Potion of Rage", 10, "+150% damage", 2);
        Potion shield = new Potion("Protector's Shield", 10, "50% less damage", 3);
        Potion regeneration = new Potion("Potion of Regeneration", 0, "restores health", 5);
        Potion time = new Potion("Sorcerer of Time", 10, "slows down enemies", 7);
        Potion blacksmith = new Potion("BlackSmith's Potion", 10, "restores health of equipment", 10);

        // Weapons
        Weapon Knife = new Weapon("Knife", 100, 0, 15, 0);
        Weapon Pistol = new Weapon("Pistol", 150, 2000, 25, 5);
        Weapon Submachine = new Weapon("Submachine", 200, 3500, 30, 10);
        Weapon FlameThrower = new Weapon("Flame Thrower", 250, 5000, 45, 15);
        Weapon Rifle = new Weapon("Rifle", 300, 7000, 55, 15);

        // Sub Weapons
        SubWeapon grenade = new SubWeapon("Hand Grenade", 200, 100);
        SubWeapon Flash = new SubWeapon("Flash", 300, 150);
        SubWeapon acid = new SubWeapon("Acid Grenade", 400, 200);


//        double health = player.health;
//        double speed = org.attackSpeed;
//        for (int i = (int) health; i>=0;i-=org.damage) {
//            health-=org.damage;
//            TimeUnit.SECONDS.sleep((long) speed);
//            player.checkDeath(health);
//        }
//        player.health = health;
//        System.out.println(player.health);
    }

    public void actI() throws InterruptedException {
//        System.out.println("\nWelcome to ACT I");
//        GameLogic.writeAnimation("Player: What's this? A letter!");
//        System.out.println();
        String[] options = {"Take", "Leave"};
        String[] keys = {"t", "l"};

        int p =-1;
        System.out.print("Player: What's this? A letter!");
        String choice = GameLogic.getInput("", options, keys);
    }
}
