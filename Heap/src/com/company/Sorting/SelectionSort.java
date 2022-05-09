package com.company.Sorting;

public class SelectionSort {
    public SelectionSort()
    {}
    public int[] selectionSort(int[] arr)
    {
        int sizeOFArray = arr.length;
        for(int i=0; i<sizeOFArray-1; i++)
        {
            for(int j=i+1; j<sizeOFArray; j++)
            {
                if(arr[j] < arr[i])
                {
                    arr = swap(i, j, arr);
                }
            }
        }
        return arr;
    }
    private static int[] swap(int index1, int index2, int[] arr)
    {
        int temp = arr[index1];
        arr[index1] = arr[index2];
        arr[index2] = temp;
        return arr;

    }
}
