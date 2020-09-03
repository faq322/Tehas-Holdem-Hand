package model;

public class Card {
    //  the first character representing the rank
    //  (one of "A", "K", "Q", "J", "T", "9", "8", "7", "6", "5", "4", "3", "2")
    //  the second character representing the suit
    //  (one of "h", "d", "c", "s")
    private char rank, suit;
    private short rankPoints;

    //constrictor
    public Card(char inputRank, char inputSuit) {
        this.rank = inputRank;
        this.suit = inputSuit;
        switch (inputRank) {
            case '2':
                this.rankPoints = 1;
                break;
            case '3':
                this.rankPoints = 2;
                break;
            case '4':
                this.rankPoints = 3;
                break;
            case '5':
                this.rankPoints = 4;
                break;
            case '6':
                this.rankPoints = 5;
                break;
            case '7':
                this.rankPoints = 6;
                break;
            case '8':
                this.rankPoints = 7;
                break;
            case '9':
                this.rankPoints = 8;
                break;
            case 'T':
                this.rankPoints = 9;
                break;
            case 'J':
                this.rankPoints = 10;
                break;
            case 'Q':
                this.rankPoints = 11;
                break;
            case 'K':
                this.rankPoints = 12;
                break;
            case 'A':
                this.rankPoints = 13;
                break;
        }
    }

    //methods
    public String getCardString() {
        String out = "" + this.rank + this.suit;
        return out;
    }

    public char getRank() {
        return rank;
    }

    public char getSuit() {
        return suit;
    }

    public short getRankPoints() {
        return rankPoints;
    }
}
