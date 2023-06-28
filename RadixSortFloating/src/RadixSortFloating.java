import java.util.ArrayList;
import java.util.Arrays;

public class RadixSortFloating {
    // get maximum value
    public static float getMaximum(float[] arr) {
        float maximum = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > maximum) {
                maximum = arr[i];
            }
        }
        return maximum;
    }

    public static void radixSort(float[] arr) {
        int arraySize = arr.length;
        float maximum = getMaximum(arr);
        int number = 10;
        float exponent = 0.01f; // current digit place

        // 2 temporary buckets for sorting
        ArrayList<Float>[] arr1 = new ArrayList[number];
        ArrayList<Float>[] arr2 = new ArrayList[number];

        // initialize the buckets
        for (int i = 0; i < 10; i++) {
            arr1[i] = new ArrayList<>();
            arr2[i] = new ArrayList<>();
        }

        // fixed exponent increment
        while (maximum / exponent > 0) {
            for (int j = 0; j < arraySize; j++) {
                int index = (int) (arr[j] / exponent) % 10; // get index
                float value = arr[j]; // get the value to be sorted
                arr1[index].add(value); // add value to corresponding bucket
            }

            exponent *= 10; // single

            for (int count = 0; count < 10; count++) {
                while (!arr1[count].isEmpty()) { // checking empty value
                    float array_value = arr1[count].remove(0);// remove first value
                    int index1 = (int) (array_value / exponent) % 10;// get index for arr2
                    arr2[index1].add(array_value);
                }
            }
            exponent *= 10; // ten

            for (int count1 = 0; count1 < 10; count1++) {
                while (!arr2[count1].isEmpty()) {
                    float array_value2 = 0.00f;
                    array_value2 = arr2[count1].remove(0);
                    int index2 = (int) (array_value2 / exponent) % 10;
                    arr1[index2].add(array_value2);
                }
            }
            exponent *= 10; // hundred

            // collection
            int arr_count = 0;
            for (int m = 0; m < 10; m++) {
                while (!arr1[m].isEmpty()) {
                    float arrayValue = arr1[m].remove(0);
                    arr[arr_count] = arrayValue;
                    arr_count++;
                }
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("This is Radix Sort with floating point number");
        float[] arr = { 1.12f, 87.13f, 426.55f, 8.78f, 409.02f, 170.01f, 677.61f, 88.51f }; // array list
        System.out.println("Unsorted Radix:" + Arrays.toString(arr));
        radixSort(arr);
        System.out.println("Sorted Radix:" + Arrays.toString(arr));
    }
}
