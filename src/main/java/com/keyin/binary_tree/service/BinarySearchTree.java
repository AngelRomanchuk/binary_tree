package com.keyin.binary_tree.service;
import java.util.*;

public class BinarySearchTree {

    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int value) {
            this.value = value;
        }
    }

    private Node root;

    public void insert(int value) {
        root = insertRecursive(root, value);
    }

    private Node insertRecursive(Node node, int value) {
        if (node == null) return new Node(value);

        if (value < node.value) {
            node.left = insertRecursive(node.left, value);
        } else if (value > node.value) {
            node.right = insertRecursive(node.right, value);
        }

        return node;
    }

    public Node getRoot() {
        return root;
    }

    // For converting the tree into a Map<String, Object> that can be JSON-serialized
    public Map<String, Object> toMap(Node node) {
        if (node == null) return null;

        Map<String, Object> map = new LinkedHashMap<>();
        map.put("value", node.value);
        map.put("left", toMap(node.left));
        map.put("right", toMap(node.right));
        return map;
    }

    public void buildBalancedTree(List<Integer> sortedValues) {
        root = buildBalancedSubtree(sortedValues, 0, sortedValues.size() - 1);
    }

    private Node buildBalancedSubtree(List<Integer> values, int start, int end) {
        if (start > end) return null;

        int mid = (start + end) / 2;
        Node node = new Node(values.get(mid));

        node.left = buildBalancedSubtree(values, start, mid - 1);
        node.right = buildBalancedSubtree(values, mid + 1, end);

        return node;
    }
}
