package AVL.GUI;

import javax.swing.*;

import AVL.ReadFile.ChooseFileDirectory;
import AVL.ReadFile.UploadFile;
import AVL.Tree.Tree;
import AVL.Tree.TreeInterface;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class Jframe extends JFrame implements ActionListener {
    JButton upload, insert, search, delete, print, treeHeight, lookUps, getSize, deletions;
    JTextArea insertTextArea, searchTextArea, deleteTextArea;
    TreeInterface treeInterface;
    public Jframe() {
        treeInterface = new Tree();
        upload = new JButton("Upload");
        upload.setBounds(50, 250, 90, 40);
        upload.addActionListener(this);

        treeHeight = new JButton("Height");
        treeHeight.setBounds(155, 250, 90, 40);
        treeHeight.addActionListener(this);

        print = new JButton("Print");
        print.setBounds(260, 250, 90, 40);
        print.addActionListener(this);

        insert = new JButton("Insert");
        insert.setBounds(260, 50, 75, 30);
        insert.addActionListener(this);

        search = new JButton("Search");
        search.setBounds(260, 120, 75, 30);
        search.addActionListener(this);

        delete = new JButton("Delete");
        delete.setBounds(260, 190, 75, 30);
        delete.addActionListener(this);

        getSize = new JButton("Size");
        getSize.setBounds(50, 300, 90, 40);
        getSize.addActionListener(this);

        lookUps = new JButton("Look-ups");
        lookUps.setBounds(155, 300, 90, 40);
        lookUps.addActionListener(this);

        deletions = new JButton("Deletions");
        deletions.setBounds(260, 300, 90, 40);
        deletions.addActionListener(this);

        insertTextArea = new JTextArea();
        searchTextArea = new JTextArea();
        deleteTextArea = new JTextArea();

        insertTextArea.setBounds(50, 50, 200, 30);
        searchTextArea.setBounds(50, 120, 200, 30);
        deleteTextArea.setBounds(50, 190, 200, 30);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setSize(400, 400); //Set Bounds
        this.getContentPane().setBackground(new Color(0x23FA56)); // Set BackGround Color
        this.setVisible(true);
        this.setLayout(null);
        this.setLocation(480, 210);
        this.setTitle("AVL tree");
        this.add(upload);
        this.add(print);
        this.add(insert);
        this.add(search);
        this.add(delete);
        this.add(insertTextArea);
        this.add(searchTextArea);
        this.add(deleteTextArea);
        this.add(treeHeight);
        this.add(getSize);
        this.add(lookUps);
        this.add(deletions);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        Controller controller = new Controller();
        if(e.getSource() == upload) {
            treeInterface = new Tree();
            try {
                treeInterface = controller.uploadFile(treeInterface);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        } else if (e.getSource() == print) {

            controller.printTree(treeInterface);
        } else if (e.getSource() == insert) {
            treeInterface = controller.insertNewElement(insertTextArea.getText(), treeInterface);
            insertTextArea.setText("");
        } else if (e.getSource() == search) {
            if(controller.search(searchTextArea.getText(), treeInterface)) {
                searchTextArea.setText("Yes");
            } else
                searchTextArea.setText("No");

        } else if (e.getSource() == delete) {
            treeInterface = controller.deleteElement(deleteTextArea.getText(), treeInterface);
            deleteTextArea.setText("");
        } else if (e.getSource() == treeHeight) {
            controller.printTreeHeight((Tree) treeInterface);
        } else if (e.getSource() == getSize) {
            System.out.println("Number of Nodes = "+ (treeInterface.getTreeSize()-1));
        }
        else if (e.getSource() == lookUps) {
            ChooseFileDirectory chooseFileDirectory = new ChooseFileDirectory();
            try {
                String []data = UploadFile.upload(chooseFileDirectory.chooseFile());
                for (String s: data) {
                    if (!(s.equals(""))) {
                        if(controller.search(s, treeInterface)) {
                            System.out.println(s + ": Yes");
                        } else
                            System.out.println(s + ": No");
                    }
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        else if (e.getSource() == deletions) {
            ChooseFileDirectory chooseFileDirectory = new ChooseFileDirectory();
            try {
                String []data = UploadFile.upload(chooseFileDirectory.chooseFile());
                for (String s: data) {
                    treeInterface = controller.deleteElement(s, treeInterface);
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
