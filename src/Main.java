import model.Board;
import model.Hand;
import model.Player;
import model.comparators.RankComparator;

import java.util.Arrays;
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
        Hand player0Hand = new Hand(board, players[0]);
        player0Hand.PrintHand(0);

        //System.out.print(player0Hand.isPair());
        EvaluationTests evaluationTests = new EvaluationTests();
        evaluationTests.repeatrings(player0Hand);
    }

}

