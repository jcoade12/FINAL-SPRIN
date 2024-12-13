package com.example.binarytree.service;

import org.springframework.stereotype.Service;

import com.example.binarytree.TreeNode;

@Service
public class BinaryTreeService {
    public TreeNode constructTree(int[] numbers) {
        TreeNode root = null;
        for (int num : numbers) {
            root = insert(root, num);
        }
        return root;
    }

    private TreeNode insert(TreeNode node, int value) {
        if (node == null) return new TreeNode(value);
        if (value < node.getValue()) node.setLeft(insert(node.getLeft(), value));
        else node.setRight(insert(node.getRight(), value));
        return node;
    }

    public String treeToJson(TreeNode root) {
        if (root == null) return "null";
        return String.format(
            "{\"value\":%d,\"left\":%s,\"right\":%s}",
            root.getValue(),
            treeToJson(root.getLeft()),
            treeToJson(root.getRight())
        );
    }

    public String formatTree(TreeNode root, int indent) {
        if (root == null) {
            return "";
        }

        StringBuilder builder = new StringBuilder();
        builder.append(" ".repeat(indent)).append(root.getValue()).append("\n");

        builder.append(formatTree(root.getLeft(), indent + 2));  // Indent left child
        builder.append(formatTree(root.getRight(), indent + 2)); // Indent right child

        return builder.toString();
    }
}
