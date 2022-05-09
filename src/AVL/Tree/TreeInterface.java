package AVL.Tree;


public interface TreeInterface {   
    int getTreeSize();
    boolean searchElement(String element);
    void insertElement(String element);
    void deleteElement(String element);
    void inorderTraversal();
}
