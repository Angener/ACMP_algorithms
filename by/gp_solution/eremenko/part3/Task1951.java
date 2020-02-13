//package by.gp_solution.eremenko.part3;
//
//import java.io.File;
//import java.io.FileNotFoundException;
//import java.io.FileWriter;
//import java.io.IOException;
//import java.util.Scanner;
//import java.util.function.Consumer;
//import java.util.function.Predicate;
//
//public class Task1951 {
//    public static void main(String[] args) {
//        int v = new Scanner(System.in).nextInt();
//    }
//}
//package by.gp_solution.eremenko.part4;
//
//        import java.io.File;
//        import java.io.FileNotFoundException;
//        import java.io.FileWriter;
//        import java.io.IOException;
//        import java.util.Scanner;
//        import java.util.function.Consumer;
//        import java.util.function.Predicate;
//
///**
// * Рассмотрим таблицу, содержащую n строк и m столбцов,
// * в каждой клетке которой расположен ноль или единица.
// * Назовем такую таблицу симпатичной, если в ней нет ни
// * одного квадрата 2 на 2, заполненного целиком нулями
// * или целиком единицами. Так, например, таблица 4 на 4,
// * расположенная слева, является симпатичной, а
// * расположенная справа таблица 3 на 3 - не является.
// * Задано несколько таблиц. Необходимо для каждой из них
// * выяснить, является ли она симпатичной.
// * Входные данные:
// * Первая строка входного файла INPUT.TXT содержит
// * количество t (1 ≤ t ≤ 10) наборов входных данных.
// * Далее следуют описания этих наборов. Описание каждого
// * набора состоит из строки, содержащей числа n и m
// * (1 ≤ n,m ≤ 100), и n строк, каждая из которых содержит
// * по m чисел, разделенных пробелами. j-ое число в i+1-ой
// * строке описания набора входных данных - элемент aij
// * соответствующей таблицы. Гарантируется, что все aij
// * равны либо нулю, либо единице.
// * Выходные данные
// * Для каждого набора входных данных выведите в файл
// * OUTPUT.TXT единственную строку, содержащую слово «YES»,
// * если соответствующая таблица является симпатичной, и
// * слово «NO» - в противном случае.
// *
// * @author Andrei Eremenko
// * @version 1.0  Dec 2019
// */
//public class Task58 {
//
//
//    public static void main(String[] args) throws FileNotFoundException {
//        File fileIn = new File("INPUT.TXT");
//        readData(fileIn);
//        writer.accept(beautifulChecker.test(readData(fileIn)));
//
//////////////////////////////////////////////////////////////////////////////
//        Scanner sc = new Scanner(new File(String.valueOf(fileIn)));
//        byte b = sc.nextByte();
//        for (byte i = 0; i < b; i++){
//
//
//
//
//
//        }
//
//
//
//
//    }
//
//    private static Predicate<Byte[][]> beautifulChecker = (array) -> {
//        boolean isBeautiful = true;
//        for (byte i = 0; i < array.length - 1; i++) {
//            for (byte j = 0; j < array[i].length - 1; j++) {
//                if ((array[i][j] + array[i][j + 1] + array[i + 1][j] + array[i + 1][j + 1]) % 4 == 0) {
//                    isBeautiful = false;
//                    break;
//                }
//            }
//        }
//        return isBeautiful;
//    };
//
//    private static Consumer<Boolean> writer = (isBeautiful) -> {
//        String result = "NO";
//        if (isBeautiful) {
//            result = "YES";
//        }
//        try (FileWriter fileWriter = new FileWriter("INPUT.TXT", true)) {
//            fileWriter.write(result);
//        } catch (IOException ex) {
//            ex.printStackTrace();
//        }
//    };
//
//    private static void readData(File file) {
//
//        try (Scanner sc = new Scanner(new File(String.valueOf(file)))) {
//
//            byte k, x;
//            while (sc.hasNextLine()) {
//                k = sc.nextByte();
//                x = sc.nextByte();
//
//                byte[][] array = new byte[k][x];
//
//                for (byte i = 0; i < array.length; i++) {
//                    for (byte j = 0; j < array[i].length; j++) {
//                        array[i][j] = sc.nextByte();
//                    }
//                }
//            }
//
//        } catch (FileNotFoundException ex) {
//            System.err.println("File not found");
//            ex.printStackTrace();
//        }
//    }
//}
