package org.example.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderNumber ;
    private Double totalPrice;
    private LocalDateTime orderTime;
    private String deliveryStatus;

    @ManyToMany(targetEntity = Product.class, fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    private List<Product> products = new ArrayList<>();


}