package by.gp_solution.eremenko.final_test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class FinalTask1 {
    public static void main(String[] args) throws FileNotFoundException {

        File fileIn = new File("INPUT.TXT");
        File fileOut = new File("OUTPUT.TXT");

        Scanner sc = new Scanner(new File(String.valueOf(fileIn)));

        int[] array = new int[sc.nextInt()];

        for (int i = 0; i < array.length; i++) {
            array[i] = sc.nextInt();
        }

        int max = array[0];
        int min = array[0];

        for (int value : array) {

            if (value > max) {
                max = value;
            }
            if (value < min) {
                min = value;
            }
        }
        outputResult(min, max, array, fileOut);
        sc.close();
    }

    private static void outputResult(int min, int max, int[] array, File file) {
        try (FileWriter fileWriter = new FileWriter(file)) {
            for (int i = 0; i < array.length; i++) {
                if (array[i] == min) {
                    fileWriter.write(i + " ");
                }
            }
            fileWriter.write("\n");
            for (int i = 0; i < array.length; i++) {
                if (array[i] == max) {
                    fileWriter.write(i + " ");
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
