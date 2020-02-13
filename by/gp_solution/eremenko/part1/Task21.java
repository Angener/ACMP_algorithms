package by.gp_solution.eremenko.part1;
import java.util.*;
public class Task21 {
    public static void main(String[] a){
        int v = new Scanner(System.in).nextInt();
        System.out.print(((Math.abs(v) + 1) * v / 2) + (v > 0 ? 0:1));
    }
}