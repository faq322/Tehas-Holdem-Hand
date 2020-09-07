package game.process.service.combination;

import game.evaluations.Evaluation;
import game.model.Hand;

import java.util.HashMap;
import java.util.Map;

public class CombinationServiceMain implements CombinationService {

    private Evaluation evaluation;

    @Override
    public int cardInARow(Hand playerHand) {
        Map<Character, Integer> map = new HashMap<Character, Integer>();
        int result = 0;
        boolean inARow = false;

        //Sort by rank
        playerHand.sortByRank();
        //Check if there is 5 cards in a row
        inARow = evaluation.inARow(playerHand);
        //Define result
        //If 5 cards in a row - its a minimum Straight. So lets stay it for a while
        result = (inARow) ? 4 : 0;
        return result;
    }

    @Override
    public int cardOneSuit(Hand playerHand) {
        Map<Character, Integer> map = new HashMap<Character, Integer>();
        int result = 0;

        //Check for suit repeating and get all repeated suits
        char suit = evaluation.suit(playerHand);

        //Define suit
        if (suit != 'x') result = 5;
        return result;
    }

    @Override
    public int cardRepeating(Hand playerHand) {
        Map<Character, Integer> map = new HashMap<Character, Integer>();
        int result = 0;

        //Check for rank repeating and get all repeated ranks
        map = evaluation.repeatrings(playerHand);
        //Define combination
        if (map.size() != 0) result = evaluation.defineRepeatings(map);
        return result;
    }
}
