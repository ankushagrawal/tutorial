package com.ankush.tutorial.tree;

import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BinartTreeTest {

    @BeforeClass
    public static void testSetup() {
    }

    @Test
    public void test1() {
        BinaryTree root = new BinaryTree(1);
        Node left1 = root.addLeftChild(root.getRoot(), 2 );
        Node left2 = root.addLeftChild(left1, 4 );
        Node right1 = root.addLeftChild(root.getRoot(), 3 );

    }

}
