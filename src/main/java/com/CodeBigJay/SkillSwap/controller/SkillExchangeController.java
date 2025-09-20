package com.CodeBigJay.SkillSwap.controller;

import com.CodeBigJay.SkillSwap.model.SkillExchange;
import com.CodeBigJay.SkillSwap.service.SkillExchangeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/exchanges")
public class SkillExchangeController {
    private final SkillExchangeService exchangeService;

    public SkillExchangeController(SkillExchangeService exchangeService) {
        this.exchangeService = exchangeService;
    }

    @PostMapping
    public SkillExchange createExchange(@RequestBody SkillExchange exchange) {
        return exchangeService.createExchange(exchange);
    }

    @GetMapping
    public List<SkillExchange> getAllExchanges() {
        return exchangeService.getAllExchanges();
    }
}
