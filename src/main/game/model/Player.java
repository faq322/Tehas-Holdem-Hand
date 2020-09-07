package game.model;

public class Player {
    //  4 character string where each 2 characters encode a card, with 2 cards per hand
    private Card card1, card2;
    private int combination = 0;
    private int comboCard1, comboCard2;

    //constrictor
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
    }

    //methods


    public int getComboCard1() {
        return comboCard1;
    }

    public void setComboCard1(int comboCard1) {
        this.comboCard1 = comboCard1;
    }

    public int getComboCard2() {
        return comboCard2;
    }

    public void setComboCard2(int comboCard2) {
        this.comboCard2 = comboCard2;
    }

    public Card getCard(int num) {
        return (num == 0) ? card1 : card2;
    }

    public int getCombination() {
        return combination;
    }

    public void setCombination(int combination) {
        this.combination = combination;
    }

    public void PrintPlayer(int num) {
        String out = "";

        out += card1.getCardString();
        out += card2.getCardString();

        System.out.println("Player[" + num + "] cards: " + out);
    }
}