package com.nnk.springboot;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.repositories.TradeRepository;

import java.util.List;
import java.util.Optional;


@SpringBootTest
public class TradeTests {

    @Autowired
    private TradeRepository tradeRepository;

    @Transactional
    @Test
    public void tradeTest() {
            // Create Trade with all required fields
            Trade trade = new Trade();
            trade.setAccount("Trade Account");
            trade.setType("Type");
            trade.setBuyQuantity(100.0); // Set the buyQuantity

            // Save
            trade = tradeRepository.save(trade);
            Assert.assertNotNull(trade.getId());
            Assert.assertEquals("Trade Account", trade.getAccount());

            // Update
            trade.setAccount("Trade Account Update");
            trade = tradeRepository.save(trade);
            Assert.assertEquals("Trade Account Update", trade.getAccount());

            // Find
            List<Trade> listResult = tradeRepository.findAll();
            Assert.assertTrue(listResult.size() > 0);

            // Delete
            Integer id = trade.getId();
            tradeRepository.delete(trade);
            Optional<Trade> tradeList = tradeRepository.findById(id);
            Assert.assertFalse(tradeList.isPresent());
        }
    
    }

