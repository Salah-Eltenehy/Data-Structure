package AVL;

public class Node{
    public Node left;
    public Node right;
    public String element;
    public int h;
    
    public Node(String e) {
        this.left = null;
        this.right = null;
        this.element = e;
        h = 0;        
    }
}
