package model;

import model.comparators.RankComparator;
import model.comparators.SuitComparator;

import java.util.*;

public class Hand {
    private Card[] availableCards = new Card[7];
    private short points;

    public Hand() {
    }

    public Hand(Board board, Player player) {
        for (int i = 0; i < 7; i++) {
            if (i < 5) {
                availableCards[i] = board.getCard(i);
            } else {
                availableCards[i] = player.getCard(i - 5);
            }
        }
    }

/*    private void addCard(Card card, int i) {
        availableCards[i] = card;
    }*/

    private void sortByRank() {
        Arrays.sort(availableCards, new RankComparator());
    }

    private void sortBySuit() {
        Arrays.sort(availableCards, new SuitComparator());
    }


    public Card[] getHand() {

        return availableCards;
    }

    public void PrintHand(int num) {
        String out = "";
        //Arrays.sort(availableCards, new SuitComparator());

        for (int i = 0; i < 7; i++) {
            out += availableCards[i].getCardString();
        }
        System.out.println("Player[" + num + "] hand: " + out);
    }
}
