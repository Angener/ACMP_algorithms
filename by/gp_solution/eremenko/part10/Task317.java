package by.gp_solution.eremenko.part10;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Task317 {
    public static void main(String[] args) throws IOException {
        File fileIn = new File("INPUT.TXT");
        File fileOut = new File ("OUTPUT.TXT");
        Scanner sc = new Scanner(new File(String.valueOf(fileIn)));
        int x = sc.nextInt();
        int y = sc.nextInt();
        int z = sc.nextInt();
        int w = sc.nextInt();
        int temporaryX;
        int temporaryY;
        int temporaryZ;
        int result = 0;

        for (int i = 0; i <= w / x; i++) {
            for (int j = 0; j <= (w - (temporaryX = x * i)) / y; j++) {
                temporaryY = y * j;
                temporaryZ = w - (temporaryX + temporaryY);
                if (temporaryZ % z == 0) {
                    result++;
                }
            }
        }

        writeResult(fileOut, result);

        sc.close();
    }
    private static void writeResult(File fileOut, int result) {
        try (FileWriter fileWriter = new FileWriter(fileOut)) {
            fileWriter.write(result + "");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
