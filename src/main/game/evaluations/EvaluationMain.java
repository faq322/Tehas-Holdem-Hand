package game.evaluations;

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
        System.out.print("RESULT: " + result);
        return result;
    }


    //For in a row
    public boolean inARow(Hand hand) {
        //LinkedHashMap<Character, Integer> map = new LinkedHashMap<Character, Integer>();
        hand.sortByRank();
        int[] arr = hand.getHandRankPoints();
        boolean inARow = false;
        int i = 0, combo = 0, result = 0;
        hand.PrintHand(0);
        if (arr[arr.length - 1] == 13 && arr[0] == 1) {
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

    //For Suit
    public char suit(Hand hand) {
        Map<Character, Integer> map = new HashMap<Character, Integer>();
        boolean result = false;
        char resultSuit = 'x';
        for (Card card : hand.getHand()) {
            char suit = card.getSuit();
            if (map.containsKey(suit)) {
                map.put(suit, map.get(suit) + 1);
                if (map.get(suit) == 5) {
                    result = true;
                    resultSuit = suit;
                }
            } else {
                map.put(suit, 1);
            }
        }

        return resultSuit;
    }


}
