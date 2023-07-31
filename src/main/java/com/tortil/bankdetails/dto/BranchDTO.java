package com.tortil.bankdetails.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BranchDTO {

	private String ifscCode;

	private String branch;

	private String address;

	private String city;

	private String district;

	private String state;

	private String bank;
}
