package com.bsu.spezkurs.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.bsu.spezkurs.model.Product;

public class ProductFactory {
	public static Product createInstance(ResultSet result)
			throws SQLException{
		Product product = new Product();
		product.setId(result.getInt(1));
		product.setName(result.getString(2));
		product.setDescription(result.getString(3));
		product.setPrice(result.getInt(4));
		product.setNumber(result.getInt(5));
		return product;
	}
}
