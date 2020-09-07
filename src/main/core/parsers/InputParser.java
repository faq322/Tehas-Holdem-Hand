package main.core.parsers;

import java.util.Scanner;

public  class InputParser {

    public static String getInput() {
        Scanner sc = new Scanner(System.in);  // Create a Scanner object

        System.out.println("Input:");
        String input = sc.nextLine();  // Read user input
        return input;
    }
}
