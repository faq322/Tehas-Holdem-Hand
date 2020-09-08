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
        for (int j = 6; j >= 0; j--) {
            for (int i = 0; i < comboRepeatings.length; i++) {
                if (playerHand.getHand(j).getRankPoints() == comboRepeatings[i] && cardCounter < 5) {
                    playerHand.getHand(j).setInHand(true);
                    cardCounter++;
                }
            }
        }
        for (int i = 6; i >= 0; i--) {
            if (cardCounter < 5 && !playerHand.getHand(i).isInHand()) {
                playerHand.getHand(i).setInHand(true);
                cardCounter++;
            }
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
        int i = 0;
        while (i < playersCount) {
            int resultPoints = playerHand[i].getPoints();
            //System.out.print("BFORE CARD ACTIVATING: ");
            playerHand[i].PrintHand(i);

            if (resultPoints == 0) {
                playerHand[i] = markCardsCombinationsNoCombo(playerHand[i]);
            }
            if (resultPoints == 1 || resultPoints == 3 || resultPoints == 7) {
                playerHand[i].setComboRepeatings(evaluationMain.repeatrings(playerHand[i]));
            }
            if (resultPoints == 2 || resultPoints == 6) {
                playerHand[i].setComboRepeatings(evaluationMain.repeatrings(playerHand[i]));
                //TODO for suits
            }
            if (resultPoints == 4 || resultPoints == 8 || resultPoints == 9) {
                //TODO comboCard 1 = highest card in a row
            }
            if (resultPoints == 5) {
                //TODO
            }

            //System.out.print("AFTER CARD ACTIVATING: ");
            playerHand[i].PrintHandActive(i);


            i++;
        }


        int resultPoints = playerHand[0].getPoints();
        if (resultPoints == 1 || resultPoints == 3 || resultPoints == 7) {

            for (int ii = 0; ii < playersCount - 1; ii++) {

                //SORTING
                for (int iii = 0; iii < playersCount - ii - 1; iii++) {
                    int a = playerHand[iii].getComboRepeatings()[0][0];
                    int b = playerHand[iii + 1].getComboRepeatings()[0][0];
                    if (a > b) {
                        //swap
                        Hand temp = playerHand[iii];
                        playerHand[iii] = playerHand[iii + 1];
                        playerHand[iii + 1] = temp;
                    }
                }
            }

            //SORT IN ALPHABET
            for (int iii = 0; iii < playersCount - 1; iii++) {
                int a = playerHand[iii].getComboRepeatings()[0][0];
                int b = playerHand[iii + 1].getComboRepeatings()[0][0];
                if (a == b) {
                    String o1 = playerHand[iii].getOutput();
                    String o2 = playerHand[iii + 1].getOutput();
                    int compare = o1.compareTo(o2);
                    if (compare > 0) {
                        //swap
                        Hand temp = playerHand[iii];
                        playerHand[iii] = playerHand[iii + 1];
                        playerHand[iii + 1] = temp;
                    } else {

                    }
                }
            }


            //EQUAL SIGNS
            String output = "";
            for (int iii = 0; iii < playersCount - 1; iii++) {

                output += playerHand[iii].getOutput();

                int a = playerHand[iii].getComboRepeatings()[0][0];
                int b = playerHand[iii + 1].getComboRepeatings()[0][0];
                if (a == b) {
                    output += "=";
                } else {
                    output += " ";
                }
            }
            output += playerHand[playersCount - 1].getOutput() + " ";
            result += output;

        }


        if (resultPoints == 2) {

            for (int ii = 0; ii < playersCount - 1; ii++) {

                //SORTING
                for (int iii = 0; iii < playersCount - ii - 1; iii++) {
                    int a1 = playerHand[iii].getComboRepeatings()[0][0];
                    int b1 = playerHand[iii + 1].getComboRepeatings()[0][0];
                    int a2 = playerHand[iii].getComboRepeatings()[1][0];
                    int b2 = playerHand[iii + 1].getComboRepeatings()[1][0];

                    if ((a1 > b1 && a1 > b2) || (a2 > b1 && a2 > b2)) {
                        //swap
                        Hand temp = playerHand[iii];
                        playerHand[iii] = playerHand[iii + 1];
                        playerHand[iii + 1] = temp;
                    }
                }
            }

            //SORT IN ALPHABET
            for (int iii = 0; iii < playersCount - 1; iii++) {
                int a = playerHand[iii].getComboRepeatings()[0][0];
                int b = playerHand[iii + 1].getComboRepeatings()[0][0];
                if (a == b) {
                    String o1 = playerHand[iii].getOutput();
                    String o2 = playerHand[iii + 1].getOutput();
                    int compare = o1.compareTo(o2);
                    if (compare > 0) {
                        //swap
                        Hand temp = playerHand[iii];
                        playerHand[iii] = playerHand[iii + 1];
                        playerHand[iii + 1] = temp;
                    } else {

                    }
                }
            }


            //EQUAL SIGNS
            String output = "";
            for (int iii = 0; iii < playersCount - 1; iii++) {

                output += playerHand[iii].getOutput();

                int a1 = playerHand[iii].getComboRepeatings()[0][0];
                int b1 = playerHand[iii + 1].getComboRepeatings()[0][0];
                int a2 = playerHand[iii].getComboRepeatings()[1][0];
                int b2 = playerHand[iii + 1].getComboRepeatings()[1][0];
                if ((a1 == b1 && a2 == b2) || (a1 == b2 && a2 == b1)) {
                    output += "=";
                } else {
                    output += " ";
                }
            }
            output += playerHand[playersCount - 1].getOutput() + " ";
            result += output;

        }


        return result;
    }
}
