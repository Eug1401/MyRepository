package com.example.demo.repository;

import com.example.demo.Entity.IncomingMessage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ObjectRepository extends JpaRepository<IncomingMessage, Long> {

}
