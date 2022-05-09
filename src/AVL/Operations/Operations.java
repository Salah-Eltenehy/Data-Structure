package AVL.Operations;

import AVL.Node;

public class Operations implements OperationsInterface{
    @Override
    public int getBalance(Node node) {
        return (node == null) ? 0 : (this.getHeight(node.left) - this.getHeight(node.right));
    }

    @Override
    public int getHeight(Node node) {
        return node == null ? -1 : node.h;
    }

    @Override
    public int getMaxHeight(int h1, int h2) {
        return (h1 > h2) ? h1: h2;
    }

    @Override
    public Node leftMostNode(Node node) {
		Node current = node;
		while (current.left != null)
		    current = current.left;
		return current;
	}
}