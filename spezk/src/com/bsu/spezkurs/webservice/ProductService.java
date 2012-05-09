package com.bsu.spezkurs.webservice;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.StringWriter;

import javax.jws.WebMethod;
import javax.jws.WebService;
import org.apache.log4j.Logger;
import com.bsu.spezkurs.dao.ProductDao;
import com.bsu.spezkurs.model.Product;

@WebService
public class ProductService {
	private static Logger logger = Logger.getLogger(ProductService.class);
	private static final String BUY_MESSAGE = "Product was bought: ";
	private static final String NO_PRODUCT_MESSAGE = "No product: ";
	private static ProductDao productDao = new ProductDao();

	@WebMethod
	public String buySelected(int id) {
		Product product = productDao.getProduct(id);
		if (product != null) {
			if (product.getNumber() > 0) {
				product.setNumber(product.getNumber() - 1);
				productDao.update(product);
				logger.info(BUY_MESSAGE + product.getName());
				return BUY_MESSAGE + product.getName();
			} else {
				logger.info(NO_PRODUCT_MESSAGE + product.getName());
			}
		}
		return NO_PRODUCT_MESSAGE + product.getName();
	}

	@WebMethod
	public String getProducts() {
		//ByteArrayOutputStream os = new ByteArrayOutputStream();
		String s = "qwer";
//		try {
//			StringOutputStream os = new StringOutputStream();
//			ObjectOutputStream out;
//			
//			out = new ObjectOutputStream(os);
//			out.writeObject(productDao.getAll());
//			s = new String(os.toString());
//			logger.error(s);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		s=Serializator.serialize(productDao.getAll());
		return s;
	}

}
