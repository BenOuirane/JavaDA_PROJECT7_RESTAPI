package com.nnk.springboot.seeds;

import java.sql.Timestamp;
import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.repositories.CurvePointRepository;

@Component
public class CurvePointDataLoader implements CommandLineRunner {

    @Autowired
    private CurvePointRepository curvePointRepository;

    @Override
    public void run(String... args) throws Exception {
        loadCurvePointData();
    }

    private void loadCurvePointData() {
        if (curvePointRepository.count() == 0) {
            CurvePoint curvePoint1 = new CurvePoint( 1, Timestamp.from(Instant.now()), 10.5, 100.0, Timestamp.from(Instant.now()));
            CurvePoint curvePoint2 = new CurvePoint( 2, Timestamp.from(Instant.now()), 20.5, 200.0, Timestamp.from(Instant.now()));
            CurvePoint curvePoint3 = new CurvePoint( 3, Timestamp.from(Instant.now()), 30.5, 300.0, Timestamp.from(Instant.now()));

            curvePointRepository.save(curvePoint1);
            curvePointRepository.save(curvePoint2);
            curvePointRepository.save(curvePoint3);

          //  System.out.println("Sample curve point data loaded.");
        } else {
          //  System.out.println("Curve point data already exists.");
        }
    }
}