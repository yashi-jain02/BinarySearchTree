/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package binarysearchtree;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 *
 * @author Bhavya Jain
 */
public class BinarySearchTree {
    
    //coordinates of currently processing node
    int x,y;
    
    //component declaration
    JTextField number = new JTextField(20);
    JButton insert = new JButton("Insert Value");
    JPanel addno = new JPanel();
    JFrame f;

    public static Node root;

    public BinarySearchTree() {
        this.root = null;

        //Initialise components and panels
        drawStuff canvas = new drawStuff();
        canvas.setPreferredSize(new Dimension(1000, 1000));
        f = new JFrame();
        f.setLayout(new FlowLayout());
        addno.add(number);
        addno.add(insert);
        f.add(canvas);
        f.add(addno);
        
        //Listeners
        insert.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                number.getText();
            }
        });
        
        
        //frame settings
        f.pack();
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

//        add(addno, BorderLayout.PAGE_END);
    }

    public boolean find(int id) {
        Node current = root;
        while (current != null) {
            if (current.data == id) {
                return true;
            } else if (current.data > id) {
                current = current.left;
            } else {
                current = current.right;
            }
        }
        return false;
    }

    public boolean delete(int id) {
        Node parent = root;
        Node current = root;
        boolean isLeftChild = false;
        while (current.data != id) {
            parent = current;
            if (current.data > id) {
                isLeftChild = true;
                current = current.left;
            } else {
                isLeftChild = false;
                current = current.right;
            }
            if (current == null) {
                return false;
            }
        }
        //if i am here that means we have found the node
        //Case 1: if node to be deleted has no children
        if (current.left == null && current.right == null) {
            if (current == root) {
                root = null;
            }
            if (isLeftChild == true) {
                parent.left = null;
            } else {
                parent.right = null;
            }
        } //Case 2 : if node to be deleted has only one child
        else if (current.right == null) {
            if (current == root) {
                root = current.left;
            } else if (isLeftChild) {
                parent.left = current.left;
            } else {
                parent.right = current.left;
            }
        } else if (current.left == null) {
            if (current == root) {
                root = current.right;
            } else if (isLeftChild) {
                parent.left = current.right;
            } else {
                parent.right = current.right;
            }
        } else if (current.left != null && current.right != null) {

            //now we have found the minimum element in the right sub tree
            Node successor = getSuccessor(current);
            if (current == root) {
                root = successor;
            } else if (isLeftChild) {
                parent.left = successor;
            } else {
                parent.right = successor;
            }
            successor.left = current.left;
        }
        return true;
    }

    public Node getSuccessor(Node deleleNode) {
        Node successsor = null;
        Node successsorParent = null;
        Node current = deleleNode.right;
        while (current != null) {
            successsorParent = successsor;
            successsor = current;
            current = current.left;
        }
        //check if successor has the right child, it cannot have left child for sure
        // if it does have the right child, add it to the left of successorParent.
//		successsorParent
        if (successsor != deleleNode.right) {
            successsorParent.left = successsor.right;
            successsor.right = deleleNode.right;
        }
        return successsor;
    }

    public void insert(int id) {
        Node newNode = new Node(id);
        if (root == null) {
            root = newNode;
//            newNode.Nx = getWidth() / 2;
//            newNode.Ny = y;
//            f.add(new Circle(root.Nx, root.Ny, root.data));
            return;
        }
        Node current = root;
        Node parent = null;
        while (true) {
            parent = current;
            if (id < current.data) {
                current = current.left;
                if (current == null) {
                    parent.left = newNode;
                    newNode.Nx = parent.Nx - 50;
                    newNode.Ny = parent.Ny + 50;
//                    f.add(new Circle(newNode.Nx + 25, newNode.Ny + 25, newNode.data));
                    return;
                }
            } else {
                current = current.right;
                if (current == null) {
                    parent.right = newNode;
                    newNode.Nx = parent.Nx + 50;
                    newNode.Ny = parent.Ny + 50;
//                    add(new Circle(newNode.Nx + 25, newNode.Ny + 25, newNode.data));
                    return;
                }
            }
        }
    }

    public void display(Node root) {
        if (root != null) {
            display(root.left);
            System.out.print(" " + root.data);
            display(root.right);

            //MY CODE
        }
    }

//    void initialize() {
//        addno.add(number);
//        addno.add(insert);
//        f.add(addno);
//    }
//    public void start() {
//        f = new JFrame("Binary Search Tree");
//        
//        f.setLayout(new FlowLayout());
//        BinarySearchTree b = new BinarySearchTree();
//        f.add(new drawStuff());
//    }
    public static void main(String arg[]) {
        
        new BinarySearchTree();

//        b.insert(3);
//        b.insert(8);
//        b.insert(1);
//        b.insert(4);
//        b.insert(6);
//        b.insert(2);
//        b.insert(10);
//        b.insert(9);
//        b.insert(20);
//        b.insert(25);
//        b.insert(15);
//        b.insert(16);
//        System.out.println("Original Tree : ");
//        b.display(b.root);
//        System.out.println("");
//        System.out.println("Check whether Node with value 4 exists : " + b.find(4));
//        System.out.println("Delete Node with no children (2) : " + b.delete(2));
//        b.display(root);
//        System.out.println("\n Delete Node with one child (4) : " + b.delete(4));
//        b.display(root);
//        System.out.println("\n Delete Node with Two children (10) : " + b.delete(10));
//        b.display(root);
    }

}

class Node {

    int data;
    int Nx, Ny;
    Node left;
    Node right;

    public Node(int data) {
        this.data = data;
        left = null;
        right = null;
    }
}

class drawStuff extends JPanel {

    @Override
    public void paintComponent(Graphics g) {
        
    }
}
