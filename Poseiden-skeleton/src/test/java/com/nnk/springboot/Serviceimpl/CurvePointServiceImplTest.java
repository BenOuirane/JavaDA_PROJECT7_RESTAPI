package com.nnk.springboot.Serviceimpl;

import org.junit.Before;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.*;

import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.repositories.CurvePointRepository;
import com.nnk.springboot.serviceImpl.CurvePointServiceImpl;


@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CurvePointServiceImplTest {

    @InjectMocks
    private CurvePointServiceImpl curvePointServiceImpl;

    @Mock
    private CurvePointRepository curvePointRepository;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    
    @Test
    public void testFindAll() {
        // Arrange
        List<CurvePoint> expectedCurvePoints = new ArrayList<>();
        expectedCurvePoints.add(new CurvePoint());
        expectedCurvePoints.add(new CurvePoint());
        when(curvePointRepository.findAll()).thenReturn(expectedCurvePoints);

        // Act
        List<CurvePoint> actualCurvePoints = curvePointServiceImpl.findAll();

        // Assert
        assertNotNull(actualCurvePoints);
        assertEquals(expectedCurvePoints.size(), actualCurvePoints.size());
        assertTrue(expectedCurvePoints.containsAll(actualCurvePoints));
        assertTrue(actualCurvePoints.containsAll(expectedCurvePoints));
    }

    @Test
    public void testFindById_Found() {
        Integer id = 1;
        CurvePoint curvePoint = new CurvePoint();
        curvePoint.setId(id);

        when(curvePointRepository.findById(id)).thenReturn(Optional.of(curvePoint));

        Optional<CurvePoint> result = curvePointServiceImpl.findById(id);

        assertTrue(result.isPresent());
        assertEquals(id, result.get().getId());
    }

    @Test
    public void testFindById_NotFound() {
        Integer id = 1;

        when(curvePointRepository.findById(id)).thenReturn(Optional.empty());

        Optional<CurvePoint> result = curvePointServiceImpl.findById(id);

        assertFalse(result.isPresent());
    }
    
    @Test
    public void testSave() {
        // Given
        CurvePoint curvePoint = new CurvePoint();
        curvePoint.setId(1);
        curvePoint.setTerm(1.2);


        // When
        when(curvePointRepository.save(curvePoint)).thenReturn(curvePoint);

        // Act
        CurvePoint result = curvePointServiceImpl.save(curvePoint);

        // Assert
        verify(curvePointRepository).save(curvePoint);
        assertNotNull(result);
        assertEquals(curvePoint.getId(), result.getId());
        assertEquals(curvePoint.getTerm(), result.getTerm());
    }
    
    @Test
    public void testDeleteById() {
        // Arrange
        Integer idToDelete = 1;

        // Act
        curvePointServiceImpl.deleteById(idToDelete);

        // Assert
        // Verify that deleteById was called with the correct id
        verify(curvePointRepository).deleteById(idToDelete);
    }
    
    
}
