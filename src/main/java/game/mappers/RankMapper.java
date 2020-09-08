package game.mappers;

public class RankMapper {

    public RankMapper() {
    }

    public int rankToPoints(char rank){
        int points=0;

        switch (rank) {
            case '2':
                points = 2;
                break;
            case '3':
                points = 3;
                break;
            case '4':
                points = 4;
                break;
            case '5':
                points = 5;
                break;
            case '6':
                points = 6;
                break;
            case '7':
                points = 7;
                break;
            case '8':
                points = 8;
                break;
            case '9':
                points = 9;
                break;
            case 'T':
                points = 10;
                break;
            case 'J':
                points = 11;
                break;
            case 'Q':
                points = 12;
                break;
            case 'K':
                points = 13;
                break;
            case 'A':
                points = 14;
                break;
        }

        return points;
    }

    public char pointsToRank(int points){
        char rank = 'a';

        switch (points) {
            case 2:
                rank = '2';
                break;
            case 3:
                rank = '3';
                break;
            case 4:
                rank = '4';
                break;
            case 5:
                rank = '5';
                break;
            case 6:
                rank = '6';
                break;
            case 7:
                rank = '7';
                break;
            case 8:
                rank = '8';
                break;
            case 9:
                rank = '9';
                break;
            case 10:
                rank = 'T';
                break;
            case 11:
                rank = 'J';
                break;
            case 12:
                rank = 'Q';
                break;
            case 13:
                rank = 'K';
                break;
            case 14:
                rank = 'A';
                break;
        }

        return rank;
    }
}
