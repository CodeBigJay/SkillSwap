package com.CodeBigJay.SkillSwap.service;

import com.CodeBigJay.SkillSwap.model.SkillExchange;
import com.CodeBigJay.SkillSwap.repository.SkillExchangeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SkillExchangeService {
    private final SkillExchangeRepository repository;

    public SkillExchangeService(SkillExchangeRepository repository) {
        this.repository = repository;
    }

    public SkillExchange createExchange(SkillExchange exchange) { return repository.save(exchange); }
    public List<SkillExchange> getAllExchanges() { return repository.findAll(); }
}
