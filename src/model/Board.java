package model;

public class Board {
    // 10 character string where each 2 characters encode a card
    private Card[] board = new Card[5];

    //constructor
    public Board(String input) {
        int charPos = 0;
        for (int i = 0; i < 5; i++) {
            Character rank = input.charAt(charPos++);
            Character suit = input.charAt(charPos++);
            Card card = new Card(rank, suit);
            board[i] = card;
        }
    }

    //methods
    public Card getCard(int num) {
        return board[num];
    }

    public void PrintBoard() {
        String out = "";
        //Arrays.sort(board, new RankComparator());
        for (int i = 0; i < 5; i++) {
            out += board[i].getCardString();
        }
        System.out.println("Board is: " + out);
    }
}
