package by.gp_solution.eremenko.part14;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Для игры в «Поле чудес» используется круглый барабан, разделенный
 * на сектора, и стрелка. В каждом секторе записано некоторое число.
 * В различных секторах может быть записано одно и то же число.
 * Однажды ведущий игры решил изменить правила. Он сам стал вращать
 * барабан и называть игроку (который барабана не видел) все числа
 * подряд в том порядке, в котором на них указывала стрелка в процессе
 * вращения барабана. Получилось так, что барабан сделал целое число
 * оборотов, то есть последний сектор совпал с первым. После этого,
 * ведущий задал участнику вопрос: какое наименьшее число секторов
 * может быть на барабане? Требуется написать программу, отвечающую
 * на этот вопрос ведущего.
 * Входные данные:
 * В первой строке входного файла INPUT.TXT записано число N –
 * количество чисел, которое назвал ведущий (2 ≤ N ≤ 30000). Во второй
 * строке записано N чисел, на которые указывала стрелка в процессе
 * вращения барабана. Первое число всегда совпадает с последним (в
 * конце стрелка указывает на тот же сектор, что и в начале). Числа,
 * записанные в секторах барабана – натуральные, не превышающие 32000.
 * Выходные данные:
 * В выходной файл OUTPUT.TXT необходимо вывести одно число – минимальное
 * число секторов, которое может быть на барабане.
 *
 * @author Andrei Eremenko
 * @version 1.0  Jan 2020
 */
public class Task17 {
    public static void main(String[] args) throws FileNotFoundException {
        File fileIn = new File("INPUT.TXT");
        File fileOut = new File("OUTPUT.TXT");
        Scanner sc = new Scanner(new File(String.valueOf(fileIn)));
        int n = sc.nextInt();
        int[] array = new int[n];

        for (int i = 0; i < n; i++) {
            array[i] = sc.nextInt();
        }

        sc.close();
        int[] prefix = new int[n + 1];
        int length = 0;

        for (int i = 1; i < n; i++) {

            while (true) {

                if (array[length] == (array[i])) {
                    length++;
                    break;
                }

                if (length == 0) {
                    break;
                }
                length = prefix[length];
            }
            prefix[i + 1] = length;
        }

        while (true) {
            int period = n - length;

            if ((n - 1) % period == 0) {
                writeResult(fileOut, period);
                return;
            }

            assert (length > 1);
            length = prefix[length];
        }
    }

    private static void writeResult(File fileOut, int period) {
        try (FileWriter fileWriter = new FileWriter(fileOut)) {

            fileWriter.write(period + "");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
