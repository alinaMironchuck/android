package com.bsu.spezkurs.bean;

import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import com.bsu.spezkurs.dao.ProductDao;
import com.bsu.spezkurs.model.Product;

@ManagedBean
@SessionScoped
public class ProductBean {
	private static Logger logger = Logger.getLogger(ProductBean.class);
	private static ProductDao productDao = new ProductDao();
	private static final String VALIDATION_MESSAGE = "Product with such name has already exists";
	private static final String UPDATE_MESSAGE = "Update: ";
	private Product product = new Product();

	public ProductBean() {
	}

	public void addProduct() {
		if (validate(product)) {
			productDao.save(product);
		} else {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_WARN,
							VALIDATION_MESSAGE, VALIDATION_MESSAGE));

		}
	}

	private boolean validate(Product product) {
		if (!productDao.isExists(product)
				&& !StringUtils.isBlank(product.getName())) {
			return true;
		}
		return false;
	}

	public void deleteProduct() {
		//TODO: message to growl and log
		productDao.delete(product.getId());
		logger.info("delete " + product.getName());
	}

	public void updateProduct() {
		if (validateUpdate(product)) {
			logger.info(UPDATE_MESSAGE + product.getId());
			productDao.update(product);
		} else {
			logger.warn(VALIDATION_MESSAGE);
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_WARN,
							VALIDATION_MESSAGE, VALIDATION_MESSAGE));
		}
	}

	private boolean validateUpdate(Product product) {
		if (productDao.isUpdate(product)
				&& !StringUtils.isBlank(product.getName())) {
			return true;
		}
		return false;
	}

	public List<Product> getProducts() {
		return productDao.getAll();
	}

	public String getName() {
		return product.getName();
	}

	public void setName(String name) {
		this.product.setName(name);
	}

	public String getDescription() {
		return product.getDescription();
	}

	public void setDescription(String description) {
		this.product.setDescription(description);
	}

	public int getPrice() {
		return product.getPrice();
	}

	public void setPrice(int price) {
		this.product.setPrice(price);
	}

	public int getNumber() {
		return product.getNumber();
	}

	public void setNumber(int number) {
		this.product.setNumber(number);
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getId() {
		return product.getId();
	}

	public void setId(int id) {
		product.setId(id);
	}

}
