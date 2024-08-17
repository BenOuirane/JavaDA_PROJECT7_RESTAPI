package com.nnk.springboot.Serviceimpl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.exceptions.TradeNotFoundException;
import com.nnk.springboot.repositories.TradeRepository;
import com.nnk.springboot.serviceImpl.TradeServiceImpl;

import java.util.Optional;
import java.util.Arrays;
import java.util.List;


@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TradeServiceImplTest {

    @InjectMocks
    private TradeServiceImpl tradeService;

    @Mock
    private TradeRepository tradeRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindAll() {
        // Given
        Trade trade1 = new Trade();
        Trade trade2 = new Trade();
        when(tradeRepository.findAll()).thenReturn(Arrays.asList(trade1, trade2));

        // When
        List<Trade> result = tradeService.findAll();

        // Then
        assertEquals(2, result.size());
        verify(tradeRepository, times(1)).findAll();
    }

    @Test
    void testFindById_TradeExists() throws TradeNotFoundException {
        // Given
        Trade trade = new Trade();
        when(tradeRepository.findById(1)).thenReturn(Optional.of(trade));

        // When
        Trade result = tradeService.findById(1);

        // Then
        assertNotNull(result);
        assertEquals(trade, result);
        verify(tradeRepository, times(1)).findById(1);
    }

    @Test
    void testFindById_TradeNotFound() {
        // Given
        when(tradeRepository.findById(1)).thenReturn(Optional.empty());

        // When & Then
        assertThrows(TradeNotFoundException.class, () -> tradeService.findById(1));
        verify(tradeRepository, times(1)).findById(1);
    }

    @Test
    void testSave() {
        // Given
        Trade trade = new Trade();
        when(tradeRepository.save(trade)).thenReturn(trade);

        // When
        Trade result = tradeService.save(trade);

        // Then
        assertNotNull(result);
        assertEquals(trade, result);
        verify(tradeRepository, times(1)).save(trade);
    }

    @Test
    void testUpdate_TradeExists() throws TradeNotFoundException {
        // Given
        Trade trade = new Trade();
        trade.setId(1);
        when(tradeRepository.existsById(trade.getId())).thenReturn(true);
        when(tradeRepository.save(trade)).thenReturn(trade);

        // When
        Trade result = tradeService.update(trade);

        // Then
        assertNotNull(result);
        assertEquals(trade, result);
        verify(tradeRepository, times(1)).existsById(trade.getId());
        verify(tradeRepository, times(1)).save(trade);
    }

    @Test
    void testUpdate_TradeNotFound() {
        // Given
        Trade trade = new Trade();
        trade.setId(1);
        when(tradeRepository.existsById(trade.getId())).thenReturn(false);

        // When & Then
        assertThrows(TradeNotFoundException.class, () -> tradeService.update(trade));
        verify(tradeRepository, times(1)).existsById(trade.getId());
    }

    @Test
    void testDeleteById_TradeExists() throws TradeNotFoundException {
        // Given
        when(tradeRepository.existsById(1)).thenReturn(true);

        // When
        tradeService.deleteById(1);

        // Then
        verify(tradeRepository, times(1)).existsById(1);
        verify(tradeRepository, times(1)).deleteById(1);
    }

    @Test
    void testDeleteById_TradeNotFound() {
        // Given
        when(tradeRepository.existsById(1)).thenReturn(false);

        // When & Then
        assertThrows(TradeNotFoundException.class, () -> tradeService.deleteById(1));
        verify(tradeRepository, times(1)).existsById(1);
        verify(tradeRepository, times(0)).deleteById(1);
    }
}
