package com.bsu.spezkurs.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.bsu.spezkurs.model.Product;
import com.bsu.spezkurs.model.ProductFactory;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

public class ProductDao {
	private static final String SERVER_NAME = "localhost";
	private static final int PORT = 3306;
	private static final String DATABASE = "SPEZ";
	private static final String DRIVER = "com.mysql.jdbc.Driver";
	private static final String USER = "root";
	private static final String PASSWORD = "";
	private static DataSource source;
	private static Connection connection;
	private static PreparedStatement getAllStatement;
	private static PreparedStatement saveStatement;
	private static PreparedStatement updateStatement;
	private static PreparedStatement deleteStatement;
	private static PreparedStatement isExistsStatement;
	private static PreparedStatement isUpdateStatement;
	private static PreparedStatement getProductStatement;
	private ResultSet result;

	public ProductDao() {
		try {
			initStatments();
		} catch (Exception sql) {
			logException(sql);
		}
	}

	public boolean delete(int name) {
		try {
			deleteStatement.setInt(1, name);
			return deleteStatement.execute();
		} catch (SQLException e) {
			logException(e);
			return false;
		}
	}

	public List<Product> getAll() {
		List<Product> products = new ArrayList<Product>();
		try {
			result = getAllStatement.executeQuery();
			while (result.next()) {
				Product product = ProductFactory.createInstance(result);
				products.add(product);
			}
			return products;
		} catch (SQLException sql) {
			logException(sql);
		}
		return new ArrayList<Product>();
	}

	public boolean isExists(Product dto) {
		try {
			isExistsStatement.setString(1, dto.getName());
			result = isExistsStatement.executeQuery();
			if (result.next()) {
				return true;
			}
		} catch (SQLException e) {
			logException(e);
			return true;
		}
		return false;
	}

	public boolean isUpdate(Product dto) {
		try {
			isUpdateStatement.setString(1, dto.getName());
			result = isUpdateStatement.executeQuery();
			if (result.next()) {
				Product product = ProductFactory.createInstance(result);
				if (product.getId() == dto.getId()) {
					return true;
				} else {
					return false;
				}
			}else {
				return true;
			}
		} catch (SQLException e) {
			logException(e);
			return false;
		}
	}

	public void save(Product dto) {
		try {
			saveStatement.setString(1, dto.getName());
			saveStatement.setString(2, dto.getDescription());
			saveStatement.setInt(3, dto.getPrice());
			saveStatement.setInt(4, dto.getNumber());
			saveStatement.execute();
		} catch (SQLException sql) {
			logException(sql);
		}

	}

	public void update(Product dto) {
		try {
			updateStatement.setString(1, dto.getName());
			updateStatement.setString(2, dto.getDescription());
			updateStatement.setInt(3, dto.getPrice());
			updateStatement.setInt(4, dto.getNumber());
			updateStatement.setInt(5, dto.getId());
			updateStatement.executeUpdate();
		} catch (SQLException sql) {
			logException(sql);
		}
	}

	public Product getProduct(int id) {
		try {
			getProductStatement.setInt(1, id);
			result = getProductStatement.executeQuery();
			result.next();
			Product product = ProductFactory.createInstance(result);
			return product;
		} catch (SQLException e) {
			logException(e);
			return null;
		}
		
	}
	private void initStatments() throws ClassNotFoundException, SQLException {
		Class.forName(DRIVER);

		MysqlDataSource sqlsrc = new MysqlDataSource();
		sqlsrc.setServerName(SERVER_NAME);
		sqlsrc.setPort(PORT);
		sqlsrc.setDatabaseName(DATABASE);
		sqlsrc.setPassword(PASSWORD);
		sqlsrc.setUser(USER);
		source = sqlsrc;

		// connection = DriverManager.getConnection(URL, USER, PASSWORD);
		connection = source.getConnection();
		getAllStatement = connection
				.prepareStatement("SELECT * FROM SPEZ.PRODUCTS");
		saveStatement = connection
				.prepareStatement("INSERT INTO SPEZ.PRODUCTS (NAME,DESCRIPTION,PRICE,NUMBER) VALUES(?,?,?,?);");
		updateStatement = connection
				.prepareStatement("UPDATE SPEZ.PRODUCTS SET NAME=?,DESCRIPTION=?,PRICE=?,NUMBER=? WHERE ID=?;");
		deleteStatement = connection
				.prepareStatement("DELETE FROM SPEZ.PRODUCTS WHERE ID=?;");
		isExistsStatement = connection
				.prepareStatement("SELECT NAME FROM SPEZ.PRODUCTS WHERE NAME=?");
		isUpdateStatement = connection
				.prepareStatement("SELECT * FROM SPEZ.PRODUCTS WHERE NAME=?;");
		getProductStatement = connection.prepareStatement("SELECT * FROM SPEZ.PRODUCTS WHERE ID=?;");
	}

	private void logException(Exception e) {
		System.out.println("Sql error: " + e.getMessage());
	}
}
