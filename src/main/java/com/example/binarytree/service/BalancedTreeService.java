package com.example.binarytree.service;

import com.example.binarytree.TreeNode;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BalancedTreeService {
    public TreeNode balanceTree(TreeNode root) {
        List<Integer> nodes = new ArrayList<>();
        storeInOrder(root, nodes);
        return buildBalancedTree(nodes, 0, nodes.size() - 1);
    }

    private void storeInOrder(TreeNode node, List<Integer> nodes) {
        if (node == null) return;
        storeInOrder(node.getLeft(), nodes);
        nodes.add(node.getValue());
        storeInOrder(node.getRight(), nodes);
    }

    private TreeNode buildBalancedTree(List<Integer> nodes, int start, int end) {
        if (start > end) return null;
        int mid = (start + end) / 2;
        TreeNode node = new TreeNode(nodes.get(mid));
        node.setLeft(buildBalancedTree(nodes, start, mid - 1));
        node.setRight(buildBalancedTree(nodes, mid + 1, end));
        return node;
    }
}
