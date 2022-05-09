package AVL.GUI;

import AVL.Operations.Operations;
import AVL.Operations.OperationsInterface;
import AVL.ReadFile.ChooseFileDirectory;
import AVL.ReadFile.UploadFile;
import AVL.Tree.Tree;
import AVL.Tree.TreeInterface;

import java.io.IOException;

public class Controller {
    //TreeInterface tree = new Tree();;
    public Controller() {

    }
    public TreeInterface uploadFile(TreeInterface tree) throws IOException {

        ChooseFileDirectory chooseFileDirectory = new ChooseFileDirectory();
        String [] data = UploadFile.upload(chooseFileDirectory.chooseFile());
        for (String s: data) {
            tree.insertElement(s);
        }
       return tree;
    }
    public TreeInterface insertNewElement(String element, TreeInterface tree) {
        tree.insertElement(element);
        return tree;
    }
    public boolean search(String element, TreeInterface tree) {
        return tree.searchElement(element);
    }
    public TreeInterface deleteElement(String e, TreeInterface tree) {
        tree.deleteElement(e);
        return  tree;
    }
    public void printTree(TreeInterface tree) {
        tree.inorderTraversal();
        System.out.println();
    }

    public void printTreeHeight(Tree tree) {
        OperationsInterface operationsInterface = new Operations();
        System.out.println("\nTree height = "+ (operationsInterface.getHeight(tree.root)));
    }
}
