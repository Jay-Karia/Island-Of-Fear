package src;
import src.colors.Colors;
import src.GameLogic;

public class Main {
    public static void main(String[] args) throws InterruptedException {
//        GameLogic.printHeader("Welcome to the Island of Fear --Developed by Jay", 50);

        String[] options = {"Read Story", "New Game", "Load Game"};
        String[] keys = {"r", "n", "l"};

//        String c = GameLogic.getInput("", options, keys);

//        switch (c) {
//            case "r" -> GameLogic.readStory();
//            case "n" -> GameLogic.createNewGame();
//            case "l" -> System.out.println("Load Game");
//            default -> System.out.println("Invalid Command");
//        }
        GameLogic.createNewGame();
    }
}