package ee.piperal.veebipood.service;

import ee.piperal.veebipood.dto.OrderRowDto;
import ee.piperal.veebipood.entity.Order;
import ee.piperal.veebipood.entity.OrderRow;
import ee.piperal.veebipood.entity.Person;
import ee.piperal.veebipood.entity.Product;
import ee.piperal.veebipood.repository.OrderRepository;
import ee.piperal.veebipood.repository.PersonRepository;
import ee.piperal.veebipood.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Service
@RequiredArgsConstructor
public class OrderService {


    //@Autowired --> Dependency Injection
    //@RequiredArgsConstructor --> Dependency Injection

    //Gets pulled in

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private ProductRepository productRepository;

    public Order saveOrder(Long personId, String parcelMachine, List<OrderRowDto> orderRows) {
        Order order = new Order();
        order.setCreated(new Date());
        order.setParcelMachine(parcelMachine);
 //       order.setOrderRows(orderRows);
        Person person = personRepository.findById(personId).orElseThrow();
        order.setPerson(person);
        order.setTotal(calculateOrdersTotal(orderRows, order));
        return orderRepository.save(order);
    }

    private double calculateOrdersTotal(List<OrderRowDto> orderRows, Order order) {
        double total = 0;
        List<OrderRow> orderRowsInOrder = new ArrayList<OrderRow>();
        for(OrderRowDto orderRow : orderRows){
            Product product = productRepository.findById(orderRow.getProductId()).orElseThrow();
            total += product.getPrice() * orderRow.getQuantity();

            OrderRow orderRowInOrder = new OrderRow();
            orderRowInOrder.setProduct(product);
            orderRowInOrder.setQuantity(orderRow.getQuantity());
            orderRowsInOrder.add(orderRowInOrder);
        }
        order.setOrderRows(orderRowsInOrder);
        return total;
    }

}
