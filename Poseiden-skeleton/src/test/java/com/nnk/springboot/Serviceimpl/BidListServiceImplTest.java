package com.nnk.springboot.Serviceimpl;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.*;

import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.exceptions.BidListNotFoundException;
import com.nnk.springboot.repositories.BidListRepository;
import com.nnk.springboot.serviceImpl.BidListServiceImpl;



@RunWith(MockitoJUnitRunner.class)
public class BidListServiceImplTest {

	@Mock
    private BidListRepository bidListRepository;

    @InjectMocks
    private BidListServiceImpl bidListService; // Assuming the method is in BidListService

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindByIdWhenBidListExists() throws BidListNotFoundException {
        // Given
        Integer id = 1;
        BidList bidList = new BidList();
        when(bidListRepository.findById(id)).thenReturn(Optional.of(bidList));

        // When
        BidList result = bidListService.findById(id);

        // Then
        assertNotNull(result);
        assertEquals(bidList, result);
    }

    @Test
    public void testFindByIdWhenBidListDoesNotExist() {
        // Given
        Integer id = 1;
        when(bidListRepository.findById(id)).thenReturn(Optional.empty());

        // When / Then
        BidListNotFoundException thrown = assertThrows(
            BidListNotFoundException.class,
            () -> bidListService.findById(id),
            "Expected findById() to throw, but it didn't"
        );
        assertEquals("BidList not found", thrown.getMessage());
    }
    
    @Test
    public void testFindAll() {
        // Arrange
        BidList bid1 = new BidList();
        bid1.setId(1);
        BidList bid2 = new BidList();
        bid2.setId(2);
        List<BidList> expectedBids = Arrays.asList(bid1, bid2);

        when(bidListRepository.findAll()).thenReturn(expectedBids);

        // Act
        List<BidList> actualBids = bidListService.findAll();

        // Assert
        assertNotNull("The returned list should not be null", actualBids);
        assertEquals("The size of the list should be 2", 2, actualBids.size());
        assertEquals("The lists should be equal", expectedBids, actualBids);
        verify(bidListRepository, times(1)).findAll();
    }
    
    @Test
    public void testSave() {
        // Given
        BidList bidList = new BidList();
        when(bidListRepository.save(bidList)).thenReturn(bidList);

        // When
        BidList result = bidListService.save(bidList);

        // Then
        assertNotNull(result);
        assertEquals(bidList, result);
        verify(bidListRepository, times(1)).save(bidList);
    }
    
    @Test
    public void testUpdateWhenBidListExists() throws BidListNotFoundException {
        // Given
        BidList bidList = new BidList();
        bidList.setId(1); // Assume the BidList has an ID of 1
        when(bidListRepository.existsById(bidList.getId())).thenReturn(true);
        when(bidListRepository.save(bidList)).thenReturn(bidList);

        // When
        BidList result = bidListService.update(bidList);

        // Then
        assertNotNull(result);
        assertEquals(bidList, result);
        verify(bidListRepository, times(1)).existsById(bidList.getId());
        verify(bidListRepository, times(1)).save(bidList);
    }

    @Test
    public void testUpdateWhenBidListDoesNotExist() {
        // Given
        BidList bidList = new BidList();
        bidList.setId(1); // Assume the BidList has an ID of 1
        when(bidListRepository.existsById(bidList.getId())).thenReturn(false);

        // When / Then
        BidListNotFoundException thrown = assertThrows(
            BidListNotFoundException.class,
            () -> bidListService.update(bidList),
            "Expected update() to throw, but it didn't"
        );
        assertEquals("BidList not found", thrown.getMessage());
        verify(bidListRepository, times(1)).existsById(bidList.getId());
        verify(bidListRepository, never()).save(bidList);
    }
    
    @Test
    public void testDeleteByIdNotFound() {
        // Arrange
        Integer id = 1;
        when(bidListRepository.existsById(id)).thenReturn(false);

        // Act & Assert
        BidListNotFoundException thrown = assertThrows(BidListNotFoundException.class, () -> {
            bidListService.deleteById(id);
        });
        assertEquals("BidList not found", thrown.getMessage());
    }

    @Test
    public void testDeleteByIdFound() {
        // Arrange
        Integer id = 1;
        when(bidListRepository.existsById(id)).thenReturn(true);

        // Act
        bidListService.deleteById(id);

        // Assert
        verify(bidListRepository, times(1)).deleteById(id);
    }
    
    
}
