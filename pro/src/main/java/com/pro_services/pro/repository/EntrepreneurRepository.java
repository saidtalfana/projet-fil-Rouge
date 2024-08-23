package com.pro_services.pro.repository;

import com.pro_services.pro.model.Entrepreneur;
import org.springframework.data.jpa.repository.JpaRepository;

import java.beans.JavaBean;

public interface EntrepreneurRepository extends JpaRepository<Entrepreneur, Long> {
}
