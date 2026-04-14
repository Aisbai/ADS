import java.util.Scanner;
public class Task4 {
    public static long power(int b, int n) {
        if (n == 0) return 1;
        return b * power(b, n - 1);
    }
    public static long sumPowers(int b, int n) {
        if (n == 0) return 1;
        return power(b, n) + sumPowers(b, n - 1);
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int b = sc.nextInt();
        int n = sc.nextInt();
        System.out.println(sumPowers(b, n));
        sc.close();
    }
}