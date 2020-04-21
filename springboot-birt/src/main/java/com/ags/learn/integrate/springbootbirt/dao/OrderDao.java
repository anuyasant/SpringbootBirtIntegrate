package com.ags.learn.integrate.springbootbirt.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ags.learn.integrate.springbootbirt.dto.Order;

@Repository
public interface OrderDao extends JpaRepository<Order, Long>{
	
}
