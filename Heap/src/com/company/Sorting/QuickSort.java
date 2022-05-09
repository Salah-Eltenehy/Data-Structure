package com.company.Sorting;

public class QuickSort {
    private static int partition(int arr[], int low, int high) {
        int pivot = arr[high]; // pivot is the last element
        int i = low - 1; // index of smaller element
        for (int j = low; j < high; j++) {
            // If current element is smaller than or equal to pivot
            if (arr[j] <= pivot) {
                i++;
                // swap arr[i] and arr[j]
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        // swap arr[i+1] and arr[high] (pivot)
        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;

        return i + 1;
    }

    private static void sort(int arr[], int low, int high) {
        if (low < high) {
            // partition the array into 2 parts
            int pivot = partition(arr, low, high);

            // Recursively sort elements before
            // partition and after partition
            sort(arr, low, pivot - 1);
            sort(arr, pivot + 1, high);
        }
    }
    public int[] applySort(int arr[]) {
        sort(arr, 0, arr.length - 1);
        return arr;
    }
}
