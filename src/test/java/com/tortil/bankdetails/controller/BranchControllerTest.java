package com.tortil.bankdetails.controller;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.tortil.bankdetails.dto.BranchDTO;
import com.tortil.bankdetails.exceptions.BranchNotFoundException;
import com.tortil.bankdetails.service.BranchService;

@RunWith(SpringRunner.class)
@WebMvcTest(BranchController.class)
class BranchControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BranchService branchService;

    // Helper method to create a sample BranchDTO for testing
    private BranchDTO createSampleBranchDTO() {
        return new BranchDTO("ABC123", "Test Branch", "Test Address", "Test City", "Test District", "Test State", "Test Bank");
    }

    @Test
    void testGetBranchByIfscCode_Success() throws Exception {
        String ifscCode = "ABC123";
        BranchDTO branchDTO = createSampleBranchDTO();

        doReturn(ResponseEntity.ok(branchDTO)).when(branchService).getBranch(ifscCode);

        mockMvc.perform(get("/banks/{ifscCode}", ifscCode))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.ifscCode").value(branchDTO.getIfscCode()))
                .andExpect(jsonPath("$.branch").value(branchDTO.getBranch()))
                .andExpect(jsonPath("$.address").value(branchDTO.getAddress()))
                .andExpect(jsonPath("$.city").value(branchDTO.getCity()))
                .andExpect(jsonPath("$.district").value(branchDTO.getDistrict()))
                .andExpect(jsonPath("$.state").value(branchDTO.getState()))
                .andExpect(jsonPath("$.bank").value(branchDTO.getBank()));
    }

    @Test
    void testGetBranchByCity_Success() throws Exception {
        String city = "Test City";
        BranchDTO branchDTO = createSampleBranchDTO();
        List<BranchDTO> branchDTOList = Collections.singletonList(branchDTO);

        doReturn(ResponseEntity.ok(branchDTOList)).when(branchService).getBranchByCity(city);

        mockMvc.perform(get("/banks/city/{city}", city))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].ifscCode").value(branchDTO.getIfscCode()))
                .andExpect(jsonPath("$[0].branch").value(branchDTO.getBranch()))
                .andExpect(jsonPath("$[0].address").value(branchDTO.getAddress()))
                .andExpect(jsonPath("$[0].city").value(branchDTO.getCity()))
                .andExpect(jsonPath("$[0].district").value(branchDTO.getDistrict()))
                .andExpect(jsonPath("$[0].state").value(branchDTO.getState()))
                .andExpect(jsonPath("$[0].bank").value(branchDTO.getBank()));
    }

    @Test
    void testGetBranchByIfscCode_NotFound() throws Exception {
        String ifscCode = "InvalidIfscCode";
        when(branchService.getBranch(ifscCode)).thenThrow(new BranchNotFoundException("Branch not found for IFSC code: " + ifscCode, 432));

        mockMvc.perform(get("/banks/{ifscCode}", ifscCode))
                .andExpect(status().isNotFound());
    }

    @Test
    void testGetBranchByCity_NotFound() throws Exception {
        String city = "NonExistentCity";
        when(branchService.getBranchByCity(city)).thenThrow(new BranchNotFoundException("No Bank Branches are present in the city: " + city, 434));

        mockMvc.perform(get("/banks/city/{city}", city))
                .andExpect(status().isNotFound());
    }
}

