package com.codechallenge.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.codechallenge.model.Record;

@Repository
public interface RecordDTO extends JpaRepository<Record, Integer>{

}
