package com.app.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor @AllArgsConstructor @Setter @Getter @Builder
public class Service {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String shortDescription;
    private String longDescription;
    private String contactEmail;
    private String contactPhone;
    private double reviewsAverage;
    private int reviewsNumbers;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @OneToMany(mappedBy = "service", cascade = CascadeType.REMOVE)
    private List<ServiceImage> serviceImages = new ArrayList<>();

    private Long businessAccountId;
}
