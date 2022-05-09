package AVL.Tree;

import AVL.Node;
import AVL.Operations.Operations;
import AVL.Operations.OperationsInterface;
import AVL.Rotate.Rotate;
import AVL.Rotate.RotateNode;

public class Tree implements TreeInterface {
    public Node root;
    OperationsInterface operations = new Operations();
    RotateNode rotateNodes = new Rotate();

    public int getTreeSize()  
    {  
        return getTreeSize(root);  
    }  

    public boolean searchElement(String element)  
    {  
        return search(root, element);  
    }

    public void inorderTraversal()  
    {  
        inorderTraversal(root);  
    }

    public void insertElement(String element)  
    {
        if(searchElement(element))
			System.out.println("Error! Already Exists!!");
		else root = insert(root, element); 
    }

    public void deleteElement(String element)  
    {
        if(!searchElement(element))
			System.out.println("Error! Not Exists!!");
		else root = delete(root, element); 
    }
    
    private int getTreeSize(Node node)  
    {  
        if (node == null)  
            return 0;

        int length = 1;  
        length = length + getTreeSize(node.left);  
        length = length + getTreeSize(node.right);  
        return length;  
    }	  

    private boolean search(Node head, String element)  
    {  
        while (head != null)  
        {  
            String headElement = head.element; 
			 
            if (element.compareToIgnoreCase(headElement) < 0)  
                head = head.left;  
            else if (element.compareToIgnoreCase(headElement) > 0)  
                head = head.right;  
            else  
                return true;  
        }  
        return false;  
    }

    private void inorderTraversal(Node head)  
    {
        if (head != null)  
        {  
            inorderTraversal(head.left);  
            System.out.print(head.element+" ");  
            inorderTraversal(head.right);  
        }  
    }

    private Node insert(Node node, String element) {        
        if (node == null)
            return (new Node(element));

        else if (element.compareToIgnoreCase(node.element) < 0)
            node.left = insert(node.left, element);
        else if (element.compareToIgnoreCase(node.element) > 0)
            node.right = insert(node.right, element);
        else
            return node;

        int balance = operations.getBalance(node);
        node.h = operations.getMaxHeight(operations.getHeight(node.right),
                                         operations.getHeight(node.left)) + 1;


        // 4 cases 
        // 1. Left Left Case
        if (balance > 1 && element.compareToIgnoreCase(node.left.element) < 0)
            return rotateNodes.rightRotate(node);

        // Right right Case
        if (balance < -1 && element.compareToIgnoreCase(node.right.element) > 0)
            return rotateNodes.leftRotate(node);

        // Left Right Case
        if (balance > 1 && element.compareToIgnoreCase(node.left.element) > 0)
            return rotateNodes.leftRightRotate(node);

        // Right Left Case
        if (balance < -1 && element.compareToIgnoreCase(node.right.element) < 0)
            return rotateNodes.rightLeftRotate(node);
        
        return node;
    }

    private Node delete(Node node, String element) {
		// Normal BST DELETE
		if (node == null)
			return node;

		if (element.compareToIgnoreCase(node.element) < 0)        
			node.left = delete(node.left, element);			
		else if (element.compareToIgnoreCase(node.element) > 0)
			node.right = delete(node.right, element);			
		else
		{
			if ((node.left == null) || (node.right == null))
			{
				Node temp = null;
				if (temp == node.left)
					temp = node.right;
				else
					temp = node.left;

				// if the Two children are Null, remove the node
				if (temp == null)
					node = null;
				else
                    // put the child in place of the parent
                    node = new Node(temp.element);
			}
			else
			{
				// Get the smallest element in the right subtree
				Node temp = operations.leftMostNode(node.right);
                // Put it in place of the original node
				node.element = temp.element;
                // Delete it from the rigth subtree
				node.right = delete(node.right, temp.element);
			}
		}

		if (node == null)
			return node;

		// The Height & the balance factor of the current Node
		node.h = operations.getMaxHeight(operations.getHeight(node.left), operations.getHeight(node.right)) + 1;
		int balance = operations.getBalance(node);

		// Fix the Tree, if the node is unbalanced(4 cases):
		// 1. Left Left Case
		if (balance > 1 && operations.getBalance(node.left) >= 0)
			return rotateNodes.rightRotate(node);

		// 2. Left Right Case
		if (balance > 1 && operations.getBalance(node.left) < 0)
		{
            return rotateNodes.leftRightRotate(node);
		}

		// 3. Right Right Case
		if (balance < -1 && operations.getBalance(node.right) <= 0)
			return rotateNodes.leftRotate(node);

		// 4. Right Left Case
		if (balance < -1 && operations.getBalance(node.right) > 0)
		{
			return rotateNodes.rightLeftRotate(node);
		}
		return node;
	}
}