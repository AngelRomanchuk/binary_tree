package com.keyin.binary_tree.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.keyin.binary_tree.model.TreeRecord;
import com.keyin.binary_tree.repository.TreeRecordRepository;
import com.keyin.binary_tree.service.BinarySearchTree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import java.util.*;

@Controller
public class TreeController {

    @Autowired
    private TreeRecordRepository treeRepo;

    // 🔹 GET route to show the HTML form
    @GetMapping("/enter-numbers")
    public String showEnterNumbersPage() {
        return "enter-numbers";
    }

    // 🔹 POST route to process form data and return result page
    @PostMapping("/process-numbers")
    public String processNumbers(@RequestParam String numbers, Model model) {
        try {
            List<Integer> inputList = parseAndSortNumbers(numbers);
            BinarySearchTree bst = new BinarySearchTree();
            bst.buildBalancedTree(inputList);  // 🔄 Use balanced logic

            Map<String, Object> treeMap = bst.toMap(bst.getRoot());
            String jsonString = new ObjectMapper().writeValueAsString(treeMap);

            treeRepo.save(new TreeRecord(numbers, jsonString));

            model.addAttribute("treeJson", jsonString);
            model.addAttribute("inputNumbers", numbers);
            return "result";
        } catch (Exception e) {
            model.addAttribute("error", "Invalid input. Please enter numbers separated by commas.");
            return "enter-numbers";
        }
    }

    // 🔹 JSON route to test tree build via URL or Postman
    @GetMapping("/api/tree/build")
    @ResponseBody
    public Map<String, Object> buildTree(@RequestParam String numbers) {
        List<Integer> inputList = parseAndSortNumbers(numbers);
        BinarySearchTree bst = new BinarySearchTree();
        bst.buildBalancedTree(inputList);  // 🔄 Use balanced logic

        Map<String, Object> treeMap = bst.toMap(bst.getRoot());

        try {
            String jsonString = new ObjectMapper().writeValueAsString(treeMap);
            treeRepo.save(new TreeRecord(numbers, jsonString));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return treeMap;
    }

    // 🔹 Show all previous trees
    @GetMapping("/api/tree/previous")
    public String showPreviousTrees(Model model) {
        List<TreeRecord> allTrees = treeRepo.findAll();
        model.addAttribute("trees", allTrees);
        return "previous-trees";
    }

    // 🔹 Helper method to parse + sort input numbers
    private List<Integer> parseAndSortNumbers(String numbers) {
        String[] parts = numbers.split(",");
        List<Integer> inputList = new ArrayList<>();
        for (String part : parts) {
            inputList.add(Integer.parseInt(part.trim()));
        }
        Collections.sort(inputList);
        return inputList;
    }
}
