package com.dcl.request;

import lombok.Data;

@Data
public class AddProduct {

    private String productName;
	
	private Double price;
	
	private String brand;
}
