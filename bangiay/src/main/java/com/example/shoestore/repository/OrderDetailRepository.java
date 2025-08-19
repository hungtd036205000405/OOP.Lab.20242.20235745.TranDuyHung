package com.example.shoestore.repository;

import com.example.shoestore.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {

}