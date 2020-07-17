package com.example.demo.repos;

import com.example.demo.Message;
import org.springframework.data.repository.CrudRepository;

public interface MessageRepo extends CrudRepository<Message, Integer> {

}