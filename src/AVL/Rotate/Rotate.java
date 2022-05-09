package AVL.Rotate;

import AVL.Node;
import AVL.Operations.Operations;
import AVL.Operations.OperationsInterface;

public class Rotate implements RotateNode {
    private OperationsInterface operationsInterface;
    
    public Rotate() {
        operationsInterface = new Operations();
    }

    @Override
    public Node rightRotate(Node node) {
        Node temp1 = node.left;
        Node temp2 = temp1.right;

        temp1.right = node;
        node.left = temp2;

        node.h = operationsInterface.getMaxHeight(operationsInterface.getHeight(node.left),
                operationsInterface.getHeight(node.right)) +1;

        temp1.h = operationsInterface.getMaxHeight(operationsInterface.getHeight(temp1.left),
                operationsInterface.getHeight(temp1.right)) +1;

        return temp1;
    }

    @Override
    public Node leftRotate(Node node) {
        Node y = node.right;
        Node T2 = y.left;

        y.left = node;
        node.right = T2;

        node.h = operationsInterface.getMaxHeight(operationsInterface.getHeight(node.left),
                operationsInterface.getHeight(node.right)) + 1;

        y.h = operationsInterface.getMaxHeight(operationsInterface.getHeight(y.left),
                operationsInterface.getHeight(y.right)) + 1;

        return y;
    }

    @Override
    public Node leftRightRotate(Node node) {
        node.left = this.leftRotate(node.left);
        return this.rightRotate(node);
    }

    @Override
    public Node rightLeftRotate(Node node) {
        node.right = this.rightRotate(node.right);
        return this.leftRotate(node);
    }

}
