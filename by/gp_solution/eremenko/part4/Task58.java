package by.gp_solution.eremenko.part4;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * Рассмотрим таблицу, содержащую n строк и m столбцов,
 * в каждой клетке которой расположен ноль или единица.
 * Назовем такую таблицу симпатичной, если в ней нет ни
 * одного квадрата 2 на 2, заполненного целиком нулями
 * или целиком единицами. Так, например, таблица 4 на 4,
 * расположенная слева, является симпатичной, а
 * расположенная справа таблица 3 на 3 - не является.
 * Задано несколько таблиц. Необходимо для каждой из них
 * выяснить, является ли она симпатичной.
 * Входные данные:
 * Первая строка входного файла INPUT.TXT содержит
 * количество t (1 ≤ t ≤ 10) наборов входных данных.
 * Далее следуют описания этих наборов. Описание каждого
 * набора состоит из строки, содержащей числа n и m
 * (1 ≤ n,m ≤ 100), и n строк, каждая из которых содержит
 * по m чисел, разделенных пробелами. j-ое число в i+1-ой
 * строке описания набора входных данных - элемент aij
 * соответствующей таблицы. Гарантируется, что все aij
 * равны либо нулю, либо единице.
 * Выходные данные
 * Для каждого набора входных данных выведите в файл
 * OUTPUT.TXT единственную строку, содержащую слово «YES»,
 * если соответствующая таблица является симпатичной, и
 * слово «NO» - в противном случае.
 *
 * @author Andrei Eremenko
 * @version 1.0  Dec 2019
 */
public class Task58 {

    // The predicate checks if the array is beautiful
    private static Predicate<byte[][]> beautifulChecker = (array) -> {
        boolean isBeautiful = true;

        for (byte i = 0; i < array.length - 1; i++) {

            for (byte j = 0; j < array[i].length - 1; j++) {

                if ((array[i][j] + array[i][j + 1] + array[i + 1][j] +
                        array[i + 1][j + 1]) % 4 == 0) {
                    isBeautiful = false;
                    break;
                }
            }
        }
        return isBeautiful;
    };

    // It accepts boolean from predicate interface and writes result
    // to the file.
    private static Consumer<Boolean> writer = (isBeautiful) -> {
        String result = "NO";

        if (isBeautiful) {
            result = "YES";
        }

        try (FileWriter fileWriter = new FileWriter("OUTPUT.TXT", true)) {
            fileWriter.write(result + "\n");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    };

    public static void main(String[] args) {
        File fileIn = new File("INPUT.TXT");

        try (Scanner sc = new Scanner(new File(String.valueOf(fileIn)))) {

            // Variable stores the number of the sets
            byte quantitySets = sc.nextByte();

            // The loop initializes sets
            for (byte u = 0; u < quantitySets; u++) {
                byte k, x;

                // Temporary initializing variables initialize
                // columns and rows quantity of every set
                k = sc.nextByte();
                x = sc.nextByte();
                byte[][] array = new byte[k][x];

                // The loop initializes elements of set
                for (byte i = 0; i < array.length; i++) {
                    for (byte j = 0; j < array[i].length; j++) {
                        array[i][j] = sc.nextByte();
                    }
                }

                // Main logic passes to lambda scopes
                writer.accept(beautifulChecker.test(array));
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}