package com.bsu.spezkurs.webservice;

import java.util.List;

import com.bsu.spezkurs.dao.ProductDao;
import com.bsu.spezkurs.model.Product;

public class SerializatorSimpleTest {
	private static ProductDao productDao = new ProductDao();

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		List<Product> products = productDao.getAll();
		String str = Serializator.serialize(products);
		List<Product> productsDes = Deserializator.deserialize(str);
		for(Product pr: productsDes){
			System.out.println(pr.getId());
			System.out.println(pr.getName());
			System.out.println(pr.getDescription());
			System.out.println(pr.getPrice());
			System.out.println(pr.getNumber());
		}

	}

}
