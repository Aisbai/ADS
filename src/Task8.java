import java.util.Scanner;
public class Task8 {
    public static void printArray(int[] arr, int index) {
        if (index == arr.length) {
            System.out.println();
            return;
        }
        System.out.print(arr[index]);
        if (index + 1 < arr.length) System.out.print(" ");
        printArray(arr, index + 1);
    }
    public static void generate(int[] arr, int pos, int k) {
        if (pos == arr.length) {
            printArray(arr, 0);
            return;
        }

        fillValues(arr, pos, 1, k);
    }
    public static void fillValues(int[] arr, int pos, int value, int k) {
        if (value > k) return;

        arr[pos] = value;
        generate(arr, pos + 1, k);
        fillValues(arr, pos, value + 1, k);
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();

        int[] arr = new int[n];
        generate(arr, 0, k);

        sc.close();
    }
}
