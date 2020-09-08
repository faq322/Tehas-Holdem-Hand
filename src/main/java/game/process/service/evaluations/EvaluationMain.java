package game.process.service.evaluations;

import game.mappers.RankMapper;
import game.model.Card;
import game.model.Hand;
import game.model.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class EvaluationMain implements Evaluation {
    Map<Integer, Card> results = new HashMap<Integer, Card>();


    //main methods
    //For repeatings
    public Map<Integer, Integer> repeatrings(Hand hand) {
        RankMapper rankMapper = new RankMapper();
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (Card card : hand.getHand()) {
            int rank = card.getRankPoints();
            if (map.containsKey(rank)) {
                map.put(rank, map.get(rank) + 1);
            } else {
                map.put(rank, 1);
            }
        }

        //Remove unrepeated ranks
        map.values().removeIf(value -> value.equals(1));

        //Remove crap
        if (map.size()>2) {
            Map.Entry<Integer, Integer> minEntry = null;
            for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
                if (minEntry == null || entry.getValue() < minEntry.getValue()) {
                    minEntry = entry;
                }
            }
            int minRank = minEntry.getKey();
            map.remove(minRank);
        }
        return map;
    }

    public int defineRepeatings(Map<Integer, Integer> map) {
        //RankMapper rankMapper = new RankMapper();
        List<Integer> combinations = new ArrayList<Integer>(map.values());
                int result = 0;
        switch (combinations.get(0)) {
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
        if (combinations.size() >= 2) {
            switch (combinations.get(1)) {
                case 2:
                    if (result == 1) result = 2; //Two pairs
                    if (result == 3) result = 6; //Full house
                    break;
                case 3:
                    if (result == 1) result = 6; //Full house
                    if (result == 3) result = 6;
                    break;
                case 4:
                    result = 7; //Four of a kind
                    break;
            }
        } else if (combinations.size() == 3) {
            switch (combinations.get(2)) {
                case 2:
                    if (result == 2) result = 2;
                    if (result == 6) result =6;
                    break;
                case 3:
                    if (result ==2) result =6;
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
        if (arr[arr.length - 1] == 14 && arr[0] == 1) {
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
        if (arr[arr.length - 1] == 14 && arr[0] == 1) {
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

    @Override
    public int comboCardHighCard(Player player) {
        int cardRankPoints1 = player.getCard(0).getRankPoints();
        int cardRankPoints2 = player.getCard(1).getRankPoints();
        int result = (cardRankPoints1 >= cardRankPoints2) ? cardRankPoints1 : cardRankPoints2;
        return result;
    }

    @Override
    public int[] comboCardsRepeatings(Map<Integer, Integer> map, int definedResult) {
        RankMapper rankMapper = new RankMapper();
        ArrayList<Integer> ranks = new ArrayList<Integer>(map.keySet());
        //ArrayList<Integer> combinations = new ArrayList<Integer>(map.values());
        int[] result = new int[ranks.size()];
        switch (definedResult) {
            case 1:
            case 3:
            case 7:
                result[0] = ranks.get(0);
                break;
            case 2:
                int rank1 = ranks.get(0);
                int rank2 = ranks.get(1);
                if (rank1 > rank2) {
                    result[0] = rank1;
                    result[1] = rank2;
                } else {
                    result[0] = rank2;
                    result[1] = rank1;
                }
                break;
            case 6:
                int rankPoints1 = ranks.get(0);
                int rankPoints2 = ranks.get(1);

                int combination1 = map.get(rankPoints1);
                int combination2 = map.get(rankPoints2);


                if (combination1 > combination2) {
                    result[0] = rankPoints1;
                    result[1] = rankPoints2;
                } else {
                    result[0] = rankPoints2;
                    result[1] = rankPoints1;
                }
                break;
        }
        return result;
    }

    //For Suit
    public char suit(Hand hand) {
        Map<Character, Integer> map = new HashMap<Character, Integer>();
        char result = 'x';
        for (Card card : hand.getHand()) {
            char suit = card.getSuit();
            if (map.containsKey(suit)) {
                map.put(suit, map.get(suit) + 1);
                if (map.get(suit) == 5) {
                    result = suit;
                }
            } else {
                map.put(suit, 1);
            }
        }
        return result;
    }


}
