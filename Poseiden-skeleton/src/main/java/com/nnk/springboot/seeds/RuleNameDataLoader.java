package com.nnk.springboot.seeds;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.repositories.RuleNameRepository;

@Component
public class RuleNameDataLoader implements CommandLineRunner {

    @Autowired
    private RuleNameRepository ruleNameRepository;

    @Override
    public void run(String... args) throws Exception {
        loadRuleNameData();
    }

    private void loadRuleNameData() {
        if (ruleNameRepository.count() == 0) {
            RuleName rule1 = new RuleName(1, "Rule1", "Description1", "Json1", "Template1", "SqlStr1", "SqlPart1");
            RuleName rule2 = new RuleName(2, "Rule2", "Description2", "Json2", "Template2", "SqlStr2", "SqlPart2");
            RuleName rule3 = new RuleName(3, "Rule3", "Description3", "Json3", "Template3", "SqlStr3", "SqlPart3");

            ruleNameRepository.save(rule1);
            ruleNameRepository.save(rule2);
            ruleNameRepository.save(rule3);

            System.out.println("Sample rule names data loaded.");
        } else {
            System.out.println("Rule names data already exists.");
        }
    }
}