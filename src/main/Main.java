package main;

import main.core.parsers.InputParser;
import main.game.GameProcess;


public class Main {
    public static void main(String[] args) throws Exception {
        String input = InputParser.getInput();
        String output = GameProcess.main(input);

        System.out.print(output);
    }
}

