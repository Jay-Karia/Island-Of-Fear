package src.GameClasses;
import src.GameLogic;
import src.colors.Colors;

import java.util.concurrent.TimeUnit;

import java.util.*;
//import java
public class Player {
    public double health;
//    public String name;
    public Weapon[] weapons;
    public Potion[] potions;
    public int[] qty;
    public long coins;
    public long rubies;

    public Player() {
        Scanner sc = new Scanner(System.in);

//        System.out.println("Enter your name");
//        name = sc.next();
//        name = "Jay";
        health = 100;
        coins = 0;
        rubies = 0;
        weapons = new Weapon[]{};
        potions = new Potion[]{};
        qty = new int[] {};
    }
 
    public int checkDeath(double health) {
        if (health == 0.0) {
            System.out.println(Colors.RED_BOLD+"You Died!");
            return -1;
        } else 
            return 1;
    }

    public void checkPotions() {
        int l = this.potions.length;
        for (int i = 0;i<l;i++) {
            System.out.println(this.potions[i].name);
        }
    }

    public void fight(Enemy enemy) throws InterruptedException {
        // Enemy
        double enemyHealth = enemy.health;
        double enemyAttackSpeed = enemy.attackSpeed;
        double enemyDamage = enemy.damage;

        // Player
        double health = this.health;

        // Weapon
        double attackSpeed;
        double damage;
        int ammo;
        Weapon selectedWeapon = null;

        double ammoUsed = 0;
        double healthLost = 0;
        double totalAttacks = 0;
        double totalEnemyAttacks = 0;

        if (this.weapons.length == 1) {
            System.out.println(Colors.ANSI_RESET+"Weapon: "+Colors.BLACK_BOLD+this.weapons[0].name+"\n");
            selectedWeapon = weapons[0];
        } else {
            System.out.print("\nSelect your weapon for the fight:");
            String[] options = new String[weapons.length];
            String[] keys = new String[weapons.length];
            for (int i=0;i<weapons.length;i++) {
                options[i] = weapons[i].name;
                keys[i] = weapons[i].name.toLowerCase().charAt(0)+"";
            }

            String choice = GameLogic.getInput("", options, keys);

            for (int i=0;i< options.length;i++) {
                if (keys[i].equals(choice)) {
                    selectedWeapon = weapons[i];
                }
            }
            System.out.println("Weapon: "+Colors.BLACK_BOLD+selectedWeapon.name+"\n");

        }

            attackSpeed = selectedWeapon.attackSpeed;
            damage = selectedWeapon.damage;
            ammo = selectedWeapon.ammo;

            int counter = 0;

            System.out.println(Colors.ANSI_RESET+"Press "+Colors.BLACK_BOLD+"\"z\"" + Colors.ANSI_RESET+" for single strike, beware of enemy attacks"+Colors.ANSI_RESET);

            // Before fight starts
            if (selectedWeapon.name.equalsIgnoreCase("knife"))
                System.out.println(Colors.ANSI_RESET+"\nHealth: "+Colors.BLACK_BOLD+this.health+Colors.ANSI_RESET+"\nWeapon Health: "+Colors.GREEN_BOLD+selectedWeapon.health+Colors.ANSI_RESET+"\n"+enemy.name+" Health: "+Colors.BLACK_BOLD+enemy.health);
             else
                System.out.println(Colors.ANSI_RESET+"\nHealth: "+Colors.BLACK_BOLD+this.health+Colors.ANSI_RESET+"\nAmmo: "+Colors.GREEN_BOLD+selectedWeapon.ammo+Colors.ANSI_RESET+"\n"+enemy.name+" Health: "+Colors.BLACK_BOLD+enemy.health);

            long startTime = System.nanoTime();
            int p = 0;

            while (enemyHealth != 0) {

                if (this.checkDeath(this.health) == -1)
                    break;

                if (counter == enemyAttackSpeed*1000) {
                    this.health -= enemyDamage;
                }
                if (ammo != 0) {
                    String choice = GameLogic.getInput("", new String[]{}, new String[] {"z"});
                    if (selectedWeapon.health <= 0) {
                        System.out.println(Colors.BLACK_BOLD+selectedWeapon.name+Colors.ANSI_RESET+" is "+Colors.RED_BOLD+"broken!\nYou Loose");
                        break;
                    }
                    if (choice.equalsIgnoreCase("z")) {
                        System.out.println(Colors.BLACK_BOLD + selectedWeapon.name+ Colors.ANSI_RESET +" attack");
                        // Attack
                        if (damage > enemyHealth)
                            damage = enemyHealth;
                        enemyHealth -= damage;
                        totalAttacks++;
                        // Reduce Weapon Health
                        selectedWeapon.health -= 2;

                        if (ammo > 0)
                            ammo-=1;

                        selectedWeapon.ammo = ammo;
                        ammoUsed++;
                    }

                    enemy.health = enemyHealth;
                    enemy.checkStats(damage);
                    if (selectedWeapon.name.equalsIgnoreCase("knife")) {
                        System.out.println("Health: "+Colors.BLACK_BOLD+selectedWeapon.health+Colors.ANSI_RESET+" 🔪"+Colors.RED_BOLD+" (-2)"+Colors.ANSI_RESET);
                    } else {
                        System.out.println("Ammo: "+Colors.BLACK_BOLD+selectedWeapon.ammo+Colors.ANSI_RESET+" 🔫"+Colors.RED_BOLD+" (-1)"+Colors.ANSI_RESET);
                    }

                    int d = enemy.checkDeath(enemyHealth);
                    if (d ==-1)
                        break;

                    Thread.sleep((long) attackSpeed*1000);
                    counter++;
                } else {
                    System.out.println(Colors.RED_BOLD+"\nYour selected weapon is out of ammo!\nYou Lose");
                    break;
                }
                long endTime = System.nanoTime();
                long timeElapsed = (endTime - startTime) / 1000000;
                timeElapsed = timeElapsed / 1000;

                // Enemy Attacks
                
                if (timeElapsed >= enemyAttackSpeed) {
                    enemyAttackSpeed+=enemy.attackSpeed;
                    health-=enemyDamage;
                    this.health = health;
                    totalEnemyAttacks++;
                    healthLost+=enemyDamage;
                    System.out.println("\n"+Colors.BLACK_BOLD + "(X): " + enemy.name+Colors.ANSI_RESET+" attacked!");
                    System.out.println("Health: "+Colors.BLACK_BOLD+this.health+Colors.ANSI_RESET+" 💖"+Colors.RED_BOLD+" (-"+enemyDamage+")" +Colors.ANSI_RESET);
                }
            }

        // Fight Summary
        GameLogic.genFightSummary(ammoUsed, healthLost, totalAttacks, totalEnemyAttacks);

    }

    public void checkStats() {
        System.out.println("\nHealth: "+ Colors.BLACK_BOLD+this.health+" 💖"+Colors.ANSI_RESET);
        System.out.println("Coins: "+ Colors.BLACK_BOLD+this.coins+" ⭕"+Colors.ANSI_RESET);
        System.out.println("Rubies: "+ Colors.BLACK_BOLD+this.rubies+" 💎"+Colors.ANSI_RESET);
    }
}
