package com.nnk.springboot.serviceImpl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.exceptions.TradeNotFoundException;
import com.nnk.springboot.repositories.TradeRepository;
import com.nnk.springboot.service.TradeService;

@Service
public class TradeServiceImpl implements TradeService {

    @Autowired
    private TradeRepository tradeRepository;

    @Override
    public List<Trade> findAll() {
        return tradeRepository.findAll();
    }

    @Override
    public Trade findById(Integer id) throws TradeNotFoundException {
        return tradeRepository.findById(id).orElseThrow(() -> new TradeNotFoundException("Trade not found"));
    }

    @Override
    public Trade save(Trade trade) {
        return tradeRepository.save(trade);
    }

    @Override
    public Trade update(Trade trade) throws TradeNotFoundException {
        if (!tradeRepository.existsById(trade.getId())) {
            throw new TradeNotFoundException("Trade not found");
        }
        return tradeRepository.save(trade);
    }

    @Override
    public void deleteById(Integer id) throws TradeNotFoundException {
        if (!tradeRepository.existsById(id)) {
            throw new TradeNotFoundException("Trade not found");
        }
        tradeRepository.deleteById(id);
    }
}