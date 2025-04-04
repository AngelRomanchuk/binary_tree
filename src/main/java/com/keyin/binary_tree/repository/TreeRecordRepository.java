package com.keyin.binary_tree.repository;

import com.keyin.binary_tree.model.TreeRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TreeRecordRepository extends JpaRepository<TreeRecord, Long> {
}
