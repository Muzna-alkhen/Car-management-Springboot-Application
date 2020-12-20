package com.example.WepApplications.controller;

import com.example.WepApplications.dto.CarDto;
import com.example.WepApplications.dto.ParameterDto;
import com.example.WepApplications.model.Car;
import com.example.WepApplications.model.Parameter;
import com.example.WepApplications.service.ParameterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value="/parameters")
public class ParameterController {

    @Autowired
    ParameterService parameterService ;

    @RequestMapping(value="/update/seats", method = RequestMethod.POST)
    public Parameter updateSeats(@RequestBody ParameterDto parameter){
        return parameterService.updateSeats(parameter);
    }
    @RequestMapping(value="/update/ratio", method = RequestMethod.POST)
    public Parameter updateRatio(@RequestBody ParameterDto parameter){
        return parameterService.updateRatio(parameter);
    }

    @RequestMapping(value="/seats", method = RequestMethod.GET)
    public int findSeats(){
        return parameterService.findSeats();
    }
    @RequestMapping(value="/ratio", method = RequestMethod.GET)
    public int findRatio(){
        return parameterService.findRatio();
    }
}
