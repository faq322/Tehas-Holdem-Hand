import model.Board;
import model.Hand;
import model.Player;

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
            //   RESULT             |        CHECKING ORDER       |
            //                      |   THIRD   |  FIRST   |SECOND|
            // 0 - High Card
            // 1 - Pair             | Repeating |
            // 2 - Two pairs        | Repeating |
            // 3 - Three of a kind  | Repeating |
            // 4 - Straight                     | In a row |
            // 5 - Flush
            // 6 - Full house       | Repeating |
            // 7 - Four of a kind   | Repeating |
            // 8 - Straight Flush               | In a row | Suit |
            // 9 - Royal Flush                  | In a row | Suit |

            //IN A ROW
            result = cardInARow(playerHand[i]);

            //ONE SUIT
            

            //REPEATING
            //No need to check for repeating if player has Straight Flush or Royal Flush
            if (result < 8) _result = cardRepeating(playerHand[i]);
            if (_result > result) result = _result;
        }
    }

    private static int cardInARow(Hand playerHand) {
        EvaluationTests evaluationTests = new EvaluationTests();
        Map<Character, Integer> map = new HashMap<Character, Integer>();
        int result = 0;
        boolean inARow = false;

        //Sort by rank
        playerHand.sortByRank();
        System.out.println("SORT BY RANK " + playerHand.getHand()[0].getRank());
        //Check if there is 5 cards in a row
        inARow = evaluationTests.inARow(playerHand);
        //Define result
        //If 5 cards in a row - its a minimum Straight. So lets stay it for a while
        result = (inARow) ? 4 : 0;
        return result;
    }

    private static int cardRepeating(Hand playerHand) {
        EvaluationTests evaluationTests = new EvaluationTests();
        Map<Character, Integer> map = new HashMap<Character, Integer>();
        int result = 0;

        //Check for repeating and get all repeated ranks
        map = evaluationTests.repeatrings(playerHand);
        //Define combination
        if (map.size() != 0) result = evaluationTests.defineRepeatings(map);
        return result;
    }
}

