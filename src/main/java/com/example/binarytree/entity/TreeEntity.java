package com.example.binarytree.entity;

import jakarta.persistence.*;

@Entity
public class TreeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String inputNumbers;

    @Lob
    private String treeJson;

    private boolean isBalanced;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getInputNumbers() {
        return inputNumbers;
    }

    public void setInputNumbers(String inputNumbers) {
        this.inputNumbers = inputNumbers;
    }

    public String getTreeJson() {
        return treeJson;
    }

    public void setTreeJson(String treeJson) {
        this.treeJson = treeJson;
    }

    public boolean isBalanced() {
        return isBalanced;
    }

    public void setBalanced(boolean balanced) {
        isBalanced = balanced;
    }
}
