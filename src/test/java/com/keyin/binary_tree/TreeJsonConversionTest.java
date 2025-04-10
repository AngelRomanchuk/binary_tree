package com.keyin.binary_tree;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.keyin.binary_tree.service.BinarySearchTree;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class TreeJsonConversionTest {

    @Test
    public void testToJsonConversion() throws Exception {
        BinarySearchTree bst = new BinarySearchTree();
        bst.insert(10);
        bst.insert(5);
        bst.insert(15);

        Map<String, Object> treeMap = bst.toMap(bst.getRoot());
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(treeMap);

        assertTrue(json.contains("\"value\":10"));
        assertTrue(json.contains("\"value\":5"));
        assertTrue(json.contains("\"value\":15"));
    }
}
