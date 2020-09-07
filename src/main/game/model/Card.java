package main.game.model;

import main.game.mappers.*;

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

