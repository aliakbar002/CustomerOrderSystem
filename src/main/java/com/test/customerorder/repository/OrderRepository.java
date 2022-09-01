package com.test.customerorder.repository;

import com.test.customerorder.Enum.OrderStatus;
import com.test.customerorder.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Date;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Integer> {

    // query for find status between date through request param
    List<Order> findByDateBetween(Date start, Date end);

    //query for find orders expired
    List<Order> findByStatus(OrderStatus status);

    //query for find Only Active orders
    List<Order> findByStatusIsNot(OrderStatus status);

     //query for active order by date
    List<Order> findByStatusIsNotOrderByDateAsc(OrderStatus status);



    // List<Order> findByStatusOrderByDateAsc(OrderStatus status);


  //  List<Order> findByStatusIsNot(OrderStatus expired);
}
