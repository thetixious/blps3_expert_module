package org.tix.lab3_1.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.tix.lab3_1.service.ApprovingService;
import org.tix.lab3_1.util.DataRequest;
import org.tix.lab3_1.util.LongWrapper;

import java.util.List;

@RestController
@RequestMapping("/approval")
public class ApprovingController {


    private final ApprovingService approvingService;

    public ApprovingController(ApprovingService approvingService) {

        this.approvingService = approvingService;
    }
    @Operation(summary = "Вывод информации о пользователе и его заявке на кредитную карту")
    @GetMapping(value = "/info")
    public ResponseEntity<?> info(@RequestBody LongWrapper longWrapper) {

        return approvingService.getInfo(longWrapper.getId());

    }

    @Operation(summary="Одобрение выбранных карт")
    @PostMapping(value = "/result")
    public ResponseEntity<?> result(@RequestBody DataRequest approvalRequest) {
        LongWrapper longWrapper = approvalRequest.getLongWrapper();
        List<Long> cardsId = approvalRequest.getCardsId();
        return approvingService.getResult(longWrapper.getId(), cardsId);

    }

}
