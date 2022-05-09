package com.company.Heap;

public class Heap implements HeapInterface {
    /**maximum number of elements in the heap*/
    private int MAX_SIZE;
    private int size;

    /**heap array to store elements*/
    private int[] array;

    /**if user want to set number of elements in the heap */
    public Heap(int x) {
        MAX_SIZE = x;
        size = 0;
        /**initialize the array*/
        array = new int[MAX_SIZE];
    }

    /**default constructor*/
    public Heap() {
        MAX_SIZE = 10000;
        size = 0;
        /**initialize the array*/
        array = new int[MAX_SIZE];
    }

    // builds the heap (Heap Order property)
    @Override
    public void buildHeap() {
        for (int i = size / 2 - 1; i >= 0; i--)
            heapify(size, i);
    }

    // implements heap sort
    @Override
    public void heapSort() {
        buildHeap(); // bild the heap first to make sure that every node(except root) is less than or equla to its parent

        // loop over non root elements
        for (int i = size - 1; i > 0; i--) {
            // put the smallest element in the subtree in the last position
            swapElements(0, i);
            // heapify over the new subtree after making sure that the smallest is in the last
            heapify(i, 0);
        }
    }

    @Override
    public void insert(int element) {
        // make sure that heap is not full
        if (size == MAX_SIZE) {
            System.out.println("The heap is full\n");
            return;
        }
        // increment the heap size
        size++;
        // get the position of the insertion thru percolateUp
        int newPos = percolateUp(size - 1, element);
        // insert the new element
        array[newPos] = element;
    }

    private int percolateUp(int hole, int element) {
        // while the hole is not the root and element is larger than its parent
        while (hole > 0 && element > array[(hole - 1) / 2]) {
            // percolate up the node
            array[hole] = array[(hole - 1) / 2];
            // make the parent of the hole is the target
            hole = (hole - 1) / 2;
        }
        // return position of insertion
        return hole;
    }

    @Override
    public void extraxtMax() {
        if (size == 0) {
            System.out.println("The heap is Empty");
            return;
        }

        if(size == 1) {
            size--;
            System.out.println("HEAP-EXTRACT-MAX: " + array[0] + "\n");
            return;
        }

        System.out.println("HEAP-EXTRACT-MAX: " + array[0] + "\n");
        array[0] = array[size - 1];
        size--;
        heapify(size, 0);
    }

    @Override
    public void printHeap() {
        System.out.print("The Heap:\t");
        for (int i = 0; i < size; i++)
            System.out.print(array[i] + " ");
        System.out.println();
    }

    /**swap two elements in the array*/
    private void swapElements(int index1, int index2) {
        int temp = array[index1];
        array[index1] = array[index2];
        array[index2] = temp;
    }

    @Override
    public void heapify(int n, int i) {
        int target = i; // Initialize target as the root of the given tree
        int left = 2 * i + 1; // left = 2 * i + 1
        int right = left + 1; // right = 2 * i + 2

        // If left child is smaller than target, make it the target
        if (left < n && array[left] < array[target])
            target = left;

        // If left child is smaller than target, make it the target
        if (right < n && array[right] < array[target])
            target = right;

        // If target is changed
        if (target != i) {
            // swap array[i] and array[target]
            swapElements(i, target);
            // Recursively heapify the sub-tree
            heapify(n, target);
        }
    }
}