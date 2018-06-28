package com.ankush.tutorial.tree;

import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class BinarySearchTreeTest {

    @BeforeClass
    public static void testSetup() {
    }

    @Test
    public void test1() {
        BinarySearchTree bt = new BinarySearchTree();
        bt.add(6);
        bt.add(4);
        bt.add(8);
        bt.add(3);
        bt.add(5);
        bt.add(7);
        bt.add(9);

        assertFalse(bt.contains(1));
        assertTrue(bt.contains(4));
        assertTrue(bt.contains(8));
        assertTrue(bt.contains(6));
        assertTrue(bt.contains(3));
        assertFalse(bt.contains(0));
        assertTrue(bt.contains(5));
        assertTrue(bt.contains(7));
        assertTrue(bt.contains(9));
        assertFalse(bt.contains(10));

    }

    @Test
    public void testDeleteWhenNoChildren() {
        BinarySearchTree bt = new BinarySearchTree();
        bt.add(8);
        bt.add(4);
        bt.add(10);
        bt.printRootValue();

        assertTrue(bt.contains(4));
        bt.delete(4);
        assertFalse(bt.contains(4));
        assertTrue(bt.contains(10));
        assertTrue(bt.contains(8));

    }

    @Test
    public void testDeleteWhenNoLeftChildren() {
        BinarySearchTree bt = new BinarySearchTree();
        bt.add(8);
        bt.add(4);
        bt.add(10);
        bt.add(12);
        bt.printRootValue();

        assertTrue(bt.contains(10));
        bt.delete(10);
        assertFalse(bt.contains(10));
        assertTrue(bt.contains(12));
        assertTrue(bt.contains(8));

    }

    @Test
    public void testDeleteWhenNoRightChildren() {
        BinarySearchTree bt = new BinarySearchTree();
        bt.add(8);
        bt.add(4);
        bt.add(10);
        bt.add(9);
        bt.printRootValue();

        assertTrue(bt.contains(10));
        bt.delete(10);
        assertFalse(bt.contains(10));
        assertTrue(bt.contains(4));
        assertTrue(bt.contains(9));

    }

    @Test
    public void testDeleteWhenBothChildrenExist() {
        BinarySearchTree bt = new BinarySearchTree();
        bt.add(8);
        bt.add(4);
        bt.add(10);
        bt.add(2);
        bt.add(6);
        bt.add(11);
        bt.add(9);
        bt.printRootValue();

        assertTrue(bt.contains(4));
        bt.delete(4);
        assertFalse(bt.contains(4));
        assertTrue(bt.contains(2));
        assertTrue(bt.contains(6));
        assertTrue(bt.contains(8));

        bt.delete(8);
        assertFalse(bt.contains(8));
        bt.printRootValue();


    }
}
