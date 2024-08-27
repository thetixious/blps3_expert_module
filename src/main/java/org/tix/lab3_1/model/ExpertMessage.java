package org.tix.lab3_1.model;

import lombok.*;

import java.io.Serializable;


@Data
@NoArgsConstructor
public class ExpertMessage implements Serializable {
    private String name;
    private String surname;


}
