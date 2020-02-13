package by.gp_solution.eremenko.part1;

import java.io.*;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Сумма
 * (Время: 1 сек. Память: 16 Мб Сложность: 19%)
 * Требуется посчитать сумму целых чисел,
 * расположенных
 * между числами 1 и N включительно.
 * Входные данные:
 * В единственной строке входного файла INPUT.TXT
 * записано единственное целое число N, не
 * превышающее по абсолютной величине 10(4).
 * Выходные данные:
 * В единственную строку выходного файла OUTPUT.TXT
 * нужно вывести одно целое число — сумму чисел,
 * расположенных между 1 и N включительно.
 *
 * @author Andrei Eremenko
 * @version 1.0  Dec 2019
 */
public class Task2 {

    private static short number;

    public static void main(String[] args) {
        File fileIn = new File("INPUT.TXT");
        File fileOut = new File("OUTPUT.TXT");

        if (validateFile(fileIn)) {
            outputResult(calculatePositiveAmount(number), fileOut);
        }
    }

    /**
     * The method checks next conditions:
     * the inputted number N is whole;
     * N's absolute value is less than 10001;
     * The input file contains only one number
     * and only one line;
     */
    private static boolean validateFile(File file) {
        boolean isCorrect = false;

        try (Scanner sc = new Scanner(new File(String.valueOf(file)))) {
            number = sc.nextShort();

            if (Math.abs(number) > Math.pow(10, 4)) {
                throw new InputMismatchException("The number's absolute" +
                        " value can't might be greater than 10(4)");
            } else if (sc.hasNext()) {
                throw new InputMismatchException("The file \"Input.txt\"" +
                        " contains several numbers or lines");
            } else {
                isCorrect = true;
            }
        } catch (FileNotFoundException ex) {
            System.err.println("File not found");
            ex.printStackTrace();
        } catch (InputMismatchException ex) {
            System.err.println("Check inputted data");
            ex.printStackTrace();
        } catch (NoSuchElementException ex) {
            System.err.println("The file does't contain numbers");
            ex.printStackTrace();
        }
        return isCorrect;
    }

    /**
     * Returns the sum of numbers from 1 to N.
     */
    private static int calculatePositiveAmount(short number) {
        return ((Math.abs(number) + 1) * number / 2) + ((number > 0) ? 0 : 1);
    }

    /**
     * Represents writing data to the file
     */
    private static void outputResult(int result, File file) {
        try (FileWriter fileWriter = new FileWriter(file)) {
            fileWriter.write(result + "");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}