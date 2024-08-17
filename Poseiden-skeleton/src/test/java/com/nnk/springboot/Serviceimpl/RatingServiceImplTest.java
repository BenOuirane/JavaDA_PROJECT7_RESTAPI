package com.nnk.springboot.Serviceimpl;

import org.junit.Before;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
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

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.exceptions.RatingNotFoundException;
import com.nnk.springboot.repositories.RatingRepository;
import com.nnk.springboot.serviceImpl.RatingServiceImpl;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class RatingServiceImplTest {

    @Mock
    private RatingRepository ratingRepository;

    @InjectMocks
    private RatingServiceImpl ratingService;

    private Rating rating1;
    private Rating rating2;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        rating1 = new Rating(1, "Moodys A", "S&P A", "Fitch A", 1);
        rating2 = new Rating(2, "Moodys B", "S&P B", "Fitch B", 2);
    }

    
    @Test
    public void testGetAllRatings() {
        when(ratingRepository.findAll()).thenReturn(Arrays.asList(rating1, rating2));

        List<Rating> ratings = ratingService.getAllRatings();

        assertEquals(2, ratings.size());
        verify(ratingRepository, times(1)).findAll();
    }

  
    @Test
    public void testGetRatingById() throws RatingNotFoundException {
        rating1 = new Rating(1, "Moodys A", "S&P A", "Fitch A", 1);  // Initialisation
        when(ratingRepository.findById(1)).thenReturn(Optional.of(rating1));

        Rating foundRating = ratingService.getRatingById(1);

        assertNotNull(foundRating);
        assertEquals("Moodys A", foundRating.getMoodysRating());
        verify(ratingRepository, times(1)).findById(1);
    }
    

    @Test
    public void testSaveRating() {
        // Arrange
        Rating rating = new Rating();
        rating.setId(1);
        rating.setMoodysRating("Aa2");
        rating.setSandPRating("AA");
        rating.setFitchRating("AA+");
        rating.setOrderNumber(10);

        when(ratingRepository.save(rating)).thenReturn(rating);

        // Act
        Rating savedRating = ratingService.saveRating(rating);

        // Assert
        assertEquals(1, savedRating.getId());
        assertEquals("Aa2", savedRating.getMoodysRating());
        assertEquals("AA", savedRating.getSandPRating());
        assertEquals("AA+", savedRating.getFitchRating());
        assertEquals(10, savedRating.getOrderNumber());
    }
  
    @Test
    public void testUpdateRating() throws RatingNotFoundException {
        // Arrange
        Integer id = 1;
        Rating existingRating = new Rating();
        existingRating.setId(id);
        existingRating.setMoodysRating("Aa2");
        existingRating.setSandPRating("AA");
        existingRating.setFitchRating("AA-");
        existingRating.setOrderNumber(1);

        Rating updatedRating = new Rating();
        updatedRating.setMoodysRating("Aa1");
        updatedRating.setSandPRating("AA+");
        updatedRating.setFitchRating("AAA");
        updatedRating.setOrderNumber(2);

        when(ratingRepository.findById(id)).thenReturn(Optional.of(existingRating));
        when(ratingRepository.save(existingRating)).thenReturn(existingRating);

        // Act
        Rating result = ratingService.updateRating(id, updatedRating);

        // Assert
        assertEquals(updatedRating.getMoodysRating(), result.getMoodysRating());
        assertEquals(updatedRating.getSandPRating(), result.getSandPRating());
        assertEquals(updatedRating.getFitchRating(), result.getFitchRating());
        assertEquals(updatedRating.getOrderNumber(), result.getOrderNumber());

        verify(ratingRepository).save(existingRating);
    }

    @Test
    public void testUpdateRatingNotFound() {
        // Arrange
        Integer id = 1;
        Rating updatedRating = new Rating();
        updatedRating.setMoodysRating("Aa1");
        updatedRating.setSandPRating("AA+");
        updatedRating.setFitchRating("AAA");
        updatedRating.setOrderNumber(2);

        when(ratingRepository.findById(id)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(RatingNotFoundException.class, () -> {
            ratingService.updateRating(id, updatedRating);
        });
    }
    
    @Test
    public void testDeleteRating() throws RatingNotFoundException {
        // Arrange
        Integer id = 1;
        Rating rating = new Rating();
        rating.setId(id);
        rating.setMoodysRating("Aa2");
        rating.setSandPRating("AA");
        rating.setFitchRating("AA-");
        rating.setOrderNumber(1);

        when(ratingRepository.findById(id)).thenReturn(Optional.of(rating));
        doNothing().when(ratingRepository).delete(rating);

        // Act
        ratingService.deleteRating(id);

        // Assert
        verify(ratingRepository).delete(rating);
    }

    @Test
    public void testDeleteRatingNotFound() {
        // Arrange
        Integer id = 1;

        when(ratingRepository.findById(id)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(RatingNotFoundException.class, () -> {
            ratingService.deleteRating(id);
        });
    }
    
}
