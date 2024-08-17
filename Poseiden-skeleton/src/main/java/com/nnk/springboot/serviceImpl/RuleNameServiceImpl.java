package com.nnk.springboot.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.exceptions.RuleNameNotFoundException;
import com.nnk.springboot.repositories.RuleNameRepository;
import com.nnk.springboot.service.RuleNameService;

@Service
public class RuleNameServiceImpl implements RuleNameService {

    @Autowired
    private RuleNameRepository ruleNameRepository;

    @Override
    public List<RuleName> findAll() {
        return ruleNameRepository.findAll();
    }

    @Override
    public RuleName findById(Integer id) throws RuleNameNotFoundException {
        return ruleNameRepository.findById(id).orElseThrow(() -> new RuleNameNotFoundException("RuleName not found"));
    }

    @Override
    public RuleName save(RuleName ruleName) {
        return ruleNameRepository.save(ruleName);
    }

    @Override
    public RuleName update(RuleName ruleName) throws RuleNameNotFoundException {
        if (!ruleNameRepository.existsById(ruleName.getId())) {
            throw new RuleNameNotFoundException("RuleName not found");
        }
        return ruleNameRepository.save(ruleName);
    }

    @Override
    public void deleteById(Integer id) throws RuleNameNotFoundException {
        if (!ruleNameRepository.existsById(id)) {
            throw new RuleNameNotFoundException("RuleName not found");
        }
        ruleNameRepository.deleteById(id);
    }
}