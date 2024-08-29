package org.tix.lab3_1.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionTemplate;
import org.tix.lab3_1.model.mainDB.ExpertMessage;
import org.tix.lab3_1.model.bankDB.Manager;
import org.tix.lab3_1.repo.bankDB.ManagerRepository;
import org.tix.lab3_1.repo.mainDB.ExpertMessageRepository;
import org.springframework.http.ResponseEntity;
import org.tix.lab3_1.DTO.CreditCardDTO;
import org.tix.lab3_1.util.CreditCardMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ApprovingService {
    private final PlatformTransactionManager transactionManager;
    private final ManagerRepository managerRepository;
    private final ExpertMessageRepository expertMessageRepository;
    private List<CreditCardDTO> response = new ArrayList<>();
    private final CreditCardMapper creditCardMapper;

    public ApprovingService(PlatformTransactionManager transactionManager, ManagerRepository managerRepository, ExpertMessageRepository expertMessageRepository, CreditCardMapper creditCardMapper) {
        this.transactionManager = transactionManager;
        this.managerRepository = managerRepository;
        this.expertMessageRepository = expertMessageRepository;
        this.creditCardMapper = creditCardMapper;
    }

    public ResponseEntity<?> getResult(Long id, List<Long> cardsId) {
        ExpertMessage expertMessage = expertMessageRepository.findById(id).orElseThrow();
        TransactionTemplate transactionTemplate = new TransactionTemplate(transactionManager);
        try {
            transactionTemplate.execute(status -> {
                expertMessage.getPreferredCards().removeIf(card -> !cardsId.contains(card.getId()));
                Manager manager = managerRepository.findFirstByStatusFalse().orElseThrow();
                StringBuilder data = new StringBuilder();
                data.append(expertMessage.getCandidateName());
                data.append(expertMessage.getCandidateSurname());
                data.append(expertMessage.getCandidatePassport());
                manager.setData(String.valueOf(data));
                manager.setStatus(true);
                response = expertMessage.getPreferredCards().stream().map(creditCardMapper::toDTO).collect(Collectors.toList());
                expertMessageRepository.save(expertMessage);
                managerRepository.save(manager);
                return response;
            });

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }


    public void saveExpertMessage(ExpertMessage expertMessage) {
        expertMessageRepository.save(expertMessage);
    }

    public ResponseEntity<?> getInfo(Long id) {
        ExpertMessage message = expertMessageRepository.findByUserId(id).orElseThrow();
        return ResponseEntity.status(HttpStatus.OK).body(message.getCandidateName());
    }
}
