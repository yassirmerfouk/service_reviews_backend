package com.app.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor @AllArgsConstructor @Setter @Getter @Builder
public class ServiceImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String storageName;
    @ManyToOne
    @JoinColumn(name = "service_id")
    private Service service;
}
