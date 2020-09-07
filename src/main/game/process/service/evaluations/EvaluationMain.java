package game.process.service.evaluations;

import game.model.Card;
import game.model.*;

import java.util.*;


public class EvaluationMain implements Evaluation {
    Map<Integer, Card> results = new HashMap<Integer, Card>();


    //main methods
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

        //Remove unrepeated ranks
        map.values().removeIf(value -> value.equals(1));


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
                    if (result == 1) result = 6; //Full house
                    if (result == 3) {
                        // ?
                    }
                    break;
                case 4:
                    result = 7; //Four of a kind
                    break;
            }
        }
        return result;
    }


    //For in a row
    public boolean inARow(Hand hand) {
        //LinkedHashMap<Character, Integer> map = new LinkedHashMap<Character, Integer>();
        hand.sortByRank();
        int[] arr = hand.getHandRankPoints();
        boolean inARow = false;
        int i = 0, combo = 0, result = 0;
        if (arr[arr.length - 1] == 12 && arr[0] == 1) {
            combo++;
        }
        while (++i < arr.length && combo < 5) {
            if (arr[i] - arr[i - 1] == 1) {
                combo++;
                inARow = (combo >= 4);
            } else if (arr[i] - arr[i - 1] != 0) {
                combo = 0;
            }
        }

        return inARow;
    }

    public int comboCardInARow(Hand hand) {
        //LinkedHashMap<Character, Integer> map = new LinkedHashMap<Character, Integer>();
        hand.sortByRank();
        int[] arr = hand.getHandRankPoints();
        int i = 0, combo = 0, result = 0;
        if (arr[arr.length - 1] == 12 && arr[0] == 1) {
            combo++;
        }
        while (++i < arr.length && combo < 5) {
            if (arr[i] - arr[i - 1] == 1) {
                combo++;
                result = arr[i];
            } else if (arr[i] - arr[i - 1] != 0) {
                combo = 0;
            }
        }

        return result;
    }

    //For Suit
    public boolean suit(Hand hand) {
        Map<Character, Integer> map = new HashMap<Character, Integer>();
        boolean result = false;
        for (Card card : hand.getHand()) {
            char suit = card.getSuit();
            if (map.containsKey(suit)) {
                map.put(suit, map.get(suit) + 1);
                if (map.get(suit) == 5) {
                    result = true;
                }
            } else {
                map.put(suit, 1);
            }
        }
        return result;
    }


}
