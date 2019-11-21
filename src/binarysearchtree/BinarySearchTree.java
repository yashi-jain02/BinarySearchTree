/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package binarysearchtree;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.*;
import java.util.*;

/**
 *
 * @author Bhavya Jain
 */
public class BinarySearchTree {

    static BinarySearchTree b;
    public boolean found = false;
    public Node foundNode;

    //coordinates of currently processing node
    public int xcoord = 500, ycoord = 50;

    //component declaration
    JTextField number = new JTextField(20);
    JButton insert = new JButton("Insert Value");
    JButton delete = new JButton("Delete Value");
    JButton reset = new JButton("Reset Tree");
    JButton find = new JButton("Find Node");
    JPanel addno = new JPanel();
    JFrame f;

    public static Node root;

    public BinarySearchTree() {
        this.root = null;

        //Initialise components and panels
        drawStuff canvas = new drawStuff();
        canvas.setPreferredSize(new Dimension(1000, 1000));
        f = new JFrame();
        f.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 10));
        addno.add(number);
        addno.add(insert);
        addno.add(delete);
        addno.add(reset);
        addno.add(find);
        f.add(canvas);
        f.add(addno);

        //Listeners
        insert.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                b.insert(Integer.parseInt(number.getText()));
                canvas.rootset(root);
                number.setText("");
                canvas.repaint();
            }
        });

        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                b.delete(Integer.parseInt(number.getText()));
                canvas.rootset(root);
                number.setText("");

                canvas.repaint();
            }
        });

        number.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent ke) {
                if (ke.getKeyCode() == 10) {
                    b.insert(Integer.parseInt(number.getText()));
                    canvas.rootset(root);
                    number.setText("");
                    canvas.repaint();
                }
            }

        });

        reset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                root = null;
                canvas.repaint();
            }
        });

        find.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (find(Integer.parseInt(number.getText()))) {
                    found = true;
                    canvas.getfound(found, foundNode);
                }
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
                foundNode = current;
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

        updatexy();
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
            root.Nx = 500;
            root.Ny = 10;
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
                    newNode.Nx = parent.Nx - 60;
                    newNode.Ny = parent.Ny + 50;
//                    f.add(new Circle(newNode.Nx + 25, newNode.Ny + 25, newNode.data));
                    return;
                }
            } else {
                current = current.right;
                if (current == null) {
                    parent.right = newNode;
                    newNode.Nx = parent.Nx + 60;
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

        b = new BinarySearchTree();

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

    void updatexy() {
        Stack<Node> nodestack = new Stack<>();
        nodestack.push(root);
        while (nodestack.isEmpty() == false) {
            Node mynode = nodestack.peek();

            if (mynode.right != null) {
                mynode.right.Nx = mynode.Nx + 60;
                mynode.right.Ny = mynode.Ny + 50;
                nodestack.push(mynode.right);
            }

            if (mynode.left != null) {
                mynode.left.Nx = mynode.Nx - 60;
                mynode.right.Ny = mynode.Ny + 50;
                nodestack.push(mynode.left);
            }

            nodestack.pop();
        }
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

    Node drawroot;
    boolean check;
    Node foundval;
    Stack<Node> nodestack = new Stack<>();

    void rootset(Node root) {
        drawroot = root;
        nodestack.push(drawroot);
    }

    void getfound(boolean found, Node foundNode) {
        check = found;
        foundval = foundNode;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
//        System.out.println("Hello");
        while (nodestack.isEmpty() == false) {
            //print the number on screen
            Node mynode = nodestack.peek();
            g.setColor(Color.yellow);
            g.fillOval(mynode.Nx - 20, mynode.Ny - 27, 50, 50);
            g.setColor(Color.BLACK);
            g.drawString(Integer.toString(mynode.data), mynode.Nx, mynode.Ny);
            nodestack.pop();

            if (mynode.left != null) {
                nodestack.push(mynode.left);
            }

            if (mynode.right != null) {
                nodestack.push(mynode.right);
            }

//            if (mynode.left != null) {
//                nodestack.push(mynode.left);
//            }
        }

        if (check == true) {
            System.out.println(check+" "+foundval.data);
            g.setColor(Color.RED);
            g.drawString("Hello testing", 300, 300);
            g.drawOval(foundval.Nx - 20, foundval.Ny - 27, 51, 51);
            check = false;
            repaint();
        }
    }
}
