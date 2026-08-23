package tr.com.huseyinaydin.sort.algorithms;

public class QuickSort {

    public static void main(String[] args) {
        int[] numbers = {7, 2, 1, 6, 8, 5, 3, 4};

        quickSort(numbers, 0, numbers.length - 1);

        for(int number : numbers) {
            System.out.print(number);
        }
    }

    public static void quickSort(int[] numbers, int low, int high) {

        if (low < high) {

            int pivotIndex = partition(numbers, low, high);

            quickSort(numbers, low, pivotIndex - 1);
            quickSort(numbers, pivotIndex + 1, high);
        }
    }

    private static int partition(int[] numbers, int low, int high) {

        int pivot = numbers[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {

            if (numbers[j] <= pivot) {
                i++;

                int temp = numbers[i];
                numbers[i] = numbers[j];
                numbers[j] = temp;
            }
        }

        int temp = numbers[i + 1];
        numbers[i + 1] = numbers[high];
        numbers[high] = temp;

        return i + 1;
    }
}