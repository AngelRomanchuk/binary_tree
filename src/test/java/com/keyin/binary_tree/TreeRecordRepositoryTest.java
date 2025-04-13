package com.keyin.binary_tree;

import com.keyin.binary_tree.model.TreeRecord;
import com.keyin.binary_tree.repository.TreeRecordRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class TreeRecordRepositoryTest {

    @Autowired
    private TreeRecordRepository repository;

    @Test
    public void testSaveAndRetrieve() {
        TreeRecord record = new TreeRecord("1,2,3", "{\"value\":1,\"right\":{\"value\":2,\"right\":{\"value\":3}}}");
        repository.save(record);

        List<TreeRecord> all = repository.findAll();
        TreeRecord last = all.get(all.size() - 1); // Get the last one

        assertEquals("1,2,3", last.getInputNumbers());
    }
}