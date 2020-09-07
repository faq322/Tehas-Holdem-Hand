package game.process.service.players;
import game.model.*;

public interface PlayersService {

    String[] splitPlayers(String inputString);

    int countPlayers(String inputString);

    Player[] initializePlayers(String inputString);
}
