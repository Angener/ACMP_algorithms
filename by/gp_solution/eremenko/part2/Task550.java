package by.gp_solution.eremenko.part2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.function.Predicate;

/**
 * День программиста отмечается в 255-й день года
 * (при этом 1 января считается нулевым днем).
 * Требуется написать программу, которая определит
 * дату (месяц и число григорианского календаря),
 * на которую приходится День программиста в заданном
 * году. В григорианском календаре високосным является:
 * год, номер которого делится нацело на 400; год,
 * номер которого делится на 4, но не делится на 100
 * Входные данные:
 * В единственной строке входного файла INPUT.TXT
 * записано целое число от 1 до 9999 включительно,
 * которое обозначает номер года нашей эры.
 * Выходные данные
 * В единственную строку выходного файла OUTPUT.TXT
 * нужно вывести дату Дня программиста в формате DD/MM/YYYY,
 * где DD — число, MM — номер месяца (01 — январь,
 * 02 — февраль, ..., 12 — декабрь), YYYY — год в десятичной
 * записи.
 *
 * @author Andrei Eremenko
 * @version 1.0  Dec 2019
 */
public class Task550 {

    private static short year;

    // Functional interface Predicate checks if the year is a leap year
    private static Predicate<Short> predicate = (year) ->
            ((year % 400 == 0) || ((year % 4 == 0) && (year % 100 != 0)));

    public static void main(String[] args) {
        File fileIn = new File("INPUT.TXT");
        File fileOut = new File("OUTPUT.TXT");

        if (validateFile(fileIn)) {
            outputResult(setProgrammerDay(year), fileOut);
        }
    }

    /**
     * The method checks next conditions:
     * the inputted number N is whole;
     * N's value is in range from 1 to 9999;
     * The input file contains only one number
     * and only one line;
     */
    private static boolean validateFile(File file) {
        boolean isCorrect = false;

        try (Scanner sc = new Scanner(new File(String.valueOf(file)))) {
            year = sc.nextShort();

            if (year < 1 || year > 9999) {
                throw new InputMismatchException("The number must be " +
                        "positive and range from 1 to 9999");
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
     * Sets and returns programmer day.
     */
    private static Calendar setProgrammerDay(short year) {
        return new GregorianCalendar(year, Calendar.SEPTEMBER,
                ((predicate.test(year) ? 12 : 13)));
    }

    /**
     * Represents writing data to the file
     */
    private static void outputResult(Calendar cal, File file) {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        try (FileWriter fileWriter = new FileWriter(file)) {
            fileWriter.write(dateFormat.format(cal.getTime()));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
