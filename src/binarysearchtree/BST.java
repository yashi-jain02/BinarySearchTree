/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package binarysearchtree;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.*;
import javax.swing.*;

/**
 *
 * @author Bhavya Jain
 */
public class BST extends JPanel {

    JFrame f;
    TreeSet<Integer> root = new TreeSet<>();

    JTextField field = new JTextField(10);
    JButton ADD = new JButton("Add to TREE");

    public static void main(String[] args) {
        new BST();
    }

    BST() {
        JPanel bottom = new JPanel();
        f = new JFrame();

        field.setToolTipText("Enter number to be added to TREE");
        bottom.add(field);
        bottom.add(ADD);
        f.setSize(1000, 1000);
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.add(this, BorderLayout.CENTER);
        f.add(bottom, BorderLayout.PAGE_END);

//        ADD.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent ae) {
//                int val = Integer.parseInt(field.getText());
//                root.add(val);
//                repaint();
//            }
//        });

        field.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent ke) {
                if(ke.getKeyCode()==10){
                    root.add(Integer.parseInt(field.getText()));
                    System.out.println("Enter");
                    repaint();
                }
            }

        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        int x = 100, y = 100;
        Iterator i = root.iterator();
        while (i.hasNext()) {
            g.drawString(i.next().toString(), x, y);
//System.out.println(i.next()+ " ");
            x += 20;
            y += 20;
        }

    }
    
//    void preorder(Iterator i){
//        if(i!=null){
//            preorder(i.next());
//        }
//        
//    }

}
