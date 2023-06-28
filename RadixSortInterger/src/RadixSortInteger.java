import java.util.ArrayList;
import java.util.Arrays;

public class RadixSortInteger {
    // find maximum value
    public static int getMaximum(int[] arr) {
        int maximum = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > maximum) {
                maximum = arr[i];
            }
        }
        return maximum;
    }

    // implementation
    public static void radixSort(int[] arr) {
        int arraySize = arr.length;
        int maximum = getMaximum(arr);
        int number = 10;
        int exponent = 1; // use for the divide current digit

        // create array list
        ArrayList<Integer>[] arr1 = new ArrayList[number];
        ArrayList<Integer>[] arr2 = new ArrayList[number];

        // initialize the bucket
        for (int i = 0; i < 10; i++) {
            arr1[i] = new ArrayList<>();
            arr2[i] = new ArrayList<>();
        }

        // fixed exponent increment
        while (maximum / exponent > 0) {
            for (int j = 0; j < arraySize; j++) {
                int index = (arr[j] / exponent) % 10; // get inddex
                int value = arr[j]; // get the value to be sorted
                arr1[index].add(value); // add value to corresponding bucket
            }

            exponent *= 10; // single digit

            for (int count = 0; count < 10; count++) {
                while (!arr1[count].isEmpty()) { // checking empty value
                    int array_value = arr1[count].remove(0); // remove first value
                    int index1 = (array_value / exponent) % 10;// get index for arr2
                    arr2[index1].add(array_value);
                }
            }
            exponent *= 10; // ten digit

            for (int count1 = 0; count1 < 10; count1++) {
                while (!arr2[count1].isEmpty()) {
                    int array_value2 = 0;
                    array_value2 = arr2[count1].remove(0);
                    int index2 = (array_value2 / exponent) % 10;
                    arr1[index2].add(array_value2);
                }
            }
            exponent *= 10; // hundred digit

            // collection
            int arr_count = 0;
            for (int m = 0; m < 10; m++) {
                while (!arr1[m].isEmpty()) {
                    int arrayValue = arr1[m].remove(0);
                    arr[arr_count] = arrayValue;
                    arr_count++;
                }
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = { 275, 87, 426, 61, 409, 170, 677, 503 }; // array list
        System.out.println("Radix Sort Integer");
        System.out.println("Initial Sort: " + Arrays.toString(arr));
        radixSort(arr);
        System.out.println("Sorted Radix: " + Arrays.toString(arr));
    }
}
