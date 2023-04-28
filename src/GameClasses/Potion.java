package src.GameClasses;

public class Potion {
    public String name;
    public int duration;
    public String effect;
    public int value;
    public String key;

    public Potion(String name, int duration, String effect, int value, String key) {
        this.name = name;
        this.duration = duration;
        this.effect = effect;
        this.value = value;
        this.key = key;
    }

    public void getInfo() {
        System.out.println(effect);
    }

    public double use(double damage) {
        if (key.equals("d")) {
            damage = (150*damage) / 100;
            return damage;
        } else if (key.equals("s")) {
            damage = (50*damage) / 100;
            return damage;
        }
        else 
            return 0;
    }

}
