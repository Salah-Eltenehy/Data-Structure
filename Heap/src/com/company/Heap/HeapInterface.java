package com.company.Heap;
public interface HeapInterface {
    void insert(int element);
    void extraxtMax();
    void printHeap();
    void heapify(int n, int i);
    void buildHeap();
    void heapSort();
}