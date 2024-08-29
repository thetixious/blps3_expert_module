package org.tix.lab3_1.util;

import lombok.Data;

import java.util.List;

@Data
public class DataRequest {
    private LongWrapper longWrapper;
    private List<Long> cardsId;

}
