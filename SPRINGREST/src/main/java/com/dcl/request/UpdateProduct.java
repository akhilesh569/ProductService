package com.dcl.request;

import lombok.Data;

@Data
public class UpdateProduct {

	private String productName;
	
	private Double price;
	
	private String brand;
}
