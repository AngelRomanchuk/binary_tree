package com.keyin.binary_tree;

import com.keyin.binary_tree.service.BinarySearchTree;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BinarySearchTreeTest {

    @Test
    public void testInsertMaintainsBSTStructure() {
        BinarySearchTree bst = new BinarySearchTree();
        bst.insert(5);
        bst.insert(3);
        bst.insert(7);

        BinarySearchTree.Node root = bst.getRoot();
        assertNotNull(root);
        assertEquals(5, root.value);
        assertEquals(3, root.left.value);
        assertEquals(7, root.right.value);
    }
}
