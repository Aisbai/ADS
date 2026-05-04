package Assignment3_1;
import java.util.Arrays;

public class SortingAndSearching {

    // ==================== BUBBLE SORT ====================
    public static void bubbleSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    // Swap
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    // ==================== HEAP SORT ====================
    public static void heapSort(int[] arr) {
        int n = arr.length;

        // Build max heap
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(arr, n, i);
        }

        // Extract elements from heap
        for (int i = n - 1; i >= 0; i--) {
            // Move current root to end
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;

            // Call max heapify on reduced heap
            heapify(arr, i, 0);
        }
    }

    private static void heapify(int[] arr, int n, int i) {
        int largest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        if (left < n && arr[left] > arr[largest]) {
            largest = left;
        }

        if (right < n && arr[right] > arr[largest]) {
            largest = right;
        }

        if (largest != i) {
            int swap = arr[i];
            arr[i] = arr[largest];
            arr[largest] = swap;

            heapify(arr, n, largest);
        }
    }

    // ==================== MERGE SORT ====================
    public static void mergeSort(int[] arr, int left, int right) {
        if (left < right) {
            int middle = left + (right - left) / 2;

            mergeSort(arr, left, middle);
            mergeSort(arr, middle + 1, right);

            merge(arr, left, middle, right);
        }
    }

    private static void merge(int[] arr, int left, int middle, int right) {
        int n1 = middle - left + 1;
        int n2 = right - middle;

        int[] leftArray = new int[n1];
        int[] rightArray = new int[n2];

        System.arraycopy(arr, left, leftArray, 0, n1);
        System.arraycopy(arr, middle + 1, rightArray, 0, n2);

        int i = 0, j = 0, k = left;

        while (i < n1 && j < n2) {
            if (leftArray[i] <= rightArray[j]) {
                arr[k] = leftArray[i];
                i++;
            } else {
                arr[k] = rightArray[j];
                j++;
            }
            k++;
        }

        while (i < n1) {
            arr[k] = leftArray[i];
            i++;
            k++;
        }

        while (j < n2) {
            arr[k] = rightArray[j];
            j++;
            k++;
        }
    }

    // ==================== QUICK SORT ====================
    public static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int pivotIndex = partition(arr, low, high);
            quickSort(arr, low, pivotIndex - 1);
            quickSort(arr, pivotIndex + 1, high);
        }
    }

    private static int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (arr[j] <= pivot) {
                i++;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;

        return i + 1;
    }

    // ==================== LINEAR SEARCH ====================
    public static int linearSearch(int[] arr, int target) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == target) {
                return i; // Element found, return index
            }
        }
        return -1; // Element not found
    }

    // ==================== BINARY SEARCH ====================
    // Note: Binary search requires a sorted array
    public static int binarySearch(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;

        while (left <= right) {
            int middle = left + (right - left) / 2;

            if (arr[middle] == target) {
                return middle;
            } else if (arr[middle] < target) {
                left = middle + 1;
            } else {
                right = middle - 1;
            }
        }
        return -1;
    }

    // Recursive version of binary search
    public static int binarySearchRecursive(int[] arr, int target, int left, int right) {
        if (left > right) {
            return -1;
        }

        int middle = left + (right - left) / 2;

        if (arr[middle] == target) {
            return middle;
        } else if (arr[middle] < target) {
            return binarySearchRecursive(arr, target, middle + 1, right);
        } else {
            return binarySearchRecursive(arr, target, left, middle - 1);
        }
    }

    // ==================== DEMONSTRATION ====================
    public static void main(String[] args) {

        // Test data
        int[] bubbleArray = {64, 34, 25, 12, 22, 11, 90};
        int[] heapArray = {12, 11, 13, 5, 6, 7};
        int[] mergeArray = {38, 27, 43, 3, 9, 82, 10};
        int[] quickArray = {10, 7, 8, 9, 1, 5};
        int[] searchArray = {2, 3, 4, 10, 40};

        System.out.println("=== Сортировка пузырьком (Bubble Sort) ===");
        System.out.println("Исходный массив: " + Arrays.toString(bubbleArray));
        bubbleSort(bubbleArray.clone());
        System.out.println("Отсортированный: " + Arrays.toString(bubbleArray));

        System.out.println("\n=== Пирамидальная сортировка (Heap Sort) ===");
        System.out.println("Исходный массив: " + Arrays.toString(heapArray));
        heapSort(heapArray);
        System.out.println("Отсортированный: " + Arrays.toString(heapArray));

        System.out.println("\n=== Сортировка слиянием (Merge Sort) ===");
        System.out.println("Исходный массив: " + Arrays.toString(mergeArray));
        mergeSort(mergeArray, 0, mergeArray.length - 1);
        System.out.println("Отсортированный: " + Arrays.toString(mergeArray));

        System.out.println("\n=== Быстрая сортировка (Quick Sort) ===");
        int[] quickArrayCopy = quickArray.clone();
        System.out.println("Исходный массив: " + Arrays.toString(quickArrayCopy));
        quickSort(quickArrayCopy, 0, quickArrayCopy.length - 1);
        System.out.println("Отсортированный: " + Arrays.toString(quickArrayCopy));

        // Search examples
        System.out.println("\n=== Линейный поиск (Linear Search) ===");
        System.out.println("Массив: " + Arrays.toString(searchArray));
        int target = 10;
        int linearResult = linearSearch(searchArray, target);
        if (linearResult != -1) {
            System.out.println("Элемент " + target + " найден на индексе: " + linearResult);
        } else {
            System.out.println("Элемент " + target + " не найден");
        }

        System.out.println("\n=== Бинарный поиск (Binary Search) ===");
        System.out.println("Отсортированный массив: " + Arrays.toString(searchArray));
        int binaryTarget = 40;
        int binaryResult = binarySearch(searchArray, binaryTarget);
        if (binaryResult != -1) {
            System.out.println("Элемент " + binaryTarget + " найден на индексе: " + binaryResult);
        } else {
            System.out.println("Элемент " + binaryTarget + " не найден");
        }

        // Test binary search recursive
        System.out.println("\n=== Бинарный поиск (рекурсивная версия) ===");
        int target2 = 3;
        int recursiveResult = binarySearchRecursive(searchArray, target2, 0, searchArray.length - 1);
        System.out.println("Элемент " + target2 + " найден на индексе: " + recursiveResult);
    }
}