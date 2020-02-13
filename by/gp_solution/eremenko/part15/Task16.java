package by.gp_solution.eremenko.part15;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Лесенкой называется набор кубиков, в котором каждый более
 * верхний слой содержит кубиков меньше, чем предыдущий.
 * Требуется написать программу, вычисляющую число лесенок,
 * которое можно построить из N кубиков.
 * Входные данные:
 * Во входном файле INPUT.TXT записано натуральное число N
 * (1 ≤ N ≤ 100) – количество кубиков в лесенке.
 * Выходные данные:
 * В выходной файл OUTPUT.TXT необходимо вывести число лесенок,
 * которые можно построить из N кубиков.
 *
 * @author Andrei Eremenko
 * @version 1.0  Jan 2020
 */
public class Task16 {

    private static int stairsQuantity = 0;

    public static void main(String[] args) {
        File fileIn = new File("INPUT.TXT");
        File fileOut = new File("OUTPUT.TXT");
        calculate(1, readNumberFromFile(fileIn));
        writeResult(fileOut, stairsQuantity);
    }

    private static void calculate(int start, int cubesQuantity) {
        for (int i = start; i <= cubesQuantity; i++) {
            calculate(i + 1, cubesQuantity - i);
        }
        if (cubesQuantity == 0) {
            stairsQuantity++;
        }
    }

    private static int readNumberFromFile(File file)
            throws NoSuchElementException {
        int cubsQuantity = 0;

        try (Scanner sc = new Scanner(new File(String.valueOf(file)))) {

            if (sc.hasNextInt()) {
                cubsQuantity = sc.nextInt();
            } else {
                throw new NoSuchElementException(
                        "The file does not contain elements");
            }

        } catch (FileNotFoundException ex) {
            System.err.println("File not found");
        }
        return cubsQuantity;
    }

    private static void writeResult(File fileOut, int stairsQuantity) {
        try (FileWriter fileWriter = new FileWriter(fileOut)) {
            fileWriter.write(stairsQuantity + "");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
