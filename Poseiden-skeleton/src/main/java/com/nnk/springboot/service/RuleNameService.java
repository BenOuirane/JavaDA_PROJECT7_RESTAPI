package com.nnk.springboot.service;

import java.util.List;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.exceptions.RuleNameNotFoundException;

public interface RuleNameService {
	
	List<RuleName> findAll();
    RuleName findById(Integer id) throws RuleNameNotFoundException;
    RuleName save(RuleName ruleName);
    RuleName update(RuleName ruleName) throws RuleNameNotFoundException;
    void deleteById(Integer id) throws RuleNameNotFoundException;

}
