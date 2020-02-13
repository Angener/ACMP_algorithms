package by.gp_solution.eremenko.part8;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * Открыв глаза, Принц Персии обнаружил, что находится на верхнем уровне
 * подземного лабиринта Джаффара. Лабиринт состоит из h уровней, расположенных
 * строго друг под другом. Каждый уровень представляет собой прямоугольную
 * площадку, разбитую на m х n участков. На некоторых участках стоят колонны,
 * поддерживающие потолок, на такие участки Принц заходить не может.
 * Принц может перемещаться с одного участка на другой соседний свободный участок
 * того же уровня, так же он может проломить пол под собой и оказаться уровнем
 * ниже (на самом нижнем уровне пол проломить нельзя). Любое перемещение занимает
 * у Принца 5 секунд.
 * На одном из участков нижнего уровня Принца ждет Принцесса. Помогите Принцу найти
 * Принцессу, потратив на это как можно меньше времени.
 * Входные данные:
 * В первой строке входного файла INPUT.TXT содержатся натуральные числа h, m и n —
 * высота и горизонтальные размеры лабиринта (2 ≤ h, m, n ≤ 50). Далее во входном
 * файле приведены h блоков, описывающих уровни лабиринта в порядке от верхнего к
 * нижнему. Каждый блок содержит m строк, по n символов в каждой: «.» обозначает
 * свободный участок, «о» обозначает участок с колонной, «1» обозначает свободный
 * участок, в котором оказался Принц в начале своего путешествия, «2» обозначает
 * свободный участок, на котором томится Принцесса. Символы «1» и «2» встречаются
 * во входном файле ровно по одному разу: символ «1» — в описании самого верхнего
 * уровня, а символ «2» — в описании самого нижнего. Соседние блоки разделены одной
 * пустой строкой.
 * Выходные данные:
 * В выходной файл OUTPUT.TXT выведите минимальное время в секундах, необходимое
 * Принцу, чтобы найти Принцессу. Поскольку добро всегда побеждает Зло,
 * гарантируется, что Принц может это сделать.
 *
 * @author Andrei Eremenko
 * @version 1.0  Jan 2020
 */
public class Task99 {
    public static void main(String[] args) throws IOException {

        File fileIn = new File("INPUT.TXT");
        File fileOut = new File("OUTPUT.TXT");
        Scanner sc = new Scanner(new File(String.valueOf(fileIn)));
        // переменные служат для инициализации массивов
        int border1 = sc.nextInt();
        int border2 = sc.nextInt();
        int border3 = sc.nextInt();
        //массив полей- хранит информацию о колоннах и т.д.
        char[][][] maze = new char[border1][border2][border3];
        // ячейчи массива зранят информаци о том, за сколько шагов можно в них попасть
        int[][][] vertexes = new int[border1][border2][border3];
        // массив хранит информацию почещений ячеек
        boolean[][][] visits = new boolean[border1][border2][border3];
        Deque<int[]> deque = new LinkedList<>();
        // инициализирует ячейки в которых находятся принци и принцесса
        int[] startPosition = new int[3];
        int[] finishPosition = new int[3];
        sc.nextLine();

        // заполняем массив из файла
        for (int i = 0; i < border1; i++) {
            for (int j = 0; j < border2; j++) {
                char[] strToArray = sc.nextLine().toCharArray();
                if (border3 >= 0) System
                        .arraycopy(strToArray, 0, maze[i][j], 0, border3);
            }
            if (sc.hasNextLine()) {
                sc.nextLine();
            }
        }

        // поиск координат начального  и конечного элементов.
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[i].length; j++) {
                for (int k = 0; k < maze[i][j].length; k++) {
                    if (maze[i][j][k] == '1') {
                        startPosition[0] = i;
                        startPosition[1] = j;
                        startPosition[2] = k;
                    }
                    if (maze[i][j][k] == '2') {
                        finishPosition[0] = i;
                        finishPosition[1] = j;
                        finishPosition[2] = k;
                    }
                }
            }
        }
        //добавляем в начало очереди ячейку с принцем
        deque.addFirst(startPosition);
        visits[startPosition[0]][startPosition[1]][startPosition[2]] = true;

        //перебераем очередь, пка есть элементы
        while (!deque.isEmpty()) {
            int[] tmp = deque.removeLast();

            // ломаем пол, если возможно
            if (tmp[0] < border1 - 1 &&
                    maze[tmp[0] + 1][tmp[1]][tmp[2]] != 'o') {
                int[] ch = new int[3];
                ch[0] = tmp[0] + 1;
                ch[1] = tmp[1];
                ch[2] = tmp[2];
                deque.addFirst(ch);
                visits[tmp[0] + 1][tmp[1]][tmp[2]] = true;
                vertexes[tmp[0] + 1][tmp[1]][tmp[2]] = vertexes[tmp[0]][tmp[1]][tmp[2]] + 1;
            }

            // поиск соседних элементов, на которые может зайти принц
            if (tmp[1] > 0 && !visits[tmp[0]][tmp[1] - 1][tmp[2]]
                    && maze[tmp[0]][tmp[1] - 1][tmp[2]] != 'o') {
                visits[tmp[0]][tmp[1] - 1][tmp[2]] = true;
                vertexes[tmp[0]][tmp[1] - 1][tmp[2]] =
                        vertexes[tmp[0]][tmp[1]][tmp[2]] + 1;
                int[] ch = new int[3];
                ch[0] = tmp[0];
                ch[1] = tmp[1] - 1;
                ch[2] = tmp[2];
                deque.addFirst(ch);
            }

            if (tmp[1] < border2 - 1 && !visits[tmp[0]][tmp[1] + 1][tmp[2]] &&
                    maze[tmp[0]][tmp[1] + 1][tmp[2]] != 'o') {
                visits[tmp[0]][tmp[1] + 1][tmp[2]] = true;
                vertexes[tmp[0]][tmp[1] + 1][tmp[2]] =
                        vertexes[tmp[0]][tmp[1]][tmp[2]] + 1;
                int[] ch = new int[3];
                ch[0] = tmp[0];
                ch[1] = tmp[1] + 1;
                ch[2] = tmp[2];
                deque.addFirst(ch);
            }

            if (tmp[2] > 0 && !visits[tmp[0]][tmp[1]][tmp[2] - 1] &&
                    maze[tmp[0]][tmp[1]][tmp[2] - 1] != 'o') {
                visits[tmp[0]][tmp[1]][tmp[2] - 1] = true;
                vertexes[tmp[0]][tmp[1]][tmp[2] - 1] =
                        vertexes[tmp[0]][tmp[1]][tmp[2]] + 1;
                int[] ch = new int[3];
                ch[0] = tmp[0];
                ch[1] = tmp[1];
                ch[2] = tmp[2] - 1;
                deque.addFirst(ch);
            }

            if (tmp[2] < border3 - 1 && !visits[tmp[0]][tmp[1]][tmp[2] + 1] &&
                    maze[tmp[0]][tmp[1]][tmp[2] + 1] != 'o') {
                visits[tmp[0]][tmp[1]][tmp[2] + 1] = true;
                vertexes[tmp[0]][tmp[1]][tmp[2] + 1] =
                        vertexes[tmp[0]][tmp[1]][tmp[2]] + 1;
                int[] ch = new int[3];
                ch[0] = tmp[0];
                ch[1] = tmp[1];
                ch[2] = tmp[2] + 1;
                deque.addFirst(ch);
            }
        }

        // передаем номер, зрянящийся в элементе с прицессой
        writeResult(fileOut, vertexes[finishPosition[0]][finishPosition[1]][finishPosition[2]]);
        sc.close();
    }

    private static void writeResult(File fileOut, int number) {
        try (FileWriter fileWriter = new FileWriter(fileOut)) {
            fileWriter.write(number * 5 + "");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

