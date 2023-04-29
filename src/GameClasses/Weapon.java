package src.GameClasses;

import src.GameLogic;
import src.colors.Colors;

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

    public void getInfo() {
        GameLogic.printHeader(Colors.BLACK_BOLD+this.name, 10);
        System.out.println(Colors.YELLOW_BOLD+"Cost: "+this.cost+" ⭕");
        if (this.name.equalsIgnoreCase("knife"))
            System.out.println(Colors.GREEN_BOLD+"Health: "+this.health + " 💖");
        else
            System.out.println(Colors.GREEN_BOLD+"Ammo: "+this.ammo + " 🔫");
        System.out.println(Colors.WHITE_BOLD+"Damage: "+this.damage+" 💣");
        System.out.println(Colors.WHITE_BOLD+"Attack Speed: "+this.attackSpeed+"s ️🏃‍♂️");
    }

    public void checkHealth() {

    }
}
