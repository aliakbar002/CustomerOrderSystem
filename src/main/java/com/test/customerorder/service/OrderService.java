package com.test.customerorder.service;

import com.test.customerorder.Enum.OrderStatus;
import com.test.customerorder.dto.OrderDto;
import com.test.customerorder.entity.Customer;
import com.test.customerorder.entity.Order;
import com.test.customerorder.exception.RecordNotFoundException;
import com.test.customerorder.repository.CustomerRepository;
import com.test.customerorder.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderService{
    @Autowired
    private  OrderRepository orderRepository;

    @Autowired
    private CustomerRepository customerRepository;
  //  private Order order;
 //   private OrderStatus status;

    //GetById Mapping USing DTO
    public OrderDto getDepartureDto(int id) {
        Order order = orderRepository.findById(id).orElse(null);
        OrderDto orderDto = new OrderDto();
        assert order != null;
        orderDto.setDeparture(order.getDeparture());
        return  orderDto;
    }


    //Get All Mapping
    public List<Order> getOrdersDetails() {
        return new ArrayList<>(orderRepository.findAll());
    }

    //Get Order ById
    public Order getResponse(int id) {
        return orderRepository.findById(id).orElse(null);
    }

    //Post Mapping
    public Order addOrder(Order order) throws RecordNotFoundException {
        int id = order.getCustomer().getId();
        Optional<Customer> customer=customerRepository.findById(id);
       if (customer!=null){
         //  order.setId(order.getId());
           order.setCustomer(order.getCustomer());
           return orderRepository.save(order);
       }else {
           throw new RecordNotFoundException("Record not exist");
       }
      //  return  orderRepository.save(order);
    }


    //Put Mapping
    public Order updateOrder(Order order) {
        Order updateOrder=orderRepository.findById(order.getId()).orElse(null);

                //updateOrder.setCustomer(order.getCustomer());

        assert updateOrder != null;
        updateOrder.setDate(order.getDate());
                // updateOrder.setName(order.getName());
                 updateOrder.setDeparture(order.getDeparture());
                 updateOrder.setOrigin(order.getOrigin());
           //      updateOrder.setStatus(order.getStatus());
                 return orderRepository.save(updateOrder);
    }

    //Delete Mapping
    public String deleteOrder(int id){
        orderRepository.deleteById(id);
        return "Order is Deleted Successfully of ID "+id;
    }

    //Get All  or list of DTO
    public List<OrderDto> getAllDeparture() {
        return orderRepository.findAll()
                .stream()
                .map(this::convertEntityToDTO)
                .collect(Collectors.toList());
    }
    private OrderDto convertEntityToDTO(Order order){
        OrderDto orderDto = new OrderDto();
        orderDto.setDeparture(order.getDeparture());
        return orderDto;
    }

   //Get Date Mapping
    public List<Order> getOrderByDate(Date start, Date end) {
        return orderRepository.findByDateBetween(start, end);
    }

    //Get list of  Orders By Status
    public List<Order> getOrderByStatus(String valueOf) {
        return orderRepository.findByStatus(OrderStatus.valueOf(valueOf));
    }

    //Update Status Only
    public Order updateStatus(Order order) {
        Order updateStatus = orderRepository.findById(order.getId()).orElse(null);

        assert updateStatus != null;
        updateStatus.setStatus(order.getStatus());
        return orderRepository.save(updateStatus);
    }

    //Get active Orders
    public List<Order> getActiveOrders() {

        //Only display Active Orders
        return  orderRepository.findByStatusIsNot(OrderStatus.Expired);
    }

     //Get Expired Orders
    public List<Order> getExpiredOrders() {
        //only Display Expired Orders
        return orderRepository.findByStatus(OrderStatus.Expired);
    }

    //Display Dated Active Orders
    public List<Order> getDatedOrder() {
          return orderRepository.findByStatusIsNotOrderByDateAsc(OrderStatus.Expired);
    }

    //Update status to Expire via Delete mapping
    public Order OrderExpired(int id) {
        Order ExpireOrder=orderRepository.findById(id).orElse(null);
        assert ExpireOrder != null;
        ExpireOrder.setStatus(OrderStatus.Expired);
        return orderRepository.save(ExpireOrder);
    }
}
