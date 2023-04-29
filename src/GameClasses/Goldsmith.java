package src.GameClasses;

import src.colors.Colors;

public class Goldsmith {
    public int act;

    public Goldsmith(int act) {
        this.act = act;
    }

    public String introduce() {
        int act = this.act;
        String[] dialogues = new String[] {"Hello stranger!", "How's everything going?", "Hope you are in good mood?", "You have the traits of a good warrior", "Reach your destination and enjoy the victory"};
        return dialogues[act];
    }

    public void speak(String str) {
        System.out.println(Colors.BLACK_BOLD+"Goldsmith: "+Colors.ANSI_RESET+str);
    }
}
