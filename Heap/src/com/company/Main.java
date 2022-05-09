package com.company;

import com.company.Heap.Heap;
import com.company.Heap.HeapInterface;
import com.company.Sorting.SelectionSort;

import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
	// write your code here
        HeapInterface heapInterface = new Heap();
        Scanner sc = new Scanner(System.in);
        while(true)
        {
            System.out.println("Enter Number of operation:");
            System.out.println("1- Insert element.");
            System.out.println("2- Extract max.");
            System.out.println("3- Print the heap.");
            System.out.println("4- Sort the heap.");
            System.out.println("5- Compare between different sorting algorithms.");
            System.out.println("Enter any other Number to Exit");
            int operation = sc.nextInt();
            if (operation == 1)
            {
                System.out.println("Enter element: ");
                int s = sc.nextInt();
                heapInterface.insert(s);
            }
            else if (operation == 2)
                heapInterface.extraxtMax();
            else if (operation == 3)
                heapInterface.printHeap();
            else if (operation == 4)
                heapInterface.heapSort();
            else if (operation == 5)
                Comparison.Compare();
            else break;
        }
        sc.close();


    }

    }


