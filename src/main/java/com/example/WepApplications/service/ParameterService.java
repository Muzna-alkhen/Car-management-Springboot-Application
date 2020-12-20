package com.example.WepApplications.service;


import com.example.WepApplications.dao.ParameterRepository;
import com.example.WepApplications.dto.ParameterDto;
import com.example.WepApplications.model.Car;
import com.example.WepApplications.model.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service

public class ParameterService {
    @Autowired
    ParameterRepository parameterRepository;

    @CacheEvict(value="seats", allEntries=true)
    public Parameter updateSeats(ParameterDto parameterDto)
    {
        Parameter parameter = parameterRepository.findByK("seats");
        parameter.setValue(parameterDto.getValue());
        parameter = parameterRepository.save(parameter);
        System.out.println("//////////THIS NOT CACHE ////////// ");
        return parameter;

    }

    @CacheEvict(value="ratio", allEntries=true)
    public Parameter updateRatio(ParameterDto parameterDto)
    {
        Parameter parameter = parameterRepository.findByK("ratio");
        parameter.setValue(parameterDto.getValue());
        parameter = parameterRepository.save(parameter);
        System.out.println("//////////THIS NOT CACHE ////////// ");
        return parameter;

    }

    @Cacheable("seats")
    public int findSeats()
    {

        Parameter parameter =parameterRepository.findByK("seats");
        int defaultSeats = parameter.getValue();
        System.out.println("//////////THIS NOT CACHE ////////// ");
        return defaultSeats;
    }

    @Cacheable("ratio")
    public int findRatio()
    {

        Parameter parameter =parameterRepository.findByK("ratio");
        int defaultRatio = parameter.getValue();
        System.out.println("//////////THIS NOT CACHE ////////// ");
        return defaultRatio;
    }
}
