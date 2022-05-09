package com.company.GUI;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import com.company.Node;
import com.company.RBTree.RBTree;
import com.company.RBTree.RBTreeInterface;
import com.company.ReadFile.ChooseFileDirectory;
import com.company.ReadFile.UploadFile;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

public class Jframe extends JFrame implements ActionListener {
    JButton upload, runButton, print, getRootButton, lookUps, clearButton, deletions;
    JTextArea chooseTextArea, whiteBoard;
    RBTreeInterface treeInterface;
    JComboBox<String> cb;
    String[] choices = {"Insert", "IsContain", "Delete"};
    public Jframe() {
        cb = new JComboBox<String>(choices);
        cb.setBounds(210, 30, 100, 30);

        chooseTextArea = new JTextArea();
        chooseTextArea.setBounds(10, 30, 200, 30);

        runButton = new JButton("Run");
        runButton.setBounds(310, 30, 65, 30);
        runButton.addActionListener(this);

        whiteBoard = new JTextArea(20, 58);
        whiteBoard.setBounds(10, 80, 365, 150);
        whiteBoard.setEditable(true);

        treeInterface = new RBTree();
        upload = new JButton("Upload");
        upload.setBounds(50, 250, 90, 40);
        upload.addActionListener(this);

        getRootButton = new JButton("Get Root");
        getRootButton.setBounds(155, 250, 90, 40);
        getRootButton.addActionListener(this);

        print = new JButton("Print");
        print.setBounds(260, 250, 90, 40);
        print.addActionListener(this);

        clearButton = new JButton("Clear");
        clearButton.setBounds(50, 300, 90, 40);
        clearButton.addActionListener(this);

        lookUps = new JButton("Look-ups");
        lookUps.setBounds(155, 300, 90, 40);
        lookUps.addActionListener(this);

        deletions = new JButton("Is Empty");
        deletions.setBounds(260, 300, 90, 40);
        deletions.addActionListener(this);


        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setSize(400, 400); //Set Bounds
        this.getContentPane().setBackground(new Color(0x23FA56)); // Set BackGround Color
        this.setVisible(true);
        this.setLayout(null);
        this.setLocation(480, 210);
        this.setTitle("RB tree");
        this.add(upload);
        this.add(print);
        this.add(runButton);
        this.add(chooseTextArea);
        this.add(whiteBoard);
        this.add(getRootButton);
        this.add(clearButton);
        this.add(lookUps);
        this.add(cb);
        this.add(deletions);
        this.add(whiteBoard);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        Controller controller = new Controller();
        if(e.getSource() == upload) {
            treeInterface = new RBTree();
            try {
                treeInterface = controller.uploadFile(treeInterface);
                whiteBoard.setText("UPLOADED SUCCESSFULLY ");
            } catch (Exception ex) {  
                ex.printStackTrace();
            }
        } else if (e.getSource() == print) {
            whiteBoard.setText("");
            try {
                ArrayList<String> arrayList=  treeInterface.inorderTraversal();
                whiteBoard.setText("");
                for (String s: arrayList) {
                    whiteBoard.setText(whiteBoard.getText() + s);
                }
            } catch (Exception e1) {
                whiteBoard.setText("Tree is empty");
            }
        } else if (e.getSource() == runButton) {            
            String ans = chooseTextArea.getText();
            System.out.println(ans);
            chooseTextArea.setText("");
            String selected = (String) cb.getSelectedItem();
            if (selected.equals("IsContain") && !(ans.equals(""))) {
                Node node = treeInterface.search(ans);
                if (node.data == null) {
                    whiteBoard.setText("Not Exists");
                } else
                    whiteBoard.setText("Exists");
            } else if (selected.equals("Delete")&& !(ans.equals(""))) {
                try {
                    Node node = treeInterface.search(ans);
                    if (node.data != null)
                    {
                        treeInterface.delete(ans);
                        whiteBoard.setText("DELETED SUCCESSFULLY");
                    }
                    else {
                        whiteBoard.setText("NOT EXISTS");
                    }

                } catch (Exception error) {
                    whiteBoard.setText("SOME ERROR HAPPENS !!");
                }
            } else if (selected.equals("Insert")&& !(ans.equals(""))) {
                Node node = treeInterface.search(ans);
                if (node.data == null)
                {
                    treeInterface.insert(ans);
                    whiteBoard.setText("INSERTED SUCCESSFULLY");
                }
                else {
                    whiteBoard.setText("ALREADY EXISTS");
                }
            }
        } else if (e.getSource() == getRootButton) {
                whiteBoard.setText(treeInterface.getRoot().data);
                if (whiteBoard.getText().equals(""))
                    whiteBoard.setText("Tree is empty");

        } else if (e.getSource() == clearButton) {
            treeInterface.clear();
            whiteBoard.setText("TREE IS DESTROYED!!");
        }
        else if (e.getSource() == lookUps) {
            ChooseFileDirectory chooseFileDirectory = new ChooseFileDirectory();
            try {
                String []data = UploadFile.upload(chooseFileDirectory.chooseFile());
                whiteBoard.setText("");
                for (String s: data) {
                    if (!(s.equals(""))) {
                        if(treeInterface.isContain(s)) {
                            whiteBoard.setText(whiteBoard.getText()+s+" : YES\n");
                            System.out.println(s + ": YES");
                        } else
                        {
                            whiteBoard.setText(whiteBoard.getText()+s+" : NO\n");
                            System.out.println(s + ": NO");
                        }
                    }
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        else if (e.getSource() == deletions) {
            whiteBoard.setText(treeInterface.isEmpty()+"");

        }
    }
}