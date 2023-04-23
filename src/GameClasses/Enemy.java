package src.GameClasses;

import src.colors.Colors;

public class Enemy {
    public String name;
    public double health;
    public double attackSpeed;
    public double damage;

    public Enemy(String name, double health, double attackSpeed, double damage) {
        this.name = name;
        this.health = health;
        this.attackSpeed = attackSpeed;
        this.damage = damage;
    }

    public double attack() {
        return damage;
    }
    public void checkDeath(double health) {
        if (health == 0)
            System.out.println(Colors.GREEN_BOLD+name+" Died!");
    }
}