package com.bsu.spezkurs.webservice;

import java.util.ArrayList;
import java.util.List;

import com.bsu.spezkurs.model.Product;

public class Deserializator {
	public static List<Product> deserialize(String products) {
		List<Product> productList = new ArrayList<Product>();
		String [] productsStr = products.split(";");
		for (String productStr: productsStr){
			productList.add(deserializeProduct(productStr));
		}
		return productList;
	}

	private static Product deserializeProduct(String productStr) {
		Product product = new Product();
		String [] productFields = productStr.split(",");
		product.setId(Integer.parseInt(productFields[0]));
		product.setName(productFields[1]);
		product.setDescription(productFields[2]);
		product.setPrice(Integer.parseInt(productFields[3]));
		product.setNumber(Integer.parseInt(productFields[4]));
		return product;
		
	}
}
