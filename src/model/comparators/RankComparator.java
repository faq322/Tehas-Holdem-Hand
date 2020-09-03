package model.comparators;

import model.Card;

import java.util.Comparator;

public class RankComparator implements Comparator<Object> {
    public int compare(Object card1, Object card2) {
        short rank1 = ((Card)card1).getRankPoints();
        short rank2 = ((Card)card2).getRankPoints();

        return rank1 - rank2;
    }

    //  the first character representing the rank
    //  (one of "A", "K", "Q", "J", "T", "9", "8", "7", "6", "5", "4", "3", "2")
    //  the second character representing the suit
    //  (one of "h", "d", "c", "s")
}
