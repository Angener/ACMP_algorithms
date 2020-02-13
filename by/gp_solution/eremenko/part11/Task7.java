package by.gp_solution.eremenko.part11;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Scanner;
import java.util.function.BiPredicate;

/**
 * Главный вождь племени Абба не умеет считать. В обмен на одну из его
 * земель вождь другого племени предложил ему выбрать одну из трех куч
 * с золотыми монетами. Но вождю племени Абба хочется получить
 * наибольшее количество золотых монет. Помогите вождю сделать
 * правильный выбор!
 * Входные данные:
 * В первой строке входного файла INPUT.TXT записаны три натуральных
 * числа через пробел. Каждое из чисел не превышает 10100. Числа
 * записаны без ведущих нулей.
 * Выходные данные:
 * В выходной файл OUTPUT.TXT нужно вывести одно целое число —
 * максимальное количество монет, которые может взять вождь.
 *
 * @author Andrei Eremenko
 * @version 1.0  Jan 2020
 */
public class Task7 {
    private static BiPredicate<String, String> biPredicate = (numberOne, numberTwo) -> {
        boolean isGreater = false;
        if (new BigInteger(numberOne).compareTo(new BigInteger(numberTwo)) < 0)
            isGreater = true;
        return isGreater;
    };

    public static void main(String[] args) throws FileNotFoundException {
        File fileIn = new File("INPUT.TXT");
        File fileOut = new File("OUTPUT.TXT");
        Scanner sc = new Scanner(new File(String.valueOf(fileIn)));
        String temporary = sc.next();

        while (sc.hasNext()) {
            String str = sc.next();

            if (biPredicate.test(temporary, str)) {
                temporary = str;
            }
        }
        writeResult(fileOut, temporary);
        sc.close();
    }

    private static void writeResult(File fileOut, String temporary) {
        try (FileWriter fileWriter = new FileWriter(fileOut)) {
            fileWriter.write(temporary);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
