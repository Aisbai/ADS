import java.util.Scanner;
public class Task1 {
    public static int sumSquares(int n) {
        if (n == 1) return 1;
        return n * n + sumSquares(n - 1);
    }
    public static void printExpression(int n) {
        if (n == 1) {
            System.out.print("1^2");
            return;
        }
        printExpression(n - 1);
        System.out.print(" + " + n + "^2");
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        printExpression(n);
        System.out.print(" = " + sumSquares(n));
        sc.close();
    }
}