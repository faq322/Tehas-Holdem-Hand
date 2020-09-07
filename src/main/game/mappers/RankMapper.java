package main.game.mappers;

public class RankMapper {

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
