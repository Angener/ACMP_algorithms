package by.gp_solution.eremenko.final_test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class FinalTask2 {

    public static void main(String[] args) throws FileNotFoundException {

        File fileIn = new File("INPUT.TXT");
        File fileOut = new File("OUTPUT.TXT");

        Scanner sc = new Scanner(new File(String.valueOf(fileIn)));
        int[] array = new int[sc.nextInt()];
        for (int i = 0; i < array.length; i++) {
            array[i] = sc.nextInt();
        }

        int[] sortedArray = new int[array.length];

        int j = 0;
        for (int item : array) {
            if (item > 9999) {
                sortedArray[j] = item;
                j++;
            }
        }
        for (int item : array) {
            if (item > 999 && item < 10000) {
                sortedArray[j] = item;
                j++;
            }
        }
        for (int item : array) {
            if (item > 99 && item < 1000) {
                sortedArray[j] = item;
                j++;
            }
        }
        for (int item : array) {
            if (item > 9 && item < 100) {
                sortedArray[j] = item;
                j++;
            }
        }
        for (int item : array) {
            if (item < 10) {
                sortedArray[j] = item;
                j++;
            }
        }

        outputResult(sortedArray, fileOut);
        sc.close();
    }

    private static void outputResult(int[] sortedArray, File file) {
        try (FileWriter fileWriter = new FileWriter(file)) {
            for (int value : sortedArray) {
                fileWriter.write(value + " ");
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}
