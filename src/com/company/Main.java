package com.company;

import com.company.dataStructures.TreeBinary;

public class Main {

    public static void main(final String[] args) {
        final TreeBinary<Integer> myTree = new TreeBinary();
        myTree.add(5);
        myTree.add(8);
        myTree.add(1);
        myTree.add(6);
        myTree.add(12);
        System.out.println("Size " + myTree.size());
        System.out.println("--------------");
        System.out.println("5: " + myTree.contains(5));
        System.out.println("8: " + myTree.contains(8));
        System.out.println("1: " + myTree.contains(1));
        System.out.println("6: " + myTree.contains(6));
        System.out.println("12: " + myTree.contains(12));
        System.out.println("7: " + myTree.contains(7));
        System.out.println("9: " + myTree.contains(9));
        System.out.println("--------------");
        myTree.delete(8);
        System.out.println("Size " + myTree.size());
        System.out.println("5: " + myTree.contains(5));
        System.out.println("8: " + myTree.contains(8));
        System.out.println("1: " + myTree.contains(1));
        System.out.println("6: " + myTree.contains(6));
        System.out.println("12: " + myTree.contains(12));
        System.out.println("7: " + myTree.contains(7));
        System.out.println("9: " + myTree.contains(9));

    }

}
