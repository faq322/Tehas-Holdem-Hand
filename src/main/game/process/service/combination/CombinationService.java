package game.process.service.combination;

import game.model.Hand;

public interface CombinationService {
    int cardInARow(Hand playerHand);

    int cardOneSuit(Hand playerHand);

    int cardRepeating(Hand playerHand);
}
