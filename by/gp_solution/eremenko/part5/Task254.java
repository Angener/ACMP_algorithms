package by.gp_solution.eremenko.part5;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * В стране Олимпиадии снова выборы.
 * Страна состоит из маленьких графств. Графства
 * объединяются в конфедерации. Каждая конфедерация
 * раз в год выбирает себе покровителя – одного из
 * 200 жрецов. Этот ритуал называется Великими
 * Перевыборами Жрецов и выглядит так: конфедерации
 * одновременно подают заявления (одно от конфедерации)
 * в Совет Жрецов о том, кого они хотели бы видеть
 * своим покровителем (если заявление не подано, то
 * считают, что конфедерация хочет оставить себе того
 * же покровителя). После этого все заявки
 * удовлетворяются. Если несколько конфедераций выбирают
 * одного и того же Жреца, то они навсегда объединяются
 * в одну. Таким образом, каждый Жрец всегда является
 * покровителем не более чем одной конфедерации. Требуется
 * написать программу, позволяющую Совету Жрецов выяснить
 * номер Жреца-покровителя каждого графства после Великих
 * Перевыборов. В Совете все графства занумерованы
 * (начиная с 1). Все Жрецы занумерованы числами от 1 до
 * 200 (некоторые из них сейчас могут не быть ничьими
 * покровителями).
 * Входные данные:
 * Во входном файле INPUT.TXT записано число N – количество
 * графств в стране (1 ≤ N ≤ 5000) – и далее для каждого
 * графства записан номер Жреца-покровителя конфедерации, в
 * которую оно входит (графства считаются по порядку их номеров).
 * Затем указаны заявления от конфедераций. Сначала записано
 * число M – количество поданных заявлений, а затем M пар чисел
 * (1 ≤ M ≤ 200): первое число – номер текущего Жреца-покровителя,
 * второе – номер желаемого Жреца-покровителя.
 * Все числа во входном файле разделяются пробелами и (или)
 * символами перевода строки.
 * Выходные данные:
 * В выходной файл OUTPUT.TXT вывести для каждого графства
 * одно число – номер его Жреца-покровителя после Великих
 * Перевыборов. Сначала – для первого графства, затем –
 * для второго и т.д.
 *
 * @author Andrei Eremenko
 * @version 1.0  Dec 2019
 */

public class Task254 {

    private static int n;
    private static int[] temporary = new int[2];
    private static int[] beforeElections;
    private static int[] afterElections;

    public static void main(String[] args) {

        File fileIn = new File("INPUT.TXT");

        try (Scanner sc = new Scanner(new File(String.valueOf(fileIn)))) {
            n = sc.nextInt();

            // Initializing of arrays
            beforeElections = new int[n];
            afterElections = new int[n];

            initializeBeforeElections(sc);
            inputNewDruids(sc);
            addActiveDruids();
            outputResult();

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * The method initializes array from the source file
     * via Scanner utility from the static context
     */
    private static void initializeBeforeElections(Scanner sc) {

        for (int i = 0; i < beforeElections.length; i++) {
            beforeElections[i] = sc.nextInt();
        }
    }

    /**
     * Compares pairs of numbers with the original values.
     * If there is a request for a change in the druids,
     * it changes.
     */
    private static void inputNewDruids(Scanner sc) {
        int m = sc.nextInt();

        for (int i = 1; i <= m; i++) {
            temporary[0] = sc.nextInt();
            temporary[1] = sc.nextInt();

            for (int j = 0; j < n; j++) {

                if (temporary[0] == beforeElections[j]) {
                    afterElections[j] = temporary[1];
                }
            }
        }
    }

    /**
     * Changes the remaining elements of the array,
     * which are zero on the druids from the beforeElection
     * array
     */
    private static void addActiveDruids() {

        for (int i = 0; i < afterElections.length; i++) {

            if (afterElections[i] == 0) {
                afterElections[i] = beforeElections[i];
            }
        }
    }

    /**
     * Outputs result to the file
     */
    private static void outputResult() {
        try (FileWriter fileWriter = new FileWriter("OUTPUT.TXT", true)) {

            for (int i : afterElections) {
                fileWriter.write(i + " ");
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}