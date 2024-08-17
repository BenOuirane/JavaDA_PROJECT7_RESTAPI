package com.nnk.springboot.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.nnk.springboot.domain.CurvePoint;

@Service
public interface CurvePointService {
	
	List<CurvePoint> findAll();
    Optional<CurvePoint> findById(Integer id);
    CurvePoint save(CurvePoint curvePoint);
    void deleteById(Integer id);

}
