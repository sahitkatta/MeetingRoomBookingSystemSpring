package com.spring.comakeit.application.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.comakeit.application.entity.Resource;

@Repository
public interface ResourceRepository extends JpaRepository<Resource, Integer> {

}
