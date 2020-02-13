package by.gp_solution.eremenko.part6;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Выведите в выходной файл округленное до n знаков после десятичной
 * точки число E. В данной задаче будем считать, что число Е в
 * точности равно 2.7182818284590452353602875.
 * Входные данные:
 * Входной файл INPUT.TXT содержит целое число n (0 ≤ n ≤ 25).
 * Выходные данные:
 * В выходной файл OUTPUT.TXT выведите ответ на задачу.
 *
 * @author Andrei Eremenko
 * @version 1.0  Jan 2020
 */

public class Task46 {

    private static final String E = "2.7182818284590452353602875";

    public static void main(String[] args) {
        File fileIn = new File("INPUT.TXT");
        File fileOut = new File("OUTPUT.TXT");

        if (checkNumber(readNumberFromFile(fileIn))) {
            writeRoundedNumber(fileOut, fileIn);
        }
    }

    /**
     * Reads a number from a file. Returns a number or throws
     * the NoSuchElementException if file does't contain it.
     */
    private static float readNumberFromFile(File file)
            throws NoSuchElementException {
        float number = 0;

        try (Scanner sc = new Scanner(new File(String.valueOf(file)))) {

            if (sc.hasNextFloat()) {
                number = sc.nextFloat();
            } else {
                throw new NoSuchElementException(
                        "The file does not contain elements");
            }
        } catch (FileNotFoundException ex) {
            System.err.println("File not found");
        }
        return number;
    }

    /**
     * The method checks the number for compliance with
     * the next conditions: number exists and is integer;
     * 0 <= number <= 25.
     */
    private static boolean checkNumber(float number) {
        boolean isConditions = false;

        try {
            if (number < 0) {
                throw new InputMismatchException(
                        "The number is negative");
            } else if (number % 1 != 0) {
                throw new InputMismatchException(
                        "The number is not an integer");
            } else if (number > 25) {
                throw new InputMismatchException(
                        "The number is greater than 25");
            } else {
                isConditions = true;
            }
        } catch (InputMismatchException ex) {
            System.err.println(ex.getMessage());
        } catch (NoSuchElementException ex) {
            System.err.println("The file does not contain elements");
        }
        return isConditions;
    }

    /**
     * outputs the rounded number to a file
     */
    private static void writeRoundedNumber(File fileOut, File fileIn) {
        try (FileWriter fileWriter = new FileWriter(fileOut)) {
            fileWriter.write(String.valueOf(new BigDecimal(E)
                    .setScale((int) readNumberFromFile(fileIn),
                            BigDecimal.ROUND_HALF_UP)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}