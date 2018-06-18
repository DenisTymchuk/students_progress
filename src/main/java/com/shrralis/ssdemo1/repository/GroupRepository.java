package com.shrralis.ssdemo1.repository;

import com.shrralis.ssdemo1.entity.Group;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.validation.constraints.NotBlank;

public interface GroupRepository extends JpaRepository<Group, Integer> {

	Group findById(int id);
}
