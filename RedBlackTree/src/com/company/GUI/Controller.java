package com.company.GUI;

import com.company.RBTree.RBTreeInterface;
import com.company.ReadFile.ChooseFileDirectory;
import com.company.ReadFile.UploadFile;

import java.io.IOException;

public class Controller {
    //RBTreeInterface tree = new Tree();;
    public Controller() {

    }
    public RBTreeInterface uploadFile(RBTreeInterface tree) throws IOException {

        ChooseFileDirectory chooseFileDirectory = new ChooseFileDirectory();
        String [] data = UploadFile.upload(chooseFileDirectory.chooseFile());
        for (String s: data) {
            tree.insert(s);
        }
        return tree;
    }
    public RBTreeInterface insertNewElement(String element, RBTreeInterface tree) {
        if (!(element.equals("\n")))
            tree.insert(element);
        return tree;
    }
    public boolean search(String element, RBTreeInterface tree) {
        return tree.isContain(element);
    }
    public RBTreeInterface deleteElement(String e, RBTreeInterface tree) {
        tree.delete(e);
        return  tree;
    }


}

