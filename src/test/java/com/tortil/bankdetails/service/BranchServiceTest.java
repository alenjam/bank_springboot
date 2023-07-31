package com.tortil.bankdetails.service;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.tortil.bankdetails.dto.BranchDTO;
import com.tortil.bankdetails.exceptions.BranchNotFoundException;
import com.tortil.bankdetails.model.Bank;
import com.tortil.bankdetails.model.Branch;
import com.tortil.bankdetails.repository.BranchRepository;

@MockitoSettings(strictness = Strictness.LENIENT)
@ExtendWith(MockitoExtension.class)
class BranchServiceTest {

	@InjectMocks
	private BranchService branchService;

	@Mock
	Branch branch;
	@Mock
	Bank bank;

	@Mock
	private BranchRepository repo;

	@BeforeEach
	void setup() {
		// Arrange
		when(branch.getIfscCode()).thenReturn("ABC123");
		when(branch.getName()).thenReturn("Test Branch");
		when(branch.getCity()).thenReturn("Test City");
		when(branch.getDistrict()).thenReturn("Test District");
		when(branch.getState()).thenReturn("Test State");
		when(branch.getAddress()).thenReturn("Test Address");

		when(bank.getName()).thenReturn("Test Bank");
		when(branch.getBank()).thenReturn(bank);
	}

	@Test
	void testConvertEntityToDTO() {

		// Act
		BranchDTO dto = branchService.convertEntityToDTO(branch);

		// Assert
		assertDTOMatchesBranch(branch, dto);
	}

	void assertDTOMatchesBranch(Branch branch, BranchDTO dto) {
		assertEquals(branch.getIfscCode(), dto.getIfscCode());
		assertEquals(branch.getName(), dto.getBranch());
		assertEquals(branch.getBank().getName(), dto.getBank());
		assertEquals(branch.getAddress(), dto.getAddress());
		assertEquals(branch.getCity(), dto.getCity());
		assertEquals(branch.getDistrict(), dto.getDistrict());
		assertEquals(branch.getState(), dto.getState());
	}

	@Test
	void testGetBranchSuccess() {

	  //Arrange
	  when(repo.findById("ABC123")).thenReturn(Optional.of(branch));

	  //Act
	  ResponseEntity<?> response = branchService.getBranch("ABC123");

	  //Assert
	  assertEquals(HttpStatus.OK, response.getStatusCode());
	  
	  BranchDTO dto = (BranchDTO) response.getBody();

	  assertDTOMatchesBranch(branch, dto);

	}

	@Test
	void testGetBranchNotFound() {

	  when(repo.findById("invalid")).thenReturn(Optional.empty());
	  
	  assertThrows(BranchNotFoundException.class , () -> {
	    branchService.getBranch("invalid"); 
	  });

	}

	@Test
	void testgetBranchByCitySuccess() {

		// Arrange
		List<Branch> branches = Collections.singletonList(branch);
		when(repo.findByCity("Test City")).thenReturn(branches);

		// Act
		ResponseEntity<?> response = branchService.getBranchByCity("Test City");

		// Assert
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertNotNull(response.getBody());
        assertTrue(response.getBody() instanceof List);

		List<BranchDTO> branchDTOList = (List<BranchDTO>) response.getBody();
        assertEquals(1, branchDTOList.size());

        BranchDTO branchDTO = branchDTOList.get(0);
        assertDTOMatchesBranch(branch, branchDTO);

	}

	@Test
	void testgetBranchByCityNotFound() {

	  when(repo.findByCity("invalid")).thenReturn(Collections.emptyList());
	  
	  BranchNotFoundException exception = assertThrows(BranchNotFoundException.class,
              () -> branchService.getBranchByCity("invalid"));
	  assertEquals("No Bank Branches are present in the city: " + "invalid", exception.getMessage());
      assertEquals(404, exception.getErrorCode());

	}

}
