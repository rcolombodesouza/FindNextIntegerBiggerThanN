package de.resolve.generator;

import java.util.Arrays;

public class FindNextBigInteger {

    private static int getNextBigInt(int number) {

        int length = (int) Math.ceil(Math.log10(number));
        int[] array = new int[length];

        // I) Split the number into an array
        for (int i = 0; i < length; i++) {
            array[length - i - 1] = number % 10;
            number = number / 10;
        }

        // II) Find index of first smallest number from right to left
        int index = -1;
        for (int i = 0; i < length - 1; i++) {
            if (array[length - i - 2] < array[length - i - 1]) {
                index = length - i - 2;
                break;
            }
        }

        if (index == -1) {
            System.out.println("No possible sequence !!");
            return -1;
        }

        // III) Find index of smallest number from the right side of index
        int smallMaxIdx = index + 1;
        for (int i = index + 2; i < length; i++) {
            if (array[smallMaxIdx] > array[i]) {
                smallMaxIdx = i;
            }
        }

        // IV) Swap first smallest number found on step II with the number from step found on step III
        int temp = array[index];
        array[index] = array[smallMaxIdx];
        array[smallMaxIdx] = temp;

        // V) Order array starting from the index of smallest number + 1 (found on step II)
        Arrays.sort(array, index + 1, length);

        // VI) Convert the array back to an int
        return integerArrayToInt(array);

    }

    /**
     * Util method to convert the int array to integer.
     *
     * @param array
     * @return integer.
     */
    private static int integerArrayToInt(int[] array) {
        int len = array.length;
        int number = array[len - 1];
        int factor = 10;

        for (int i = 1; i < len; i++) {
            number = number + factor * array[len - i - 1];
            factor = factor * 10;
        }
        return number;
    }

    /**
     * Driver method.
     *
     * @param args
     */
    public static void main(String[] args) {
        int number = 645987;
        int nextBigNumb = getNextBigInt(number);
        System.out.println(nextBigNumb);
    }
}
