package org.tix.lab3_1.DTO;

import lombok.Data;
import org.tix.lab3_1.model.util.Bonus;
import org.tix.lab3_1.model.util.CardType;
import org.tix.lab3_1.model.util.Goal;

@Data
public class CreditCardDTO {
    private Long id;
    private String name;
    private CardType cardType;
    private Double creditLimit;
    private Goal goal;
    private Bonus bonus;

}
