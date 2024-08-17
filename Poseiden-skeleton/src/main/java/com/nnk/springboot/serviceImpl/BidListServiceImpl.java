package com.nnk.springboot.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.exceptions.BidListNotFoundException;
import com.nnk.springboot.repositories.BidListRepository;
import com.nnk.springboot.service.BidListService;

@Service
public class BidListServiceImpl implements BidListService{
	
	@Autowired
    private BidListRepository bidListRepository;

    @Override
    public List<BidList> findAll() {
        return bidListRepository.findAll();
    }

    @Override
    public BidList findById(Integer id) throws BidListNotFoundException {
        return bidListRepository.findById(id).orElseThrow(() -> new BidListNotFoundException("BidList not found"));
    }

    @Override
    public BidList save(BidList bidList) {
        return bidListRepository.save(bidList);
    }

 
    @Override
    public BidList update(BidList bidList) throws BidListNotFoundException {
        if (!bidListRepository.existsById(bidList.getId())) {
            throw new BidListNotFoundException("BidList not found");
        }
        return bidListRepository.save(bidList);
    }

    @Override
    public void deleteById(Integer id) throws BidListNotFoundException {
        if (!bidListRepository.existsById(id)) {
            throw new BidListNotFoundException("BidList not found");
        }
        bidListRepository.deleteById(id);
    }

}
