package main.game.validators;

import java.util.function.Predicate;
import java.util.regex.Pattern;

public class Validator {
    public Validator() {

    }

    private Predicate<String> IS_BOARD_VALID =
            Pattern.compile(
                    //checks 5 board cards
                    //Matches: 4cKs4h8s7s, 2h3h4h5d8d
                    //Non-Matches: 4CKS4H8S7S, 4c
                    "^([AKQJT98765432][hdcs]){5}.*$"
            ).asPredicate();

    private Predicate<String> ARE_PLAYERS_VALID =
            Pattern.compile(
                    //checks players, maximal player count is 10
                    //Matches: 4cKs4h8s7s Ad4s Ac4d As9s KhKd 5d6d
                    //Matches: 2h3h4h5d8d KdKs 9hJh
                    //Non-Matches: 2h3h4h5d8d Ah
                    "^[a-z,A-Z,0-9]{10}(\\s([AKQJT98765432][hdcs]){2}){2,10}$"
            ).asPredicate();


    public void isBoardValid(String input) throws Exception {
        Boolean result_board = IS_BOARD_VALID.test(input);

        //Error if tests haven't passed
        if (!result_board ){//&& !result_individual) {
            throw new Exception(
                    "Board validation tests haven't passed."
            );
        }
    }


    public void arePlayersValid(String input) throws Exception {
        Boolean result_players = ARE_PLAYERS_VALID.test(input);

        //Error if tests haven't passed
        if (!result_players ){//&& !result_individual) {
            throw new Exception(
                    "Player validation tests haven't passed."
            );
        }

    }

/*    public int countPlayers(String input){

    }*/
}