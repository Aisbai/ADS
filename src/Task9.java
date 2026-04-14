import java.util.Scanner;
public class Task9 {
    public static void swap(char[] arr, int i, int j) {
        char t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }
    public static void permute(char[] arr, int index) {
        if (index == arr.length) {
            System.out.println(new String(arr));
            return;
        }
        tryPositions(arr, index, index);
    }
    public static void tryPositions(char[] arr, int index, int i) {
        if (i == arr.length) return;
        swap(arr, index, i);
        permute(arr, index + 1);
        swap(arr, index, i);

        tryPositions(arr, index, i + 1);
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        char[] arr = s.toCharArray();

        permute(arr, 0);

        sc.close();
    }
}