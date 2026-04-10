package ee.piperal.veebipood.controller;

import ee.piperal.veebipood.dto.OrderRowDto;
import ee.piperal.veebipood.entity.Order;
import ee.piperal.veebipood.entity.OrderRow;
import ee.piperal.veebipood.repository.OrderRepository;
import ee.piperal.veebipood.service.OrderService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class OrderController {

    private OrderRepository OrderRepository;
    private OrderService orderService;

    @GetMapping("order")
    public List<Order> getOrder() {
        return OrderRepository.findAll();
    }

    @DeleteMapping("order/{id}")
    public List<Order> delOrder(@PathVariable Long id) {
        OrderRepository.deleteById(id);
        return OrderRepository.findAll();
    }

    @PostMapping("order/add")
    public Order addOrder(@RequestParam Long personId,
                          @RequestParam(required = false) String parcelMachine,
                          @RequestBody List<OrderRowDto> orderRows) {
        return orderService.saveOrder(personId, parcelMachine, orderRows);
    }
}
