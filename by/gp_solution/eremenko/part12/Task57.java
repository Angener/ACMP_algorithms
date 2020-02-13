package by.gp_solution.eremenko.part12;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Компания «Маша и медведи» является самым крупным интернет-провайдером
 * во всем лесу. Именно поэтому, с просьбой подключить их к интернету
 * обратились N поросят. Домики поросят расположены в различных точках
 * (xi, yi). Ближайшая точка подключения расположена в точке (xnet, ynet).
 * Для того чтобы подключиться к сети всем N поросятам необходимо:
 * 1. провести провод от точки подключения до домика одного из поросят;
 * 2. от подключенного поросенка провести провода ко всем остальным.
 * При этом провода могут при необходимости пересекаться.
 * Поросята платят деньги в зависимости от длины провода. Количество
 * денег у них ограничено и составляет p тугриков. Они хотят определить:
 * хватит ли им денег на подключение? Так же известно, что единица длины
 * провода стоит c тугриков. Помогите им сделать необходимые расчеты!
 * Входные данные:
 * В первой строке входного файла INPUT.TXT находится числа N, с и p –
 * целые числа со следующими ограничениями: 1 ≤ N ≤ 103, 0 ≤ c ≤ 104,
 * 0 ≤ p ≤ 1015 . В следующих N строках находятся координаты домов
 * поросят (xi; yi). В последней строке записаны координаты точки
 * соединения (xnet, ynet). Все координаты целые и не превосходят 1000
 * по модулю. Гарантируется, что необходимая для подключения суммарная
 * длина проводов либо целая, либо отличается от целой более чем на 10-2.
 * Выходные данные:
 * В выходной файл OUTPUT.TXT следует вывести «YES», если у поросят
 * достаточно денег для подключения и «NO» в противном случае.
 *
 * @author Andrei Eremenko
 * @version 1.0  Jan 2020
 */
public class Task57 {

    private static int c, n, xFirstHouse, yFirstHouse;
    private static int[] x = new int[1000];
    private static int[] y = new int[1000];
    private static double p;

    public static void main(String[] args) throws FileNotFoundException {
        File fileIn = new File("INPUT.TXT");
        File fileOut = new File("OUTPUT.TXT");
        Scanner sc = new Scanner(new File(String.valueOf(fileIn)));

        n = sc.nextInt();
        c = sc.nextInt();
        p = sc.nextDouble();

        for (int i = 0; i < n; i++) {
            x[i] = sc.nextInt();
            y[i] = sc.nextInt();
        }
        xFirstHouse = sc.nextInt();
        yFirstHouse = sc.nextInt();

        if (countWires()) {
            writeResult(fileOut, "YES");
        } else {
            writeResult(fileOut, "NO");
        }
        sc.close();
    }

    private static boolean countWires() {
        double accountant = p + 1;
        for (int i = 0; i < n; i++) {
            double sum = 0;
            for (int j = 0; j < n; j++) {
                int dx = x[i] - x[j];
                int dy = y[i] - y[j];
                sum += Math.sqrt((dx * dx + dy * dy));
                if (sum > accountant) {
                    break;
                }
            }
            int dx = x[i] - xFirstHouse;
            int dy = y[i] - yFirstHouse;
            sum += Math.sqrt(dx * dx + dy * dy);
            accountant = Math.min(sum, accountant);
        }
        return c * accountant <= p;
    }

    private static void writeResult(File fileOut, String result) {
        try (FileWriter fileWriter = new FileWriter(fileOut)) {
            fileWriter.write(result);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
