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

    public void checkStats(double damage) {
        System.out.println("Enemy Health: "+ Colors.BLACK_BOLD+this.health+" 💖"+Colors.GREEN_BOLD+" (-"+damage+")"+Colors.ANSI_RESET);
    }
    public int checkDeath(double health) {
        if (health == 0 ) {
            System.out.println(Colors.GREEN_BOLD+name+" Died!"+Colors.ANSI_RESET);
            return -1;
        } else
            return 1;
    }

    public double attack() throws InterruptedException {
        Thread.sleep((long) attackSpeed*1000);
        System.out.println("Enemy Attacked");
        return damage;
    }
}
