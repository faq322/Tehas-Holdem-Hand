package game.process.service.evaluations;

import game.model.Hand;

import java.util.Map;

public interface Evaluation {

    Map<Character, Integer> repeatrings(Hand hand);

    int defineRepeatings(Map<Character, Integer> map);

    boolean inARow(Hand hand);

    boolean suit(Hand hand);
}
