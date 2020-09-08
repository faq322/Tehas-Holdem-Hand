package game.process;

import game.model.Board;
import game.model.Hand;
import game.model.Player;
import game.process.service.combination.CombinationService;
import game.process.service.combination.CombinationServiceMain;
import game.process.service.evaluations.EvaluationMain;
import game.process.service.players.PlayersService;
import game.process.service.players.PlayersServiceMain;
import game.validators.Validator;

import java.util.HashMap;
import java.util.Map;

public class GameProcess {

    private PlayersService playersService = new PlayersServiceMain();
    private CombinationService combinationService = new CombinationServiceMain();
    private EvaluationMain evaluationMain = new EvaluationMain();

    public String main(String userInput) throws Exception {
        Validator validator = new Validator(); //Create a main.game.validators.Validator object

        //Validations
        validator.isBoardValid(userInput); //Check if board is valid
        validator.arePlayersValid(userInput); //Check if players are valid

        //Initialize board
        Board board = new Board(userInput);
        board.PrintBoard();  // Output board

        int playersCount = playersService.countPlayers(userInput);
        Player[] players = playersService.initializePlayers(userInput);
        Hand[] playerHand = new Hand[playersCount];
        Map<Integer, Integer> results = new HashMap<Integer, Integer>();

        for (int i = 0; i < playersCount; i++) {
            System.out.print("PLAYER " + i);
            playerHand[i] = new Hand(board, players[i]);

            int result = 0;

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
            int resultCardInARow = combinationService.cardInARow(playerHand[i]);
            if (resultCardInARow != 0) result = resultCardInARow;


            //ONE SUIT
            int resultCardOneSuit = combinationService.cardOneSuitResult(playerHand[i]);
            if (result == 4 && resultCardOneSuit == 5) {
                //if RESULT HIGH CARD = ACE THAN 9 ! IN DEFINE
                result = (players[i].getComboCard1() == 12) ? 9 : 8;
            } else if (resultCardOneSuit > result) result = resultCardOneSuit;


            //REPEATING
            //No need to check for repeating if player has Straight Flush or Royal Flush
            if (result <= 8) {
                int resultCardRepeating = combinationService.cardRepeating(playerHand[i]);
                if (resultCardRepeating > result) result = resultCardRepeating;
            }

            if (result == 0) {
                //TODO High Card
                //int resultHighCard = combinationService
            }


            System.out.println(" -TOTAL PLAYER RESULT: " + result);


            playerHand[i].setPoints(result);

            if (results.containsKey(result)) {
                results.put(result, results.get(result) + 1);
            } else {
                results.put(result, 1);
            }
        }

        //OUTPUT
        String finalOut = "";
        for (int j = 0; j < 9; j++) { //result
            Map<Integer, Hand> waitlist = new HashMap<Integer, Hand>();
            for (int i = 0; i < playersCount; i++) { //player
                if (playerHand[i].getPoints() == j) {
                    if (results.get(j) == 1) {
                        finalOut += players[i].getCard(0).getCardString() + players[i].getCard(1).getCardString();
                        finalOut += " ";
                    } else {
                        waitlist.put(waitlist.size(), playerHand[i]);
                    }
                }
            }
            if (!waitlist.isEmpty()) {
                //String out = players[i].getCard(0).getCardString() + players[i].getCard(1).getCardString();
                finalOut += combinationService.compareWaitlist(waitlist);
            }
        }

        if (!finalOut.isEmpty() && finalOut.charAt(finalOut.length() - 1) == ' ') {
            finalOut = finalOut.substring(0, finalOut.length() - 1);
        }

        System.out.println("Final result: " + finalOut);
        return finalOut;
    }


}

