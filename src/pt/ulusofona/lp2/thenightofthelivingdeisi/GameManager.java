package pt.ulusofona.lp2.thenightofthelivingdeisi;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class GameManager {
    int[] worldSize;
    int initialTeam;
    int currentTeam;
    boolean gameStatus;
    Board board = new Board();

    public GameManager() {
    }

    public boolean loadGame(File file) {
        ArrayList<String> info = new ArrayList<String>();
        try {
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                info.add(line);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return false;
    }

}

