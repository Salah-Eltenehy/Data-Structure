package com.company.Sorting;

public  /* Java program for Merge Sort */
class MergeSort {
    // Merges two subarrays of arr[].
    // First subarray is arr[l..m]
    // Second subarray is arr[m+1..r]
    private static void merge(int arr[], int l, int m, int r) {
        // Find sizes of two subarrays to be merged
        int n1 = m - l + 1;
        int n2 = r - m;

        // create temp arrays for merging
        int L[] = new int [n1];
        int R[] = new int [n2];

        // Copy elements to temp arrays
        for (int i = 0; i < n1; ++i)
            L[i] = arr[l + i];
        for (int j = 0; j < n2; ++j)
            R[j] = arr[m + 1 + j];

        int i = 0, j = 0;
        // Merge the temp arrays
        // Initial index of merged subarray array
        int k = l;
        while (i < n1 && j < n2) {
            if (L[i] <= R[j])
                arr[k++] = L[i++];
            else
                arr[k++] = R[j++];
        }

        // Copy remaining elements of L[] if any
        while (i < n1) { arr[k++] = L[i++];}

        // Copy remaining elements of R[] if any
        while (j < n2) { arr[k++] = R[j++]; }
    }

    // merge sort
    private static void sort(int arr[], int l, int r) {
        if (l < r)
        {
            // Find the middle point
            int m = (l + r) / 2;

            // Sort first and second halves
            sort(arr, l, m);
            sort(arr , m+1, r);

            // Merge the sorted halves
            merge(arr, l, m, r);
        }
    }

    public int [] applySort(int arr[]) {
        sort(arr, 0, arr.length-1);
        return arr;
    }
}