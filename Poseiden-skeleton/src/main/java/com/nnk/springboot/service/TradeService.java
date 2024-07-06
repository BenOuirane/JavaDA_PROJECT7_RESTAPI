package com.nnk.springboot.service;

import java.util.List;
import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.exceptions.TradeNotFoundException;

public interface TradeService {
	
	List<Trade> findAll();
    Trade findById(Integer id) throws TradeNotFoundException;
    Trade save(Trade trade);
    Trade update(Trade trade) throws TradeNotFoundException;
    void deleteById(Integer id) throws TradeNotFoundException;

}
