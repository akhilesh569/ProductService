package com.dcl.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dcl.dto.ProductDto;
import com.dcl.request.AddProduct;
import com.dcl.request.UpdateProduct;
import com.dcl.service.ProductServiceImpl;

@RestController
@RequestMapping("/product")
public class ProductController {

	@Autowired
	private ProductServiceImpl pservice; 
	
	@PostMapping("/add")
	public ResponseEntity<?> addProduct(@RequestBody AddProduct request){
		ProductDto pdto=pservice.addproduct(request);
		return ResponseEntity.ok(pdto);
	}
	
	@GetMapping("/get/{productId}")
	public ResponseEntity<?> getProduct(@PathVariable Integer productId){
	  ProductDto pdto=pservice.findById(productId);
		return ResponseEntity.ok(pdto);
	}
	
	@GetMapping("/get")
	public ResponseEntity<?> getAll(){
		List<ProductDto>pdto=pservice.findAll();
		return ResponseEntity.ok(pdto);
	}
	
	@DeleteMapping("/delete/{productId}")
	public ResponseEntity<?> deleteById(@PathVariable Integer productId){
		pservice.deleteProductById(productId);
		return ResponseEntity.ok("product deleted succesfully");
	}
	
	@PutMapping("/update/{productId}")
	public ResponseEntity<?> updateProductById(@PathVariable Integer productId,@RequestBody UpdateProduct request){
		ProductDto pdto=pservice.updateProductById(productId, request);
		return ResponseEntity.ok(pdto);
	}
}
