import core.parsers.InputParser;
import game.process.GameProcess;


public class Main {


    public static void main(String[] args) throws Exception {
        GameProcess gameProcess = new GameProcess();
        String input = InputParser.getInput();
        String output = gameProcess.main(input);

        System.out.print(output);
    }
}

