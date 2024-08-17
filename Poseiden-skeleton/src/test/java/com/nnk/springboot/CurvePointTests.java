package com.nnk.springboot;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.repositories.CurvePointRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CurvePointTests {

    @Autowired
    private CurvePointRepository curvePointRepository;

    @Transactional
    @Test
    public void curvePointTest() {
        CurvePoint curvePoint = new CurvePoint(10, 10d, 30d);

        // Save
        curvePoint = curvePointRepository.save(curvePoint);
        Assertions.assertNotNull(curvePoint.getId());

        // Update
        curvePoint.setTerm(40d);
        curvePoint = curvePointRepository.save(curvePoint);
        Assertions.assertEquals(40d, curvePoint.getTerm());

        // Find
        List<CurvePoint> listResult = curvePointRepository.findAll();
        Assertions.assertTrue(listResult.size() > 0);

        // Delete
        Integer id = curvePoint.getId();
        curvePointRepository.delete(curvePoint);
        Optional<CurvePoint> curvePointList = curvePointRepository.findById(id);
        Assertions.assertFalse(curvePointList.isPresent());
    }
}
