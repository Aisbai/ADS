import java.util.Scanner;
public class Task6 {
    public static void reverseStrings(Scanner sc, int n) {
        if (n == 0) return;

        String s = sc.nextLine();
        reverseStrings(sc, n - 1);
        System.out.println(s);
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine());
        reverseStrings(sc, n);
        sc.close();
    }
}