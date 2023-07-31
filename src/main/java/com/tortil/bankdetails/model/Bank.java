package com.tortil.bankdetails.model;

import java.util.List;

import io.micrometer.common.lang.NonNull;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name="banks")
public class Bank {
	@Id
	private Long id;

	@NonNull
	private String name;

	@OneToMany(mappedBy = "bank")
	private List<Branch> branches;

}
