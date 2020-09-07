package game.process.service.combination;

import game.model.Hand;
import game.process.service.evaluations.Evaluation;
import game.process.service.evaluations.EvaluationMain;

import java.util.HashMap;
import java.util.Map;

public class CombinationServiceMain implements CombinationService {

    private Evaluation evaluation = new EvaluationMain();

    @Override
    public int cardInARow(Hand playerHand) {
        Map<Character, Integer> map = new HashMap<Character, Integer>();
        playerHand.sortByRank();
        //Check if there is 5 cards in a row
        boolean inARow = evaluation.inARow(playerHand);
        //Define result
        //If 5 cards in a row - its a minimum Straight. So lets stay it for a while
        int result = (inARow) ? 4 : 0;
        return result;
    }

    @Override
    public int comboCardInARow(Hand playerHand, int combination){
        int result=evaluation.comboCardInARow(playerHand);


        return result;
    }

    @Override
    public int cardOneSuit(Hand playerHand) {
        Map<Character, Integer> map = new HashMap<Character, Integer>();
        int result = 0;

        //Check for suit repeating and get all repeated suits
        boolean suit = evaluation.suit(playerHand);

        //Define suit
        if (suit) result = 5;
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

    @Override
    public String compareWaitlist(Map<Integer, Hand> waitlist) {
        String result = "";


        return result;
    }
}
