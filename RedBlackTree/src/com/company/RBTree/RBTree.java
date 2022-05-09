package com.company.RBTree;

import com.company.Node;

import java.util.ArrayList;

public class RBTree implements RBTreeInterface{
    private Node root;
    private Node TNULL;
    //Initialize the Tree
    public RBTree() {
        TNULL = new Node();
        TNULL.color = 0;
        TNULL.left = null;
        TNULL.right = null;
        root = TNULL;
    }

    @Override
    public Node getRoot() {
        return this.root;
    }

    @Override
    public void clear() {
        this.root = null;
    }

    @Override
    public boolean isEmpty() {
        return this.root == null;
    }

    @Override
    public boolean isContain(String element) {
        return isContain(root, element);
    }

    @Override
    public ArrayList<String> inorderTraversal() {
       return inorderTraversal(root);
    }

    @Override
    public Node search(String k) {
        return search(root, k);
    }
    @Override
    public void delete(String data) {
        delete(root, data);
    }

    @Override
    public void insert(String element) {
        applyInsert(element);
    }

    private boolean isContain(Node head, String element) {
        while (head != null)
        {
            String headElement = head.data;

            if (element.compareToIgnoreCase(headElement) < 0)
                head = head.left;
            else if (element.compareToIgnoreCase(headElement) > 0)
                head = head.right;
            else
                return true;
        }
        return false;
    }
    public static ArrayList<String> arrayList = new ArrayList<>();
    private ArrayList<String> inorderTraversal(Node head) {
        if (head != null)
        {
            //print tree in inorder and display it in console & GUI
            inorderTraversal(head.left);
            if(head.color == 0) {
                System.out.println(head.data + " (Black) ");
                arrayList.add(head.data + " (Black)  \n");
                }
            else {
                System.out.println(head.data + " (RED) ");
                arrayList.add(head.data + " (RED)  \n");
            }
            inorderTraversal(head.right);

        }
        return arrayList;
    }

    private Node search(Node node, String key) {
        //if the node is root
        if (node == TNULL || key.compareToIgnoreCase(node.data) == 0)
            return node;
        //if node locate in left subTree
        if (key.compareToIgnoreCase(node.data) < 0)
            return search(node.left, key);
        //if node locate in right subTree
        return search(node.right, key);
    }

    private void fixDelete(Node x) {
        Node w;

        while (x != root && x.color == 0) {
            if (x == x.p.left)
            {
                w = x.p.right;
                if (w.color == 1)
                {
                    w.color = 0;
                    x.p.color = 1;
                    leftRotate(x.p);
                    w = x.p.right;
                }

                if (w.left.color == 0 && w.right.color == 0) {
                    w.color = 1;
                    x = x.p;
                }
                else
                {
                    if (w.right.color == 0) {
                        w.left.color = 0;
                        w.color = 1;
                        rightRotate(w);
                        w = x.p.right;
                    }

                    w.color = x.p.color;
                    x.p.color = 0;
                    w.right.color = 0;
                    leftRotate(x.p);
                    x = root;
                }
            }
            else {
                w = x.p.left;
                if (w.color == 1) {
                    w.color = 0;
                    x.p.color = 1;
                    rightRotate(x.p);
                    w = x.p.left;
                }

                if (w.right.color == 0 && w.right.color == 0) {
                    w.color = 1;
                    x = x.p;
                }
                else {
                    if (w.left.color == 0) {
                        w.right.color = 0;
                        w.color = 1;
                        leftRotate(w);
                        w = x.p.left;
                    }

                    w.color = x.p.color;
                    x.p.color = 0;
                    w.left.color = 0;
                    rightRotate(x.p);
                    x = root;
                }
            }
        }
        x.color = 0;
    }

    private void transplant(Node x, Node y) {
        if (x.p == null)
            root = y;
        else if (x == x.p.left)
            x.p.left = y;
        else
            x.p.right = y;
        y.p = x.p;
    }

    private void delete(Node node, String key) {
        Node z = TNULL;
        Node x, y;
        while (node != TNULL) {
            //in case the node is the root
            if (key.compareToIgnoreCase(node.data) == 0)
            {
                z = node;
                break;
            }
            //in case the node in right subtree
            if (key.compareToIgnoreCase(node.data) > 0)
                node = node.right;
            else
                //in case the node in left subtree
                node = node.left;
        }
        //in case the node not exists
        if (z == TNULL) {
            System.out.println("Error! Not Exists !!");
            return;
        }

        y = z;
        //Red-Black height
        int originalColor = y.color;
        if (z.left == TNULL) {
            x = z.right;
            transplant(z, z.right);
        }
        else if (z.right == TNULL) {
            x = z.left;
            transplant(z, z.left);
        }
        else
        {
            y = leftMostNode(z.right);
            originalColor = y.color;
            x = y.right;
            if (y.p != z)
            {
                transplant(y, x);
                x = z.right;
                x.p = y;
            }

            transplant(z, y);
            y.left = z.left;
            y.left.p = y;
            y.color = z.color;
        }
        if (originalColor == 0)
            fixDelete(x);
    }

    private void fixInsert(Node z) {
        Node y;
        while (z.p.color == 1) {
            if (z.p == z.p.p.right) {
                y = z.p.p.left;
                if (y.color == 1) {
                    y.color = 0;
                    z.p.color = 0;
                    z.p.p.color = 1;
                    z = z.p.p;
                } else {
                    if (z == z.p.left) {
                        z = z.p;
                        rightRotate(z);
                    }
                    z.p.color = 0;
                    z.p.p.color = 1;
                    leftRotate(z.p.p);
                }
            }
            else
            {
                y = z.p.p.right;
                if (y.color == 1) {
                    y.color = 0;
                    z.p.color = 0;
                    z.p.p.color = 1;
                    z = z.p.p;
                }
                else
                {
                    if (z == z.p.right) {
                        z = z.p;
                        leftRotate(z);
                    }
                    z.p.color = 0;
                    z.p.p.color = 1;
                    rightRotate(z.p.p);
                }
            }
            if (z == root)
                break;
        }
        root.color = 0;
    }

    private Node leftMostNode(Node node) {
        //get the left most node in the left sub tree
        while (node.left != TNULL)
            node = node.left;
        return node;
    }

    private void leftRotate(Node x) {
        Node y = x.right;
        x.right = y.left;
        if (y.left != TNULL)
            y.left.p = x;
        y.p = x.p;
        if (x.p == null)
            root = y;
        else if (x == x.p.left)
            x.p.left = y;
        else
            x.p.right = y;
        y.left = x;
        x.p = y;
    }

    private void rightRotate(Node x) {
        Node y = x.left;
        x.left = y.right;
        if (y.right != TNULL)
            y.right.p = x;
        y.p = x.p;
        if (x.p == null)
            root = y;
        else if (x == x.p.right)
            x.p.right = y;
        else
            x.p.left = y;
        y.right = x;
        x.p = y;
    }

    private void applyInsert(String element) {
        Node node = new Node();
        node.p = null;
        node.data = element;
        node.left = TNULL;
        node.right = TNULL;
        node.color = 1;

        Node y = null;
        Node x = root;

        while (x != TNULL) {
            y = x;
            if (element.compareToIgnoreCase(x.data) < 0)
                x = x.left;
            else
                x = x.right;
        }

        node.p = y;
        if (y == null)
        {
            root = node;
            node.color = 0;
            return;
        }
        else if (element.compareToIgnoreCase(y.data) < 0)
            y.left = node;
        else
            y.right = node;

        if (node.p.p == null)
            return;

        fixInsert(node);
    }
}