package com.pro_servises.pro.repository;

import com.pro_servises.pro.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactRepository extends JpaRepository<Contact, Integer> {
}
