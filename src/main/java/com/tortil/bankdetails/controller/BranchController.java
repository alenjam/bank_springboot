package com.tortil.bankdetails.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tortil.bankdetails.service.BranchService;

@RestController
@RequestMapping("/banks")
public class BranchController {
	
	@Autowired
	private BranchService branchService;
	
	@GetMapping("/{ifscCode}")
	public ResponseEntity<?> getBranchByIfscCode(@PathVariable String ifscCode) {
		return branchService.getBranch(ifscCode);
	}
	
	@GetMapping("/city/{city}")
	public ResponseEntity<?> getBranchByCity(@PathVariable String city) {
		return branchService.getBranchByCity(city);
	}
}
