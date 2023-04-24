package src.GameClasses;
import src.GameLogic;

import java.util.*;
public class Player {
    public double health;
//    public String name;
    public double attackSpeed;
    public String[] weapons;
    public String[] potions;
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
    }

    public void checkDeath(double health) {
        if (health == 0.0) {
            GameLogic.displayDeath();
        }
    }

    public void attack() {

    }

    public void checkStats() {

    }
}
