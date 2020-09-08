package game.process.service.evaluations;

import game.model.Hand;
import game.model.Player;

import java.util.Map;

public interface Evaluation {

    Map<Integer, Integer> repeatrings(Hand hand);

    int defineRepeatings(Map<Integer, Integer> map);

    boolean inARow(Hand hand);

    char suit(Hand hand);

    int comboCardInARow(Hand hand);



    int comboCardHighCard(Player player); //Useless

    int[] comboCardsRepeatings(Map<Integer, Integer> map, int definedResult);
}
