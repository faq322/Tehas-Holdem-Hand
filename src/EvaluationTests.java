import model.Card;
import model.Hand;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;


public class EvaluationTests {
    Map<Integer, Card> results = new HashMap<Integer, Card>();

    public EvaluationTests() {
        //this.results = results;
    }

    //main methods
    public void repeatrings(Hand hand){
        Map<Character, Integer> map = new HashMap<Character, Integer>();
        for (Card card : hand.getHand()) {
            char rank = card.getRank();
            if (map.containsKey(rank)) {
                map.put(rank, map.get(rank) + 1);
            } else {
                map.put(rank, 1);
            }
        }

        Set<Character> keys = map.keySet();

        System.out.println("Pair: " +map);
        System.out.println("Pair: " +keys);

        //Map<Character, Integer> out = new HashMap<Character, Integer>();
        for (Character ch : keys) {
            if (map.get(ch) == 1) {
                System.out.println("REMOVE "+ ch);
                map.remove(ch);
            }
        }
        System.out.println("Pair: " +map);
        System.out.println("Pair: " +keys);
        //System.out.println("Pair: " +map);
    }


}
