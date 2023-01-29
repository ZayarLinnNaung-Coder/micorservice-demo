package com.zshopping.orderservice.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "t_order")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String orderNumber;

    @OneToMany(cascade = CascadeType.ALL)
    private List<OrderItem> orderItemList;
}
