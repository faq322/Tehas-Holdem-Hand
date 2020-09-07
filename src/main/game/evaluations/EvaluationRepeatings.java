package main.game.evaluations;

import main.game.model.Card;
import main.game.model.Hand;

import java.util.HashMap;
import java.util.Map;

public class EvaluationRepeatings extends EvaluationMain {
    //For repeatings
    public Map<Character, Integer> repeatrings(Hand hand) {
        Map<Character, Integer> map = new HashMap<Character, Integer>();
        for (Card card : hand.getHand()) {
            char rank = card.getRank();
            if (map.containsKey(rank)) {
                map.put(rank, map.get(rank) + 1);
            } else {
                map.put(rank, 1);
            }
        }

        System.out.println("Pair: " + map);
        //Remove unrepeated ranks
        map.values().removeIf(value -> value.equals(1));
        System.out.println("Pair: " + map);

        return map;
    }
}
