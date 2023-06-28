public class RadixSortComplexityInteger {
    // get maximum value of integer
    public static int getMaximum(int[] arr) {
        int maximum = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > maximum) {
                maximum = arr[i];
            }
        }
        return maximum;
    }

    // get maximumvalue of floating point number
    public static float getMaximumfloat(float[] arr) {
        float maximum = arr[0];
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
        int number = 100000;
        int exponent = 1;

        int[] arr1 = new int[number];

        while (maximum / exponent > 0) {
            int[] count = new int[number];

            for (int j = 0; j < arraySize; j++) {
                int index = (arr[j] / exponent) % 10;
                count[index]++;
            }

            for (int k = 1; k < number; k++) {
                count[k] += count[k - 1];
            }

            for (int m = arraySize - 1; m >= 0; m--) {
                int index = (arr[m] / exponent) % 10;
                arr1[count[index] - 1] = arr[m];
                count[index]--; // decrement count
            }

            System.arraycopy(arr1, 0, arr, 0, arraySize);
            exponent *= 10;
        }
    }

    // implementation
    public static void radixSortfloating(float[] arr) {
        int arraySize = arr.length;
        float maximum = getMaximumfloat(arr);
        int number = 1000000;
        float exponent = 1.0f;

        float[] arr1 = new float[number];

        while (maximum / exponent > 0) {
            int[] count = new int[number];

            for (int j = 0; j < arraySize; j++) {
                int index = (int) ((arr[j] / exponent) % 10);
                count[index]++; // increment count
            }

            // modify the count array to store cumulative sum of counts
            for (int k = 1; k < number; k++) {
                count[k] += count[k - 1];
            }

            for (int m = arraySize - 1; m >= 0; m--) {
                int index = (int) ((arr[m] / exponent) % 10);// get index of digit
                arr1[count[index] - 1] = arr[m];// place element in correct position
                count[index]--;
            }
            // copy sorted elemetns back to original sort
            System.arraycopy(arr1, 0, arr, 0, arraySize);
            exponent *= 10;
        }
    }

    public static void main(String[] args) {
        int[] sizes = { 1, 10, 100, 1000, 10000, 100000 };
        long[] operations = new long[sizes.length];

        System.out.println("Analysis of Radix Sort Integer");
        // implementation of number of operations of integer
        for (int i = 0; i < sizes.length; i++) {
            int[] arr = generateRandomArray(sizes[i]);
            long start = System.nanoTime(); // start measuring operations time
            radixSort(arr);
            long end = System.nanoTime(); // end measuring operations time
            operations[i] = end - start; // store operation time
            System.out.println("Input Size (n):" + sizes[i] + " \t Operations:" + operations[i]);
        }

        float[] sizes2 = { 50.0f, 100.0f, 150.0f, 200.0f, 250.0f, 300.0f };
        long[] operations2 = new long[sizes2.length];

        System.out.println("Analysis of Radix Sort Floating");
        // implementation of number of operations of floating point number
        for (int j = 0; j < sizes2.length; j++) {
            float[] arr = generateRandomArray2((int) sizes2[j]);
            long start = System.nanoTime(); // start measuring operations time
            radixSortfloating(arr);
            long end = System.nanoTime(); // end measuring operations time
            operations2[j] = end - start; // store opeation time
            System.out.println("Input Size (n):" + sizes2[j] + " \t Operations:" + operations2[j]);
        }
    }

    // generate random integer array for given size
    public static int[] generateRandomArray(int size) {
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = (int) (Math.random() * 1000); // Random numbers between 0 and 999
        }
        return arr;
    }

    // generate random floating point number of given size
    public static float[] generateRandomArray2(int size) {
        float[] arr = new float[size];
        for (int i = 0; i < size; i++) {
            arr[i] = (float) (Math.random() * 1000); // Random numbers between 0 and 999
        }
        return arr;
    }
}
