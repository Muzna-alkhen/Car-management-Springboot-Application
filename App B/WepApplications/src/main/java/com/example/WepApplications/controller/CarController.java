package com.example.WepApplications.controller;
import com.example.WepApplications.model.Car;
import com.example.WepApplications.dto.CarDto;
import com.example.WepApplications.dto.SellDto;
import com.example.WepApplications.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RequestMapping(value="/cars")
@RestController
public class CarController {
    @Autowired
    CarService carService;
    @RequestMapping(value = "/cars/export")
    public void exportToCSV(HttpServletResponse response) throws IOException {
            response.setContentType("text/csv");
            DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
            String currentDateTime = dateFormatter.format(new Date());

            String headerKey = "Content-Disposition";
            String headerValue = "attachment; filename=users_" + currentDateTime + ".csv";
            response.setHeader(headerKey, headerValue);



            List<Car> listCars = carService.findAll();

            ICsvBeanWriter csvWriter = new CsvBeanWriter( response.getWriter(), CsvPreference.STANDARD_PREFERENCE);
            String[] csvHeader = {"Car ID", "Name", "Price", "Seats"};
            String[] nameMapping = {"id", "name", "price", "seats"};

            csvWriter.writeHeader(csvHeader);

            for (Car car : listCars) {
                csvWriter.write(car, nameMapping);
            }

            csvWriter.close();
        }





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
    @RequestMapping(value="/notsold", method = RequestMethod.GET)
    public ResponseEntity<List<Car>> getAllUnSoldCars() {
        List<Car> list = carService.findAllUnSoledCar();
        return new ResponseEntity<List<Car>>(list, new HttpHeaders(), HttpStatus.OK);
    }

    @Transactional
    @RequestMapping(value="/sell/{id}", method = RequestMethod.POST)
    public Car sellCar(@RequestBody SellDto sellDto, @PathVariable Long id) throws ParseException {  return carService.sell(sellDto,id);
    }

    @RequestMapping(value="/delete/{id}", method = RequestMethod.DELETE)
    public Car deleteCar( @PathVariable Long id){  return carService.delete(id);
    }


    }
