import java.util.Scanner;
public class Task5 {
    public static void reversePrint(Scanner sc, int n) {
        if (n == 0) return;
        int x = sc.nextInt();
        reversePrint(sc,n - 1);
        System.out.print(x);
        if (n != 1) {
            System.out.print(" ");
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        reversePrint(sc, n);
        sc.close();
    }
}