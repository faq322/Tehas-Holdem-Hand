package model;

public class Card {
    //  the first character representing the rank
    //  (one of "A", "K", "Q", "J", "T", "9", "8", "7", "6", "5", "4", "3", "2")
    //  the second character representing the suit
    //  (one of "h", "d", "c", "s")
    private char rank, suit;

    //constrictor
    public Card(char inputRank, char inputSuit) {
        this.rank = inputRank;
        this.suit = inputSuit;
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

    public int getRankPoints() {
        RankMapper rankMapper = new RankMapper();
        int rankPoints = rankMapper.rankToPoints(rank);
        return rankPoints;
    }
}

class RankMapper {

    public RankMapper() {
    }

    public int rankToPoints(char rank){
        int points=0;

        switch (rank) {
            case '2':
                points = 1;
                break;
            case '3':
                points = 2;
                break;
            case '4':
                points = 3;
                break;
            case '5':
                points = 4;
                break;
            case '6':
                points = 5;
                break;
            case '7':
                points = 6;
                break;
            case '8':
                points = 7;
                break;
            case '9':
                points = 8;
                break;
            case 'T':
                points = 9;
                break;
            case 'J':
                points = 10;
                break;
            case 'Q':
                points = 11;
                break;
            case 'K':
                points = 12;
                break;
            case 'A':
                points = 13;
                break;
        }

        return points;
    }

    public char pointsToRank(int points){
        char rank = 'a';

        switch (points) {
            case 1:
                rank = '2';
                break;
            case 2:
                rank = '3';
                break;
            case 3:
                rank = '4';
                break;
            case 4:
                rank = '5';
                break;
            case 5:
                rank = '6';
                break;
            case 6:
                rank = '7';
                break;
            case 7:
                rank = '8';
                break;
            case 8:
                rank = '9';
                break;
            case 9:
                rank = 'T';
                break;
            case 10:
                rank = 'J';
                break;
            case 11:
                rank = 'Q';
                break;
            case 12:
                rank = 'K';
                break;
            case 13:
                rank = 'A';
                break;
        }

        return rank;
    }
}