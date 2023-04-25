package src.GameClasses;

public class Weapon {
    public String name;
    public int health;
    public int cost;
    public int damage;
    public int ammo;

    public double attackSpeed;


    public Weapon (String name, int health, int cost, int damage, int ammo, double attackSpeed) {
        this.name = name;
        this.health = health;
        this.cost = cost;
        this.damage = damage;
        this.ammo = ammo;
        this.attackSpeed = attackSpeed;
    }

    public void checkStats() {

    }

    public void checkHealth() {

    }
}
