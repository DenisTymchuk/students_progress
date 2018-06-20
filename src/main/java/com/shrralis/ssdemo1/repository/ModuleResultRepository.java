package com.shrralis.ssdemo1.repository;

import com.shrralis.ssdemo1.entity.ModuleResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ModuleResultRepository extends JpaRepository<ModuleResult, Integer> {
}
