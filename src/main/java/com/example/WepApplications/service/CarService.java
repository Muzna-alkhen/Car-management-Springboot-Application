package com.example.WepApplications.service;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.example.WepApplications.aspect.Log;
import com.example.WepApplications.dto.CarDto;
import com.example.WepApplications.dto.SellDto;
import com.example.WepApplications.dao.CarRepository;
import com.example.WepApplications.dao.ParameterRepository;
import com.example.WepApplications.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class CarService {
    @Autowired
    CarRepository carRepository;
    @Autowired
    ParameterRepository parameterRepository;
    @Autowired
    ParameterService parameterService;

    @Log
    public Car save(CarDto carDto) {
        Car car = new Car();
        car.setName(carDto.getName());
        car.setPrice(carDto.getPrice());
        int seats = carDto.getSeats();
        if (seats == 0)
        {

            int defaultSeats = parameterService.findSeats();
            car.setSeats(defaultSeats);

        }
        else
        {
            car.setSeats(seats);
        }
        return carRepository.save(car);
    }

    @Log
    public List<Car> findAll() {
        List<Car> list = new ArrayList<>();
        carRepository.findAll().iterator().forEachRemaining(list::add);
        return list;
    }

    @Log
    public Car update(CarDto carDto, Long id) {
        Optional<Car> car = carRepository.findById(id);
        if(car.isPresent())
        {

            Car updatedCar = car.get();
            if ( ! (carDto.getName().equals("")))
            {updatedCar.setName(carDto.getName());}
            if (carDto.getSeats() != 0 )
            { updatedCar.setSeats(carDto.getSeats());}
            if (carDto.getPrice() != 0)
            {updatedCar.setPrice(carDto.getPrice());}
            updatedCar = carRepository.save(updatedCar);

            return updatedCar;
        } else {


            return null;
        }
    }
    @Log
    @Transactional
    public Car sell(SellDto sellDto, Long id) throws ParseException {
        Optional<Car> car = carRepository.findById(id);
        if(car.isPresent())
        {

            Car soldCar = car.get();
            soldCar.setCustomerName(sellDto.getCustomerName());
            soldCar.setSoldDate(sellDto.getSoldDate());
            if (sellDto.getSoldPrice() == 0)
            {

                int defaultRatio= parameterService.findRatio();
                soldCar.setSoldPrice( (soldCar.getPrice() )   *       (defaultRatio)    );

            }
            else
            {
                soldCar.setSoldPrice(sellDto.getSoldPrice());

            }
            soldCar = carRepository.save(soldCar);

            return soldCar;
        } else {


            return null;
        }



    }

    public List<Car> findAllUnSoledCar()
    {
        List<Car> list = new ArrayList<>();
        carRepository.findBySoldPrice(0).iterator().forEachRemaining(list::add);
        return list;

    }
    @Log
    public Car delete(Long id) {
        Optional<Car> car = carRepository.findById(id);
        Car deletedCar = car.get();

        if(car.isPresent())
        {
            carRepository.deleteById(id);
            return deletedCar;
        }
        else
        {
            return null;
        }
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
