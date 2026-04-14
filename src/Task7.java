import java.util.Scanner;
public class Task7 {
    public static void fillTop(int[][] a, int row, int col, int right, int[] value) {
        if (col > right) return;
        a[row][col] = value[0]++;
        fillTop(a, row, col + 1, right, value);
    }
    public static void fillRight(int[][] a, int row, int bottom, int col, int[] value) {
        if (row > bottom) return;
        a[row][col] = value[0]++;
        fillRight(a, row + 1, bottom, col, value);
    }
    public static void fillBottom(int[][] a, int row, int col, int left, int[] value) {
        if (col < left) return;
        a[row][col] = value[0]++;
        fillBottom(a, row, col - 1, left, value);
    }
    public static void fillLeft(int[][] a, int row, int top, int col, int[] value) {
        if (row < top) return;
        a[row][col] = value[0]++;
        fillLeft(a, row - 1, top, col, value);
    }
    public static void spiral(int[][] a, int top, int bottom, int left, int right, int[] value) {
        if (top > bottom || left > right) return;
        fillTop(a, top, left, right, value);
        fillRight(a, top + 1, bottom, right, value);
        if (top < bottom) {
            fillBottom(a, bottom, right - 1, left, value);
        }
        if (left < right) {
            fillLeft(a, bottom - 1, top + 1, left, value);
        }
        spiral(a, top + 1, bottom - 1, left + 1, right - 1, value);
    }
    public static void printMatrix(int[][] a, int row, int col) {
        if (row == a.length) return;
        if (col == a[row].length) {
            System.out.println();
            printMatrix(a, row + 1, 0);
            return;
        }
        System.out.print(a[row][col]);
        if (col + 1 < a[row].length) System.out.print(" ");
        printMatrix(a, row, col + 1);
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[][] a = new int[n][n];
        int[] value = {1};
        spiral(a, 0, n - 1, 0, n - 1, value);
        printMatrix(a, 0, 0);
        sc.close();
    }
}