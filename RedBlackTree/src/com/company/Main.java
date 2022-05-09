package com.company;

import java.io.IOException;

import com.company.GUI.Jframe;
import com.company.RBTree.RBTree;
import com.company.RBTree.RBTreeInterface;
import com.company.ReadFile.ChooseFileDirectory;
import com.company.ReadFile.UploadFile;

public class Main {

    public static void main(String[] args) throws IOException {                    
       new Jframe();


        /*RBTreeInterface rbTreeInterface = new RBTree();
        rbTreeInterface.insert("salah");
        System.out.println(rbTreeInterface.getRoot().data);
        rbTreeInterface.delete("salah");
        System.out.println(rbTreeInterface.search("salah").data);*/
    }
}