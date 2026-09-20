package com.dcl.service;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import com.dcl.dto.ProductDto;
import com.dcl.entity.Product;
import com.dcl.repo.ProductRepo;
import com.dcl.request.AddProduct;
import com.dcl.request.UpdateProduct;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepo prepo;
	
	@Autowired
	private ModelMapper mapper;
	
	@Override
	public ProductDto addproduct(AddProduct request) {
		// TODO Auto-generated method stub
//		Product p=new Product();
//		p.setProductName(request.getProductName());
//		p.setPrice(request.getPrice());
//		p.setBrand(request.getBrand());
//		prepo.save(p);
//		
//		ProductDto dt=new ProductDto();
//		dt.setProductId(p.getProductId());
//		dt.setProductName(p.getProductName());
//		dt.setPrice(p.getPrice());
//		dt.setBrand(p.getBrand());
//		
//		return dt;
		
		Product p=new Product();
		mapper.map(request, Product.class);
		p=prepo.save(p);
	   return mapper.map(p, ProductDto.class);
	}

	
	@Override
	public ProductDto findById(Integer productId) {
		// TODO Auto-generated method stub
//		Product p=new Product();
		Product p= prepo.findById(productId).orElse(null);
		if(p==null) {
			new RuntimeException("product not found");
		}
		ProductDto pdto=new ProductDto();
		pdto.setProductId(productId);
		pdto.setProductName(p.getProductName());
		pdto.setPrice(p.getPrice());
		pdto.setBrand(p.getBrand());
		
		return pdto;
	}

	@Override
	public List<ProductDto> findAll() {
		// TODO Auto-generated method stub
		List<Product>pList=prepo.findAll();
		
		Function<Product, ProductDto> function = (p)-> {
			ProductDto pdto=new ProductDto();
			pdto.setProductId(p.getProductId());
			pdto.setProductName(p.getProductName());
			pdto.setPrice(p.getPrice());
			pdto.setBrand(p.getBrand());
			return pdto;
		};
		
		List<ProductDto>ppdto=pList.stream().map(function).collect(Collectors.toList());
			return ppdto;
	}


	@Override
	public void deleteProductById(Integer productId) {
		// TODO Auto-generated method stub
		Product p=prepo.findById(productId).orElseThrow(()->new RuntimeException("product not found in db"));
		prepo.deleteById(productId);
	}


	@Override
	public ProductDto updateProductById(Integer productId, UpdateProduct request) {
		// TODO Auto-generated method stub
		Product alreadyExists=prepo.findById(productId).orElseThrow(()->new RuntimeException("prodct id not found"));
		alreadyExists.setProductName(request.getProductName());
		alreadyExists.setPrice(request.getPrice());
		alreadyExists.setBrand(request.getBrand());
		Product afterUpdate=prepo.save(alreadyExists);
		
		ProductDto pdto=new ProductDto();
		pdto.setProductId(afterUpdate.getProductId());
		pdto.setProductName(afterUpdate.getProductName());
		pdto.setPrice(afterUpdate.getPrice());
		pdto.setBrand(afterUpdate.getBrand());
		
		return pdto;
	}


}
