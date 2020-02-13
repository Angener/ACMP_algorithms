package by.gp_solution.eremenko.part3;

import java.io.*;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Неспокойно сейчас на стапелях шестого дока межгалактического
 * порта планеты Торна. Всего через месяц закончится реконструкция
 * малого броненесущего корвета “Эния”. И снова этому боевому
 * кораблю и его доблестной команде предстоят тяжелые бои за
 * контроль над плутониевыми рудниками Сибелиуса. Работа не
 * прекращается ни на секунду, лазерные сварочные аппараты
 * работают круглые сутки. От непрерывной работы плавятся шарниры
 * роботов-ремонтников. Но задержаться нельзя ни на секунду.
 * И вот в этой суматохе обнаруживается, что термозащитные
 * панели корвета вновь требуют срочной обработки сульфидом тория.
 * Известно, что на обработку одного квадратного метра панели
 * требуется 1 нанограмм сульфида. Всего необходимо обработать
 * N прямоугольных панелей размером A на B метров. Вам необходимо
 * как можно скорее подсчитать, сколько всего сульфида необходимо
 * на обработку всех панелей “Энии”. И не забудьте, что панели
 * требуют обработки с обеих сторон.
 * Входные данные:
 * Во входном файле INPUT.TXT содержатся 3 целых положительных
 * числа N (N ≤ 100), A (A ≤ 100), B (B ≤ 100)
 * Выходные данные:
 * В выходной файл OUTPUT.TXT нужно вывести единственное число –
 * вес необходимого для обработки сульфида тория в нанограммах.
 *
 * @author Andrei Eremenko
 * @version 1.0  Dec 2019
 */
public class Task195 {

    public static void main(String[] args) {
        File fileIn = new File("INPUT.TXT");
        File fileOut = new File("OUTPUT.TXT");

        if (validateString(readFile(fileIn))) {
            outputResult(calculateThoriumSulfideQuantity(readFile(fileIn)), fileOut);
        }
    }

    /**
     * The method reads string from a file
     */
    private static String readFile(File file) {
        String result = "";

        try (BufferedReader bufferedReader = new BufferedReader(
                new FileReader(file))) {
            result = bufferedReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Validates the data in the source file, which
     * should contains only one line with three
     * numbers are  1 to 100 (including).
     */
    private static boolean validateString(String string) {
        Pattern pattern = Pattern.compile("^([1-9]+[0-9]?|100)\\s([1-9]+" +
                "[0-9]?|100)\\s([1-9]+[0-9]?|100)$");
        Matcher matcher = pattern.matcher(string);
        return matcher.matches();
    }

    /**
     * Calculates the product of numbers according task's
     * conditions through the next formula (ThoriumSulfide =
     * Quantity Sizes(2) (Size(A) * Size(B)) * Panels quantity(N)).
     */
    private static int calculateThoriumSulfideQuantity(String string) {
        int result = 2;
        int[] numArr = Arrays.stream(string.split(" "))
                .mapToInt(Integer::parseInt).toArray();

        for (int n : numArr) {
            result *= n;
        }
        return result;
    }

    /**
     * Represents writing data to the file
     */
    private static void outputResult(int result, File file) {

        try (FileWriter fileWriter = new FileWriter(file)) {
            fileWriter.write(result + "");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}