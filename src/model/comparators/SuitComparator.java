package model.comparators;

import model.Card;

import java.util.Comparator;

public class SuitComparator implements Comparator<Object> {
    @Override
    public int compare(Object card1, Object card2) {
        String suit1 = String.valueOf(((Card)card1).getSuit());
        String suit2 = String.valueOf(((Card)card2).getSuit());

        return suit1.compareTo(suit2);
    }
}
