package com.example.demo.repository;

import com.example.demo.entity.MessageEntity;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface MessageRepository extends CrudRepository<MessageEntity, Integer> {

    List<MessageEntity> findByTag(String tag);

}