package com.example.WepApplications.service;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import com.example.WepApplications.dao.CarRepository;
import com.example.WepApplications.dao.ParameterRepository;
import com.example.WepApplications.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class CarService {
    @Autowired
    CarRepository carRepository;
    @Autowired
    ParameterRepository parameterRepository;


    public Car save(CarDto carDto) {
        Car car = new Car();
        car.setName(carDto.getName());
        car.setPrice(carDto.getPrice());
        int seats = carDto.getSeats();
        if (seats == 0)
        {
            Parameter parameter =parameterRepository.findByK("seats");
            int defaultSeats = parameter.getValue();
            car.setSeats(defaultSeats);

        }
        else
        {
            car.setSeats(seats);
        }
        return carRepository.save(car);
    }

    public List<Car> findAll() {
        List<Car> list = new ArrayList<>();
        carRepository.findAll().iterator().forEachRemaining(list::add);
        return list;
    }











/*    public List<Car> getAllCars()
    {
        List<Car> carList = repository.findAll();

        if(carList.size() > 0) {
            return carList;
        } else {
            return new ArrayList<Car>();
        }
    }
    public Car getCarById(Long id) {
        Optional<Car> car = repository.findById(id);

        if (car.isPresent()) {
            return car.get();
        }
        else
        {return null;}
    }

    public void deleteCarById(Long id)
    {
        Optional<Car> car = repository.findById(id);

        if(car.isPresent())
        {
            repository.deleteById(id);
        }
        else
        {}
    }*/
}
