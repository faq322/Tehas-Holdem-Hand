package game.process.service.combination;

import game.model.Hand;

import java.util.Map;

public interface CombinationService {
    int cardInARow(Hand playerHand);

    int cardOneSuit(Hand playerHand);

    int cardRepeating(Hand playerHand);

    int comboCardInARow(Hand playerHand, int combination);

    String compareWaitlist(Map<Integer, Hand> waitlist);
}
