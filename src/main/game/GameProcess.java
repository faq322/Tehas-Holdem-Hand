package main.game;

import main.game.evaluations.EvaluationMain;
import main.game.model.*;
import main.game.validators.Validator;

import java.util.HashMap;
import java.util.Map;

public class GameProcess {
    public static String main(String userInput) throws Exception {


        Validator validator = new Validator(); //Create a main.game.validators.Validator object

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
        Map<Integer, Integer> results = new HashMap<Integer, Integer>();
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
            _result = cardOneSuit(playerHand[i]);
            if (result == 4 && _result == 5) result = 8;
            else if (_result > result) result = _result;

            //REPEATING
            //No need to check for repeating if player has Straight Flush or Royal Flush
            if (result < 8) _result = cardRepeating(playerHand[i]);
            if (_result > result) result = _result;

            System.out.println("+++++++++++++\n  -TOTAL PLAYER RESULT: " + result + "\n++++++++++++++");
            players[i].setCombination(result);
            if (results.containsKey(result)) {
                results.put(result, results.get(result) + 1);
            } else {
                results.put(result, 1);
            }
        }

        //OUTPUT
        String finalOut = "";
        for (int j = 0; j < 9; j++) {
            for (int i = 0; i < playersCount; i++) {
                Map<Integer, Hand> waitlist = new HashMap<Integer, Hand>();
                if (players[i].getCombination() == j) {
                    if (results.get(j) == 1) {
                        finalOut += players[i].getCard(0).getCardString() + players[i].getCard(1).getCardString();
                        finalOut += " ";
                    } else {
                        waitlist.put(j, playerHand[i]);
                    }
                }
                if (!waitlist.isEmpty()) {
                    finalOut += compareWaitlist(waitlist);
                }
            }
        }

        System.out.println("Final result: " + finalOut);
        return finalOut;
    }

    private static String compareWaitlist(Map<Integer, Hand> waitlist) {
        String result ="";


        return result;
    }

    private static int cardInARow(Hand playerHand) {
        EvaluationMain evaluationMain = new EvaluationMain();
        Map<Character, Integer> map = new HashMap<Character, Integer>();
        int result = 0;
        boolean inARow = false;

        //Sort by rank
        playerHand.sortByRank();
        //Check if there is 5 cards in a row
        inARow = evaluationMain.inARow(playerHand);
        //Define result
        //If 5 cards in a row - its a minimum Straight. So lets stay it for a while
        result = (inARow) ? 4 : 0;
        return result;
    }

    private static int cardRepeating(Hand playerHand) {
        EvaluationMain evaluationMain = new EvaluationMain();
        Map<Character, Integer> map = new HashMap<Character, Integer>();
        int result = 0;

        //Check for rank repeating and get all repeated ranks
        map = evaluationMain.repeatrings(playerHand);
        //Define combination
        if (map.size() != 0) result = evaluationMain.defineRepeatings(map);
        return result;
    }

    private static int cardOneSuit(Hand playerHand) {
        EvaluationMain evaluationMain = new EvaluationMain();
        Map<Character, Integer> map = new HashMap<Character, Integer>();
        int result = 0;

        //Check for suit repeating and get all repeated suits
        char suit = evaluationMain.suit(playerHand);

        //Define suit
        if (suit != 'x') result = 5;
        return result;
    }

}

