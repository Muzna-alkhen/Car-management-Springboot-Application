package com.example.WepApplications.controller;
import com.example.WepApplications.model.Car;
import com.example.WepApplications.model.CarDto;
import com.example.WepApplications.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(value="/cars")
@RestController
public class CarController {
    @Autowired
    CarService carService;



    @RequestMapping(value="/all", method = RequestMethod.GET)
    public ResponseEntity<List<Car>> getAllCars() {
        List<Car> list = carService.findAll();
        return new ResponseEntity<List<Car>>(list, new HttpHeaders(), HttpStatus.OK);
    }


    @RequestMapping(value="/create", method = RequestMethod.POST)
    public Car saveCar(@RequestBody CarDto car){
        return carService.save(car);
    }




    @RequestMapping(value="/update/{id}", method = RequestMethod.POST)
    public Car updateCar(@RequestBody CarDto car, @PathVariable Long id){  return carService.update(car,id);
    }
    @RequestMapping(value="/notSold", method = RequestMethod.GET)
    public ResponseEntity<List<Car>> getAllUnSoldCars() {
        List<Car> list = carService.findAllUnSoledCar();
        return new ResponseEntity<List<Car>>(list, new HttpHeaders(), HttpStatus.OK);
    }
/*
    @RequestMapping(value="/delete/{id}", method = RequestMethod.DELETE)
    public Car deleteCar( @PathVariable Long id){  return carService.delete(id);
    }

    @Transactional
    @RequestMapping(value="/sell/{id}", method = RequestMethod.POST)
    public Car sellCar(@RequestBody Map<String,String> body, @PathVariable Long id){  return carService.sell(body,id);
    }



    @RequestMapping(value="/{id}", method = RequestMethod.GET)
    public ResponseEntity<Car> getCarById(@PathVariable("id") Long id)
            {
        Car entity = carService.getCarById(id);
        return new ResponseEntity<Car>(entity, new HttpHeaders(), HttpStatus.OK);
    }

*/

    }
