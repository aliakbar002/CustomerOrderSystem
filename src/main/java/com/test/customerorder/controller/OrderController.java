package com.test.customerorder.controller;

import com.test.customerorder.Enum.OrderStatus;
import com.test.customerorder.dto.OrderDto;
import com.test.customerorder.entity.Order;
import com.test.customerorder.exception.RecordNotFoundException;
import com.test.customerorder.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {
  @Autowired
   private OrderService orderService;

  @GetMapping
    public List<Order> getAllOrders(){
      return orderService.getOrdersDetails();
  }

  //Get By id
  @GetMapping("/order/{id}")
  public Order getResponse(@PathVariable int id){
    return  orderService.getResponse(id);
  }

  //only get departures
@GetMapping("/dto")
  public List<OrderDto> AllDeparture(){
    return orderService.getAllDeparture();
  }

  @PutMapping("{id}")
  public Order updateOrder(@RequestBody Order order){
    return  orderService.updateOrder(order);
  }

  @DeleteMapping("/setExpired/{id}")
  public Order setExpired(@PathVariable int id){
    return orderService.OrderExpired(id);
  }

  @DeleteMapping("{id}")
  public String deleteOrder(@PathVariable int id){
    return  orderService.deleteOrder(id);
  }

  @PostMapping("/save")
  public  Order addOrder(@RequestBody Order order) throws RecordNotFoundException {
    return  orderService.addOrder(order);
  }

  //DTO Mapping Get By Id
  @GetMapping("{id}")
  public OrderDto getDeparture(@PathVariable int id){
    return orderService.getDepartureDto(id);
  }

  //Get List of Date
  @GetMapping("/date")
  public  List<Order> getOrderByDate(@RequestParam Date start, @RequestParam Date end){
    return orderService.getOrderByDate(start,end);
  }

  //Get By Specific status
  @GetMapping ("/get")
  public List<Order> getOrderByStatus(@RequestParam OrderStatus status){
    return orderService.getOrderByStatus(String.valueOf(status));
  }

  //Update the Status
  @PutMapping("{id}/status")
  public Order updateStatus(@RequestBody Order order){
    return  orderService.updateStatus(order);
  }

  //Getting the only Active Orders
  @GetMapping("/active")
  public List<Order> GetActiveOrder(){
    return orderService.getActiveOrders();
  }

  //Getting the order Only Expired
  @GetMapping("/expired")
  public List<Order> getExpired(){
    return orderService.getExpiredOrders();
  }

  //Get Active Order By Date Ascending
  @GetMapping("/Date")
   public List<Order> getDatedOrder(){
    return  orderService.getDatedOrder();
  }

}


