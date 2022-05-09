package AVL.Rotate;

import AVL.Node;

public interface RotateNode {
    Node rightRotate(Node node);
    Node leftRotate(Node node);
    Node rightLeftRotate(Node node);
    Node leftRightRotate(Node node);
}
