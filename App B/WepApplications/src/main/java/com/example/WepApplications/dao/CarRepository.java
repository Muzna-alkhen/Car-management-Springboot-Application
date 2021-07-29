package com.example.WepApplications.dao;
import com.example.WepApplications.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;


@Repository
public interface CarRepository extends CrudRepository<Car, Long> {
    List<Car> findBySoldPrice(@Param("soldPrice") float soldPrice);
    List<Car> findBySoldDate (@Param("soldDate") Date soldDate);
}