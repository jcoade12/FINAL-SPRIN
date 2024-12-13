package com.example.binarytree.repository;

import com.example.binarytree.entity.TreeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TreeRepository extends JpaRepository<TreeEntity, Long> {
}
