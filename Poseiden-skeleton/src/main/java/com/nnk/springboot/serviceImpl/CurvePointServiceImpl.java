package com.nnk.springboot.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.repositories.CurvePointRepository;
import com.nnk.springboot.service.CurvePointService;

@Service
public class CurvePointServiceImpl implements CurvePointService {
	
	    @Autowired
	    private CurvePointRepository curvePointRepository;

	    @Override
	    public List<CurvePoint> findAll() {
	        return curvePointRepository.findAll();
	    }

	    @Override
	    public Optional<CurvePoint> findById(Integer id) {
	        return curvePointRepository.findById(id);
	    }

	    @Override
	    public CurvePoint save(CurvePoint curvePoint) {
	        return curvePointRepository.save(curvePoint);
	    }

	    @Override
	    public void deleteById(Integer id) {
	        curvePointRepository.deleteById(id);
	    }

}
