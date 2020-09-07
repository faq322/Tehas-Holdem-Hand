package game.mappers;

public class RankMapper {

    public RankMapper() {
    }

    public int rankToPoints(char rank){
        int points=0;

        switch (rank) {
            case '2':
                points = 0;
                break;
            case '3':
                points = 1;
                break;
            case '4':
                points = 2;
                break;
            case '5':
                points = 3;
                break;
            case '6':
                points = 4;
                break;
            case '7':
                points = 5;
                break;
            case '8':
                points = 6;
                break;
            case '9':
                points = 7;
                break;
            case 'T':
                points = 8;
                break;
            case 'J':
                points = 9;
                break;
            case 'Q':
                points = 10;
                break;
            case 'K':
                points = 11;
                break;
            case 'A':
                points = 12;
                break;
        }

        return points;
    }

    public char pointsToRank(int points){
        char rank = 'a';

        switch (points) {
            case 0:
                rank = '2';
                break;
            case 1:
                rank = '3';
                break;
            case 2:
                rank = '4';
                break;
            case 3:
                rank = '5';
                break;
            case 4:
                rank = '6';
                break;
            case 5:
                rank = '7';
                break;
            case 6:
                rank = '8';
                break;
            case 7:
                rank = '9';
                break;
            case 8:
                rank = 'T';
                break;
            case 9:
                rank = 'J';
                break;
            case 10:
                rank = 'Q';
                break;
            case 11:
                rank = 'K';
                break;
            case 12:
                rank = 'A';
                break;
        }

        return rank;
    }
}
