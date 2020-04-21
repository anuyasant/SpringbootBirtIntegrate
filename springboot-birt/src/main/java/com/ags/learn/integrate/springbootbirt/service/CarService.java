package com.ags.learn.integrate.springbootbirt.service;

import org.springframework.stereotype.Service;

import com.ags.learn.integrate.springbootbirt.dto.Car;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarService {
    static List<Car> cars;
    static{
        Car car1 = new Car();
        car1.setYear("2000");
        car1.setMake("Chevrolet");
        car1.setModel("Corvette");
        Car car2 = new Car();
        car2.setYear("2005");
        car2.setMake("Dodge");
        car2.setModel("Viper");
        Car car3 = new Car();
        car3.setYear("2002");
        car3.setMake("Ford");
        car3.setModel("Mustang GT");
        cars = Arrays.asList(  car1, car2, car3 ) ;
    }

    public List<Car> getAllCars (){
        return this.cars ;
    }

    public List<Car> getCarsByYear(String year){
    	List<Car> list=cars.stream().filter(car->
            car.getYear().equals(year)
        ).collect(Collectors.toList());
        return list;
    }
}
