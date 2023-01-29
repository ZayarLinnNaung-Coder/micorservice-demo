package com.zshopping.orderservice.service.impl;

import com.zshopping.orderservice.converter.OrderItemConverter;
import com.zshopping.orderservice.dto.InventoryResponse;
import com.zshopping.orderservice.dto.OrderRequest;
import com.zshopping.orderservice.event.OrderPlacedEvent;
import com.zshopping.orderservice.model.Order;
import com.zshopping.orderservice.model.OrderItem;
import com.zshopping.orderservice.repository.OrderRepository;
import com.zshopping.orderservice.service.IOrderService;
import lombok.AllArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class OrderService implements IOrderService {

    private final OrderRepository orderRepository;
    private final KafkaTemplate<String, OrderPlacedEvent> kafkaTemplate;
    private final WebClient.Builder webClientBuilder;

    @Override
    @Transactional
    public String placeOrder(OrderRequest orderRequest) {
        List<OrderItem> orderItemList = orderRequest.getOrderItemList().stream()
                .map(OrderItemConverter::convertDtoToEntity).toList();

        Order order = Order.builder()
                .orderNumber(UUID.randomUUID().toString())
                .orderItemList(orderItemList)
                .build();

        List<String> skuCodeList = order.getOrderItemList().stream().map(OrderItem::getSkuCode).toList();

        // call inventory service, and place order if product is in stock
        InventoryResponse[] inventoryResponseArray = webClientBuilder.build().get()
                .uri("http://inventory-service/api/inventories",
                        uriBuilder -> uriBuilder.queryParam("skuCode", skuCodeList).build()
                )
                .retrieve()
                .bodyToMono(InventoryResponse[].class)
                .block();

        boolean allProductsInInStock = Arrays.stream(inventoryResponseArray).allMatch(InventoryResponse::isInStock);

        if(allProductsInInStock){
            orderRepository.save(order);
            kafkaTemplate.send("notificationTopic", new OrderPlacedEvent(order.getOrderNumber()));
            return "Order successfully completed...";
        }else{
            throw new IllegalArgumentException("Product is not in inStock. Please try again in next month");
        }

    }

    @Override
    public List<Order> findAllOrders() {
        return orderRepository.findAll();
    }


}
