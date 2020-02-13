package by.gp_solution.eremenko.part6;

import java.io.File;
import java.math.BigDecimal;
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
 * @version 1.0  Dec 2019
 */

public class Task46_1 {

    private static final String number = "2.7182818284590452353602875";
    private static byte b = new Scanner(System.in).nextByte();

    public static void main(String[] args) {
        System.out.println(new BigDecimal(number).setScale(b, BigDecimal.ROUND_HALF_UP));
    }




}
