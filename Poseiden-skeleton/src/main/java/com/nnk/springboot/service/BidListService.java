package com.nnk.springboot.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.exceptions.BidListNotFoundException;

@Service
public interface BidListService {

	List<BidList> findAll();
    BidList findById(Integer id) throws BidListNotFoundException;
    BidList save(BidList bidList);
    BidList update(BidList bidList) throws BidListNotFoundException;
    void deleteById(Integer id) throws BidListNotFoundException;
}
