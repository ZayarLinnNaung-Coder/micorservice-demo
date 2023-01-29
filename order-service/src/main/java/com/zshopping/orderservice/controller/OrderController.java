package com.zshopping.orderservice.controller;

import com.zshopping.orderservice.dto.OrderRequest;
import com.zshopping.orderservice.model.Order;
import com.zshopping.orderservice.service.IOrderService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping(value = "/api/orders")
@AllArgsConstructor
public class OrderController {
    private final IOrderService orderService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @CircuitBreaker(name = "inventory", fallbackMethod = "fallBackMethod1")
    @TimeLimiter(name = "inventory")
    public CompletableFuture<String> placeOrder(@RequestBody OrderRequest orderRequest){
        return CompletableFuture.supplyAsync(() -> orderService.placeOrder(orderRequest));
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Order> findAllOrders(){return orderService.findAllOrders();}

    public CompletableFuture<String> fallBackMethod1(OrderRequest orderRequest, RuntimeException runtimeException){
        return CompletableFuture.supplyAsync(() -> "Oops... something went wrong. Please try again later !");
    }
}
