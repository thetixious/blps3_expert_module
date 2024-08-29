package org.tix.lab3_1.model.mainDB;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "message")
public class ExpertMessage implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    private Long creditOfferId;
    private String candidateName;
    private String candidateSurname;
    private String candidatePassport;
    private Double candidateCreditLimit;
    private Double candidateSalary;
    @ElementCollection(targetClass = Cards.class)
    private Set<Cards> preferredCards = new HashSet<>();



}

