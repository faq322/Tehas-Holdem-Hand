package main.game.model;

import main.game.comparators.*;
import java.util.Arrays;

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

    public void sortByRank() {
        Arrays.sort(availableCards, new RankComparator());
    }

    public void sortBySuit() {
        Arrays.sort(availableCards, new SuitComparator());
    }


    public Card[] getHand() {
        return availableCards;
    }

    public int[] getHandRankPoints() {
        int[] arr = new int[availableCards.length];
        for (int i = 0; i < availableCards.length; i++) {
            arr[i] = availableCards[i].getRankPoints();
        }
        return arr;
    }

    public char[] getHandRank() {
        char[] arr = new char[availableCards.length];
        for (int i = 0; i < availableCards.length; i++) {
            arr[i] = availableCards[i].getRank();
        }
        return arr;
    }

    public char[] getHandSuit() {
        char[] arr = new char[availableCards.length];
        for (int i = 0; i < availableCards.length; i++) {
            arr[i] = availableCards[i].getSuit();
        }
        return arr;
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
