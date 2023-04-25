package src.GameClasses;
import src.GameLogic;
import src.colors.Colors;

import java.util.*;
public class Player {
    public double health;
//    public String name;
    public Weapon[] weapons;
    public Potion[] potions;
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
    }

    public void checkDeath(double health) {
        if (health == 0.0) {
            System.out.println(Colors.RED_BOLD+"You Died!");
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

            System.out.println(Colors.ANSI_RESET+"Press "+Colors.BLACK_BOLD+"\"z\"" + Colors.ANSI_RESET+" for single strike, beware of enemy attacks (denoted by "+Colors.BLACK_BOLD+"\"X\""+Colors.ANSI_RESET+")");

            while (enemyHealth != 0) {
                if (ammo != 0) {
                    String choice = GameLogic.getInput("", new String[]{}, new String[] {"z"});
                    if (selectedWeapon.health <= 0) {
                        System.out.println(Colors.BLACK_BOLD+selectedWeapon.name+Colors.ANSI_RESET+" is "+Colors.RED_BOLD+"broken!\nYou Loose");
                        break;
                    }
                    if (choice.equalsIgnoreCase("z")) {
                        // Attack
                        if (damage > enemyHealth)
                            damage = enemyHealth;
                        enemyHealth -= damage;
                        // Reduce Weapon Health
                        selectedWeapon.health -= 2;

                        if (ammo > 0)
                            ammo-=1;
                    }

                    enemy.health = enemyHealth;
                    enemy.checkStats();
                    System.out.println("Health: "+Colors.BLACK_BOLD+selectedWeapon.health+Colors.ANSI_RESET+" 🔪");

                    enemy.checkDeath(enemyHealth);

                    Thread.sleep((long) attackSpeed*1000);
                } else {
                    System.out.println(Colors.ANSI_RED+"Your selected weapon is out of ammo!");
                }
            }

    }

    public void checkStats() {
        System.out.print("\nHealth: "+ Colors.BLACK_BOLD+this.health+" 💖"+Colors.ANSI_RESET);
        System.out.println("Coins: "+ Colors.BLACK_BOLD+this.coins+" ⭕"+Colors.ANSI_RESET);
        System.out.println("Rubies: "+ Colors.BLACK_BOLD+this.rubies+" 💎"+Colors.ANSI_RESET);
    }


}