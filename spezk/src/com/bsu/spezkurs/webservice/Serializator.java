package com.bsu.spezkurs.webservice;

import java.util.List;
import com.bsu.spezkurs.model.Product;

public class Serializator {
	public static String serialize(List<Product> products) {
		StringBuilder serializedList = new StringBuilder("");
		for (Product product : products) {
			serializedList.append(serializeProduct(product));
		}
		return serializedList.toString();
	}

	private static String serializeProduct(Product product) {
		StringBuilder serializedProduct = new StringBuilder("");
		serializedProduct.append(product.getId() + ",");
		serializedProduct.append(product.getName() + ",");
		serializedProduct.append(product.getDescription() + ",");
		serializedProduct.append(product.getPrice() + ",");
		serializedProduct.append(product.getNumber() + ";");
		return serializedProduct.toString();
		
	}
}
