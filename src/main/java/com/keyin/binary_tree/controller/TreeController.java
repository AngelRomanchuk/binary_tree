package com.keyin.binary_tree.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.keyin.binary_tree.model.TreeRecord;
import com.keyin.binary_tree.repository.TreeRecordRepository;
import com.keyin.binary_tree.service.BinarySearchTree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
@RequestMapping("/api/tree")
public class TreeController {

    @Autowired
    private TreeRecordRepository treeRepo;

    @GetMapping("/build")
    @ResponseBody
    public Map<String, Object> buildTree(@RequestParam String numbers) {
        String[] parts = numbers.split(",");
        BinarySearchTree bst = new BinarySearchTree();
        for (String part : parts) {
            bst.insert(Integer.parseInt(part.trim()));
        }

        Map<String, Object> treeMap = bst.toMap(bst.getRoot());

        try {
            String jsonString = new ObjectMapper().writeValueAsString(treeMap);
            treeRepo.save(new TreeRecord(numbers, jsonString));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return treeMap;
    }

    @GetMapping("/enter-numbers")
    public String showEnterNumbersPage() {
        return "enter-numbers";
    }
}
