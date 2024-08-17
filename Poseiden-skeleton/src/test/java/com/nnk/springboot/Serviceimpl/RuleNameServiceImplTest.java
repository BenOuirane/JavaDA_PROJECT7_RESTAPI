package com.nnk.springboot.Serviceimpl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.exceptions.RuleNameNotFoundException;
import com.nnk.springboot.repositories.RuleNameRepository;
import com.nnk.springboot.serviceImpl.RuleNameServiceImpl;

@SpringBootTest
public class RuleNameServiceImplTest {

    @Mock
    private RuleNameRepository ruleNameRepository;

    @InjectMocks
    private RuleNameServiceImpl ruleNameService;

    private RuleName ruleName;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        ruleName = new RuleName();
        ruleName.setId(1);
        ruleName.setName("Rule 1");
    }

    @Test
    public void testFindAll() {
        List<RuleName> ruleNames = Arrays.asList(ruleName);
        when(ruleNameRepository.findAll()).thenReturn(ruleNames);

        List<RuleName> result = ruleNameService.findAll();
        assertEquals(1, result.size());
        verify(ruleNameRepository, times(1)).findAll();
    }

    @Test
    public void testFindByIdSuccess() throws RuleNameNotFoundException {
        when(ruleNameRepository.findById(1)).thenReturn(Optional.of(ruleName));

        RuleName result = ruleNameService.findById(1);
        assertNotNull(result);
        assertEquals("Rule 1", result.getName());
        verify(ruleNameRepository, times(1)).findById(1);
    }

    @Test
    public void testFindByIdNotFound() {
        when(ruleNameRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(RuleNameNotFoundException.class, () -> {
            ruleNameService.findById(1);
        });
        verify(ruleNameRepository, times(1)).findById(1);
    }

    @Test
    public void testSave() {
        when(ruleNameRepository.save(ruleName)).thenReturn(ruleName);

        RuleName result = ruleNameService.save(ruleName);
        assertNotNull(result);
        assertEquals("Rule 1", result.getName());
        verify(ruleNameRepository, times(1)).save(ruleName);
    }

    @Test
    public void testUpdateSuccess() throws RuleNameNotFoundException {
        when(ruleNameRepository.existsById(ruleName.getId())).thenReturn(true);
        when(ruleNameRepository.save(ruleName)).thenReturn(ruleName);

        RuleName result = ruleNameService.update(ruleName);
        assertNotNull(result);
        assertEquals("Rule 1", result.getName());
        verify(ruleNameRepository, times(1)).save(ruleName);
        verify(ruleNameRepository, times(1)).existsById(ruleName.getId());
    }

    @Test
    public void testUpdateNotFound() {
        when(ruleNameRepository.existsById(ruleName.getId())).thenReturn(false);

        assertThrows(RuleNameNotFoundException.class, () -> {
            ruleNameService.update(ruleName);
        });
        verify(ruleNameRepository, times(1)).existsById(ruleName.getId());
        verify(ruleNameRepository, never()).save(any(RuleName.class));
    }

    @Test
    public void testDeleteByIdSuccess() throws RuleNameNotFoundException {
        when(ruleNameRepository.existsById(1)).thenReturn(true);

        ruleNameService.deleteById(1);
        verify(ruleNameRepository, times(1)).deleteById(1);
        verify(ruleNameRepository, times(1)).existsById(1);
    }

    @Test
    public void testDeleteByIdNotFound() {
        when(ruleNameRepository.existsById(1)).thenReturn(false);

        assertThrows(RuleNameNotFoundException.class, () -> {
            ruleNameService.deleteById(1);
        });
        verify(ruleNameRepository, times(1)).existsById(1);
        verify(ruleNameRepository, never()).deleteById(1);
    }
}
