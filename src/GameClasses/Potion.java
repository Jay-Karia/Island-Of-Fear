package src.GameClasses;

import src.GameLogic;
import src.colors.Colors;

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

//    public double use(double damage) {
//        if (key.equals("d")) {
//            damage += (150*damage) / 100;
//            return damage;
//        } else if (key.equals("s")) {
//            damage = (50*damage) / 100;
//            return damage;
//        }
//        else
//            return 0;
//    }

    public void getInfo() {
        GameLogic.printHeader(this.name, 25);
        System.out.println(Colors.ANSI_RESET+"\nEffect: "+Colors.BLACK_BOLD+effect+" 🌟");
        System.out.println(Colors.ANSI_RESET+"Duration: "+Colors.BLACK_BOLD+duration+" ⌚");
        System.out.println(Colors.ANSI_RESET+"Value: "+Colors.BLACK_BOLD+value+" 💎");
    }

}
