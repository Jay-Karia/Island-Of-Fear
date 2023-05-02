package src.GameClasses;

import src.GameLogic;
import src.colors.Colors;

import java.util.concurrent.TimeUnit;

import java.util.*;

//import java
public class Player {
    public double health;
    public Weapon[] weapons;
    public Potion[] potions;
    public int[] qty;
    public long coins;
    public long rubies;
    public int counter;

    public Player() {
        health = 100;
        coins = 2000;
        rubies = 0;
        weapons = new Weapon[] {null, null, null, null, null};
        potions = new Potion[5];
        qty = new int[5];
    }

    public int checkDeath(double health) {
        if (health <= 0.0) {
            System.out.println(Colors.RED_BOLD + "You Died!");
            return -1;
        } else
            return 1;
    }

    public int checkPotions() throws NullPointerException {
        int l = 0;
        for (int i = 0; i < 5; i++) {
            if (this.potions[i] == null)
                continue;
            else
                l++;
        }
        if (l > 0) {
            for (int i = 0; i < l; i++) {
                qty[i] += 1;
            }
            return 1;
        } else {
            return -1;
        }
    }

    public void fight(Enemy enemy, Potion selectedPotion) throws InterruptedException {
        // Enemy
        double enemyHealth = enemy.health;
        double max = enemy.health;
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

        for (int i = 0; i < 5; i++) {
            if (this.weapons[i] == null)
                continue;
            else
                counter++;
        }

        if (counter == 1) {
            System.out.println(Colors.ANSI_RESET + "Weapon: " + Colors.BLACK_BOLD + this.weapons[0].name + "\n");
            selectedWeapon = weapons[0];
        } else {
            System.out.print("\nSelect your weapon for the fight:");
            String[] options = new String[counter];
            String[] keys = new String[counter];
            for (int i = 0; i < counter; i++) {
                options[i] = weapons[i].name;
                keys[i] = weapons[i].name.toLowerCase().charAt(0) + "";
            }

            String choice = GameLogic.getInput("", options, keys);

            for (int i = 0; i < options.length; i++) {
                if (keys[i].equals(choice)) {
                    selectedWeapon = weapons[i];
                }
            }
            System.out.println(Colors.ANSI_RESET + "\nWeapon: " + Colors.BLACK_BOLD + selectedWeapon.name + Colors.ANSI_RESET);
            if (selectedPotion != null) {
                System.out.println("Potion: " + Colors.BLACK_BOLD + selectedPotion.name + Colors.ANSI_RESET);
            }
            System.out.println();

        }

        attackSpeed = selectedWeapon.attackSpeed;
        damage = selectedWeapon.damage;
        ammo = selectedWeapon.ammo;

        Thread.sleep(2000);


        Thread.sleep(2000);

        // Before fight starts
        GameLogic.printHeader("Fight Stats", 15);
        if (selectedWeapon.name.equalsIgnoreCase("knife"))
            System.out.println(Colors.ANSI_RESET + "\nPlayer Health: " + Colors.BLACK_BOLD + this.health + Colors.ANSI_RESET + "\nWeapon Health: " + Colors.GREEN_BOLD + selectedWeapon.health + Colors.ANSI_RESET + "\n\n" + Colors.BLACK_BOLD + selectedWeapon.name + Colors.ANSI_RESET + " Speed: " + Colors.BLACK_BOLD + selectedWeapon.attackSpeed + "s" + Colors.ANSI_RESET + "\n" + Colors.BLACK_BOLD + selectedWeapon.name + Colors.ANSI_RESET + " Damage: " + Colors.BLACK_BOLD + selectedWeapon.damage + enemy.color + "\n\n" + enemy.name + Colors.ANSI_RESET + " Health: " + Colors.BLACK_BOLD + enemy.health + "\n" + enemy.name + " Speed: " + enemy.color + enemy.attackSpeed + "s\n" + Colors.BLACK_BOLD + enemy.name + Colors.ANSI_RESET + " Damage: " + Colors.BLACK_BOLD + enemy.damage + "\n");
        else
            System.out.println(Colors.ANSI_RESET + "\nPlayer Health: " + Colors.BLACK_BOLD + this.health + Colors.ANSI_RESET + "\nAmmo: " + Colors.GREEN_BOLD + selectedWeapon.ammo + Colors.ANSI_RESET + "\n\n" + Colors.BLACK_BOLD + selectedWeapon.name + Colors.ANSI_RESET + " Speed: " + Colors.BLACK_BOLD + selectedWeapon.attackSpeed + "s" + Colors.ANSI_RESET + "\n" + Colors.BLACK_BOLD + selectedWeapon.name + Colors.ANSI_RESET + " Damage: " + Colors.BLACK_BOLD + selectedWeapon.damage + enemy.color + "\n\n" + enemy.name + Colors.ANSI_RESET + " Health: " + Colors.BLACK_BOLD + enemy.health + "\n" + enemy.name + " Speed: " + enemy.color + enemy.attackSpeed + "s\n" + Colors.BLACK_BOLD + enemy.name + Colors.ANSI_RESET + " Damage: " + Colors.BLACK_BOLD + enemy.damage + "\n");

        long startTime = System.nanoTime();
        long endTime = 0;
        long timeElapsed = 0;

        if (selectedPotion != null) {
            String effect = selectedPotion.duration > 0 ? " for " + "" + selectedPotion.duration + "s" : "";
            System.out.println(Colors.ANSI_RESET + "Potion Effect: " + Colors.BLACK_BOLD + selectedPotion.effect + effect);
        }

        GameLogic.getInput("Enter any key to start fighting", new String[]{}, new String[]{});
        System.out.println(Colors.ANSI_RESET + "\nPress " + Colors.BLACK_BOLD + "\"z\"" + Colors.ANSI_RESET + " for single strike, beware of enemy attacks" + Colors.ANSI_RESET);

        while (enemyHealth != 0) {
            if (this.checkDeath(this.health) == -1)
                break;
            if (ammo != 0) {
                String choice = GameLogic.getInput("", new String[]{}, new String[]{"z"});

                // Checking the health of the knife
                if (selectedWeapon.name.equalsIgnoreCase("knife")) {
                    if (selectedWeapon.health <= 0) {
                        System.out.println(Colors.BLACK_BOLD + selectedWeapon.name + Colors.ANSI_RESET + " is " + Colors.RED_BOLD + "broken!\nYou Loose");
                        break;
                    }
                } else {
                    if (selectedWeapon.ammo <= 0) {
                        System.out.println(Colors.RED_BOLD + selectedWeapon.name + " out of ammo!\nYou Loss");
                        break;
                    }
                }
                if (choice.equalsIgnoreCase("z")) {
                    System.out.println(Colors.BLACK_BOLD + selectedWeapon.name + Colors.ANSI_RESET + " attack");

                    // Potion effect
                    if (selectedPotion != null) {
                        if (selectedPotion.duration >= timeElapsed) {
                            // Potion of Rage
                            switch (selectedPotion.key) {
                                case "d" -> {
                                    damage = selectedWeapon.damage + (double) (150 * selectedWeapon.damage) / 100;  // +150% damage
                                    damage = (double) Math.round(damage * 100) / 100;
                                }

                                // Protector's Shield
                                case "s" -> {
                                    enemyDamage = (50 * enemy.damage) / 100; // -50% damage
                                    enemyDamage = (double) Math.round(enemyDamage * 100) / 100;
                                }

                                // Potion of Regeneration
                                case "r" -> {
                                    this.health = 100;
                                    health = 100;
                                }
                                // Sorcerer of Time
                                case "t" -> enemyAttackSpeed = enemy.attackSpeed + (enemy.attackSpeed * 50) / 100;
                            }
                            // Blacksmith's Potion

                        } else {
                            damage = selectedWeapon.damage;
                            enemyDamage = enemy.damage;
                            this.health = health;
                            enemyAttackSpeed = enemy.attackSpeed;
                        }
                    }
                    // Attack
                    if (damage > enemyHealth)
                        damage = enemyHealth;
                    enemyHealth -= damage;
                    totalAttacks++;
                    // Reduce Weapon Health
                    selectedWeapon.health -= 2;

                    if (ammo > 0)
                        ammo -= 1;

                    if (!selectedWeapon.name.equalsIgnoreCase("knife")) {
                        selectedWeapon.ammo = ammo;
                        ammoUsed++;
                    } else
                        ammoUsed += 2;
                }
                enemy.health = enemyHealth;
                enemy.checkStats(damage);
                if (selectedWeapon.name.equalsIgnoreCase("knife")) {
                    System.out.println("Health: " + Colors.BLACK_BOLD + selectedWeapon.health + Colors.ANSI_RESET + " 🔪" + Colors.RED_BOLD + " (-2)" + Colors.ANSI_RESET);
                } else {
                    System.out.println("Ammo: " + Colors.BLACK_BOLD + selectedWeapon.ammo + Colors.ANSI_RESET + " 🔫" + Colors.RED_BOLD + " (-1)" + Colors.ANSI_RESET);
                }

                int d = enemy.checkDeath(enemyHealth);
                if (d == -1)
                    break;

                Thread.sleep((long) attackSpeed * 1000);
            } else {
                System.out.println(Colors.RED_BOLD + "\nYour selected weapon is out of ammo!\nYou Lose");
                break;
            }
            endTime = System.nanoTime();
            timeElapsed = (endTime - startTime) / 1000000;
            timeElapsed = timeElapsed / 1000;

            // Enemy Attacks

            if (timeElapsed >= enemyAttackSpeed) {
                if (enemyDamage > this.health)
                    enemyDamage = this.health;
                enemyAttackSpeed += enemy.attackSpeed;
                health -= enemyDamage;
                this.health = health;
                totalEnemyAttacks++;
                healthLost += enemyDamage;
                System.out.println("\n" + enemy.color + "(X): " + enemy.name + Colors.ANSI_RESET + " attacked!");
                System.out.println("Health: " + Colors.BLACK_BOLD + this.health + Colors.ANSI_RESET + " 💖" + Colors.RED_BOLD + " (-" + enemyDamage + ")" + Colors.ANSI_RESET);
            }
        }

        // Fight Summary
        GameLogic.genFightSummary(ammoUsed, healthLost, totalAttacks, totalEnemyAttacks);
        GameLogic.getInput("\nEnter any key to continue...", new String[]{}, new String[]{});

        // Loot
        int[] loot = GameLogic.genLoot(enemy);
        this.coins += loot[0];
        this.rubies += loot[1];
        selectedWeapon.ammo += loot[2];

        // resets enemy health for another fight
        enemy.health = max;
    }

    public void checkStats() throws InterruptedException {
        GameLogic.printHeader("Stats", 10);
        System.out.println(Colors.RED_BOLD_BRIGHT + "\nHealth: " + this.health + " 💖" + Colors.ANSI_RESET);
        System.out.println(Colors.YELLOW_BOLD_BRIGHT + "Coins: " + this.coins + " ⭕" + Colors.ANSI_RESET);
        System.out.println(Colors.BLUE_BOLD_BRIGHT + "Rubies: " + this.rubies + " 💎" + Colors.ANSI_RESET);
        GameLogic.getInput("\nEnter any key to continue...", new String[]{}, new String[]{});
    }
}
