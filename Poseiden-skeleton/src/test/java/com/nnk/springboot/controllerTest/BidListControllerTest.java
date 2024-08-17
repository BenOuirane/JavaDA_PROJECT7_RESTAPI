package com.nnk.springboot.controllerTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.ui.Model;

import com.nnk.springboot.controllers.BidListController;
import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.service.BidListService;

@SpringBootTest
@AutoConfigureMockMvc
public class BidListControllerTest {
	
	@Mock
    private BidListService bidListService;

    @Mock
    private Model model;
    
    @Autowired
    private MockMvc mockMvc;
    
    @InjectMocks
    private BidListController bidListController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
     //   mockMvc = MockMvcBuilders.standaloneSetup(bidListController)  .build();
                               
    }


    @Test
    public void testHome() {
        // Arrange
        List<BidList> bidLists = Arrays.asList(new BidList(), new BidList());
        when(bidListService.findAll()).thenReturn(bidLists);

        // Act
        String viewName = bidListController.home(model);

        // Assert
        verify(bidListService, times(1)).findAll();
        verify(model, times(1)).addAttribute("bidLists", bidLists);
        assertEquals("bidList/list", viewName);
    }
    
    @Test
    public void testAddBidForm() throws Exception {
        // Perform a GET request to the /bidList/add endpoint
        mockMvc.perform(MockMvcRequestBuilders.get("/bidList/add"))
            // Expect the status to be OK (200)
            .andExpect(MockMvcResultMatchers.status().isOk())
            // Expect the view name to be "bidList/add"
            .andExpect(MockMvcResultMatchers.view().name("bidList/add"))
            // Optionally, print the result for debugging
            .andDo(MockMvcResultHandlers.print());
    }
    
    @Test
    @WithMockUser(username = "user", roles = {"ADMIN"})
    void testValidate_Success() throws Exception {
    	mockMvc.perform(MockMvcRequestBuilders
    	        .post("/bidList/validate")
    	        .param("fieldName", "validValue"))
    	    .andDo(print()) // Print request and response details
    	    .andExpect(status().is3xxRedirection());

            }

    @Test
    @WithMockUser(username = "user", roles = {"ADMIN"})
    void testValidate_Failure() throws Exception {
        // Simulate validation error by returning invalid model attributes
        mockMvc.perform(MockMvcRequestBuilders
                .post("/bidList/validate")
                .param("fieldName", "invalidValue")) // Adjust parameters as needed
            .andExpect(status().isOk())
            .andExpect(view().name("bidList/add"));
    }
       
   

    
    @Test
    @WithMockUser(username = "user", roles = {"ADMIN"})
    public void testUpdateBid_withValidData_shouldRedirectToList() throws Exception {
        BidList validBidList = new BidList();
        validBidList.setAccount("Valid Account");
        validBidList.setType("Valid Type");
        validBidList.setBidQuantity(100.0);

        mockMvc.perform(post("/bidList/update/1")
                .flashAttr("bidList", validBidList)
        )
        .andExpect(status().is3xxRedirection())
        .andExpect(redirectedUrl("/bidList/list"));
    }

    
    @Test
    @WithMockUser(username = "user", roles = {"ADMIN"})
    public void testUpdateBid_withValidationErrors_shouldReturnUpdateView() throws Exception {
        BidList bidList = new BidList();
        // Valid data
        bidList.setAccount("Valid Account");
        bidList.setType("Valid Type");

        mockMvc.perform(post("/bidList/update/1")
                .flashAttr("bidList", bidList)
                .param("bindingResult", "true")  // Simulate binding result with errors
        )
        .andExpect(status().isOk())
        .andExpect(view().name("bidList/update"));
    }

    @Test
    @WithMockUser(username = "user", roles = {"ADMIN"})
    void deleteBid_Success() throws Exception {
        mockMvc.perform(get("/bidList/delete/1"))
               .andExpect(status().is3xxRedirection())
               .andExpect(redirectedUrl("/bidList/list"));

        verify(bidListService).deleteById(1);
    }

    @Test
    @WithMockUser(username = "user", roles = {"ADMIN"})
    void deleteBid_BidNotFound() throws Exception {
    //    doThrow(new BidNotFoundException("Bid not found")).when(bidListService).deleteById(anyInt());

        mockMvc.perform(get("/bidList/delete/1"))
               .andExpect(status().is3xxRedirection())
               .andExpect(redirectedUrl("/bidList/list")); // Adjust to reflect actual error handling

        verify(bidListService).deleteById(1);
    }
    
}