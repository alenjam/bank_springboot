package com.tortil.bankdetails.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name="branches")
public class Branch{
	@Id
	@Column(name="ifsc")
	private String ifscCode;

	@Column(name="branch")
	private String name;

	private String address;

	private String city;

	private String district;

	private String state;
	
	@ManyToOne
	@JoinColumn(name="bank_id", referencedColumnName="id")
	private Bank bank;

}
