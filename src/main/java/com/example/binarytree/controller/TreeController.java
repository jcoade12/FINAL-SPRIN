package com.example.binarytree.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.binarytree.TreeNode;
import com.example.binarytree.entity.TreeEntity;
import com.example.binarytree.repository.TreeRepository;
import com.example.binarytree.service.BalancedTreeService;
import com.example.binarytree.service.BinaryTreeService;

@Controller
public class TreeController {

    @Autowired
    private BinaryTreeService binaryTreeService;

    @Autowired
    private BalancedTreeService balancedTreeService;

    @Autowired
    private TreeRepository treeRepository;

    @GetMapping("/enter-numbers")
    public String enterNumbers() {
        return "enter-numbers";
    }

    @PostMapping("/process-numbers")
    public String processNumbers(@RequestParam String numbers, 
                                 @RequestParam(required = false, defaultValue = "false") boolean balanced, 
                                 Model model) {
        try {
            // Parse input numbers
            int[] nums = Arrays.stream(numbers.split(","))
                               .mapToInt(Integer::parseInt)
                               .toArray();

            // Construct and optionally balance the tree
            TreeNode root = binaryTreeService.constructTree(nums);
            if (balanced) {
                root = balancedTreeService.balanceTree(root);
            }

            // Convert to JSON and formatted text
            String treeJson = binaryTreeService.treeToJson(root);
            String formattedTree = binaryTreeService.formatTree(root, 0);

            // Save to database
            TreeEntity entity = new TreeEntity();
            entity.setInputNumbers(numbers);
            entity.setTreeJson(treeJson);
            entity.setBalanced(balanced);
            treeRepository.save(entity);

            // Add attributes for display
            model.addAttribute("treeJson", treeJson);
            model.addAttribute("formattedTree", formattedTree);
            return "result";
        } catch (Exception e) {
            model.addAttribute("error", "Invalid input. Please provide a valid comma-separated list of integers.");
            return "enter-numbers";
        }
    }

    @GetMapping("/previous-trees")
    public String viewPreviousTrees(Model model) {
        List<TreeEntity> trees = treeRepository.findAll();
        model.addAttribute("trees", trees);
        return "previous-trees";
    }
}
