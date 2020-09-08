package game.model;

public class Player {
    //  4 character string where each 2 characters encode a card, with 2 cards per hand
    private Card card1, card2;
    private int combination = 0;
    private int comboCard1, comboCard2;

    public Player() {
    }

    public Player(String input) {
        int charPos = 0;
        Character rank;
        Character suit;

        rank = input.charAt(charPos++);
        suit = input.charAt(charPos++);
        card1 = new Card(rank, suit);

        rank = input.charAt(charPos++);
        suit = input.charAt(charPos++);
        card2 = new Card(rank, suit);

        int card1RankPoints = card1.getRankPoints();
        int card2RankPoints = card2.getRankPoints();
        if (card1RankPoints >= card2RankPoints) {
            comboCard1 = card1RankPoints;
            comboCard2 = card2RankPoints;
        } else {
            comboCard1 = card2RankPoints;
            comboCard2 = card1RankPoints;
        }
    }

    //methods
    public int getComboCard1() {
        return comboCard1;
    }

    public Card getCard(int num) {
        return (num == 0) ? card1 : card2;
    }

    public void PrintPlayer(int num) {
        String out = "";

        out += card1.getCardString();
        out += card2.getCardString();

       // System.out.println("Player[" + num + "] cards: " + out);
    }
}