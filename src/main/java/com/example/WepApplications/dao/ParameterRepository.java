package com.example.WepApplications.dao;


import com.example.WepApplications.model.Parameter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParameterRepository extends CrudRepository<Parameter, Long> {
Parameter findByK (String key);

}