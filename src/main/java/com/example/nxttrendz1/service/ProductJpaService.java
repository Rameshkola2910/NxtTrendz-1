/*
 *
 * You can use the following import statements
 * 
 * import org.springframework.beans.factory.annotation.Autowired;
 * import org.springframework.http.HttpStatus;
 * import org.springframework.stereotype.Service;
 * import org.springframework.web.server.ResponseStatusException;
 * import java.util.ArrayList;
 * import java.util.List;
 * 
 */
package com.example.nxttrendz1.service;

import com.example.nxttrendz1.model.Product;
import com.example.nxttrendz1.repository.ProductRepository;
import com.example.nxttrendz1.repository.ProductJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.*;

@Service
public class ProductJpaService implements ProductRepository {

	@Autowired
	private ProductJpaRepository ProductJpaRepository;

	@Override
	public ArrayList<Product> getProducts() {
		return (ArrayList<Product>) ProductJpaRepository.findAll();
	}

	@Override
	public Product getProductById(int productId) {
		try {
			return ProductJpaRepository.findById(productId).get();
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
	}

	@Override
	public Product addProduct(Product product) {
		ProductJpaRepository.save(product);
		return product;
	}

	@Override
	public Product updateProduct(int productId, Product product) {
		try {
			Product newProduct = ProductJpaRepository.findById(productId).get();
			if (product.getProductName() != null) {
				newProduct.setProductName(product.getProductName());
			}
			if (product.getPrice() != 0) {
				newProduct.setPrice(product.getPrice());
			}
			ProductJpaRepository.save(newProduct);
			return newProduct;
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
	}

	@Override
	public void deleteProduct(int productId) {
		try {
			ProductJpaRepository.deleteById(productId);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		throw new ResponseStatusException(HttpStatus.NO_CONTENT);
	}

}