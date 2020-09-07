package game.process.service.players;
import game.model.*;
public class PlayersServiceMain implements PlayersService {


    @Override
    public String[] splitPlayers(String inputString) {
        String[] playersStr = inputString.substring(11).split("\\s");

        System.out.println("Players: " + playersStr);
        return playersStr;
    }

    @Override
    public int countPlayers(String inputString) {
        String[] playersStr = splitPlayers(inputString);
        int result = playersStr.length;

        System.out.println("Player count: " + result);
        return result;
    }

    @Override
    public Player[] initializePlayers(String inputString) {
        String[] playersStr = splitPlayers(inputString);
        int playersCount = countPlayers(inputString);

        Player[] players = new Player[playersCount];
        for (int i = 0; i < playersCount; i++) {
            players[i] = new Player(playersStr[i]);
            players[i].PrintPlayer(i);
        }

        return players;
    }


}
