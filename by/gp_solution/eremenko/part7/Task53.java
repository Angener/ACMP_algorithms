package by.gp_solution.eremenko.part7;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Раскраска таблицы умножения
 * (Время: 1 сек. Память: 16 Мб Сложность: 22%)
 * Таблицей умножения назовем таблицу размера n строк на m столбцов,
 * в которой на пересечении i-ой строки и j-ого столбца стоит число
 * i∙j (строки и столбцы нумеруются с единицы).
 * В одной из математических школ было решено провести педагогический
 * эксперимент. Для того, чтобы ученикам было проще запоминать
 * таблицу умножения, некоторые числа в ней будут покрашены в красный,
 * некоторые - в синий, а некоторые - в зеленый цвет (оставшиеся
 * числа будут черными).
 * Процесс покраски чисел можно условно разбить на четыре этапа. На
 * первом этапе все числа красятся в черный цвет. На втором - все
 * четные числа красятся в красный цвет, на третьем – все числа,
 * делящиеся на 3, красятся в зеленый цвет, на четвертом - все числа,
 * делящиеся на 5, красятся в синий цвет.
 * Директор школы хочет знать, какое количество картриджей для
 * принтеров необходимо закупить для печати таблиц. Поэтому ему
 * необходима информация о том, сколько чисел какого цвета будет в
 * одной раскрашенной таблице умножения n на m. Напишите программу,
 * решающую задачу подсчета соответствующих количеств.
 * Входные данные:
 * Входной файл INPUT.TXT содержит два натуральных числа n и m
 * (1 ≤ n,m ≤ 1000).
 * Выходные данные:
 * В первой строке выходного файла OUTPUT.TXT выведите количество чисел,
 * покрашенных в красный цвет, во второй - в зеленый, в третьей - в синий,
 * в четвертой - в черный. Следуйте формату, приведенному в примерах.
 *
 * @author Andrei Eremenko
 * @version 1.0  Jan 2020
 */
public class Task53 {
    public static void main(String... args) {
        File fileIn = new File("INPUT.TXT");
        File fileOut = new File("OUTPUT.TXT");

        if (checkNumbers(readNumbersFromFile(fileIn))) {
            writeRoundedNumber(fileOut, countColors(
                    fillArrayOfNumbersProducts(
                            readNumbersFromFile(fileIn))));
        }
    }

    /**
     * Reads data from a file. Throws NoSuchElementException
     * if file contains less than two numbers.
     */
    private static int[] readNumbersFromFile(File file)
            throws NoSuchElementException {
        int[] numbers = new int[2];

        try (Scanner sc = new Scanner(new File(String.valueOf(file)))) {
            if (!sc.hasNext()) {
                throw new NoSuchElementException("The file does not contain elements");
            }
            numbers[0] = sc.nextInt();
            if (!sc.hasNext()) {
                throw new NoSuchElementException("The file does not contain a second element");
            }
            numbers[1] = sc.nextInt();
            if (sc.hasNext()) {
                throw new NoSuchElementException("The file contains greater than two element");
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return numbers;
    }

    /**
     * Checks numbers for compliance with the next
     * conditions: two numbers are exist and they are
     * greatest than one and less than one thousand.
     * Returns true if numbers comply with the conditions
     * or false if are not.
     */
    private static boolean checkNumbers(int[] numbers) {
        boolean isConditions = false;

        try {
            if (numbers[0] < 1 || numbers[1] < 1) {
                throw new InputMismatchException("The file contains a number less than 1");
            } else if (numbers[1] > 1000 || numbers[0] > 1000) {
                throw new InputMismatchException("The file contains a number greater than 1000");
            } else {
                isConditions = true;
            }
        } catch (NoSuchElementException ex) {
            System.err.println(ex.getMessage());
        }
        return isConditions;
    }

    /**
     * The method accepts an array of integers - two
     * numbers which determine quantity of columns and
     * rows of multiplication table. Returns array of
     * integers - products of all factors.
     */
    private static int[] fillArrayOfNumbersProducts(int[] numbers) {
        int[] products = new int[numbers[0] * numbers[1]];
        int counter = 0;
        for (int i = 1; i <= numbers[0]; i++) {
            for (int j = 1; j <= numbers[1]; j++) {
                products[counter] = i * j;
                counter++;
            }
        }
        return products;
    }

    /**
     * Accepts an array of integers and paints them
     * in accordance with the task's conditions
     */
    private static int[] countColors(int[] products) {
        int red = 0;
        int green = 0;
        int blue = 0;
        int black = products.length;

        for (int value : products) {

            if (value % 2 == 0) {
                red++;
                black--;
            }

            if (value % 3 == 0) {
                green++;

                if (value % 2 == 0) {
                    red--;
                } else {
                    black--;
                }
            }

            if (value % 5 == 0) {
                blue++;

                if (value % 3 == 0) {
                    green--;
                } else if (value % 2 == 0) {
                    red--;
                } else {
                    black--;
                }
            }
        }
        return new int[]{red, green, blue, black};
    }

    /**
     * outputs results to the file.
     */
    private static void writeRoundedNumber(File fileOut, int[] colors) {
        try (FileWriter fileWriter = new FileWriter(fileOut)) {
            fileWriter.write("RED : " + colors[0] + "\n");
            fileWriter.write("GREEN : " + colors[1] + "\n");
            fileWriter.write("BLUE : " + colors[2] + "\n");
            fileWriter.write("BLACK : " + colors[3] + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
