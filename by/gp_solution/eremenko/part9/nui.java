package by.gp_solution.eremenko.part9;

import java.util.Scanner;

public class nui {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        long out = 0;
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
        int max = arr[0];
        for (int i = 1; i < n; i++) {
            if(max < arr[i]) max = arr[i];
        }
        for(int i = 0; i < n - 1; i++){
            if (arr[i+1] > arr[i]) {
                out += arr[i + 1] - arr[i];
            }
        }
        out += max - arr[n-1];
        System.out.print(out);
    }
}
