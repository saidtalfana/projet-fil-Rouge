package com.pro_servises.pro.service;

import com.pro_servises.pro.model.Person;

public interface PersonService {


    Person findByUserName(String username);
}
