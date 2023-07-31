package com.tortil.bankdetails.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.tortil.bankdetails.dto.BranchDTO;
import com.tortil.bankdetails.exceptions.BranchNotFoundException;
import com.tortil.bankdetails.model.Branch;
import com.tortil.bankdetails.repository.BranchRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class BranchService {

	@Autowired
	private BranchRepository branchRepository;

	public BranchDTO convertEntityToDTO(Branch branch) {
		return new BranchDTO(branch.getIfscCode(), branch.getName(), branch.getAddress(), branch.getCity(),
				branch.getDistrict(), branch.getState(), branch.getBank().getName());
	}

	public ResponseEntity<?> getBranch(String ifscCode) {
		Branch branch = branchRepository.findById(ifscCode)
				.orElseThrow(() -> new BranchNotFoundException("Branch not found for IFSC code: " + ifscCode, 404));

		BranchDTO branchDTO = convertEntityToDTO(branch);
		return ResponseEntity.ok().body(branchDTO);
	}

	public ResponseEntity<?> getBranchByCity(String city) {
		List<Branch> branches = branchRepository.findByCity(city);
		if (branches.isEmpty()) {
			throw new BranchNotFoundException("No Bank Branches are present in the city: " + city, 404);
		}

		List<BranchDTO> branchDTOList = branches.stream().map(this::convertEntityToDTO).toList();
		return ResponseEntity.ok().body(branchDTOList);
	}

}
