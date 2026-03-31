import java.util.Scanner;
public class Task2 {
    public static int sumFirstN(int[] arr, int n) {
        if (n == 0) return 0;
        return sumFirstN(arr, n - 1) + arr[n - 1];
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int size = sc.nextInt();
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = sc.nextInt();
        }
        int n = sc.nextInt();
        System.out.println(sumFirstN(arr, n));
        sc.close();
    }
}