package com.company;

import com.company.Heap.Heap;
import com.company.Heap.HeapInterface;
import com.company.Sorting.MergeSort;
import com.company.Sorting.QuickSort;
import com.company.Sorting.SelectionSort;

import java.util.Random;

public class Comparison {
    public static void Compare()
    {
        int size = 7000;
        int array[] = new int[size];
        Random random = new Random();
        HeapInterface heapInterface = new Heap();
        for (int i = 0; i < size; i++) {
            array[i] = random.nextInt();
        }
        System.out.println("\n\nNumber of elements: "+size);

        for (int i = 0; i < size; i++)
            heapInterface.insert(array[i]);


        long t1 = System.nanoTime();
        heapInterface.heapSort();
        long t2 = System.nanoTime();
        long x = (t2 - t1) / 1000;
        System.out.println("Heap Sort Time is: " + x + " micro s");

        SelectionSort selectionSort = new SelectionSort();
        t1 = System.nanoTime();
        selectionSort.selectionSort(array);
        t2 = System.nanoTime();
        x = (t2 - t1) / 1000;
        System.out.println("Selection Sort Time is: " + x + " micro s");
        MergeSort mergeSort = new MergeSort();
        t1 = System.nanoTime();
        mergeSort.applySort(array);
        t2 = System.nanoTime();
        x = (t2 - t1) / 1000;
        System.out.println("Merge Sort Time is: " + x + " micro s");
        QuickSort quickSort = new QuickSort();
        t1 = System.nanoTime();
        quickSort.applySort(array);
        t2 = System.nanoTime();
        x = (t2 - t1) / 1000;
        System.out.println("Quick Sort Time is: " + x + " micro s\n\n");
    }
}
