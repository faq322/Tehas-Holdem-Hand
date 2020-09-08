package game.model;

import game.comparators.RankComparator;
import game.comparators.SuitComparator;

import java.util.*;

public class Hand {
    private Card[] availableCards = new Card[7];
    private int points;
    private String output;
    private int[][] comboRepeatings;

    public int[][] getComboRepeatings() {
        return comboRepeatings;
    }

    public void setComboRepeatings(Map<Integer, Integer> comboRepeatings) {

        int[][] arr = new int[comboRepeatings.size()][2];
        Set entries = comboRepeatings.entrySet();
        Iterator entriesIterator = entries.iterator();

        int i = 0;
        while(entriesIterator.hasNext()){

            Map.Entry mapping = (Map.Entry) entriesIterator.next();

            arr[i][0] = mapping.getKey().hashCode();
            arr[i][1] = mapping.getValue().hashCode();

            i++;
        }

        this.comboRepeatings = arr;
    }

    public Hand() {
    }

    public Hand(Board board, Player player) {
        for (int i = 0; i < 7; i++) {
            if (i < 5) {
                availableCards[i] = new Card(board.getCard(i).getRank(),board.getCard(i).getSuit());//board.getCard(i);
                availableCards[i].setInHand(false);
                availableCards[i].setInPlayer(false);
            } else {
                availableCards[i] = player.getCard(i - 5);
                availableCards[i].setInHand(false);
                availableCards[i].setInPlayer(true);
            }
            output = player.getCard(0).getCardString() + player.getCard(1).getCardString();
        }
    }

    public void sortByRank() {
        Arrays.sort(availableCards, new RankComparator());
    }

    public void sortBySuit() {
        Arrays.sort(availableCards, new SuitComparator());
    }


    public Card[] getHand() {
        return availableCards;
    }

    public Card getHand(int i) {
        return availableCards[i];
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

    public void PrintHandActive(int num) {
        String out = "";
        //Arrays.sort(availableCards, new SuitComparator());

        for (int i = 0; i < 7; i++) {
            if (availableCards[i].isInHand()) out += availableCards[i].getCardString();
        }
        System.out.println("Player[" + num + "] hand: " + out);
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public String getPlayerCardsString() {
        String result = "";
        for (int i = 0; i < 7; i++) {
            result += (availableCards[i].isInPlayer()) ? availableCards[i].getCardString() : "";
        }
        return result;
    }
}
