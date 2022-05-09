package com.company.RBTree;

import com.company.Node;

import java.util.ArrayList;

public interface RBTreeInterface {
    Node getRoot();
    void clear();
    boolean isEmpty();
    boolean isContain(String element);
    ArrayList<String> inorderTraversal();
    Node search(String k);
    void insert(String element);
    void delete(String data);
}