package game.process.service.combination;

import game.model.Hand;
import game.process.service.evaluations.Evaluation;
import game.process.service.evaluations.EvaluationMain;

import java.util.HashMap;
import java.util.Map;

public class CombinationServiceMain implements CombinationService {

    private Evaluation evaluation = new EvaluationMain();

    @Override
    public int cardInARow(Hand playerHand) {
        Map<Character, Integer> map = new HashMap<Character, Integer>();
        playerHand.sortByRank();
        //Check if there is 5 cards in a row
        boolean inARow = evaluation.inARow(playerHand);
        //Define result
        //If 5 cards in a row - its a minimum Straight. So lets stay it for a while
        int result = (inARow) ? 4 : 0;
        return result;
    }

    @Override
    public int comboCardInARow(Hand playerHand, int combination) {
        int result = evaluation.comboCardInARow(playerHand);


        return result;
    }

    @Override
    public int cardOneSuitResult(Hand playerHand) {
        Map<Character, Integer> map = new HashMap<Character, Integer>();
        int result = 0;

        //Check for suit repeating and get all repeated suits
        char suit = evaluation.suit(playerHand);

        //Define suit
        if (suit != 'x') result = 5;
        return result;
    }

    @Override
    public Hand markCardsCombinationsNoCombo(Hand playerHand) {
        playerHand.sortByRank();
        for (int i = 0; i < 7; i++) {
            if (i > 1) playerHand.getHand(i).setInHand(true);
            else playerHand.getHand(i).setInHand(false);
        }
        return playerHand;
    }

    @Override
    public Hand markCardsCombinationsRepeating(Hand playerHand, int[] comboRepeatings) {
        playerHand.sortByRank();
        int cardCounter = 0;
        for (int j = 0; j < 7; j++) {
            for (int i = 0; i < comboRepeatings.length; i++) {
                if (playerHand.getHand(j).getRankPoints() == comboRepeatings[i]) {
                    playerHand.getHand(j).setInHand(true);
                    cardCounter++;
                }
                //else playerHand.getHand(i).setInHand(false);
            }
        }

        for (int i = 6; i >= 0; i--) {
            if (cardCounter < 5 && !playerHand.getHand(i).isInHand()) {
                playerHand.getHand(i).setInHand(true);
                cardCounter++;
            }
            //else playerHand.getHand(i).setInHand(false);
        }

        return playerHand;
    }


    @Override
    public int cardRepeating(Hand playerHand) {
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        int result = 0;

        //Check for rank repeating and get all repeated ranks
        map = evaluation.repeatrings(playerHand);
        //Define combination
        if (map.size() != 0) result = evaluation.defineRepeatings(map);
        return result;
    }



    @Override
    public String compareWaitlist(Map<Integer, Hand> waitlist) {
        String result = "";
        Evaluation evaluationMain = new EvaluationMain();
        Hand[] playerHand = new Hand[waitlist.size()];
        playerHand = waitlist.values().toArray(playerHand);
        int playersCount = playerHand.length;
        //ArrayList<Hand>(waitlist.values().toArray());
        int i = 0;
        while (i < playersCount) {
            //for (int i = 0; i < playersCount; i++) {
            int resultPoints = playerHand[i].getPoints();
            System.out.print("BFORE CARD ACTIVATING: ");
            playerHand[i].PrintHand(i);

            if (resultPoints == 0) {
                playerHand[i] = markCardsCombinationsNoCombo(playerHand[i]);
            }
            if (resultPoints == 1 || resultPoints == 3 || resultPoints == 7) {
                int[] comboRepeatings = evaluationMain.comboCardsRepeatings(evaluationMain.repeatrings(playerHand[i]), resultPoints);
                playerHand[i] = markCardsCombinationsRepeating(playerHand[i], comboRepeatings);

                playerHand[i].setComboRepeatings(evaluationMain.repeatrings(playerHand[i]));
                //System.out.println("COMBO REPEATINGs: " + Arrays.deepToString(playerHand[i].getComboRepeatings()));

            }
            if (resultPoints == 2 || resultPoints == 6) {
                int[] comboRepeatings = evaluationMain.comboCardsRepeatings(evaluationMain.repeatrings(playerHand[i]), resultPoints);
                //playerHand[i].setComboRepeatings(comboRepeatings);
                playerHand[i] = markCardsCombinationsRepeating(playerHand[i], comboRepeatings);
                //TODO comboCard 1 = comboCard (one pair)
                //TODO comboCard 2 = comboCard (second pair)
            }
            if (resultPoints == 4 || resultPoints == 8 || resultPoints == 9) {
                //TODO comboCard 1 = highest card in a row
                //int comboCard = comboCardInARow(playerHand[i], result);
                //System.out.print(" |InARow.ComboCard = " + comboCard + "|");
                //players[i].setComboCard1(comboCard);
            }
            if (resultPoints == 5) {

            }


            System.out.print("AFTER CARD ACTIVATING: ");
            playerHand[i].PrintHandActive(i);


            i++;
        }


        int resultPoints = playerHand[0].getPoints();
/*        Hand[] resultPlayerHand = new Hand[playersCount];
        resultPlayerHand[0] = playerHand[0];*/
        if (resultPoints == 1 || resultPoints == 3 || resultPoints == 7) {


            for (int ii = 1; ii < playersCount-1; ii++) {
                playerHand[ii].getComboRepeatings();
                String output = playerHand[ii].getPlayerCardsString();
                Boolean equal = false;
                int[][] playerCombination = playerHand[ii].getComboRepeatings();
//for (int iii; ii<)

                if (equal) {
                    output += "=";
                } else {
                    output += " ";
                }

                result += output;
            }
        }
        return result;
    }
}
