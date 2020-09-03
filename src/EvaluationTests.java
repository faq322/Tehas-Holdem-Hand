import model.Card;
import model.Hand;

import java.util.*;


public class EvaluationTests {
    Map<Integer, Card> results = new HashMap<Integer, Card>();

    public EvaluationTests() {
        //this.results = results;
    }

    //main methods
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

    public int defineRepeatings(Map<Character, Integer> map) {
        ArrayList<Integer> arr = new ArrayList<Integer>(map.values());
        int result = 0;

        switch (arr.get(0)) {
            case 2:
                result = 1; //Pair
                break;
            case 3:
                result = 3; //Three of a kind
                break;
            case 4:
                result = 7; //Four of a kind
                break;
        }
        if (arr.size() == 2) {
            switch (arr.get(1)) {
                case 2:
                    if (result == 1) result = 2; //Two pairs
                    if (result == 3) result = 6; //Full house
                    break;
                case 3:
                    if (result == 1) result = 6;//Full house
                    break;
            }
        }
        System.out.print("RESULT: " + result);
        return result;
    }
}
