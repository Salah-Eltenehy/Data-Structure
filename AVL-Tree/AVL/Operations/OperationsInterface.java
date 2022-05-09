package AVL.Operations;

import AVL.Node;

public interface OperationsInterface {
    int getBalance(Node node);
    int getHeight(Node node);
    int getMaxHeight(int h1, int h2);
    Node leftMostNode(Node node);
}
