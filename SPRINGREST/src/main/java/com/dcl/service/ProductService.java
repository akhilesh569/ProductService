package com.dcl.service;

import java.util.List;

import com.dcl.dto.ProductDto;
import com.dcl.request.AddProduct;
import com.dcl.request.UpdateProduct;
public interface ProductService {

	public ProductDto addproduct(AddProduct request);
	
   ProductDto findById(Integer productId);
	
	 List<ProductDto> findAll();
	 
	 void deleteProductById(Integer productId);
	 
	 ProductDto updateProductById(Integer productId,UpdateProduct request);
}
