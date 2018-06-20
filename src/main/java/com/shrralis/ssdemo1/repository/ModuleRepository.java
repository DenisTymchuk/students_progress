package com.shrralis.ssdemo1.repository;

import com.shrralis.ssdemo1.entity.Module;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ModuleRepository extends JpaRepository<Module, Integer> {
}
