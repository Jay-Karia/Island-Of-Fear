package src.GameClasses;
import src.GameLogic;
import src.colors.Colors;

import java.util.*;
public class Player {
    public double health;
//    public String name;
    public double attackSpeed;
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
        attackSpeed = 2;
        coins = 0;
        rubies = 0;
        weapons = new Weapon[]{};
        potions = new Potion[]{};
    }

    public void checkDeath(double health) {
        if (health == 0.0) {
            GameLogic.displayDeath();
        }
    }

    public void fight(Enemy enemy) {
        double enemyHealth = enemy.health;
        double enemyAttackSpeed = enemy.attackSpeed;
        double enemyDamage = enemy.damage;

        double health = this.health;
        double attackSpeed = this.attackSpeed;

        System.out.println(enemy.name);
    }

    public void attack() {

    }

    public void checkStats() {
        System.out.println("\nHealth: "+ Colors.BLACK_BOLD+this.health+" 💖"+Colors.ANSI_BLACK);
        System.out.println(Colors.ANSI_BLACK+"Attack Speed: "+ Colors.BLACK_BOLD+this.attackSpeed+" 🏹");
    }


}