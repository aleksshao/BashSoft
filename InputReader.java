package BashSoft;

import java.util.Scanner;

/**
 * Created by ALEKSANDAR on 6/3/2016.
 */
public class InputReader {
    private static final String endCommand = "quit";

    public static void readCommands() {
        Scanner scanner = new Scanner(System.in);
        while (true){
            OutputWriter.writeMessage(String.format("%s", SessionData.currentPath));
            String input = scanner.nextLine().trim();
            if (input.equals(endCommand)){
                break;
            }
            CommandInterpreter.interpretCommand(input);
        }
    }
}
