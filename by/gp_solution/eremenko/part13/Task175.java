package by.gp_solution.eremenko.part13;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * Вы приобрели новые электронные наручные часы с жидкокристаллическим
 * дисплеем. Дисплей отображает часы и минуты с помощью четырех
 * элементов, каждый из которых отображает одну цифру.
 * Три из них состоят из семи полосок, каждая из которых может быть
 * либо белой (неотличимой от фона), либо черной. Вид такого элемента
 * и отображаемые им цифры. Четвертый элемент предназначен для
 * отображения старшей цифры часа. Если она равна нулю, то элемент
 * полностью неактивен (все полоски белые), иначе показывается
 * соответствующая цифра.
 * Вам хочется проверить: все ли в порядке с новым приобретением,
 * а именно, нет ли таких полосок в каком-либо из элементов,
 * которые либо всегда белые, либо всегда черные. Вы хотите начать
 * проверку в некоторое начальное время. Требуется определить, сколько
 * Вам потребуется минут для убеждения в исправности часов.
 * Входные данные:
 * В первой строке входного файла INPUT.TXT находится время начала
 * проверки в формате HH:MM. Часы и минуты записаны с лидирующими
 * нулями, если таковые имеются. (00 ≤ HH ≤ 23, 00 ≤ MM ≤ 59).
 * Выходные данные:
 * В выходной файл OUTPUT.TXT выведите минимальное число минут,
 * необходимое для проверки Ваших часов, если она началась в заданное
 * время.
 *
 * @author Andrei Eremenko
 * @version 1.0  Jan 2020
 */
public class Task175 {
    public static void main(String[] args) throws FileNotFoundException {
        File fileIn = new File("INPUT.TXT");
        File fileOut = new File("OUTPUT.TXT");
        Scanner sc = new Scanner(new File(String.valueOf(fileIn)));
        String str = sc.nextLine();
        StringTokenizer st = new StringTokenizer(str, ":");
        int hours = Integer.parseInt(st.nextToken());
        int minutes = Integer.parseInt(st.nextToken());
        int counter = hours * 60 + minutes;
        int temporary;

        if (hours < 10) {
            temporary = 1200;
        } else if (hours > 19) {
            temporary = 2040;
        } else if (hours > 16) {
            temporary = 1740;
        } else {
            temporary = 1440;
        }
        writeResult(fileOut, temporary, counter);
        System.out.println(temporary - counter);
    }

    private static void writeResult(File fileOut, int temporary, int counter) {
        try (FileWriter fileWriter = new FileWriter(fileOut)) {
            int result = temporary - counter;

            fileWriter.write(result + "");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
