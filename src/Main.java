import model.Board;
import model.Hand;
import model.Player;
import model.comparators.RankComparator;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception {
        Validator validator = new Validator(); //Create a Validator object
        Scanner input = new Scanner(System.in);  // Create a Scanner object

        System.out.println("Input:");
        String userInput = input.nextLine();  // Read user input

        //Validations
        validator.isBoardValid(userInput); //Check if board is valid
        validator.arePlayersValid(userInput); //Check if players are valid

        //Initialize board
        Board board = new Board(userInput);
        board.PrintBoard();  // Output board


        //Count players
        String[] playersStr = userInput.substring(11).split("\\s");
        int playersCount = playersStr.length;
        System.out.println("Player count: " + playersCount);

        //Initialize players
        Player[] players = new Player[playersCount];
        for (int i = 0; i < playersCount; i++) {
            players[i] = new Player(playersStr[i]);
            players[i].PrintPlayer(i);
        }

        //StartGame
        Hand[] playerHand = new Hand[playersCount];
        for (int i = 0; i < playersCount; i++) {
            System.out.println("\n **************\n PLAYER " + i);

            playerHand[i] = new Hand(board, players[i]);
            playerHand[i].PrintHand(i);
            int result = 0, _result = 0;

            //IN A ROW

            //ONE SUIT

            //REPEATINGS
            if (result < 4) _result = repeatings(playerHand[i]);
            if (_result > result) result = _result;
        }
    }

    private static int repeatings(Hand playerHand) {
        EvaluationTests evaluationTests = new EvaluationTests();
        Map<Character, Integer> map = new HashMap<Character, Integer>();

        //Check for repeating and get all repeated ranks
        map = evaluationTests.repeatrings(playerHand);
        //Define combination
        int result = 0;
        if (map.size() != 0) result = evaluationTests.defineRepeatings(map);
        return result;
    }
}

